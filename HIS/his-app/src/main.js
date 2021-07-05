import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Vant from 'vant';
import 'vant/lib/index.css';
import axios from 'axios'

const app=createApp(App)
app.use(store)
app.use(router)
app.use(Vant).config.globalProperties.$http=axios
axios.defaults.baseURL="http://localhost:9000"
app.mount('#app')
