<template>
    <el-container class = "home-container">
        <el-header>
            <div>
                <img src="../assets/mainlogo.png" alt="">
            </div>
            <span>谐和医院信息管理中心</span>
            <el-button class = "logout-button" type="info" @click="logout">安全退出</el-button>
        </el-header>
        <el-container >
            <!-- 侧边栏 -->
            <el-aside width="200px">
                <el-menu 
                        class="el-menu-vertical-demo"
                        background-color="#545c64"
                        text-color="#fff"
                        active-text-color="#ffd04b"
                        :router = "true"
                        :default-active = "activePath">
                    <el-submenu :index="item.path" v-for = "item in menuList" :key = "item.id">
                        <template #title><i class="el-icon-user"></i>
                            <span>{{item.title}}</span>
                        </template>
                        <el-menu-item :index = "it.path" v-for="it in item.subList" :key="it.id" @click = "savePath(it.path)">
                            <template #title>
                                <i class="el-icon-magic-stick"></i>
                                <span>{{it.title}}</span>
                            </template>
                        </el-menu-item>
                    </el-submenu>
                </el-menu>
            </el-aside>
                <!-- 主体内容 -->
            <el-main>
               <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>
<script>

export default {
    data(){
        return {
            menuList:[],
            activePath: '',
        }
    },
    created(){
        //查询菜单列表
        this.getMenuList();
        this.activePath = window.sessionStorage.getItem("activePath");
    },
    methods: {
        logout(){
            window.sessionStorage.clear();
            this.$router.push("/login");
        },
        //获取导航菜单
        async getMenuList(){
            const {data:res} = await this.$http.get("menus");
            console.log(res);
            this.menuList = res.menus;
        },
        savePath(activePath){
            window.sessionStorage.setItem('activePath', activePath);
            this.activePath = activePath;
        }
    }
}
</script>
<style scoped>
.el-header{
    background-color: #274c6e;
    display: flex;
    justify-content: space-between;
    padding-right: 0%;
    color: #fff;
    font-size: 20px;
    align-items: center;
}
.el-aside {
    background-color:#545c64;
}
.el-munu{
    border-right: none;
}
.el-main {
    background-color: rgb(246, 248, 225);
}
.home-container{
    height: 100%;
    width: 100%;
}
img{
    width: 55px;
    height: 55px;
}


</style>