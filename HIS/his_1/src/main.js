import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './assets/css/global.css'
import 'element-plus/lib/theme-chalk/index.css';
import ElementPlus from 'element-plus';
import axios from 'axios'
const app = createApp(App)

app.config.globalProperties.$http=axios;
//设置访问根路径,后端路径
axios.defaults.baseURL ="http://localhost:9000"
app.use(ElementPlus)
app.use(store)
app.use(router)
app.mount('#app')
