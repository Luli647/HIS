import { createRouter, createWebHistory } from 'vue-router'


const routes = [
  {
    path: '/',
    name: 'index',
    component: () => import('../views/Index.vue')
  },
  {
    path: '/home',
    name: 'home',
    component: () => import('../views/Home.vue'),
    redirect:'mainPage',
    children:[
      {
        path: '/mainPage',
        name: 'mainPage',
        component: () => import('../views/MainPage.vue')
      },
      {
        path: '/msg',
        name: 'message',
        component: () => import('../views/Message.vue')
      },
      {
        path: '/settings',
        name: 'settings',
        component: () => import('../views/Settings.vue')
      },
    ]
  },
  {
    path: '/register',
    name: 'register',
    component: () => import('../views/Register.vue')
  },
  {
    path: '/registByDep',
    name: 'registByDep',
    component: () => import('../views/registByDep.vue')
  },
  {
    path: '/confirmRegist',
    name: 'confirm',
    component: () => import('../views/confirmRegist.vue')
  },
  {
    path: '/customer',
    name: 'cs',
    component: () => import('../views/Customer.vue')
  },
  
 
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
