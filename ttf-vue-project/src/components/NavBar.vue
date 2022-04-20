<template>
<nav> 
    <v-app-bar
        shaped
        height="50px"
        app
        class="secondary"
    >
        <v-app-bar-nav-icon @click="drawer = !drawer" class="white--text">            
        </v-app-bar-nav-icon>
        <v-toolbar-title class="white--text">Title</v-toolbar-title>
        <v-spacer></v-spacer>
        <LoginPass />
    </v-app-bar>
    <v-navigation-drawer app v-model="drawer" class="primary">
        <v-layout>
           <v-list>
              <v-list-item v-for="link in links" :key="link.text" router :to="link.route">
                <v-list-item-action>
                 <v-icon color="secondary">{{ link.icon }}</v-icon>
                </v-list-item-action>
                <v-list-item-content>
                     <v-list-item-title class="white--text">{{ link.text }}</v-list-item-title>
                </v-list-item-content>
              </v-list-item>
           </v-list>
        </v-layout>
    </v-navigation-drawer>

</nav>
</template>

<script>
import LoginPass from './LoginPass.vue'

export default {
    components: { LoginPass },
    data(){
        return {
            drawer: false,
            links: [
            { icon: 'mdi-account', text: 'ProfileView', route: '/profile' },        
            { icon: 'mdi-view-dashboard', text: 'DashboardView', route: '/dashboard' },        
            { icon: 'mdi-puzzle', text: 'ScheduleView', route: '/schedule' },
        ],
        }
    }
}
</script>
