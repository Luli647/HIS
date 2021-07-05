<template>
    <div class="login_form">
        <div class="inner">
            <div class="logo">
                <img class="img" src="../assets/hospital.png" alt="">
            </div>
            <van-form @submit="userLogin">
            <van-cell-group inset>
                <van-field
                v-model="state.userName"
                name="用户名"
                label="用户名"
                placeholder="用户名"
                :rules="[{ required: true, message: '请填写用户名' }]"
                />
                <van-field
                v-model="state.password"
                type="password"
                name="密码"
                label="密码"
                placeholder="密码"
                :rules="[{ required: true, message: '请填写密码' }]"
                />
            </van-cell-group>
            <div style="margin: 16px;">
                <van-button round block type="primary" native-type="submit">
                提交
                </van-button>
            </div>
            </van-form>
        </div>
    </div>
    
</template>

<script>
import { reactive } from 'vue';
export default {
    setup() {
    const state = reactive({
      userName: '',
      password: '',
    });
    const onSubmit = (values) => {
      console.log('submit', values);
      console.log(state);
    };

    return {
      state,
      onSubmit,
    };
    },
    name: "Index",
    components:{},

    methods:{
        async userLogin(){
            console.log(this.state);
            const {data:res}=await this.$http.post("/userLogin", this.state);
            console.log(res);
            if( res =="success"){
                this.$notify({
                    message:'登录成功',
                    background:'green',
                    duration: 1000
                    });
                this.$router.push({path:"/home"});
                
            }else{
                this.$notify("登录失败，用户名或密码错误！");
            }
        }
    },
}
</script>
<style scoped>

.login_form{
    height: 100%;
    background-color: rgb(248, 247, 241);
}
.inner{
    /* align-self: center;
    margin-top: 250px; */
    width: 100%;
    position: fixed;
    top: 40%;
    left: 50%;
    transform: translate(-50%,-50%);
}
.logo{
    vertical-align: middle;
    text-align: center;
}
.img{
    width: 20%;
    height: 20%;
}
</style>