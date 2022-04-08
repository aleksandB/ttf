<template>
    <div v-if="!isLoggedIn">
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
                    <v-text-field v-model="username" label="Login" prepend-icon="mdi-folder"></v-text-field>
                    <v-text-field v-model="password" label="Password" prepend-icon="mdi-folder"></v-text-field>
                    <v-btn @click="submit" class="success rounded-pill" :loading="loading">Login in</v-btn>
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
                <v-form class="px-3" ref="form">
                    <v-text-field v-model="username" label="Login" prepend-icon="mdi-folder"></v-text-field>
                    <v-text-field v-model="password" label="Password" prepend-icon="mdi-folder"></v-text-field>
                    <v-text-field v-model="password" label="Email" prepend-icon="mdi-folder"></v-text-field>
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
export default {
    data() {
        return {
            form: {
                username: '',
                password: '',
            },
            dialog: false,
            dialog2: false,            
            loading: false,
            isLoggedIn: false            
        }
    },
    computed: {
        
    },
    methods: {
        submit() {
            console.log(this.login, this. password);
            this.dialog = false;
            let username = this.username;
            let password = this.password;
            this.$store.dispatch('login', {username, password})
                .then(() => 
                    this.$router.push('/'))
                .catch(err =>
                    console.log(err))
                }
            
        }
    

}
</script>
