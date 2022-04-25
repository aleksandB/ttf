import Vue from 'vue'
import Router from 'vue-router'
import DashboardView from './views/DashboardView.vue'
import ProfileView from './views/ProfileView.vue'
import ScheduleView from './views/ScheduleView.vue'


Vue.use(Router)

export const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'profile',
      component: ProfileView
    },    
    {
      path: '/profile',
      name: 'profile',
      component: ProfileView
    },    
    {
      path: '/dashboard',
      name: 'dashboard',
      component: DashboardView
    },    
    {
      path: '/schedule',
      name: 'schedule',
      component: ScheduleView
    },
    {
      path: '/adminpage',
      name: 'adminpage',
      component: () => import('./views/AdminView.vue')
    },
    {
      path: "*",
      redirect: "/"
    }

  ]
})

router.beforeEach((to, from, next) => {
  //const publicPages = ['/login', '/register', '/home'];
  const publicPages = ['/profile'];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem('user');
  // trying to access a restricted page + not logged in
  // redirect to login page
  if (authRequired && !loggedIn) {
    next('/login');
  } else {
    next();
  }
});
