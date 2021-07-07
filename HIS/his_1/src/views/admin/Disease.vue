<template>
    <div>
        <h3>诊断目录</h3>
        <el-breadcrumb separator-class="el-icon-arrow-right">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>信息管理</el-breadcrumb-item>
            <el-breadcrumb-item>诊断目录管理</el-breadcrumb-item>
        </el-breadcrumb>

        <el-card>
            <!-- 搜索栏 -->
            <el-input style="width: 300px;" placeholder="请输入诊断编码进行查询" v-model = "queryInfo.query" clearable @clear="getDiseaseList" >
            </el-input>
            <el-button style="width: 60px;" icon = "el-icon-search" @click ="getDiseaseList"></el-button>
            <el-button type="primary" @click="addDialogVisible=true">添加诊断信息</el-button>
            <!--查询表格-->
            <el-table
                :data="tableData"
                stripe
                border
                style="width: 100%">
                <el-table-column
                prop="diseaseCode"
                label="疾病编码"
                width="180">
                </el-table-column>
                <el-table-column
                prop="diseaseName"
                label="疾病名称"
                width="180">
                </el-table-column>
                <el-table-column
                prop="diseaseICD"
                label="国际ICD码"
                width="180">
                </el-table-column>
                <el-table-column
                prop="diseCategory"
                label="疾病类别"
                width="180">
                </el-table-column>
                <el-table-column>
                <template #default="scope">
                    <!--每一条的删除与修改-->
                    <el-button  type="primary" @click = "showUpdateDialog(scope.row.diseaseCode)">修改</el-button>
                    <el-button type="danger"  @click = "deleteDisease(scope.row.diseaseCode)" >删除</el-button>
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

        <el-dialog title="添加诊断目录信息" v-model = "addDialogVisible" width="50%" @close="addDialogClose">
            <el-form :model = "addForm" :rules = "addFormRules" ref= "addFormRef" label-width="70px">
                <el-form-item label = "疾病编码" label-width="12%" prop = "diseaseCode">
                    <el-input v-model = "addForm.diseaseCode"></el-input>
                </el-form-item>
                <el-form-item label = "疾病名称" label-width="12%" prop = "diseaseName">
                    <el-input v-model = "addForm.diseaseName"></el-input>
                </el-form-item>
                <el-form-item label = "疾病ICD编码" label-width="12%" prop = "diseaseICD">
                    <el-input v-model = "addForm.diseaseICD"></el-input>
                </el-form-item>
                <el-form-item label="疾病类别" label-width="12%" prop = "diseCategory" >
                    <el-select v-model="addForm.diseCategoryID" placeholder="请选择疾病类别"  style="width: 100%;">
                    <el-option v-for="it in diseaseType" :key = "it.diseaseCode" :label = it.diseCategory :value = it.diseCategoryID></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item size="large" >
                    <el-button type="primary" @click="addDisease">确定</el-button>
                    <el-button @click="addDialogVisible = false">取消</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

               <!-- 修改用户信息区域 -->
        <el-dialog title="修改诊断目录信息" v-model = "updateDialogVisible" width="50%" @close="updateDialogClose">
            <el-form :model = "updateForm" :rules = "updateFormRules" ref= "updateFormRef" label-width="70px">
                <el-form-item label = "疾病编码" label-width="12%" prop = "diseaseCode">
                    <el-input v-model = "updateForm.diseaseCode" disabled></el-input>
                </el-form-item>
                <el-form-item label = "疾病名" label-width="12%" prop = "diseaseName">
                    <el-input v-model = "updateForm.diseaseName"></el-input>
                </el-form-item>
                <el-form-item label = "疾病ICD码" label-width="12%" prop = "diseaseICD">
                    <el-input v-model = "updateForm.diseaseICD" ></el-input>
                </el-form-item>
               <el-form-item label="疾病类别" label-width="12%" prop = "diseCategory" >
                    <el-select v-model="updateForm.diseCategoryID" placeholder="请选择疾病类别"  style="width: 100%;">
                    <el-option v-for="it in diseaseType" :key = "it.diseaseCodeID" :label = it.diseCategory :value = it.diseCategoryID></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item size="large" >
                    <el-button type="primary" @click="updateDisease">确定修改</el-button>
                    <el-button @click="updateDialogVisible = false">取消</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

    </div>
</template>
<script>
export default {
    created(){
        this.getDiseaseList();
        this.getDiseaseType();
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
                
            },
            updateFormRules:{
                
            },
            //添加用户信息
            diseaseType:{},
            addDialogVisible: false,
            addForm:{},
            //修改用户信息
            updateDialogVisible: false, //修改信息的对话框是否可见
            updateForm:{},

            addFormRules:{
                diseaseCode: [
                { required: true, message: '请输入疾病编码', trigger: 'blur' },
                { min: 1, max: 50, message: '长度要求大于1', trigger: 'blur' }
                ],
            },
            updateFormRules:{
                 diseaseName: [
                { required: true, message: '请输入疾病编码', trigger: 'blur' },
                { min: 1, max: 50, message: '长度要求大于1', trigger: 'blur' }
                ],
            },
        }
    },
    methods:{
        async getDiseaseList(){
            const {data: res} = await this.$http.get("/allDisease",{params:this.queryInfo});
            console.log(res);
            this.tableData = res.list;
            this.total = res.total;
        },
        async getDiseaseType(){
            const {data: res} = await this.$http.get("/getDiseaseType");
            this.diseaseType = res;
        },
        handleSizeChange(newSize){
            this.queryInfo.pageSize= newSize;
            this.getDiseaseList()
        },
        handleCurrentChange(newPage){
            this.queryInfo.pageNum = newPage;
            this.getDiseaseList();
        },
        addDialogClose(){
            this.$refs.addFormRef.resetFields();
        },
        updateDialogClose(){
            this.$refs.updateFormRef.resetFields();
        },
        async deleteDisease(diseaseCode){
            const confirmResult = await this.$confirm('此操作将永久删除该诊断，是否继续？', '提示',{
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).catch(err =>err)
            if(confirmResult != 'confirm'){
                return this.$message.info("已取消删除");
            }
            const {data :res}=await this.$http.delete("deleteDisease?diseaseCode="+diseaseCode);
            if (res !="success"){
                return this.$message.error("操作失败");
            }
            this.$message.success("操作成功！");
            this.getDiseaseList();
        },
        addDisease(){
            this.$refs.addFormRef.validate(async valid=>{
                if(!valid) return;
                const {data:res} = await this.$http.post("addDisease",this.addForm); 
                if(res !="success"){
                    return this.$message.error("操作失败");
                }
                this.$message.success("操作成功");
                this.addDialogVisible= false;
                this.getDiseaseList();
            });
        },
        async showUpdateDialog(diseaseCode){
            const {data: res} = await this.$http.get("getDisease?disease="+diseaseCode);
            this.updateForm = res;
            this.updateDialogVisible = true;
        },
        updateDisease(){
            this.$refs.updateFormRef.validate(async valid=>{
                if(!valid) return;
                const{data:res} = await this.$http.put("updateDisease", this.updateForm);
                if(res!="success") return this.$message.error("操作失败！！");
                this.$message.success("操作成功！");
                this.updateDialogVisible = false;
                this.getDiseaseList();
            })
        },
    }
}
</script>
<style scoped>

</style>