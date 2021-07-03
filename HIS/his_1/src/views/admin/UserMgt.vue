<template>
    <div>
        <h3>用户管理</h3>
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>人员管理</el-breadcrumb-item>
            <el-breadcrumb-item>用户管理</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card>
            <!-- 搜索栏 -->
            <el-input style="width: 300px;" placeholder="请输入用户名进行查询" v-model = "queryInfo.query" clearable @clear="getUserList" >
            </el-input>
            <el-button style="width: 60px;" icon = "el-icon-search" @click ="getUserList"></el-button>
            <el-button type="primary" @click="addDialogVisible=true">添加用户</el-button>
            <!--查询表格-->
            <el-table
                :data="tableData"
                stripe
                border
                style="width: 100%">
                <el-table-column
                prop="userName"
                label="用户名"
                width="180">
                </el-table-column>
                <el-table-column
                prop="realName"
                label="实际姓名"
                width="180">
                </el-table-column>
                <el-table-column
                prop="useType"
                label="用户类别"
                width="180">
                </el-table-column>
                <el-table-column
                prop="dept"
                label="所属科室"
                width="180">
                </el-table-column>
                <el-table-column
                prop="registName"
                label="挂号级别"
                width="180">
                </el-table-column>
                <el-table-column>
                <template #default="scope">
                    <!--每一条的删除与修改-->
                    <el-button  type="primary" @click = "showUpdateDialog(scope.row.userName)">修改</el-button>
                    <el-button type="danger"  @click = "deleteUser(scope.row.userName)" >删除</el-button>
                </template>
                </el-table-column>
            </el-table>
                <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :page-sizes="[1, 2, 5, 10]"
                :page-size="queryInfo.pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
            </el-pagination>
        </el-card>

               <!-- 添加用户区域 -->
        <el-dialog title="添加用户" v-model = "addDialogVisible" width="50%" @close="addDialogClose">
            <el-form :model = "addForm" :rules = "addFormRules" ref= "addFormRef" label-width="70px">
                <el-form-item label = "用户名" label-width="12%" prop = "userName">
                    <el-input v-model = "addForm.userName"></el-input>
                </el-form-item>
                <el-form-item label = "密码" label-width="12%" prop = "password">
                    <el-input v-model = "addForm.password"></el-input>
                </el-form-item>
                <el-form-item label = "真实姓名" label-width="12%" prop = "realName">
                    <el-input v-model = "addForm.realName"></el-input>
                </el-form-item>
                <el-form-item label = "用户类别" label-width="12%" prop = "useType">
                    <el-input v-model = "addForm.useType"></el-input>
                </el-form-item>
                <el-form-item label="所属科室" label-width="12%" prop = "deptID" >
                    <el-select v-model="addForm.deptID" placeholder="请选择所属科室"  style="width: 100%;">
                    <el-option v-for="it in deptList" :key = "it.id" :label = it.deptName :value = it.id></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="挂号级别" label-width="12%" prop = "registLeID" >
                    <el-select v-model="addForm.registLeID" placeholder="请选择挂号级别"  style="width: 100%;">
                    <el-option v-for="it in registLe" :key = "it.id" :label = it.registName :value = it.id></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item size="large" >
                    <el-button type="primary" @click="updateUser">确定</el-button>
                    <el-button @click="addDialogVisible = false">取消</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

               <!-- 修改用户信息区域 -->
        <el-dialog title="修改用户" v-model = "updateDialogVisible" width="50%" @close="updateDialogClose">
            <el-form :model = "updateForm" :rules = "updateFormRules" ref= "updateFormRef" label-width="70px">
                <el-form-item label = "用户名" label-width="12%" prop = "userName">
                    <el-input v-model = "updateForm.userName" disabled></el-input>
                </el-form-item>
                <el-form-item label = "密码" label-width="12%" prop = "password">
                    <el-input v-model = "updateForm.password"></el-input>
                </el-form-item>
                <el-form-item label = "真实姓名" label-width="12%" prop = "realName">
                    <el-input v-model = "updateForm.realName" disabled></el-input>
                </el-form-item>
                <el-form-item label = "用户类别" label-width="12%" prop = "useType">
                    <el-input v-model = "updateForm.useType"></el-input>
                </el-form-item>
                <el-form-item label="所属科室" label-width="12%" prop = "deptID" >
                    <el-select v-model="updateForm.deptID" placeholder="请选择所属科室"  style="width: 100%;">
                    <el-option v-for="it in deptList" :key = "it.id" :label = it.deptName :value = it.id></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="挂号级别" label-width="12%" prop = "registLeID" >
                    <el-select v-model="updateForm.registLeID" placeholder="请选择挂号级别"  style="width: 100%;">
                    <el-option v-for="it in registLe" :key = "it.id" :label = it.registName :value = it.id></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item size="large" >
                    <el-button type="primary" @click="updateUser">确定修改</el-button>
                    <el-button @click="updateDialogVisible = false">取消</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>  
</template>
<script>
export default{
    created(){
        this.getUserList();
        this.getRegistLe();
        this.getDeptList();
    },
    data(){
        return{
            queryInfo:{
                query:"",           //查询信息
                pageNum: 1,         //当前页
                pageSize: 5,       //每页最大记录数
            },
            tableData:[],
            total:0,
            //添加表单规则
            addFormRules:{
                userName: [
                { required: true, message: '请输入用户名', trigger: 'blur' },
                { min: 3, max: 10, message: '长度在3到10之间', trigger: 'blur' }
                ],
                realName: [
                { required: true, message: '请输入用户真实姓名', trigger: 'blur' },
                { min: 3, max: 10, message: '长度在3到10之间', trigger: 'blur' }
                ],
                password: [
                { required: true, message: '请输入密码', trigger: 'blur' },
                { min: 3, max: 20, message: '长度在3到10之间', trigger: 'blur' }
                ],
                useType: [
                { required: true, message: '请输入用户类别', trigger: 'blur' },
                { min: 3, max: 10, message: '长度在3到10之间', trigger: 'blur' }
                ],
                dept: [
                { required: true, message: '请选择科室类型', trigger: 'change' },
                ],
                registName: [
                { required: true, message: '请选择科室类型', trigger: 'change' },
                ],
            },
            updateFormRules:{
                 password: [
                { required: true, message: '请输入密码', trigger: 'blur' },
                { min: 3, max: 20, message: '长度在3到10之间', trigger: 'blur' }
                ],
            },
            //添加用户信息
            registLe:{},
            deptList:{},
            addDialogVisible: false,
            addForm:{},
            //修改用户信息
            updateDialogVisible: false, //修改信息的对话框是否可见
            updateForm:{},
        }
    },
    methods:{
        async getUserList(){
            const {data: res} = await this.$http.get("/allUser",{params:this.queryInfo});
            this.tableData = res.list;
            this.total = res.total;
        },
        handleSizeChange(newSize){
            this.queryInfo.pageSize= newSize;
            this.getUserList()
        },
        handleCurrentChange(newPage){
            this.queryInfo.pageNum = newPage;
            this.getUserList();
        },
        addDialogClose(){
            this.$refs.addFormRef.resetFields();
        },
        updateDialogClose(){
            this.$refs.updateFormRef.resetFields();
        },
        async getRegistLe(){
            const {data: res} = await this.$http.get("/getRegistLe");
            this.registLe = res;
        },
        async getDeptList(){
            const {data: res} = await this.$http.get("/getDepList");
            this.deptList = res;
        },
        addUser(){
            this.$refs.addFormRef.validate(async valid=>{
                if(!valid) return;
                const {data:res} = await this.$http.post("addUser",this.addForm); 
                if(res !="success"){
                    return this.$message.error("操作失败");
                }
                this.$message.success("操作成功");
                this.addDialogVisible= false;
                this.getUserList();
            });
        },
        //删除
        async deleteUser(userName){
            const confirmResult = await this.$confirm('此操作将永久删除该用户，是否继续？', '提示',{
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).catch(err =>err)
            if(confirmResult != 'confirm'){
                return this.$message.info("已取消删除");
            }
            const {data :res}=await this.$http.delete("deleteUser?userName="+userName);
            if (res !="success"){
                return this.$message.error("操作失败");
            }
            this.$message.success("操作成功！");
            this.getUserList();
        },
        //修改
        async showUpdateDialog(userName){
            const {data: res} = await this.$http.get("getUser?userName="+userName);
            this.updateForm = res;
            this.updateDialogVisible = true;
        },
        updateUser(){
            this.$refs.updateFormRef.validate(async valid=>{
                if(!valid) return;
                console.log(this.updateForm);
                const{data:res} = await this.$http.put("updateUser", this.updateForm);
                console.log(res);
                if(res!="success") return this.$message.error("操作失败！！");
                this.$message.success("操作成功！");
                this.updateDialogVisible = false;
                this.getUserL//ist();
            })
        },
    }
}
</script>
<style scoped>
</style>