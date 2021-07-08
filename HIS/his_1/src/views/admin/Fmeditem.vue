<template>
    <div>
        <h3>非药品收费项目管理</h3>
        <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>常数项信息管理</el-breadcrumb-item>
        <el-breadcrumb-item>非药品收费项目管理</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card>
            <!-- 搜索栏 -->
            <el-input style="width: 300px;" placeholder="请输入项目编号进行查询" v-model = "queryInfo.query" clearable @clear="getItemList" >
            </el-input>
            <el-button style="width: 60px;" icon = "el-icon-search" @click ="getItemList"></el-button>
            <el-button type="primary" @click="addDialogVisible=true">添加项目</el-button>
            <!--查询表格-->
            <el-table
                :data="tableData"
                stripe
                border
                style="width: 100%">
                <el-table-column
                prop="itemCode"
                label="项目编码"
                width="180">
                </el-table-column>
                <el-table-column
                prop="itemName"
                label="项目名称"
                width="180">
                </el-table-column>
                <el-table-column
                prop="format"
                label="单位"
                width="180">
                </el-table-column>
                <el-table-column
                prop="price"
                label="价格"
                width="180">
                </el-table-column>
                <el-table-column>
                <template #default="scope">
                    <!--每一条的删除与修改-->
                    <el-button  type="primary" @click = "showUpdateDialog(scope.row.itemCode)">修改</el-button>
                    <el-button type="danger"  @click = "deleteitem(scope.row.itemCode)" >删除</el-button>
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
        <el-dialog title="添加项目信息" v-model = "addDialogVisible" width="50%" @close="addDialogClose">
            <el-form :model = "addForm" :rules = "addFormRules" ref= "addFormRef" label-width="70px">
                <el-form-item label = "项目编码" label-width="12%" prop = "itemCode">
                    <el-input v-model = "addForm.itemCode"></el-input>
                </el-form-item>
                <el-form-item label = "项目名称" label-width="12%" prop = "itemName">
                    <el-input v-model = "addForm.itemName"></el-input>
                </el-form-item>
                <el-form-item label = "单位" label-width="12%" prop = "fromat">
                    <el-input v-model = "addForm.format"></el-input>
                </el-form-item>
                <el-form-item label = "价格" label-width="12%" prop = "price">
                    <el-input v-model = "addForm.price"></el-input>
                </el-form-item>
                <el-form-item size="large" >
                    <el-button type="primary" @click="addItem">确定</el-button>
                    <el-button @click="addDialogVisible = false">取消</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

        <el-dialog title="修改项目信息" v-model = "updateDialogVisible" width="50%" @close="updateDialogClose">
            <el-form :model = "updateForm" :rules = "updateFormRules" ref= "updateFormRef" label-width="70px">
                <el-form-item label = "项目编码" label-width="12%" prop = "itemCode">
                    <el-input v-model = "updateForm.itemCode" disabled></el-input>
                </el-form-item>
                <el-form-item label = "项目名" label-width="12%" prop = "itemName">
                    <el-input v-model = "updateForm.itemName"></el-input>
                </el-form-item>
                <el-form-item label = "单位" label-width="12%" prop = "format">
                    <el-input v-model = "updateForm.format" ></el-input>
                </el-form-item>
               <el-form-item label = "价格" label-width="12%" prop = "price">
                    <el-input v-model = "updateForm.price" ></el-input>
                </el-form-item>
                <el-form-item size="large" >
                    <el-button type="primary" @click="updateItem">确定修改</el-button>
                    <el-button @click="updateDialogVisible = false">取消</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>

</template>
<script>
export default{
    created(){
        this.getItemList();
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
                itemCode: [
                { required: true, message: '请输入项目代码', trigger: 'blur' },
                { min: 3, max: 10, message: '长度在3到10之间', trigger: 'blur' }
                ],
                
            },
            updateFormRules:{
                 itemName: [
                { required: true, message: '请输入项目名称', trigger: 'blur' },
                { min: 3, max: 20, message: '长度在3到10之间', trigger: 'blur' }
                ],
            },
            addDialogVisible: false,
            addForm:{},
            //修改用户信息
            updateDialogVisible: false, //修改信息的对话框是否可见
            updateForm:{},
        }
    },
    methods:{
        async getItemList(){
            const {data: res} = await this.$http.get("/allItems",{params:this.queryInfo});
            this.tableData = res.list;
            this.total = res.total;
        },
        async deleteitem(item){
            const confirmResult = await this.$confirm('此操作将永久删除该项目，是否继续？', '提示',{
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).catch(err =>err)
            if(confirmResult != 'confirm'){
                return this.$message.info("已取消删除");
            }
            const {data :res}=await this.$http.delete("deleteItem?itemCode="+item);
            if (res !="success"){
                return this.$message.error("操作失败");
            }
            this.$message.success("操作成功！");
            this.getItemList();
        },
        handleSizeChange(newSize){
            this.queryInfo.pageSize= newSize;
            this.getItemList()
        },
        handleCurrentChange(newPage){
            this.queryInfo.pageNum = newPage;
            this.getItemList();
        },
        addItem(){
            this.$refs.addFormRef.validate(async valid=>{
                if(!valid) return;
                const {data:res} = await this.$http.post("addItem",this.addForm); 
                if(res !="success"){
                    return this.$message.error("操作失败");
                }
                this.$message.success("操作成功");
                this.addDialogVisible= false;
                this.getItemList();
            });
        },
        async showUpdateDialog(itemCode){
            const {data: res} = await this.$http.get("getItem?itemCode="+itemCode);
            this.updateForm = res;
            this.updateDialogVisible = true;
        },
        updateItem(){
            this.$refs.updateFormRef.validate(async valid=>{
                if(!valid) return;
                const{data:res} = await this.$http.put("updateItem", this.updateForm);
                if(res!="success") return this.$message.error("操作失败！！");
                this.$message.success("操作成功！");
                this.updateDialogVisible = false;
                this.getItemList();
            })
        },
    }

}
</script>
<style scoped>
</style>