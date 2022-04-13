<template>
    <div v-if="!loggedIn">
    <v-dialog max-width="400px" v-model="dialog">
        <template v-slot:activator="{ on, attrs}">
            <v-btn
                color="secondary"
                v-bind="attrs"
                v-on="on"
                class="pa-2 ma-2"
                rounded
                small
            >
               Sign in
            </v-btn>           
        </template>
        <v-card>
            <v-card-title>
                <h2>Authentification form</h2>
            </v-card-title>
            <v-card-text>
                <v-form class="px-3" ref="form">
                    <v-text-field 
                        v-model="user.username" 
                        label="Login" 
                        prepend-icon="mdi-folder"
                        v-validate="'required'"
                        type="text"
                        name="username"                            
                    ></v-text-field>
                    <div 
                        v-if="errors.has('username')"
                        class="alert alert-danger"
                        role="alert"
                    >Username is required!</div>
                    <v-text-field 
                         v-model="user.password"
                         label="Password"
                         prepend-icon="mdi-folder"                         
                         v-validate="'required'"
                         type="password"
                         name="password"
                    ></v-text-field>
                    <div 
                        v-if="errors.has('password')"
                        class="alert alert-danger"
                        role="alert"
                    >Password is required!</div>                    
                    <v-btn @click="handleLogin" class="form-group success rounded-pill" :loading="loading">Login in</v-btn>                    
                    <div class="form-group">
                    <div v-if="message" class="alert alert-danger" role="alert">{{message}}</div>
                    </div>
                </v-form>
            </v-card-text>
        </v-card>
    </v-dialog>
    <v-dialog max-width="400px" v-model="dialog2">
        <template v-slot:activator="{ on, attrs}">
            <v-btn
                color="secondary"
                v-bind="attrs"
                v-on="on"
                class="pa-2 ma-2"
                rounded
                small
            >
               Sign up
            </v-btn>           
        </template>
        <v-card>
            <v-card-title>
                <h2>Registration form</h2>
            </v-card-title>
            <v-card-text>
                <v-form class="px-3">
                    <v-text-field 
                        v-model="userReg.username"
                        v-validate="'required|min:3|max:20'"
                        type="text"
                        name="username" 
                        label="Login" 
                        prepend-icon="mdi-folder"
                    ></v-text-field>
                    <div 
                        v-if="submitted && errors.has('username')"
                        class="alert-danger"
                    >{{ errors.first('username')}}</div>
                    <v-text-field 
                        v-model="userReg.email" 
                        label="Email"
                        v-validate="'required|email|max:50'"
                        type="text"
                        name="email"  
                        prepend-icon="mdi-folder"
                    ></v-text-field>
                    <div 
                        v-if="submitted && errors.has('email')"
                        class="alert-danger"
                    >{{ errors.first('email')}}</div>
                    <v-text-field 
                        v-model="userReg.password" 
                        label="Password"
                        v-validate="'required|min:6|max:40'"
                        type="password"
                        name="password"   
                        prepend-icon="mdi-folder"
                    ></v-text-field>
                    <div
                        v-if="submitted && errors.has('password')"
                        class="alert-danger"
                    >{{errors.first('password')}}</div>  
                    <v-btn @click="hanldeRegister" class="success rounded-pill" :loading="loading">Sign up</v-btn>
                    <div
                       v-if="message"
                       class="alert"
                       :class="successful ? 'alert-success' : 'alert-danger'"
                    >{{message}}</div>
                </v-form>
            </v-card-text>
        </v-card>
    </v-dialog>
    </div>
    <div v-else>        
        <v-btn
                color="secondary"
                @click="handleLogin"
                class="pa-2 ma-2"
                rounded
                small
            >         
               {{ this.$store.state.auth.user.username }}
        </v-btn>        
        <v-btn
                color="secondary"
                @click="logOut"
                class="pa-2 ma-2"
                rounded
                small
            >
               Sign out
        </v-btn>        
    </div>
</template>

<script>
import User from '../models/user';
import EventBus from "../common/EventBus"
export default {    
    data() {
        return {
            user: new User('',''),
            userReg: new User('','',''),            
            dialog: false,
            dialog2: false,            
            loading: false,
            submitted: false,
            successful: false,
            message: ''               
        }
    },
    computed: {
        loggedIn() {
            return this.$store.state.auth.status.loggedIn;
        }
    },
    created() {
        if (this.loggedIn) {
            console.log('User logged In!'); //Will be changed after
            this.$router.push('/profile');
        }
    },
    methods: {
        handleLogin() {
            console.log(this.login, this. password);
            this.dialog = false;
            this.$validator.validateAll().then(isValid => {
                if(!isValid) {
                    this.loading = false;
                    return;
                }
                if(this.user.username && this.user.password) {
                    this.$store.dispatch('auth/login',this.user)
                    .then(
                        () => {
                            console.log('here')
                            this.$router.push('/profile');
                        },
                        error => {
                            this.loading = false;
                            this.message = 
                            (error.response && error.response.data) || error.message || error.toString();
                        }
                    );
                }
            });
        },
        hanldeRegister() {
            this.message = '';
            this.submitted = true;
            this.$validator.validate().then(isValid => {
                if (isValid) {
                    this.$store.dispatch('auth/register',this.userReg).then (
                        data => {
                            this.message = data.message;
                            this.successful = true;
                        },
                        error => {
                            this.message = 
                            (error.response && error.response.data) ||
                            error.message ||
                            error.toString();
                           this.successful = false; 
                        }
                    );
                }
            });
        },
        logOut(){
            this.$store.dispatch('auth/logout');
            this.$router.push('/profile')  //Will be changed after
        },
        currentUser() {           
           this.$router.push('/profile')  //Will be changed after
        },
        mounted() {
            EventBus.on("logout", () => {
                this.logOut();
            });
        },
        beforeDestroy() {
            EventBus.remove("logout");
        }
    }
}
</script>
