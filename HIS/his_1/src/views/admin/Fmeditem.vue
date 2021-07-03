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
            <el-input style="width: 300px;" placeholder="请输入项目编号进行查询" v-model = "queryInfo.query" clearable @clear="getitemList" >
            </el-input>
            <el-button style="width: 60px;" icon = "el-icon-search" @click ="getitemList"></el-button>
            <el-button type="primary" @click="addDialogVisible=true">添加用户</el-button>
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
                    <el-button  type="primary" @click = "showUpdateDialog(scope.row.itemName)">修改</el-button>
                    <el-button type="danger"  @click = "deleteitem(scope.row.itemName)" >删除</el-button>
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
    </div>

</template>
<script>
export default{
    created(){

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
        }
    },

}
</script>
<style scoped>
</style>