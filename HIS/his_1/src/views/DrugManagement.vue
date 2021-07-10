<template>

    <h3 style="text-align: center">药房管理</h3>
    <div style="text-align:right">
      <el-button class = "logout-button" type="info" @click="logout">安全退出</el-button>
    </div>
  
    

  <el-container>
    <el-header>
      
      <el-row>
        <el-col :span="8">
          <el-autocomplete class="inline-input" v-model="state" :fetch-suggestions="querySearch" placeholder="请输入药品助记码"
            @select="handleSelect"></el-autocomplete>
          <el-button type="primary" icon="el-icon-view" @click="handleSearch">查询药品</el-button>
        </el-col>
        <el-col :span="4" :offset="4">
          <div>
            <el-button type="primary" icon="el-icon-edit"
              @click="form = {};dialogState = 'add';dialogFormVisible = true">新增药品</el-button>
              
          </div>
        </el-col>
        <el-col :span="3" :offset="3">
          <div>
            <el-upload class="upload-demo" action="http://localhost:9000/drugManagement/importDrug"
              :file-list="fileList" auto-upload="true" show-file-list="false">
              <el-button id="import-button" type="primary" icon="el-icon-sold-out">导入药品</el-button>
            </el-upload>
          </div>
        </el-col>
      </el-row>
    </el-header>
    <el-main>
      <el-table ref="multipleTable" :data="tableData" fit stripe tooltip-effect="dark" style="width: 100%"
        @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55">
        </el-table-column>
        <el-table-column prop="code" label="药品编码" width="150">
        </el-table-column>
        <el-table-column prop="name" label="药品名称" width="200">
        </el-table-column>
        <el-table-column prop="format" label="药品规格" width="200">
        </el-table-column>
        <el-table-column prop="unit" label="药品单位" width="80">
        </el-table-column>
        <el-table-column prop="price" label="药品单价" width="80">
        </el-table-column>
        <el-table-column prop="dosageid" label="药品剂型" width="80">
        </el-table-column>
        <el-table-column prop="typeid" label="药品类型" width="80">
        </el-table-column>
        <el-table-column label="操作" align="center" width="240">
          <template #default="scope">
            <el-button type="primary"
              @click="dialogState = 'edit';openEdit(scope.$index, scope.row);dialogFormVisible = true">编辑</el-button>
            <el-button type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-dialog title="药品信息" v-model="dialogFormVisible">
        <el-form :model="form">
          <el-form-item label="药品编码" :label-width="formLabelWidth">
            <el-input v-model="form.code" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="药品名称" :label-width="formLabelWidth">
            <el-input v-model="form.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="药品规格" :label-width="formLabelWidth">
            <el-input v-model="form.format" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="包装单位" :label-width="formLabelWidth">
            <el-input v-model="form.unit" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="生产厂家" :label-width="formLabelWidth">
            <el-input v-model="form.manufacturer" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="药品剂型" :label-width="formLabelWidth">
            <el-input v-model="form.dosageid" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="药品类型" :label-width="formLabelWidth">
            <el-input v-model="form.typeid" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="药品单价" :label-width="formLabelWidth">
            <el-input v-model="form.price" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogFormVisible = false;handleCommit()">确 定</el-button>
          </span>
        </template>
      </el-dialog>
    </el-main>
    <el-footer>
      <el-row>
        <el-col :span="1" :offset="1">
          <el-button id="multiple-delete" type="primary" @click="handleMultipleDelete()" plain>批量删除</el-button>
        </el-col>
        <el-col :span="1" :offset="13">
          <el-pagination @current-change="handleCurrentChange" layout="prev, pager, next" :total="1000">
          </el-pagination>
        </el-col>
      </el-row>
    </el-footer>
  </el-container>
</template>


<script>
import { ref, onMounted } from 'vue'
import axios from "axios"
let component = {};
export default {
  setup () {
    const mnemonicCodes = ref([]);
    const querySearch = (queryString, cb) => {
      var results = queryString
        ? mnemonicCodes.value.filter(createFilter(queryString))
        : mnemonicCodes.value;
      // 调用 callback 返回建议列表的数据
      cb(results);

    };
    const createFilter = (queryString) => {
      return (mnemonicCodes) => {
        return (
          mnemonicCodes.value.indexOf(queryString.toUpperCase()) ===
          0
        );
      };
    };
    const loadAll = () => {
      axios({
        method: "get",
        url: "/drugManagement/allMnemonicCodes"
      }).then((response) => {
        mnemonicCodes.value = response.data;
        console.log(mnemonicCodes.value)
      })
    };
    const handleSelect = (item) => {
      console.log(item);
    };
    onMounted(() => {
      loadAll();
    });
    return {
      mnemonicCodes,
      state: ref(''),
      querySearch,
      createFilter,
      loadAll,
      handleSelect
    };
  },
  created () {
    component = this;
    this.getDrugList();
  },
  data () {

    return {
      queryInfo: {
        query: "",           //查询信息
        pageNum: 1,         //当前页
        pageSize: 10,       //每页最大记录数
      },
      mnemonicCode: "",
      tableData: [],
      multipleSelection: [],
      drugNum: "",
      dialogFormVisible: false,
      formLabelWidth: "30",
      dialogState: "",
      index: "",
      form: {
      },
      fileList: [],
      multipleDeleteList: []
    }
  },
  methods: {
    async getDrugList () {
      this.getPath();
      console.log(this.query);
      const { data: res } = await this.$http.get(this.query)
      this.tableData = res.list;
      console.log(this.tableData);
      this.drugNum = res.total;
    },
    getPath () {
      this.query = "/drugManagement/allDrugs/" + this.queryInfo.pageNum + "/" + this.queryInfo.pageSize;
    },
    handleCurrentChange (newPage) {
      this.queryInfo.pageNum = newPage;
      this.getDrugList();
    },
    handleSelectionChange (val) {
      this.multipleSelection = val;
    },
    openEdit (index, row) {
      this.form = JSON.parse(JSON.stringify(row));
      this.row = row;
      this.index = index;
    },
    handleSearch () {
      axios.post("/drugManagement/searchDrug", { mnemonicCode: this.state })
        .then((response) => {
          this.tableData = response.data;
          console.log(this.tableData);
        }).catch((error) => {
          console.log(error)
        })
    },
    handleCommit () {
      if (this.dialogState = "add") {
        this.handleAdd();
      }
      else {
        this.handleEdit();
      }
    },
    logout(){
            window.sessionStorage.clear();
            this.$router.push("/login");
        },
    handleAdd () {
      this.$confirm('确认完成创建?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http.post("/drugManagement/createDrug", this.form)
          .then((response) => {
            console.log(response);
            this.getDrugList();
          }).catch((error) => {
            console.log(error);
          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消创建'
        });
      });
    },
    handleEdit () {
      this.$confirm('确认保存修改?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http.post("/drugManagement/updateDrug", this.form)
          .then((response) => {
            console.log(response);
            this.tableData[this.index] = this.form;
          }).catch((error) => {
            console.log(error);
          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消修改'
        });
      });
    },
    handleDelete (index, row) {
      this.$confirm('将要删除药品' + row.name + ', 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http.post("deleteDrug", this.tableData[index])
          .then((response) => {
            console.log(response);
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.tableData.splice(index, 1);
          }).catch((error) => {
            console.log(error);
          });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    handleMultipleDelete () {
      if (this.multipleSelection.length === 0) {
        this.$message({
          type: 'info',
          message: '未选中药品'
        });
        return;
      }
      this.multipleDeleteList = [];
      for (let i = 0; i < this.multipleSelection.length; i++) {
        this.multipleDeleteList[i] = this.tableData[this.multipleSelection[i]];
      }

      this.$confirm('将要删除' + this.multipleSelection.length + '个药品, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http.post("drugManagement/deleteMultipleDrug", this.multipleDeleteList)
          .then((response) => {
            console.log(response);
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            for (let i in this.multipleSelection) {
              this.tableData.splice(i, 1);
            }
          }).catch((error) => {
            console.log(error);
          });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    }

  }
}
</script>






<style>
.el-header,
.el-footer {
  text-align: center;
  line-height: 40px;
}
.el-table__header tr,
.el-table__header th {
  padding: 0;
  height: 30px;
  line-height: 30px;
}
.el-table__body tr,
.el-table__body td {
  padding: 0;
  height: 40px;
  line-height: 30px;
}
.el-main {
  text-align: center;
  line-height: 120px;
}

body > .el-container {
  margin-bottom: 10px;
}
</style>