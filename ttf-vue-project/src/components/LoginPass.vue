<template>
    <div v-if="!loggedIn">
    <v-dialog max-width="400px" v-model="dialog">
        <template v-slot:activator="{ on, attrs}">
            <v-btn
                color="secondary"
                v-bind="attrs"
                v-on="on"
                class="pa-2 ma-2"
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
                    <v-text-field v-model="user.password"
                         label="Password"
                         prepend-icon="mdi-folder"                         
                         v-validate="'required'"
                         type="text"
                         name="password"
                    ></v-text-field>
                    <div 
                        v-if="errors.has('password')"
                        class="alert alert-danger"
                        role="alert"
                    >Password is required!</div>                    
                    <v-btn @click="submit" class="form-group success rounded-pill" :loading="loading">Login in</v-btn>                    
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
                    <v-text-field v-model="user.username" label="Login" prepend-icon="mdi-folder"></v-text-field>
                    <v-text-field v-model="user.password" label="Password" prepend-icon="mdi-folder"></v-text-field>
                    <v-text-field v-model="user.password" label="Email" prepend-icon="mdi-folder"></v-text-field>  <!-- Need change!-->
                    <v-btn @click="submit" class="success rounded-pill" :loading="loading">Sign up</v-btn>
                </v-form>
            </v-card-text>
        </v-card>
    </v-dialog>
    </div>
    <div v-else>
        <v-btn
                color="secondary"
                v-bind="attrs"
                v-on="on"
                class="pa-2 ma-2"
            >
               Sign out
            </v-btn>
    </div>
</template>

<script>
import User from '../models/user';
export default {    
    data() {
        return {
            user: new User('',''),            
            dialog: false,
            dialog2: false,            
            loading: false,
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
            this.$router.push('/');
        }
    },
    methods: {
        submit() {
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
                            this.$router.push('/');
                        },
                        error => {
                            this.loading = false;
                            this.message = 
                            (error.response && error.response.data) || error.message || error.toString();
                        }
                    );
                }
            });
        }
    }
}
</script>

<style scoped>
label {
  display: block;
  margin-top: 10px;
}
.card-container.card {
  max-width: 350px !important;
  padding: 40px 40px;
}
.card {
  background-color: #f7f7f7;
  padding: 20px 25px 30px;
  margin: 0 auto 25px;
  margin-top: 50px;
  -moz-border-radius: 2px;
  -webkit-border-radius: 2px;
  border-radius: 2px;
  -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
}
.profile-img-card {
  width: 96px;
  height: 96px;
  margin: 0 auto 10px;
  display: block;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  border-radius: 50%;
}
</style>