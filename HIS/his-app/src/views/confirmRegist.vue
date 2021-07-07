<template>
    <div>
        <van-nav-bar
            left-text="返回"
            left-arrow
            @click-left="onClickLeft"
        />
        <van-cell-group>
            <van-cell title="就诊科室" :value=dept />
            <van-cell title="就诊人" :value=user />
            <van-cell title="医生" :value=doc />
        </van-cell-group>
        <van-button type="primary" size="large" @click="onClickBtn">确认</van-button>
    </div>
</template>
<script>
import { Toast } from 'vant';
export default {
    setup() {
        const onAdd = () => Toast('新增');
        const dept = window.sessionStorage.getItem("dept");
        const user = window.sessionStorage.getItem("user");
        const doc = window.sessionStorage.getItem("doc");
        return {
            onAdd,
            dept,
            user,
            doc,
        };
        
    },

    methods:{
        onClickLeft(){
            this.$router.push({path:"/register"});
        },
        onClickBtn(){
            console.log("挂号确认");
            Toast('挂号成功');
        }
    }
}
</script>
<style scoped>

</style>