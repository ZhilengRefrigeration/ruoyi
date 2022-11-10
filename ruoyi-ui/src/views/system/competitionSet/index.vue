<template>
  <div>
    <el-divider content-position="left" style="font-weight: bold">赛会设置
    </el-divider>
    <el-button type="primary" @click="close()" style="position: absolute;margin-left: 750px;margin-top: -40px" size="small">返回</el-button>
  <el-tabs type="border-card" tab-position="left" v-model="activeName" @tab-click="handleTagClick" style="height: 900px">
    <el-tab-pane name="competitionInfo"  label="赛会信息">
      <span slot="label"><i class="el-icon-document"></i>赛会信息</span>
      <el-descriptions style="margin-left: 20px" :column="2">
        <el-descriptions-item label="赛会名称">{{competitionObj.competitionName}}</el-descriptions-item>
        <el-descriptions-item label="赛会审核状态">
          <el-tag size="small" v-if="competitionObj.auditStatus==1" type='success' >通过</el-tag>
          <el-tag size="small" v-if="competitionObj.auditStatus==0" type='info' >待审核</el-tag>
          <el-tag size="small" v-if="competitionObj.auditStatus==-1" type='danger' >未通过</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="赛会状态">
            <el-tag size="small">
              <dict-tag  :options="dict.type.competition_status" :value="competitionObj.status"/>
            </el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="比赛赛制">
          <el-tag size="small" v-if="competitionObj.competitionType==1">一人制</el-tag>
          <el-tag size="small" v-if="competitionObj.competitionType==2">二人制</el-tag>
          <el-tag size="small" v-if="competitionObj.competitionType==3">三人制</el-tag>
          <el-tag size="small" v-if="competitionObj.competitionType==4">四人制</el-tag>
          <el-tag size="small" v-if="competitionObj.competitionType==5">五人制</el-tag>
          <el-tag size="small" v-if="competitionObj.competitionType==6">六人制</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="身高隐藏">
          <el-tag size="small" v-if="competitionObj.heightHide==1" type='info' >隐藏</el-tag>
          <el-tag size="small" v-else type='success' >显示</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="报名起止时间">{{competitionObj.enrollBeginTime}}-{{competitionObj.enrollEndTime}}</el-descriptions-item>
        <el-descriptions-item label="比赛起止时间">{{competitionObj.competitionBeginTime}}-{{competitionObj.competitionEndTime}}</el-descriptions-item>
        <el-descriptions-item label="比赛主办方">{{competitionObj.organizer}}</el-descriptions-item>
        <el-descriptions-item label="比赛承办商">{{competitionObj.undertake}}</el-descriptions-item>
        <el-descriptions-item label="赛事联系人">{{competitionObj.contacts}}</el-descriptions-item>
        <el-descriptions-item label="联系人电话">{{competitionObj.contactsTel}}</el-descriptions-item>
        <el-descriptions-item label="赛会地址" >
          {{competitionObj.competitionAddress}}
        </el-descriptions-item>
        <el-descriptions-item label="赛会图片">
          <el-image fit="contain"
            style="width: 400px; height: 400px"
            :src="competitionObj.competitionBackImg"
            :preview-src-list="[competitionObj.competitionBackImg]">
          </el-image>
        </el-descriptions-item>
        <el-descriptions-item label="比赛说明" style="width: 150px;overflow:hidden;">
          <span > {{competitionObj.remark}}</span>
        </el-descriptions-item>
      </el-descriptions>

    </el-tab-pane>
    <el-tab-pane label="球队审核" name="competitionTeamApprove"><span slot="label"><i class="el-icon-s-check"></i> 球队审核</span>
      <el-table v-loading="loading" :data="competitionOfTeamList" @selection-change="handleSelectionChange">
        <el-table-column label="球队ID" align="center" prop="teamId" />
        <el-table-column label="球队名" align="center" prop="teamName" />
        <el-table-column label="球队所属的组" align="center" prop="competitionGroup" />
        <el-table-column label="创建时间" align="center" prop="createdTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="状态" align="center" prop="status" >
          <template slot-scope="scope">
            <el-tag v-if="scope.row.status==0">申请</el-tag>
            <el-tag v-if="scope.row.status==1">同意</el-tag>
            <el-tag v-if="scope.row.status==-1">驳回</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="联系人" align="center" prop="contacts" />
        <el-table-column label="联系人电话" align="center" prop="contactsTel" />
        <el-table-column label="组内的序号" align="center" prop="serialNumber" />
        <el-table-column label="备注说明" align="center" prop="remark" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-popconfirm  v-if="competitionObj.status==0" @confirm="bindConfirm(scope.row.id,1)"
              title="你确定同意此球队加入赛会吗？"
            >
              <el-button
                slot="reference"
                size="mini"
                type="text"
                icon="el-icon-success"
                v-hasPermi="['system:competitionOfTeam:edit']"
              >同意</el-button>
            </el-popconfirm>
            <el-popconfirm  v-if="competitionObj.status==0" @confirm="bindConfirm(scope.row.id,-1)"
              title="你确定不同意此球队加入赛会吗？"
            >
            <el-button
              slot="reference"
              size="mini"
              type="text"
              icon="el-icon-info"
              v-hasPermi="['system:competitionOfTeam:remove']"
            >驳回</el-button>
            </el-popconfirm>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-s-custom"
              @click="handleTeamUser(scope.row)"
              v-hasPermi="['system:competitionOfTeam:remove']"
            >球队成员</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-tab-pane>
    <el-tab-pane label="球队分组" name="competitionTeamGroup"> <span slot="label"><i class="el-icon-film"></i> 球队分组</span> 球队分组</el-tab-pane>
    <el-tab-pane label="赛程设置" name="competitionVsSet"> <span slot="label"><i class="el-icon-c-scale-to-original"></i> 赛程设置</span> 赛程设置</el-tab-pane>
    <el-tab-pane label="赛会推广" name="competitionSpread"> <span slot="label"><i class="el-icon-s-promotion"></i> 赛会推广</span> 赛会推广</el-tab-pane>
  </el-tabs>

    <el-drawer
      title="球队成员"
      :visible.sync="drawer"
      direction="ltr"
      size="80%">
      <el-table :data="competitionMembersList" @selection-change="handleSelectionChange">
        <el-table-column label="个人照片" align="center" prop="personalPhoto" >
          <template slot-scope="scope">
            <el-image
              style=" height: 100px"
              :src="scope.row.personalPhoto"
              :preview-src-list="[scope.row.personalPhoto]"
              fit="contain"></el-image>
          </template>
        </el-table-column>
        <el-table-column label="真实姓名" align="center" prop="realName" />
        <el-table-column label="球衣号" align="center" prop="jerseyNumber" />
        <el-table-column label="证件类型" align="center" prop="idType" />
        <el-table-column label="证件号码" align="center" prop="idCardNo" />
        <el-table-column label="联系电话" align="center" prop="contactsTel" />
        <el-table-column label="比赛得分" align="center" prop="score" />
        <el-table-column label="总罚球" align="center" prop="penalty" />
        <el-table-column label="2分球" align="center" prop="twoPoints" />
        <el-table-column label="3分球" align="center" prop="threePoints" />
        <el-table-column label="总犯规" align="center" prop="breaks" />
        <el-table-column label="总篮板球" align="center" prop="rebound" />
        <el-table-column label="总盖帽" align="center" prop="block" />
        <el-table-column label="状态" align="center" prop="status" >
          <template slot-scope="scope">
            <el-tag v-if="scope.row.status==0">报名加入中</el-tag>
            <el-tag v-if="scope.row.status==1">同意加入</el-tag>
            <el-tag v-if="scope.row.status==-1">不同意加入</el-tag>
          </template>
        </el-table-column>
        <el-table-column label="人员类型" align="center" prop="userType" >
          <template slot-scope="scope">
            <el-tag v-if="scope.row.userType==0">球员</el-tag>
            <el-tag v-if="scope.row.userType==1">队长</el-tag>
          </template>
        </el-table-column>
      </el-table>
    </el-drawer>
  </div>
</template>

<script>
import { listCompetition, getCompetition, delCompetition, addCompetition, updateCompetition } from "@/api/system/competition";
import { listCompetitionOfTeam, getCompetitionOfTeam, delCompetitionOfTeam, addCompetitionOfTeam, updateCompetitionOfTeam } from "@/api/system/competitionOfTeam";
import { listCompetitionMembers, getCompetitionMembers, delCompetitionMembers, addCompetitionMembers, updateCompetitionMembers } from "@/api/system/competitionMembers";

export default {
  name: "CompetitionSet",
  dicts: ['competition_status'],
  data() {
    return {
      imgfit:"fill",
      drawer:false,
      activeName:"competitionInfo",
      // 遮罩层
      loading: true,
      // 比赛信息表格数据
      competitionObj: {},
      competitionOfTeamList:[],
      competitionMembersList:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      // 表单参数
      rules: {
      }
    };
  },
  created() {
    const id = this.$route.params && this.$route.params.id;
    if (id) {
      this.loading = true;
      getCompetition(id).then((response) => {
        this.competitionObj = response.data;
        this.loading = false;
      });
    }
  },
  methods: {
    onSubmit() {
      console.log('submit!');
    },
    /** 查询比赛信息列表 */
    getList() {
      this.loading = true;
      listCompetition(this.queryParams).then(response => {
        this.competitionList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    bindConfirm(id,tage){
      console.info(id)
      console.info(tage)
      updateCompetitionOfTeam({"id":id,"status":tage}).then(response => {

      });
    },
    handleTeamUser(row){
      this.drawer = true
      listCompetitionMembers({"competitionId":this.competitionObj.id,"competitionTeamId":row.teamId}).then(response => {
         this.competitionMembersList =  response.rows;
      });
    },
    // 表单重置
    reset() {

    },
    handleTagClick(tab, event){
      console.info(tab.name)
      if(tab.name=='competitionTeamApprove'){
        listCompetitionOfTeam({"competitionId":this.competitionObj.id}).then(response => {
          this.competitionOfTeamList = response.rows;
        });
      }else if(tab.name=='competitionTeamGroup'){

      }else if(tab.name=='competitionVsSet'){

      }else if(tab.name=='competitionSpread'){

      }
    },
    /** 关闭按钮 */
    close() {
      const obj = { path: "/wechat/competition" };
      this.$tab.closeOpenPage(obj);
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加比赛信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCompetition(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改比赛信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCompetition(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCompetition(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除比赛信息编号为"' + ids + '"的数据项？').then(function() {
        return delCompetition(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/competition/export', {
        ...this.queryParams
      }, `competition_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
