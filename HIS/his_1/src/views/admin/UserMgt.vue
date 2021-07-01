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
            <el-button type="primary" @click="showaddForm">添加用户</el-button>
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
                    <el-button  type="primary" @click = "showUpdateDialog(scope.row.deptCode)">修改</el-button>
                    <el-button type="danger"  @click = "deleteDep(scope.row.deptCode)" >删除</el-button>
                </template>
                </el-table-column>
            </el-table>

        </el-card>
    </div>  
</template>
<script>
export default{
    created(){
        this.getUserList();
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
        }
    },
    methods:{
        async getUserList(){
            const {data: res} = await this.$http.get("/allUser");
            this.tableData = res.list;
            this.total = res.total;
        },
    }
}
</script>
<style>
</style>