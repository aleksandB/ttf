<template>
  <v-app class="grey lighten-4">
    
    
    <v-snackbar 
      v-model="snackbar" 
      timeout="4000"
      centered
      top
      rounded="pill"
      color="deep-purple accent-4"      
    >{{
      snackbarMessage
    }}
      <template v-slot:action="{ attrs }">
          <v-btn
            color="black"
            text
            v-bind="attrs"
            @click="snackbar = false"
          >
            Close
          </v-btn>
      </template>
    </v-snackbar>
    <NavBar />
    <v-main>
      <router-view></router-view>
    </v-main>
    <FooterView />
    
  </v-app>
</template>


<script>
import NavBar from './components/NavBar.vue';
import EventBus from "./common/EventBus";
import FooterView from "./components/FooterView.vue"
import EventBuss, { ACTIONS } from "./EventBus/index";



export default {
  name: 'App',
  components: {
    NavBar,
    FooterView,
    
  },
  data() {
    return {snackbar: false,
    snackbarMessage: "",
    }},
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout');
      this.$router.push('/login');
    }
  },
  mounted() {
    EventBus.on("logout", () => {
      this.logOut();
    });
    EventBuss.$on(ACTIONS.SNACKBAR, (message) => {
      this.snackbarMessage = message;
      this.snackbar = true;
    });
  },
  beforeUnmount() {
    EventBus.remove("logout");
  }
};
</script>


