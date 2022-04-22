<template>
  <v-footer
    dark
    padless
  >
    <v-card
      class="flex"
      flat
      tile
    >
      <v-card-title class="teal">
      </v-card-title>
      <v-card-text class="py-2 white--text text-right">
        {{ appVersion }} — <strong>Version</strong>
      </v-card-text>
      
    </v-card>
  </v-footer>
</template>

<script>
import { version } from '../../package'

export default {    
    data: () => ({
        appVersion: version
    }),
    methods: {
    
  },
}
</script>

<style>

</style>