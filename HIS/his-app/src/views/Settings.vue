<template>
    <van-button round block type="primary" @click="logout">
                退出登录
    </van-button>
</template>
<script>
export default {
    methods:{
        logout(){
            this.$router.push({path:"/"})
        }
    }
}
</script>
<style scoped>

</style>