import { createRouter, createWebHistory } from 'vue-router'

const routes =  [
  {
    path: '/login',
    name: 'login',
    component: () => import(/* webpackChunkName: "about" */ '../views/Login.vue')
  },
  {
    path: '/home',
    name: 'home',
    component: () => import(/* webpackChunkName: "about" */ '../views/Home.vue'),
    redirect: '/mainpage',
    children:[
      {path:'/mainpage',component: () => import(/* webpackChunkName: "about" */ '../views/MainPage.vue')},
      {path:'/department_mgt',component: () => import(/* webpackChunkName: "about" */ '../views/admin/DepartmentList.vue')},
      {path:'/user_mgt',component: () => import(/* webpackChunkName: "about" */ '../views/admin/UserMgt.vue')},
      {path:'/scheduling',component: () => import(/* webpackChunkName: "about" */ '../views/admin/Scheduling.vue')},
    ]
  },
  
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})
router.beforeEach((to, from, next)=>{
  if(to.path=='/login') return next();
  const userFlag = window.sessionStorage.getItem("user");
  if(!userFlag) return next('/login');
  next();
})
export default router
