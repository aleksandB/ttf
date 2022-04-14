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
                <validation-observer
                    ref="observer"
                    v-slot="{ invalid }"
                >
                    <v-form class="px-3" @submit.prevent="handleLogin">
                        <validation-provider
                            v-slot="{ errors }"
                            name="Name"
                            rules="required"
                        >
                            <v-text-field 
                                v-model="user.username" 
                                label="Name" 
                                prepend-icon="mdi-folder"                               
                                type="text"
                                name="username"
                                :error-messages="errors"
                                required                            
                            ></v-text-field>
                        </validation-provider>
                        <validation-provider
                            v-slot="{ errors }"
                            name="Password"
                            rules="required|min:6"
                        >
                        <v-text-field 
                            v-model="user.password"
                            label="Password"
                            prepend-icon="mdi-folder"                         
                            :error-messages="errors"
                            type="password"
                            name="password"
                            required
                        ></v-text-field>
                        </validation-provider>
                                         
                        <v-btn type="submit" class="form-group success rounded-pill" :loading="loading" :disabled ="invalid">Login in</v-btn>                    
                        <v-btn @click="clear" class="pa-2 ma-2 success rounded-pill">Clear</v-btn>
                        <div class="form-group">
                        <div v-if="message" class="alert alert-danger" role="alert">{{message}}</div>
                        </div>
                    </v-form>
                </validation-observer>
            </v-card-text>
        </v-card>
    </v-dialog>
    <v-dialog max-width="400px" v-model="dialog2">
        <template v-slot:activator="{ on, attrs}">
            <v-btn
                color="success"
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
                <validation-observer
                    ref="observerReg"
                    v-slot="{ invalid }"
                >
                    <v-form class="px-3" @submit.prevent="hanldeRegister">
                        <validation-provider
                            v-slot="{ errors }"
                            name="Name"
                            rules="required|min:2"
                        >
                        <v-text-field 
                            v-model="userReg.username"
                            type="text"
                            name="username" 
                            label="Name" 
                            prepend-icon="mdi-folder"
                            :error-messages="errors"
                            required 
                        ></v-text-field>
                        </validation-provider>
                        <validation-provider
                            v-slot="{ errors }"
                            name="email"
                            rules="required|email"
                        >                        
                        <v-text-field 
                            v-model="userReg.email" 
                            label="Email"                            
                            type="text"
                            name="email"  
                            prepend-icon="mdi-folder"
                            :error-messages="errors"
                            required
                        ></v-text-field>
                        </validation-provider>     
                        <validation-provider
                            v-slot="{ errors }"
                            name="Password"
                            rules="required|min:6|max:20"
                        >                   
                        <v-text-field 
                            v-model="userReg.password" 
                            label="Password"                            
                            type="password"
                            name="password"   
                            prepend-icon="mdi-folder"
                            :error-messages="errors"
                            required
                        ></v-text-field> 
                        </validation-provider>                      
                        <v-btn type="submit" class="success rounded-pill" :loading="loading" :disabled="invalid">Sign up</v-btn>
                        <v-btn @click="clearReg" class="pa-2 ma-2 success rounded-pill">Clear</v-btn>
                        <div
                        v-if="message"
                        class="alert"
                        :class="successful ? 'alert-success' : 'alert-danger'"
                        >{{message}}</div>
                    </v-form>
                </validation-observer>
            </v-card-text>
        </v-card>
    </v-dialog>
    </div>
    <div v-else>        
        <v-btn
                color="success"
                @click="currentUser"
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
import { required, digits, email, max, min } from 'vee-validate/dist/rules'
import { extend, ValidationObserver, ValidationProvider, setInteractionMode } from 'vee-validate'

setInteractionMode('eager')

  extend('digits', {
    ...digits,
    message: '{_field_} needs to be {length} digits. ({_value_})',
  })

  extend('required', {
    ...required,
    message: '{_field_} can not be empty',
  })

  extend('max', {
    ...max,
    message: '{_field_} may not be smaller than {length} characters',
  })

  extend('min', {
    ...min,
    message: '{_field_} may not be greater than {length} characters',
  })


extend('email', {
    ...email,
    message: 'Email must be valid',
  })


export default {
    components: {
        ValidationProvider,
        ValidationObserver
    },    
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
            this.$refs.observer.validate()
                .then(isValid => {
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
            this.$refs.observerReg.validate().then(isValid => {
                if (isValid) {
                    this.$store.dispatch('auth/register',this.userReg).then (
                        data => {
                            this.message = data.message;
                            this.successful = true;
                            this.clearReg();
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
        },
        clear() {
            this.user = new User ('','');        
            this.$refs.observer.reset();
            this.message='';
        },
        clearReg() {            
            this.userReg = new User('','','');
            this.$refs.observerReg.reset();
            this.message = '';
        },
    }
}
</script>
