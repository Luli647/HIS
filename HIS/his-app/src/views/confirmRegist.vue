<template>
    <div>
        <van-nav-bar
            left-text="返回"
            left-arrow
            @click-left="onClickLeft"
        />
        <van-form @submit="onClickBtn">
        <van-field
            v-model="realName"
            name="realName"
            label="用户姓名"
            placeholder="用户姓名"
            :rules="[{ required: true, message: '请填写用户姓名' }]"
        />
        <van-field name="gender" label="性别">
            <template #input>
                <van-radio-group v-model="gender" direction="horizontal">
                <van-radio name=71>男</van-radio>
                <van-radio name=72>女</van-radio>
                </van-radio-group>
            </template>
            </van-field>
        <van-field
            v-model="age"
            name="age"
            label="年龄"
            placeholder="年龄"
            :rules="[{ required: true, message: '请填写年龄' }]"
        />
        <van-field
            v-model="idnumber"
            name="idnumber"
            label="身份证号"
            placeholder="身份证号"
            :rules="[{ required: true, message: '请填写身份证号码' }]"
        />
        <van-field
            v-model="homeAddress"
            name="homeAddress"
            label="家庭住址"
            placeholder="家庭住址"
            :rules="[{ required: true, message: '请填写您的家庭住址' }]"
        />
        <div style="margin: 16px;">
            <van-button round block type="primary" native-type="submit">提交</van-button>
        </div>
        </van-form>
      
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
    data(){
        return{
            realName:'',
            gender:71,
            age:'',
            idnumber:'',
            homeAddress:'',
        }
    },

    methods:{
        onClickLeft(){
            this.$router.push({path:"/register"});
        },
        onClickBtn(values){
            console.log(values);
            Toast('挂号成功');
        }
    }
}
</script>
<style scoped>

</style>