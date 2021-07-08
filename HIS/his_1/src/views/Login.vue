<template>
  <div class="login_container">
      <div class="login_box">
          <!--头像-->
          <div class="avatar_box">
              <img src="../assets/hospital.png" alt="">
          </div>
          <!--登录表单-->
          <el-form ref="loginFormRef" :model="loginForm" :rules="loginFormRules" label-width="60px" class="login_form">
              <!--用户名-->
            <el-form-item label="ID" prop="userName">
                <el-input v-model="loginForm.userName" placeholder="输入id..."></el-input>
            </el-form-item>
            <!--密码-->
            <el-form-item label="密码" prop="password">
                <el-input v-model="loginForm.password" placeholder="输入密码..." type="password"></el-input>
            </el-form-item>
            <!--按钮-->
            <el-form-item class="btn">
                <el-button type="primary" @click="login" class="submit-btn">提交</el-button>
            </el-form-item>
            <!--找回密码-->
                <div class="tiparea">
                <p>忘记密码？<a>立即找回</a></p>
                </div>
          </el-form>
      </div>
  </div>
  <router-view/>
</template>
<script>
    export default {
        data(){
            
            return{
                loginForm:{
                    userName: "admin",
                    password: "123456"
                },
                loginFormRules:{
                    //验证用户名和密码的合法性
                    userName:[
                        { required: true, message: '请输入用户名', trigger: 'blur' },
                        { min: 3, max: 20, message: '用户名长度在 3 到 20 个字符', trigger: 'blur' }
                    ],
                    userPassword:[
                        { required: true, message: '请输入密码', trigger: 'blur' },
                        { min: 6, max: 15, message: '密码长度在 6 到 15 个字符', trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {
            //先验证校验规则，然后访问后台，登录成功后跳转页面
            login(){
                this.$refs.loginFormRef.validate(async valid => {
                    if (!valid) return;
                    else{
                        const {data:res}=await this.$http.post("login", this.loginForm);
                        if( res.flag =="correct"){
                            this.$message.success("操作成功！！");
                            const{ data: res1 } = await this.$http.post("getUserType", this.loginForm);
                            if(res1==1)
                            this.$router.push({path:"/home"});
                            if(res1==5)
                            this.$router.push({path:"/drug_mgt"});
                            window.sessionStorage.setItem("user",res.user);
                        }else{
                            this.$message.error("用户名不存在或密码错误！！！");
                        }
                    }
                })
            },
        }
        
    };
</script>
<style scoped>
.login_container{
    background-color: #2b4b6b;
    height: 100%;
}
.login_box{
    width: 600px;
    height: 400px;
    background-color: #fff;
    border-radius: 3px;
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
}

.avatar_box{
    height: 200px;
    width: 190px;

    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
}
.btn{
    position: absolute;
    left: 50%;
}
.login_form{
    position: absolute;
    top: 40%;
    width: 100%;
    padding: 0 20px;
    box-sizing: border-box;
}
.tiparea{
    text-align: right;
    font-size: 15px;
    color: #333;
}
.tiparea p a{
    color: #409eff;
}
</style>
