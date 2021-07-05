<template>
    <div class="homePage">
         <van-nav-bar
            title="社区医院挂号"
        />
        <van-tabbar v-model="active">
            <van-tabbar-item icon="home-o" to="/mainPage">主界面</van-tabbar-item>
            <van-tabbar-item icon="friends-o" to="/msg" >消息</van-tabbar-item>
            <van-tabbar-item icon="setting-o" to="/settings">设置</van-tabbar-item>
        </van-tabbar>
    <router-view></router-view>    
    </div>
</template>
<script>
export default{
    
    methods:{
      
    },
}
</script>
<style scoped>
.homePage{
    width: 100%;
    height: 100%;
}

</style>