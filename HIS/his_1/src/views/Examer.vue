<template>
    <div>
        <h2 style="text-align: center">门诊医技工作站</h2>
    </div>
      <div style="text-align:right">
      <el-button class = "logout-button" type="info" @click="logout">安全退出</el-button>
    </div>
    <!-- 搜索栏 -->
    <el-card>
        <div style="text-align: center">
        <!-- 搜索栏 -->
            <el-input style="width: 300px;" placeholder="请输入患者病例号进行查询" v-model = "medicalID"  >
            </el-input>
            <el-button style="width: 60px;" icon = "el-icon-search" @click ="getPatient"></el-button>
    </div>
    <div v-for="user in users" :key="user.id">
        <el-descriptions class="margin-top" title="患者信息表" :column="2" :size="size" border>
    <template #extra>
      <el-button type="primary" size="small" @click="checkImpl(user.medicalID)">执行确认</el-button>
    </template>
    <el-descriptions-item>
      <template #label>
        <i class="el-icon-user"></i>
        患者姓名
      </template>
      {{user.realName}}
    </el-descriptions-item>

    <el-descriptions-item>
        <template #label>
            <i class="el-icon-mobile-phone"></i>
            患者年龄
        </template>
        {{user.age}}
    </el-descriptions-item>

        <el-descriptions-item>
        <template #label>
            <i class="el-icon-mobile-phone"></i>
            检查发起科室
        </template>
        {{user.deptName}}
        </el-descriptions-item>
        <el-descriptions-item>
        <template #label>
            <i class="el-icon-location-outline"></i>
            检查发起医生
        </template>
        {{user.docName}}
        </el-descriptions-item>
        <el-descriptions-item>
        <template #label>
            <i class="el-icon-tickets"></i>
            检查部位
        </template>
            {{user.position}}
        </el-descriptions-item>
        <el-descriptions-item>
        <template #label>
            <i class="el-icon-tickets"></i>
            检查项目
        </template>
            {{user.item}}
        </el-descriptions-item>
        <el-descriptions-item>
        <template #label>
            <i class="el-icon-tickets"></i>
            检查开具时间
        </template>
            {{user.checkCreateTime}}
        </el-descriptions-item>

        <el-descriptions-item>
        <template #label>
            <i class="el-icon-office-building"></i>
            患者联系地址
        </template>
        {{user.address}}
        </el-descriptions-item>
    </el-descriptions>
    </div>
    </el-card>

    <el-dialog v-model = "addDialogVisible" width="80%"  @close="addDialogClose">
        <h2 style="text-align: center">填写诊断结果</h2>
        <el-input
        ref="addRef"
        type="textarea"
        :autosize="{ minRows: 2, maxRows: 4}"
        placeholder="请输入诊断结果"
        v-model="result">
        </el-input>
        <el-divider></el-divider>
        <div style="text-align:right">
            <el-button type="primary" @click="submitResult">提交结果</el-button>
        </div>
        
    </el-dialog>
</template>
<script>
export default {
    created() {
    },
    data(){
        return{
            medicalID:"",
            result:"",
            users:{
                
            },
            addDialogVisible: false,
        }
    },
    methods:{
        async getPatient(){
            const{data:res} = await this.$http.get("/getPatientCheck?medicalID="+this.medicalID);
            this.users = res;
        },
        checkImpl(id){
            console.log(id);
            this.addDialogVisible = true;
        },
        addDialogClose(){
            this.result = "";
            this.addDialogVisible = false;
        },
        async submitResult(){
            const{data: res} = await this.$http.get("/addResults?results="+this.result+"&medicalID="+this.medicalID);
            if (res !="success"){
                return this.$message.error("操作失败");
            }
            this.$message.success("操作成功！");
            this.results="";
            this.addDialogVisible = false;
        },
        logout(){
            window.sessionStorage.clear();
            this.$router.push("/login");
        },
    }
}
</script>
<style scoped>

</style>