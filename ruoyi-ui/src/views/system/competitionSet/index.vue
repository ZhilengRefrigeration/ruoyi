<template>
  <div>
    <el-divider content-position="left" style="font-weight: bold">赛会信息
    </el-divider>
    <el-button type="primary" @click="close()" style="position: absolute;margin-left: 750px;margin-top: -40px" size="small">返回</el-button>
    <el-descriptions style="margin-left: 20px" :column="4">
      <el-descriptions-item label="赛会名称">{{competitionObj.competitionName}}</el-descriptions-item>
      <el-descriptions-item label="赛会状态">
        <el-tag size="small" v-if="competitionObj.auditStatus==1" type='success' >通过</el-tag>
        <el-tag size="small" v-if="competitionObj.auditStatus==0" type='info' >待审核</el-tag>
        <el-tag size="small" v-if="competitionObj.auditStatus==-1" type='danger' >未通过</el-tag>
      </el-descriptions-item>
      <el-descriptions-item label="比赛赛制">
        <el-tag size="small" v-if="competitionObj.competitionType==1">一人制</el-tag>
        <el-tag size="small" v-if="competitionObj.competitionType==2">二人制</el-tag>
        <el-tag size="small" v-if="competitionObj.competitionType==3">三人制</el-tag>
        <el-tag size="small" v-if="competitionObj.competitionType==4">四人制</el-tag>
        <el-tag size="small" v-if="competitionObj.competitionType==5">五人制</el-tag>
        <el-tag size="small" v-if="competitionObj.competitionType==6">六人制</el-tag>
      </el-descriptions-item>
      <el-descriptions-item label="报名起止时间">开始报名时间</el-descriptions-item>
      <el-descriptions-item label="比赛起止时间">开始报名时间</el-descriptions-item>
      <el-descriptions-item label="比赛地址">开始报名时间</el-descriptions-item>
      <el-descriptions-item label="比赛主办方">{{competitionObj.organizer}}</el-descriptions-item>
      <el-descriptions-item label="比赛承办商">{{competitionObj.undertake}}</el-descriptions-item>
      <el-descriptions-item label="赛事联系人">{{competitionObj.contacts}}</el-descriptions-item>
      <el-descriptions-item label="联系人电话">{{competitionObj.contactsTel}}</el-descriptions-item>
      <el-descriptions-item label="比赛说明" style="width: 150px;overflow:hidden;">
        <span style="width: 300px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;"> {{competitionObj.remark}}</span>
      </el-descriptions-item>
      <el-descriptions-item label="赛会地址" >
        {{competitionObj.competitionAddress}}
      </el-descriptions-item>
    </el-descriptions>
  <el-tabs type="border-card" tab-position="left" style="height: 900px">
    <el-tab-pane>
      <span slot="label"><i class="el-icon-s-tools"></i> 赛会设置</span>赛会设置
    </el-tab-pane>
    <el-tab-pane label="球队审核"><span slot="label"><i class="el-icon-s-check"></i> 球队审核</span> 球队审核 </el-tab-pane>
    <el-tab-pane label="球队分组"> <span slot="label"><i class="el-icon-film"></i> 球队分组</span> 球队分组</el-tab-pane>
    <el-tab-pane label="赛程设置"> <span slot="label"><i class="el-icon-c-scale-to-original"></i> 赛程设置</span> 赛程设置</el-tab-pane>
    <el-tab-pane label="赛会推广"> <span slot="label"><i class="el-icon-s-promotion"></i> 赛会推广</span> 赛会推广</el-tab-pane>
  </el-tabs>
  </div>
</template>

<script>
import { listCompetition, getCompetition, delCompetition, addCompetition, updateCompetition } from "@/api/system/competition";
export default {
  name: "CompetitionSet",
  dicts: ['competition_status'],
  data() {
    return {
      imgfit:"fill",
      // 遮罩层
      loading: true,
      // 比赛信息表格数据
      competitionObj: {},
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
    // 表单重置
    reset() {
      this.form = {
        id: null,
        mainTeamId: null,
        mainTeamName: null,
        guestTeamId: null,
        guestTeamName: null,
        competitionCode: null,
        competitionName: null,
        designated: null,
        competitionType: null,
        competitionTime: null,
        buildingId: null,
        buildingName: null,
        competitionAddress: null,
        founder: null,
        status: 0,
        cityCode: null,
        cityName: null,
        maxPlayer: null,
        createdTime: null,
        lastUpdatedTime: null,
        createdBy: null,
        modifiedBy: null,
        isDeleted: null,
        longitude: null,
        latitude: null,
        remark: null,
        competitionNature: null,
        enrollBeginTime: null,
        enrollEndTime: null,
        contacts: null,
        contactsAreaCode: null,
        contactsTel: null,
        competitionBeginTime: null,
        competitionEndTime: null,
        organizer: null,
        undertake: null,
        competitionBackImg: null,
        createdId: null,
        auditStatus: 0,
        heightHide: null,
        sponsor: null
      };
      this.resetForm("form");
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
