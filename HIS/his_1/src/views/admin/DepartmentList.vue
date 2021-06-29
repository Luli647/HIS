<template>
    <div>
        <h3>科室管理</h3>
        <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>人员管理</el-breadcrumb-item>
        <el-breadcrumb-item>科室管理</el-breadcrumb-item>
        </el-breadcrumb>
        <el-card>
            <el-input style="width: 300px;" placeholder="请输入科室编号或名称" >
            </el-input>
            <el-button style="width: 60px;" icon = "el-icon-search"></el-button>
            <el-button type="primary" >添加科室</el-button>
            <el-table
                :data="tableData"
                stripe
                border
                style="width: 100%">
                <el-table-column
                prop="id"
                label="编号"
                width="180">
                </el-table-column>
                <el-table-column
                prop="name"
                label="科室名称"
                width="180">
                </el-table-column>
                <el-table-column
                prop="type"
                label="类型">
                </el-table-column>
                <el-table-column
                label="操作"
                width="220">
                <template #default="scope">
                    <el-button  @click="handleClick(scope.row)" type="primary" round>修改</el-button>
                    <el-button type="danger" round>删除</el-button>
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

            ]
        }
    },
    methods:{
        //获取科室列表
        async getDepList(){
            this.getPath();
            console.log(this.query);
            const {data: res} = await this.$http.get(this.query);
            this.tableData = res.list;
            this.total = res.total;
        },
        getPath(){
            this.query="/allDep/"+this.queryInfo.pageNum+"/"+this.queryInfo.pageSize;
        },
        handleSizeChange(newSize){
            this.queryInfo.pageSize= newSize;
            this.getDepList()
        },
        handleCurrentChange(newPage){
            this.queryInfo.pageNum = newPage;
            this.getDepList();
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