<template>
  <v-app class="grey lighten-4">
    <NavBar />
    <v-main>
      <router-view></router-view>
    </v-main>
  </v-app>
</template>



<script>
import NavBar from './components/NavBar.vue';
import EventBus from "./common/EventBus";

export default {
  name: 'App',
  components: {
    NavBar,
  },
  data: () => ({
    //
  }),
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
  },
  beforeUnmount() {
    EventBus.remove("logout");
  }
};
</script>


