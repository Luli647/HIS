<template>
  <el-container>

    <el-container direction="vertical" style="width:30%;" v-show="patientVisible">

      <el-container direction="horizontal">
        <el-tag style="width:50%;height:30px;margin-top:5px;margin-right:33%;text-align:left;">患者选择：</el-tag>
        <el-button type="primary" icon="el-icon-refresh" @click="refresh" style="width:15%;"></el-button>
      </el-container>

      <el-container>
        <el-input placeholder="请输入内容" v-model="inputPatient">
          <template #prepend>患者名：</template>
          <template #append>
            <el-button icon="el-icon-search" @click="searchPatient" style="width:80px"></el-button>
          </template>
        </el-input>
      </el-container>

      <el-dialog
          title="提示"
          v-model="centerDialogVisible"
          width="30%"
          destroy-on-close
          center>
        <span>确定选择该患者？</span>
        <template #footer>
    <span class="dialog-footer">
      <el-button @click="centerDialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="choosePatient">确 定</el-button>
    </span>
        </template>

      </el-dialog>

      <el-container style="margin-top: 5px">
        <el-tabs type="border-card" style="width:100%">
          <el-tab-pane label="本人">
            <el-tag style="width:95%;height:30px;text-align:left;">未诊患者：</el-tag>
            <el-table ref="singleTable"
                      :data="brwzhz"
                      highlight-current-row
                      @current-change="handleCurrentChange"
                      style="width: 100%" :show-header="false">
              <el-table-column
                  prop="caseNumber"
                  width="100%">
              </el-table-column>
              <el-table-column
                  prop="realName"
                  width="180%">
              </el-table-column>
              <el-table-column
                  prop="age">
              </el-table-column>
            </el-table>
            <el-tag style="width:95%;height:30px;text-align:left;margin-top:50px">已诊患者：</el-tag>
            <el-table :data="bryzhz" style="width: 100%" :show-header="false">
              <el-table-column
                  prop="caseNumber"
                  width="100%">
              </el-table-column>
              <el-table-column
                  prop="realName"
                  width="180%">
              </el-table-column>
              <el-table-column
                  prop="age">
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane label="科室">
            <el-tag style="width:95%;height:30px;text-align:left;">未诊患者：</el-tag>
            <el-table ref="singleTable"
                      :data="kswzhz"
                      highlight-current-row
                      @current-change="handleCurrentChange"
                      style="width: 100%" :show-header="false">
              <el-table-column
                  prop="caseNumber"
                  width="100%">
              </el-table-column>
              <el-table-column
                  prop="realName"
                  width="180%">
              </el-table-column>
              <el-table-column
                  prop="age">
              </el-table-column>
            </el-table>
            <el-tag style="width:95%;height:30px;text-align:left;margin-top:50px">已诊患者：</el-tag>
            <el-table :data="ksyzhz" style="width: 100%" :show-header="false">
              <el-table-column
                  prop="caseNumber"
                  width="100%">
              </el-table-column>
              <el-table-column
                  prop="realName"
                  width="180%">
              </el-table-column>
              <el-table-column
                  prop="age">
              </el-table-column>
            </el-table>
          </el-tab-pane>
        </el-tabs>
      </el-container>

      <el-container style="height: 100%;"></el-container>
    </el-container>

    <el-container direction="vertical" style="width: 70%;">

      <el-container direction="horizontal">
        <el-button type="primary" plain style="height:20px" @click="patientVisible=!patientVisible">隐藏患者栏</el-button>
        <div id="patientDetail" align="left" style="margin-left:20px;margin-top:10px;width:85%">请选择患者</div>
        <el-button type="text" @click="diagnosisDone">诊毕</el-button>
      </el-container>

      <el-tabs type="border-card">
        <el-tab-pane label="病历首页">
          <div style="width:100%;height:30px;background-color:aliceblue" >
            <el-button type="text" icon="el-icon-tickets" style="color: cornflowerblue;margin-left:10%" @click="storeTemp">暂存</el-button>
            <el-button type="text" icon="el-icon-success" @click="submitMedicalRecord" style="color: cornflowerblue;margin-left:10%">提交</el-button>
            <el-button type="text" icon="el-icon-printer" @click="clearMedicalRecord" style="color: cornflowerblue;margin-left:10%">清空所有</el-button>
            <el-button type="text" icon="el-icon-circle-plus-outline" style="color: cornflowerblue;margin-left:10%">刷新</el-button>
          </div>
          <el-tag align="left" style="height:30px">病史内容：</el-tag>

          <el-form ref="medicalrecordform" :model="medicalRecordForm" label-width="10%">
            <el-form-item label="主诉" >
              <el-input v-model="medicalRecordForm.readme"></el-input>
            </el-form-item>
            <el-form-item label="现病史">
              <el-input type="textarea" v-model="medicalRecordForm.present"></el-input>
            </el-form-item>
            <el-form-item label="现病史治疗状况">
              <el-input type="textarea" v-model="medicalRecordForm.presentTreat"></el-input>
            </el-form-item>
            <el-form-item label="既往史">
              <el-input type="textarea" v-model="medicalRecordForm.history"></el-input>
            </el-form-item>
            <el-form-item label="过敏史">
              <el-input type="textarea" v-model="medicalRecordForm.allergy"></el-input>
            </el-form-item>
            <el-form-item label="体格检查">
              <el-input type="textarea" v-model="medicalRecordForm.physique"></el-input>
            </el-form-item>

            <el-tag style="height:30px;margin-left:-90%">评估/诊断：</el-tag>
            <el-card class="box-card">
              <template #header>
                <div class="card-header" align="left">
                  <span>西医/中医诊断</span>
                  <el-button class="button" type="text" style="margin-left:80%" @click="deleteDiagnose">删除</el-button>
                  <el-button class="button" type="text" @click="addDiagnose">增加</el-button>
                </div>
              </template>

              <!--增加诊断对话框-->
              <el-dialog v-model="addDiagnoseDialog" width="70%">
                <el-input placeholder="请输入疾病编号进行查找" v-model = "queryInfo.query" style="width:200px"></el-input>
                <el-button style="width: 60px;" icon = "el-icon-search" @click ="getDiseaseList"></el-button>
                <el-table
                :data="diseaseListData"
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
                <el-table-column>
                <template #default="scope">
                    <!--选择诊断进行添加-->
                    <el-button  type="primary" @click = "addDianoseConfirm(scope.row.diseaseCode)">添加</el-button>
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
              </el-dialog>


              <el-table
                  ref="multipleTable"
                  :data="medicalDisease"
                  tooltip-effect="dark"
                  style="width: 100%"
                 >
                
                <el-table-column
                    prop="diseaseICD"
                    label="ICD编码"
                    width="200%">
                </el-table-column>
                <el-table-column
                    prop="diseaseName"
                    label="姓名"
                    width="400%">
                </el-table-column>
                <el-table-column>
                <template #default="scope">
                    <el-button type="danger"  @click = "deleteDiagnose(scope.$index)" >删除</el-button>
                </template>
                </el-table-column>
              </el-table>
            </el-card>

            <!--
            <el-card class="box-card">
              <template #header>
                <div class="card-header" align="left">
                  中医诊断
                <el-button class="button" type="text" style="margin-left:80%">删除</el-button>
                <el-button class="button" type="text">增加</el-button>
                </div>
              </template>
              <el-table
                  ref="multipleTable"
                  :data="medicalDiseaseChinese"
                  tooltip-effect="dark"
                  style="width: 100%"
                  @selection-change="handleSelectionChange">
                <el-table-column
                    type="selection"
                    width="55">
                </el-table-column>
                <el-table-column
                    prop="diseaseICD"
                    label="ICD编码"
                    width="200%">
                </el-table-column>
                <el-table-column
                    prop="diseaseName"
                    label="姓名"
                    width="400%">
                </el-table-column>
                <el-table-column
                    prop="getSiskDate"
                    label="发病时间">
                </el-table-column>
              </el-table>
            </el-card>
            -->

            <el-form-item label="检查建议">
              <el-input type="textarea" v-model="medicalRecordForm.proposal"></el-input>
            </el-form-item>
            <el-form-item label="注意事项">
              <el-input type="textarea" v-model="medicalRecordForm.careful"></el-input>
            </el-form-item>
          </el-form>
          <el-button type="text" icon="el-icon-success" align="left" @click="submitMedicalRecord" style="color: cornflowerblue">提交</el-button>
        </el-tab-pane>

        <el-tab-pane label="检查/检验/处理申请">
          <el-container direction="horizontal">
            <el-container direction="vertical" style="width: 65%">
              <div align="center" style="width:100%;height:30px;background-color:aliceblue" >
                <el-link icon="el-icon-circle-plus" style="color: cornflowerblue;margin-left:10%;margin-top:5px" @click="addCheck">新增项目</el-link>
                <el-link icon="el-icon-remove" style="color: cornflowerblue;margin-left:3%">删除项目</el-link>
                <el-link icon="el-icon-success" style="color: cornflowerblue;margin-left:3%">开立项目</el-link>
                <el-link icon="el-icon-delete" style="color: cornflowerblue;margin-left:3%">作废项目</el-link>
                <el-link icon="el-icon-circle-check" style="color: cornflowerblue;margin-left:3%">另存组套</el-link>
                <el-link icon="el-icon-circle-plus-outline" style="color: cornflowerblue;margin-left:3%">刷新</el-link>
              </div>
              <div align="left">
                <el-tag style="height:30px;margin-top:5px">本项目金额合计：</el-tag>
                <el-tag ref="jcBalance" type="warning" style="height:30px;margin-left:5px">0元</el-tag>
              </div>
              <el-table
                  ref="multipleTable"
                  :data="checkApply"
                  tooltip-effect="dark"
                  style="width: 100%"
                  @selection-change="handleSelectionChange">
                <el-table-column
                    type="selection"
                    width="55">
                </el-table-column>
                <el-table-column
                    prop="itemID"
                    label="项目ID"
                    width="120%">
                </el-table-column>
                <el-table-column
                    prop="name"
                    label="项目名称"
                    width="180%">
                </el-table-column>
                <el-table-column
                    prop="deptID"
                    label="执行科室"
                    width="120%">
                </el-table-column>
                <el-table-column
                    prop="state"
                    label="执行状态"
                    width="120%">
                </el-table-column>
                <el-table-column
                    prop="price"
                    label="单价"
                    width="100%">
                </el-table-column>
                <el-table-column
                    label="检查结果"
                    width="120%">
                  <el-button type="text" @click="showResult">查看详细</el-button>
                </el-table-column>
              </el-table>
            </el-container>
            <el-container style="width: 33%;height:50px;margin-left:2%">
              <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
                <el-tab-pane label="常用模板" name="first">
                  <el-table :data="checkTemplate" style="width: 100%">
                    <el-table-column
                        prop="name"
                        label="名称"
                        width="350%">
                    </el-table-column>
                    <el-table-column
                        label="操作"
                        width="100%">
                      <el-button type="text">使用</el-button>
                      <el-button type="text">详细</el-button>
                    </el-table-column>
                  </el-table>
                </el-tab-pane>
               </el-tabs>
            </el-container>
          </el-container>
        </el-tab-pane>

        <!--
        <el-tab-pane disabled label="检验申请*">
          <el-container direction="horizontal">
            <el-container direction="vertical" style="width: 65%">
              <div align="center" style="width:100%;height:30px;background-color:aliceblue" >
                <el-link icon="el-icon-circle-plus" style="color: cornflowerblue;margin-left:10%;margin-top:5px">新增项目</el-link>
                <el-link icon="el-icon-remove" style="color: cornflowerblue;margin-left:3%">删除项目</el-link>
                <el-link icon="el-icon-success" style="color: cornflowerblue;margin-left:3%">开立项目</el-link>
                <el-link icon="el-icon-delete" style="color: cornflowerblue;margin-left:3%">作废项目</el-link>
                <el-link icon="el-icon-circle-check" style="color: cornflowerblue;margin-left:3%">另存组套</el-link>
                <el-link icon="el-icon-circle-plus-outline" style="color: cornflowerblue;margin-left:3%">刷新</el-link>
              </div>
              <div align="left">
                <el-tag style="height:30px;margin-top:5px">本项目金额合计：</el-tag>
                <el-tag type="warning" style="height:30px;margin-left:5px">0元</el-tag>
              </div>
              <el-table
                  ref="multipleTable"
                  :data="jysq"
                  tooltip-effect="dark"
                  style="width: 100%"
                  @selection-change="handleSelectionChange">
                <el-table-column
                    type="selection"
                    width="55">
                </el-table-column>
                <el-table-column
                    prop="itemID"
                    label="项目ID"
                    width="120%">
                </el-table-column>
                <el-table-column
                    prop="name"
                    label="项目名称"
                    width="180%">
                </el-table-column>
                <el-table-column
                    prop="deptID"
                    label="执行科室"
                    width="120%">
                </el-table-column>
                <el-table-column
                    prop="state"
                    label="执行状态"
                    width="120%">
                </el-table-column>
                <el-table-column
                    prop="price"
                    label="单价"
                    width="100%">
                </el-table-column>
                <el-table-column
                    label="检查结果"
                    width="120%">
                  <el-button type="text">查看详细</el-button>
                </el-table-column>
              </el-table>
            </el-container>
            <el-container style="width: 33%;height:50px;margin-left:2%">
              <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
                <el-tab-pane label="常用模板" name="first">
                  <el-table :data="jycymb" style="width: 100%">
                    <el-table-column
                        prop="name"
                        label="名称"
                        width="350%">
                    </el-table-column>
                    <el-table-column
                        label="操作"
                        width="100%">
                      <el-button type="text">使用</el-button>
                      <el-button type="text">详细</el-button>
                    </el-table-column>
                  </el-table>
                </el-tab-pane>
              </el-tabs>
            </el-container>
          </el-container>
        </el-tab-pane>
         -->

        <!--
        <el-tab-pane disable label="处理申请*">
          <el-container direction="horizontal">
            <el-container direction="vertical" style="width: 65%">
              <div align="center" style="width:100%;height:30px;background-color:aliceblue" >
                <el-link icon="el-icon-circle-plus" style="color: cornflowerblue;margin-left:10%;margin-top:5px">新增项目</el-link>
                <el-link icon="el-icon-remove" style="color: cornflowerblue;margin-left:3%">删除项目</el-link>
                <el-link icon="el-icon-success" style="color: cornflowerblue;margin-left:3%">开立项目</el-link>
                <el-link icon="el-icon-delete" style="color: cornflowerblue;margin-left:3%">作废项目</el-link>
                <el-link icon="el-icon-circle-check" style="color: cornflowerblue;margin-left:3%">另存组套</el-link>
                <el-link icon="el-icon-circle-plus-outline" style="color: cornflowerblue;margin-left:3%">刷新</el-link>
              </div>
              <div align="left">
                <el-tag style="height:30px;margin-top:5px">本项目金额合计：</el-tag>
                <el-tag type="warning" style="height:30px;margin-left:5px">0元</el-tag>
              </div>
              <el-table
                  ref="multipleTable"
                  :data="clsq"
                  tooltip-effect="dark"
                  style="width: 100%"
                  @selection-change="handleSelectionChange">
                <el-table-column
                    type="selection"
                    width="55">
                </el-table-column>
                <el-table-column
                    prop="itemID"
                    label="项目ID"
                    width="120%">
                </el-table-column>
                <el-table-column
                    prop="name"
                    label="项目名称"
                    width="180%">
                </el-table-column>
                <el-table-column
                    prop="deptID"
                    label="执行科室"
                    width="120%">
                </el-table-column>
                <el-table-column
                    prop="state"
                    label="执行状态"
                    width="120%">
                </el-table-column>
                <el-table-column
                    prop="price"
                    label="单价"
                    width="100%">
                </el-table-column>
                <el-table-column
                    label="检查结果"
                    width="120%">
                  <el-button type="text">查看详细</el-button>
                </el-table-column>
              </el-table>
            </el-container>
            <el-container style="width: 33%;height:50px;margin-left:2%">
              <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
                <el-tab-pane label="常用模板" name="first">
                  <el-table :data="czclmb" style="width: 100%">
                    <el-table-column
                        prop="name"
                        label="名称"
                        width="350%">
                    </el-table-column>
                    <el-table-column
                        label="操作"
                        width="100%">
                      <el-button type="text">使用</el-button>
                      <el-button type="text">详细</el-button>
                    </el-table-column>
                  </el-table>
                </el-tab-pane>
              </el-tabs>
            </el-container>
          </el-container>
        </el-tab-pane>
        -->

        <el-tab-pane label="门诊确诊*"></el-tab-pane>

        <el-tab-pane label="成药处方">
          <div align="left">
            <el-tag align="left" style="height:30px;margin-top:5px">门诊诊断：</el-tag>
            <el-tag ref="diagnose" v-show="false" type="info" style="height:30px;margin-left:5px"></el-tag>
          </div>

          <div align="center" style="width:100%;height:30px;background-color:aliceblue" >
            <el-link icon="el-icon-circle-plus" style="color: cornflowerblue;margin-left:20%;margin-top:5px">增方</el-link>
            <el-link icon="el-icon-remove" style="color: cornflowerblue;margin-left:3%">删方</el-link>
            <el-link icon="el-icon-success" style="color: cornflowerblue;margin-left:3%">开立</el-link>
            <el-link icon="el-icon-delete" style="color: cornflowerblue;margin-left:3%">作废</el-link>
            <el-link icon="el-icon-circle-plus-outline" style="color: cornflowerblue;margin-left:3%">刷新</el-link>
            <el-link icon="el-icon-circle-plus-outline" style="color: cornflowerblue;margin-left:20%;margin-top:5px">增药</el-link>
            <el-link icon="el-icon-remove-outline" style="color: cornflowerblue;margin-left:3%">删药</el-link>
          </div>

          <el-container direction="horizontal" style="width:100%">
            <el-container direction="vertical" style="width:25%;border: 1px solid #eee">
              <el-tag align="left" type="info" style="text-align:left;width:30%;height:30px;margin-top:5px">门诊处方：</el-tag>
              <el-table
                  ref="singleTable"
                  :data="prescription"
                  style="width: 100%"
                  @current-change="prescriptionChange">
                <el-table-column width="50%">
                  <template #default="scope">
                    <el-radio v-model="radio" :label="scope.row">&thinsp;</el-radio>
                  </template>
                </el-table-column>
                <el-table-column
                    prop="prescriptionName"
                    label="名称"
                    width="190%">
                </el-table-column>
                <el-table-column
                    prop="prescriptionState"
                    label="状态"
                    width="60%">
                </el-table-column>
              </el-table>
            </el-container>

            <el-container direction="vertical" style="width:73%;margin-left: 2%">
              <div align="left">
                <el-tag style="height:30px;margin-top:5px">本处方金额合计：</el-tag>
                <el-tag type="warning" style="height:30px;margin-left:5px">0元</el-tag>
              </div>
              <el-table
                  ref="multipleTable"
                  :data="prescriptionDetailed"
                  tooltip-effect="dark"
                  style="width: 100%"
                  @selection-change="handleSelectionChange">
                <el-table-column
                    type="selection"
                    width="55">
                </el-table-column>
                <el-table-column
                    prop="drugsName"
                    label="药品名称"
                    width="300%">
                </el-table-column>
                <el-table-column
                    prop="drugsFormat"
                    label="规格"
                    width="120%">
                </el-table-column>
                <el-table-column
                    prop="drugsPrice"
                    label="单价"
                    width="100%">
                </el-table-column>
                <el-table-column
                    prop="drugsUsage"
                    label="用法"
                    width="120%">
                </el-table-column>
                <el-table-column
                    prop="dosage"
                    label="用量"
                    width="80%">
                </el-table-column>
                <el-table-column
                    prop="frequency"
                    label="频次"
                    width="80%">
                </el-table-column>
                <el-table-column
                    prop="amount"
                    label="数量"
                    width="80%">
                </el-table-column>
              </el-table>
            </el-container>
          </el-container>

          <el-container direction="horizontal" style="width:100%">
            <el-tabs type="card" style="width: 100%">
              <el-tab-pane label="处方模板">
                <el-container direction="horizontal" style="width:100%">
                  <el-container direction="vertical" style="width:35%;border:1px solid #eee ">
                    <el-form :inline="true" :model="cfmb" class="demo-form-inline" style="margin-top:40px;margin-left:-20%">
                      <el-form-item label="名称：">
                        <el-input v-model="cfmb.name" placeholder="输入模板名称"></el-input>
                      </el-form-item>
                      <el-form-item>
                        <el-button type="primary" @click="onSubmit">查询</el-button>
                      </el-form-item>
                    </el-form>

                    <el-table
                        ref="singleTable"
                        :data="drugsTemplate"
                        highlight-current-row
                        @current-change="drugsTemplateChange"
                        style="width: 95%;margin-left:5%">
                      <el-table-column
                          property="name"
                          label="模板名称"
                          width="300%">
                      </el-table-column>
                      <el-table-column
                          property="scope"
                          label="范围"
                          width="80%">
                      </el-table-column>
                    </el-table>
                  </el-container>

                  <el-container style="width:60%;">
                    <el-card class="box-card" style="width:95%">
                      <template #header>
                        <div class="card-header" align="left">
                          <span>模板明细</span>
                          <el-button class="button" type="text" style="margin-left:80%">使用该模板</el-button>
                        </div>
                      </template>
                      <el-table
                          :data="drugsDetailed"
                          style="width: 100%">
                        <el-table-column
                            prop="drugsName"
                            label="药品姓名"
                            width="220">
                        </el-table-column>
                        <el-table-column
                            prop="drugsFormat"
                            label="规格"
                            width="200">
                        </el-table-column>
                        <el-table-column
                            prop="drugsUnit"
                            label="单位"
                            width="70">
                        </el-table-column>
                        <el-table-column
                            prop="drugsUsage"
                            label="用法"
                            width="70">
                        </el-table-column>
                        <el-table-column
                            prop="dosage"
                            label="用量"
                            width="70">
                        </el-table-column>
                        <el-table-column
                            prop="frequency"
                            label="频次"
                            width="70">
                        </el-table-column>
                      </el-table>
                    </el-card>
                  </el-container>
                </el-container>
              </el-tab-pane>

              <el-tab-pane label="常用药品*"></el-tab-pane>
              <el-tab-pane label="建议方案*"></el-tab-pane>
              <el-tab-pane label="历史处方*"></el-tab-pane>
            </el-tabs>
          </el-container>
        </el-tab-pane>

        <!--
        <el-tab-pane label="草药处方*">
          <div align="left">
            <el-tag align="left" style="height:30px;margin-top:5px">门诊诊断：</el-tag>
            <el-tag type="info" style="height:30px;margin-left:5px">【中医诊断】流行性腮腺炎</el-tag>
          </div>

          <div align="center" style="width:100%;height:30px;background-color:aliceblue" >
            <el-link icon="el-icon-circle-plus" style="color: cornflowerblue;margin-left:20%;margin-top:5px">增方</el-link>
            <el-link icon="el-icon-remove" style="color: cornflowerblue;margin-left:3%">删方</el-link>
            <el-link icon="el-icon-success" style="color: cornflowerblue;margin-left:3%">开立</el-link>
            <el-link icon="el-icon-delete" style="color: cornflowerblue;margin-left:3%">作废</el-link>
            <el-link icon="el-icon-circle-plus-outline" style="color: cornflowerblue;margin-left:3%">刷新</el-link>
            <el-link icon="el-icon-circle-plus-outline" style="color: cornflowerblue;margin-left:20%;margin-top:5px">增药</el-link>
            <el-link icon="el-icon-remove-outline" style="color: cornflowerblue;margin-left:3%">删药</el-link>
          </div>

          <el-container direction="horizontal" style="width:100%">
            <el-container direction="vertical" style="width:25%;border: 1px solid #eee">
              <el-tag align="left" type="info" style="text-align:left;width:30%;height:30px;margin-top:5px">门诊处方：</el-tag>
              <el-table
                  ref="singleTable"
                  :data="caoymzcf"
                  style="width: 100%"
                  @current-change="handleCurrentChange">
                <el-table-column width="50%">
                  <template #default="scope">
                    <el-radio v-model="radio" :label="scope.row">&thinsp;</el-radio>
                  </template>
                </el-table-column>
                <el-table-column
                    prop="mc"
                    label="名称"
                    width="190%">
                </el-table-column>
                <el-table-column
                    prop="zt"
                    label="状态"
                    width="60%">
                </el-table-column>
              </el-table>
            </el-container>

            <el-container direction="vertical" style="width:73%;margin-left: 2%">
              <div align="left">
                <el-tag style="height:30px;margin-top:5px">本处方金额合计：</el-tag>
                <el-tag type="warning" style="height:30px;margin-left:5px">0元</el-tag>
              </div>
              <el-table
                  ref="multipleTable"
                  :data="caoycf"
                  tooltip-effect="dark"
                  style="width: 100%"
                  @selection-change="handleSelectionChange">
                <el-table-column
                    type="selection"
                    width="55">
                </el-table-column>
                <el-table-column
                    prop="ypmc"
                    label="药品名称"
                    width="300%">
                </el-table-column>
                <el-table-column
                    prop="gg"
                    label="规格"
                    width="120%">
                </el-table-column>
                <el-table-column
                    prop="dj"
                    label="单价"
                    width="100%">
                </el-table-column>
                <el-table-column
                    prop="yf"
                    label="用法"
                    width="120%">
                </el-table-column>
                <el-table-column
                    prop="yl"
                    label="用量"
                    width="80%">
                </el-table-column>
                <el-table-column
                    prop="pc"
                    label="频次"
                    width="80%">
                </el-table-column>
                <el-table-column
                    prop="sl"
                    label="数量"
                    width="80%">
                </el-table-column>
              </el-table>
            </el-container>
          </el-container>

          <el-container direction="horizontal" style="width:100%">
            <el-tabs type="card" style="width: 100%">
              <el-tab-pane label="处方模板">
                <el-container direction="horizontal" style="width:100%">
                  <el-container direction="vertical" style="width:35%;border:1px solid #eee ">
                    <el-form :inline="true" :model="caoycfmb" class="demo-form-inline" style="margin-top:40px;margin-left:-20%">
                      <el-form-item label="名称：">
                        <el-input v-model="caoycfmb.name" placeholder="输入模板名称"></el-input>
                      </el-form-item>
                      <el-form-item>
                        <el-button type="primary" @click="onSubmit">查询</el-button>
                      </el-form-item>
                    </el-form>

                    <el-table
                        ref="singleTable"
                        :data="caoycfmb"
                        highlight-current-row
                        @current-change="handleCurrentChange"
                        style="width: 95%;margin-left:5%">
                      <el-table-column
                          property="mbmc"
                          label="模板名称"
                          width="300%">
                      </el-table-column>
                      <el-table-column
                          property="fw"
                          label="范围"
                          width="80%">
                      </el-table-column>
                    </el-table>
                  </el-container>

                  <el-container style="width:60%;">
                    <el-card class="box-card" style="width:95%">
                      <template #header>
                        <div class="card-header" align="left">
                          <span>模板明细</span>
                          <el-button class="button" type="text" style="margin-left:80%">使用该模板</el-button>
                        </div>
                      </template>
                      <el-table
                          :data="caoycfmbmx"
                          style="width: 100%">
                        <el-table-column
                            prop="ypmc"
                            label="药品姓名"
                            width="220">
                        </el-table-column>
                        <el-table-column
                            prop="gg"
                            label="规格"
                            width="200">
                        </el-table-column>
                        <el-table-column
                            prop="dw"
                            label="单位"
                            width="70">
                        </el-table-column>
                        <el-table-column
                            prop="yf"
                            label="用法"
                            width="70">
                        </el-table-column>
                        <el-table-column
                            prop="yl"
                            label="用量"
                            width="70">
                        </el-table-column>
                        <el-table-column
                            prop="pc"
                            label="频次"
                            width="70">
                        </el-table-column>
                      </el-table>
                    </el-card>
                  </el-container>
                </el-container>
              </el-tab-pane>

              <el-tab-pane label="常用药品*"></el-tab-pane>
              <el-tab-pane label="建议方案*"></el-tab-pane>
              <el-tab-pane label="历史处方*"></el-tab-pane>
            </el-tabs>
          </el-container>
        </el-tab-pane>
        -->

        <el-tab-pane label="费用查询*"></el-tab-pane>
      </el-tabs>

      <el-container style="height: 100%;"></el-container>
    </el-container>
  </el-container>
</template>

<script>
import { defineComponent, ref } from 'vue'
import axios from "axios"
let component = {};
export default defineComponent ({
  setup() {
    return {
      inputPatient: ref('')
    }
  },
  data() {
    return {
      User:'',
      UserID: 0,
      DeptID: 0,
      patientVisible:true,
      centerDialogVisible:false,
      brwzhz:[],
      bryzhz:[],
      kswzhz:[],
      ksyzhz:[],
      patientDetails:{},
      medicalRecordForm: {},
      medicalID: 0,
      medicalDisease:[],
      medicalDiseaseChinese:[],
      checkApply:[],
      checkTemplate:[],
      prescription:[],
      prescriptionChosen:{},
      prescriptionDetailed:[],
      drugsTemplate:[],
      drugsTemplateChosen:{},
      drugsDetailed:[],

      multipleSelection: [],
      activeName: 'first',
      currentRow: null,
      radio: 1,

      jysq:[],
      jycymb:[],
      czsq:[],
      czcymb:[],
      cymzcf:[],
      cycf:[],
      cycfmb:[],
      cycfmbmx:[],
      caoymzcf:[],
      caoycf:[],
      caoycfmb:[],
      caoycfmbmx:[],
      cfmb: [],

      dellist:[],
      addDiagnoseDialog :false,
      queryInfo:{
                query:"",           //查询信息
                pageNum: 1,         //当前页
                pageSize: 5,       //每页最大记录数
      },
      diseaseListData:[],
      total:0,
    }
  },
  created() {
    component = this;
    console.log("！！！！！！！！！！！")
    this.User = JSON.parse(window.sessionStorage.getItem('userid'));
    this.UserID = parseInt(JSON.stringify(this.User.id));
    this.DeptID = parseInt(JSON.stringify(this.User.DeptID));
    this.getPatientList();
  },
  methods: {
    async getPatientList () {
      const { data: res } = await this.$http.post("/clinicDoctor/patientbyUserID",{UserID: this.UserID})
      this.brwzhz = res;
      const { data: res2 } = await this.$http.post("/clinicDoctor/patientDonebyUserID",{UserID: this.UserID})
      this.bryzhz = res2;
      const { data: res3 } = await this.$http.post("/clinicDoctor/patientbyDeptID",{UserID: this.UserID})
      this.kswzhz = res3;
      const { data: res4 } = await this.$http.post("/clinicDoctor/patientDonebyDeptID",{UserID: this.UserID})
      this.ksyzhz = res4;
    },
    refresh(){
      this.getPatientList();
    },
    handleCurrentChange(val) {
      this.patientDetails = val;
      this.centerDialogVisible = true;
    },
    choosePatient(){
      this.centerDialogVisible = false;
      this.patientVisible=!this.patientVisible;
      document.getElementById("patientDetail").innerHTML="患者姓名:"+this.patientDetails.realName+"  病历号:"+this.patientDetails.caseNumber+"  年龄:"+this.patientDetails.age;
      this.getPatientRecord();
    },
    searchPatient(){
      this.inputPatient;
    },
    async getPatientRecord () {
      const { data: res } = await this.$http.post("/clinicDoctor/medicalRecordbyCaseNumber",{CaseNumber: this.patientDetails.caseNumber})
      this.medicalRecordForm = null;
      this.medicalRecordForm = res;
      const { data: res2 } = await this.$http.post("/clinicDoctor/medicalDiseasebyMedicalID", this.medicalRecordForm.id)
      this.medicalDisease = res2;
      const { data: res3 } = await this.$http.post("/clinicDoctor/checkApplybyMedicalID", this.medicalRecordForm.id)
      this.checkApply = res3;
      const { data: res4 } = await this.$http.post("/clinicDoctor/checkTemplatebyUserID", this.UserID)
      this.checkTemplate = res4;
      const { data: res5 } = await this.$http.post("/clinicDoctor/prescriptionbyUserID", this.UserID)
      this.prescription = res5;
      const { data: res6 } = await this.$http.post("/clinicDoctor/drugsTemplatebyUserID", this.UserID)
      this.drugsTemplate = res6;
    },
    async updateMedicalRecord () {
      const { data: res } = this.$http.post("/clinicDoctor/updateMedicalRecord", this.medicalRecordForm);
    },
    submitMedicalRecord (){
      this.$confirm('确认提交?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.medicalRecordForm.caseState = 2;
        this.medicalRecordForm.caseNumber=this.patientDetails.caseNumber;
        this.medicalRecordForm.registID=this.patientDetails.id;
        this.medicalRecordForm.diagnosis = this.medicalDisease[0].diseaseCode;
        console.log(this.medicalRecordForm);
        this.updateMedicalRecord()
            .then(() => {
              this.$message({
                type: 'success',
                message: '已成功提交'
              });
            }).catch((error) => {
              console.log(error);
            })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消提交'
        });
      });
    },
    clearMedicalRecord(){
      this.medicalRecordForm={};
    },
    diagnosisDone(){
      this.$confirm('确认结束诊断?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.medicalRecordForm.caseState = 3;
        this.updateMedicalRecord();
      })
    },
    showResult(){

    },
    prescriptionChange(val){
      this.prescriptionChosen = val;
      this.getPrescriptionDetailed();
    },
    async getPrescriptionDetailed(){
      const { data: res } = this.$http.post("/clinicDoctor/prescriptionDetailedbyPrescriptionID", this.prescriptionChosen.id);
      this.prescriptionDetailed = res;
    },
    drugsTemplateChange(val){
      this.drugsTemplateChosen = val;
      this.getDrugsDetailed();
    },
    async getDrugsDetailed(){
      const { data: res } = this.$http.post("/clinicDoctor/drugsDetailedbyDrugsTempID", this.drugsTemplateChosen.id);
      this.drugsDetailed = res;
    },
    onSubmit() {
      console.log('submit!');
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    handleClick(tab, event) {
      console.log(tab, event);
    },
    storeTemp(){
      window.sessionStorage.setItem("medicalRecord",this.medicalRecordForm);
      this.$message({
                type: 'success',
                message: '已成功提交'
      });
    },
    addCheck(){

    },

    //添加诊断部分
    addDiagnose(){
      this.addDiagnoseDialog = true;
    },
    async getDiseaseList(){
            const {data: res} = await this.$http.get("/allDisease",{params:this.queryInfo});
            this.diseaseListData = res.list;
            this.total = res.total;
    },
    handleSizeChange(newSize){
            this.queryInfo.pageSize= newSize;
            this.getDiseaseList()
        },
    handleCurrentChange(newPage){
            this.queryInfo.pageNum = newPage;
            this.getDiseaseList();
    },
    async addDianoseConfirm(diseaseCode){
      const {data: res} = await this.$http.get("getDisease?disease="+diseaseCode);
      this.medicalDisease.push(res);
      this.addDiagnoseDialog = false;
    },
    //删除诊断
    async deleteDiagnose(id){
      const confirmResult = await this.$confirm('此操作将删除该诊断，是否继续？', '提示',{
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).catch(err =>err)
            if(confirmResult != 'confirm'){
                return this.$message.info("已取消删除");
            }
            delete this.medicalDisease[id];
            this.$message.success("操作成功！");    
    },
  }
})
</script>

<style>

</style>