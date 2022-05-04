import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import { router } from './router'
import store from './store'
import * as VeeValidate from 'vee-validate';
import Vuex from 'vuex'
import setupInterceptors from './services/setupInterceptors'


Vue.config.productionTip = false

Vue.use(VeeValidate)
Vue.use(Vuex)
setupInterceptors(store)
new Vue({
  vuetify,
  router,
  store,  
  render: h => h(App)
}).$mount('#app')
