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
  }
};
</script>


