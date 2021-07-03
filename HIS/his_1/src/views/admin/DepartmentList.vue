<template>
    <div>
        <h3>科室管理</h3>
        <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>人员管理</el-breadcrumb-item>
        <el-breadcrumb-item>科室管理</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card>
            <!-- 搜索栏 -->
            <el-input style="width: 300px;" placeholder="请输入科室编号进行查询" v-model = "queryInfo.query" clearable @clear="getDepList" >
            </el-input>
            <el-button style="width: 60px;" icon = "el-icon-search" @click ="getDepList"></el-button>
            <el-button type="primary" @click="showaddForm">添加科室</el-button>
            <el-table
                :data="tableData"
                stripe
                border
                style="width: 100%">
                <el-table-column
                prop="deptCode"
                label="编号"
                width="180">
                </el-table-column>
                <el-table-column
                prop="deptName"
                label="科室名称"
                width="180">
                </el-table-column>
                <el-table-column
                prop="constantName"
                label="类型">
                </el-table-column>
                <el-table-column
                label="操作"
                width="220">
                <template #default="scope">
                    <!--每一条的删除与修改-->
                    <el-button  type="primary" @click = "showUpdateDialog(scope.row.deptCode)">修改</el-button>
                    <el-button type="danger"  @click = "deleteDep(scope.row.deptCode)" >删除</el-button>
                </template>
                </el-table-column>
            </el-table>
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage4"
                :page-sizes="[1, 2, 5, 10]"
                :page-size="queryInfo.pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total">
            </el-pagination>
        </el-card>

        <!-- 添加科室区域 -->
        <el-dialog title="添加科室" v-model = "addDialogVisible" width="50%" @close="addDialogClose">
            <el-form :model = "addForm" :rules = "addFormRules" ref= "addFormRef" label-width="70px">
                <el-form-item label = "科室编号" label-width="12%" prop = "deptCode">
                    <el-input v-model = "addForm.deptCode"></el-input>
                </el-form-item>
                <el-form-item label = "科室名称" label-width="12%" prop = "deptName">
                    <el-input v-model = "addForm.deptName"></el-input>
                </el-form-item>
                  <el-form-item label="科室类别" label-width="12%" prop = "deptCategoryID" >
                    <el-select v-model="addForm.deptCategoryID" placeholder="请选择科室类别"  style="width: 100%;">
                    <el-option v-for="it in constItemList" :key = "it.id" :label = it.constantName :value = it.id></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item size="large" >
                    <el-button type="primary" @click="addDep">确定</el-button>
                    <el-button @click="addDialogVisible = false">取消</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

        <!-- 修改科室信息 -->
        <el-dialog title="修改科室信息" v-model = "updateDialogVisible" width="50%" @close="updateDialogClose">
            <el-form :model = "updateForm" :rules = "updateFormRules" ref= "updateFormRef" label-width="70px">
                <el-form-item label = "科室编号" label-width="12%" prop = "deptCode">
                    <el-input v-model = "updateForm.deptCode" disabled></el-input>
                </el-form-item>
                <el-form-item label = "科室名称" label-width="12%" prop = "deptName">
                    <el-input v-model = "updateForm.deptName"></el-input>
                </el-form-item>
                <el-form-item label="科室类别" label-width="12%" prop = "deptCategoryID" >
                    <el-select v-model="updateForm.deptCategoryID" placeholder="请选择科室类别"  style="width: 100%;">
                    <el-option v-for="it in constItemList" :key = "it.id" :label = it.constantName :value = it.id></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item size="large">
                    <el-button type="primary" @click="updateDep">确定修改</el-button>
                    <el-button @click="updateDialogVisible = false">取消</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

    </div>
</template>
<script>
export default {
    created(){
        this.getDepList();
    },
    data(){
        return{
            queryInfo:{
                query:"",           //查询信息
                pageNum: 1,         //当前页
                pageSize: 5,       //每页最大记录数
            },
            departmentList:[],   //用户列表
            total:0,           //总记录数
            tableData:[
            ],
            addDialogVisible: false,  //添加信息的对话框是否可见
            updateDialogVisible: false, //修改信息的对话框是否可见
            addForm:{
            },
            updateForm:{
            },
            constItemList:{},
            //表单验证规则
            addFormRules:{
                deptCode: [
                { required: true, message: '请输入科室编号', trigger: 'blur' },
                { min: 3, max: 10, message: '长度在3到10之间', trigger: 'blur' }
                ],
                deptName: [
                { required: true, message: '请输入科室名称', trigger: 'blur' },
                { min: 3, max: 10, message: '长度在3到10之间', trigger: 'blur' }
                ],
                deptCategoryID: [
                { required: true, message: '请选择科室类型', trigger: 'change' },
                ],
            },
            updateFormRules:{
                name: [
                { required: true, message: '请输入科室名称', trigger: 'blur' },
                { min: 3, max: 10, message: '长度在3到10之间', trigger: 'blur' }
                ],
                type: [
                { required: true, message: '请输入科室类型', trigger: 'blur' },
                { min: 1, max: 10, message: '长度在1到10之间', trigger: 'blur' }
                ],
            }
        }
    },
    methods:{
        //获取科室列表
        async getDepList(){
            const {data: res} = await this.$http.get("/allDep",{params:this.queryInfo});
            this.tableData = res.list;
            this.total = res.total;
        },
        handleSizeChange(newSize){
            this.queryInfo.pageSize= newSize;
            this.getDepList()
        },
        handleCurrentChange(newPage){
            this.queryInfo.pageNum = newPage;
            this.getDepList();
        },
        addDialogClose(){
            this.$refs.addFormRef.resetFields();
        },
        addDep(){
            this.$refs.addFormRef.validate(async valid=>{
                if(!valid) return;
                console.log(this.addForm);
                const {data:res} = await this.$http.post("addDep", this.addForm); 
                if(res !="success"){
                    return this.$message.error("操作失败");
                }
                this.$message.success("操作成功");
                this.addDialogVisible= false;
                this.getDepList();
            });
        },
        //删除
        async deleteDep(id){
            const confirmResult = await this.$confirm('此操作将永久删除该用户，是否继续？', '提示',{
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).catch(err =>err)
            if(confirmResult != 'confirm'){
                return this.$message.info("已取消删除");
            }
            const {data :res}=await this.$http.delete("deleteDep?id="+id);
            if (res !="success"){
                return this.$message.error("操作失败");
            }
            this.$message.success("操作成功！");
            this.getDepList();
        },

        //修改
        updateDialogClose(){
            this.$refs.updateFormRef.resetFields();
        },
        updateDep(){
            this.constItemList  = window.sessionStorage.getItem("constantItem");
            this.$refs.updateFormRef.validate(async valid=>{
                if(!valid) return;
                const{data:res} = await this.$http.put("updateDep", this.updateForm);
                console.log(res);
                if(res!="success") return this.$message.error("操作失败！！");
                this.$message.success("操作成功！");
                this.updateDialogVisible = false;
                this.getDepList();
            })
        },
        async showUpdateDialog(id){
            this.getConstList();
            const {data: res} = await this.$http.get("getUpdateDep?id="+id);
            this.updateForm = res;
            this.updateDialogVisible = true;
        },
        //获取department类别编码对应的类别名，并打开添加表格
        showaddForm(){
            this.getConstList();
            this.addDialogVisible = true;
        },
        async getConstList(){
            const {data: res} = await this.$http.get("addDepsearchID");
            this.constItemList = res;
        }
    }
}
</script>
<style scoped>
.el-breadcrumb{
    margin-bottom: 15px;
    font-size: 15px;
}
</style>