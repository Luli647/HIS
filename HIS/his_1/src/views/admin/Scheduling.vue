<template>
<el-button @click="addRuleVisible=true">
    添加排班规则
</el-button>
<el-dialog v-model="addRuleVisible" @close="addRuleClose" width="85%">
    <el-card>
    新增排班规则
    <el-form :model="searchForm" ref="addRuleRef" label-width="80px">
        <!-- 搜索区域 -->
    <el-row class="selectInfo" justify="center">
        <el-form-item label="排班科室" prop="deptID">
            <el-select v-model="searchForm.deptID" placeholder="请选择所属科室"  style="width: 100%;">
                <el-option v-for="it in deptList" :key = "it.id" :label = it.deptName :value = it.id></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="挂号级别" prop="registLeID">
            <el-select v-model="searchForm.registLeID" placeholder="请选择挂号级别"  style="width: 100%;">
                <el-option v-for="it in registLe" :key = "it.id" :label = it.registName :value = it.id></el-option>
            </el-select>
        </el-form-item>
        <el-button-group>
            <el-button type="primary" icon="el-icon-search" @click="getUserForSch">查询</el-button>
        </el-button-group>
    </el-row>
        <!-- 表格部分，先做出来，之后再找更好的办法 -->
        <el-table :data = tableData style="width:100%" >
            <el-table-column label="医生姓名" prop="userName"></el-table-column>
            <el-table-column label="星期日上午" prop="sunMorning">
                <template #default="scope">
                <el-checkbox v-model="scope.row.sunMorning"></el-checkbox>
            </template>
            </el-table-column>
            <el-table-column label="星期日下午" prop="sunAfternoon">
                <template #default="scope">
                <el-checkbox v-model="scope.row.sunAfternoon"></el-checkbox>
            </template>
            </el-table-column>
            <el-table-column label="星期一上午" prop="monMorning">
                <template #default="scope">
                <el-checkbox v-model="scope.row.monMorning"></el-checkbox>
            </template>
            </el-table-column>
            <el-table-column label="星期一下午" prop="monAfternoon">
                <template #default="scope">
                <el-checkbox v-model="scope.row.monAfternoon"></el-checkbox>
            </template>
            </el-table-column>
            <el-table-column label="星期二上午" prop="tuesMorning">
                <template #default="scope">
                <el-checkbox v-model="scope.row.tuesMorning"></el-checkbox>
            </template>
            </el-table-column>
            <el-table-column label="星期二下午" prop="tuesAfternoon">
                <template #default="scope">
                <el-checkbox v-model="scope.row.tuesAfternoon"></el-checkbox>
            </template>
            </el-table-column>
            <el-table-column label="星期三上午" prop="wedsMorning">
                <template #default="scope">
                <el-checkbox v-model="scope.row.wedsMorning"></el-checkbox>
            </template>
            </el-table-column>
            <el-table-column label="星期三下午" prop="wedsAfternoon">
                <template #default="scope">
                <el-checkbox v-model="scope.row.wedsAfternoon"></el-checkbox>
            </template>
            </el-table-column>
            <el-table-column label="星期四上午" prop="thursMorning">
                <template #default="scope">
                <el-checkbox v-model="scope.row.thursMorning"></el-checkbox>
            </template>
            </el-table-column>
            <el-table-column label="星期四下午" prop="thursAfternoon">
                <template #default="scope">
                <el-checkbox v-model="scope.row.thursAfternoon"></el-checkbox>
            </template>
            </el-table-column>
            <el-table-column label="星期五上午" prop="friMorning">
                <template #default="scope">
                <el-checkbox v-model="scope.row.friMorning"></el-checkbox>
            </template>
            </el-table-column>
            <el-table-column label="星期五下午" prop="friAfternoon">
                <template #default="scope">
                <el-checkbox v-model="scope.row.friAfternoon"></el-checkbox>
            </template>
            </el-table-column>
            <el-table-column label="星期六上午" prop="satMorning">
                <template #default="scope">
                <el-checkbox v-model="scope.row.satMorning"></el-checkbox>
            </template>
            </el-table-column>
            <el-table-column label="星期六下午" prop="satAfternoon">
                <template #default="scope">
                <el-checkbox v-model="scope.row.satAfternoon"></el-checkbox>
            </template>
            </el-table-column>
            
        </el-table>
        <el-button @click="addRule" >确定</el-button>
    </el-form>
</el-card>
</el-dialog>

    
</template>
<script>
export default{
    created(){
        const date = new Date();
        this.getRegistLe();
        this.getDeptList();
    },
    data(){
        return{
            form:{
                deptName:'',
            },
            deptList:{},
            searchForm:{},
            registLe:{},
            dateTable:{},
            weekList:[
                {"key":"sunMorning", "value":"星期天上午"},
                {"key":"sunAfternoon", "value":"星期天下午"},
                {"key":"monMorning", "value":"星期一上午"},
                {"key":"monAfternoon", "value":"星期一下午"},
                {"key":"tuesMorning", "value":"星期二上午"},
                {"key":"tuesAfternoon", "value":"星期二下午"},
                {"key":"wedsMorning", "value":"星期三上午"},
                {"key":"wedsAfternoon", "value":"星期三下午"},
                {"key":"thursMorning", "value":"星期四上午"},
                {"key":"thursAfternoon", "value":"星期四下午"},
                {"key":"friMorning", "value":"星期五上午"},
                {"key":"friAfternoon", "value":"星期五下午"},
                {"key":"satMorning", "value":"星期六上午"},
                {"key":"satAfternoon", "value":"星期六下午"},
            ],
            tableData:[      
            ],
            addRuleVisible: false,

        }
    },
    computed:{
        //通过获取当前的星期数判断是否可以挂号（只可以挂本周的且是今天及今天之后的号）
        registerAvaliable(id){
            return id>=date.getDay()? true: false;        
            }
    },
    methods:{
        async getRegistLe(){
            const {data: res} = await this.$http.get("/getRegistLe");
            this.registLe = res;
        },
        async getDeptList(){
            const {data: res} = await this.$http.get("/getDepList");
            this.deptList = res;
        },
        async getUserForSch(){
            const {data: res} = await this.$http.get("/getUserForSch", {params:this.searchForm});
         
            this.tableData=res;
        },
        addRuleClose(){
            this.$refs.addRuleRef.resetFields();
        },
        async addRule(){
            var s =encodeURIComponent(JSON.stringify(this.tableData));
            console.log(s);
            const {data:res} = await this.$http.get("/updateSchedule?s="+s);
            
            if(res!="success") return this.$message.error("操作失败！！");
            this.$message.success("操作成功！");
        },
        async scheduleChanged(userInfo){
            const {data:res} = await this.$http.put();
        },

    }
}
</script>
<style scoped>
.el-card{
    text-align: center;
    font-size: 20px;
}
/* 居中 */
.selectInfo{
     display: flex;
    justify-content: center; 
  
} 
</style>