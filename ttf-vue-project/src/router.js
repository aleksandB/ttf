import Vue from 'vue'
import Router from 'vue-router'
import DashboardView from './views/DashboardView.vue'
import TeamView from './views/TeamView.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'dashboard',
      component: DashboardView
    },    
    {
      path: '/team',
      name: 'team',
      component: TeamView
    }

  ]
})
