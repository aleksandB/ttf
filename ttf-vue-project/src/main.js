import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from './router'
import store from './store'
import VeeValidate from 'vee-validate'


Vue.config.productionTip = false


Vue.use(VeeValidate)
new Vue({
  vuetify,
  router,
  store,
  render: h => h(App)
}).$mount('#app')
