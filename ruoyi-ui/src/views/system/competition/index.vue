<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="比赛名称" prop="competitionName">
        <el-input
          v-model="queryParams.competitionName"
          placeholder="请输入比赛名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="球场名称" prop="buildingName">
        <el-input
          v-model="queryParams.buildingName"
          placeholder="请输入球场名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="比赛状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择比赛状态" clearable>
          <el-option
            v-for="dict in dict.type.competition_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="比赛地址" prop="competitionAddress">
        <el-input
          v-model="queryParams.competitionAddress"
          placeholder="请输入比赛地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系人" prop="contacts">
        <el-input
          v-model="queryParams.contacts"
          placeholder="请输入赛事联系人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="主办方" prop="organizer">
        <el-input
          v-model="queryParams.organizer"
          placeholder="请输入主办方"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="赞助商" prop="sponsor">
        <el-input
          v-model="queryParams.sponsor"
          placeholder="请输入赞助商"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:competition:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:competition:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:competition:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:competition:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="competitionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="35" align="center" />
      <el-table-column label="id" align="center" prop="id" width="55" />
      <el-table-column label="赛会背景图" align="center" prop="competitionBackImg" width="150" >
        <template slot-scope="scope">
          <el-image
            style="width: 200px; height: 100px"
            :src="scope.row.competitionBackImg"
            :preview-src-list="[scope.row.competitionBackImg]"
            :fit="imgfit"></el-image>
        </template>
      </el-table-column>
      <el-table-column label="比赛名称" align="center" prop="competitionName" width="200" show-overflow-tooltip="true"/>
      <el-table-column label="比赛状态" align="center" prop="status" width="100">
        <template slot-scope="scope">
          <el-tag>
            <dict-tag :options="dict.type.competition_status" :value="scope.row.status"/>
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="审核状态" align="center" prop="auditStatus" width="100" >
        <template slot-scope="scope">
          <el-tag v-if="scope.row.auditStatus==1" type='success' >通过</el-tag>
          <el-tag v-if="scope.row.auditStatus==0" type='info' >待审核</el-tag>
          <el-tag v-if="scope.row.auditStatus==-1" type='danger' >未通过</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="比赛赛制" align="center" prop="competitionType" width="100">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.competitionType==1">一人制</el-tag>
          <el-tag v-if="scope.row.competitionType==2">二人制</el-tag>
          <el-tag v-if="scope.row.competitionType==3">三人制</el-tag>
          <el-tag v-if="scope.row.competitionType==4">四人制</el-tag>
          <el-tag v-if="scope.row.competitionType==5">五人制</el-tag>
          <el-tag v-if="scope.row.competitionType==6">六人制</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="报名开始时间" align="center" prop="enrollBeginTime" width="110">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.enrollBeginTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="报名结束时间" align="center" prop="enrollEndTime" width="110">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.enrollEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="赛事联系人" align="center" prop="contacts" width="100"/>
      <el-table-column label="联系人电话" align="center" prop="contactsTel" width="120"/>
      <el-table-column label="比赛开始时间" align="center" prop="competitionBeginTime" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.competitionBeginTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="比赛结束时间" align="center" prop="competitionEndTime" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.competitionEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="比赛地址" align="center" prop="competitionAddress"  show-overflow-tooltip="true" width="180"/>
      <el-table-column label="备注说明" align="center" prop="remark" show-overflow-tooltip="true"  width="210"/>
      <el-table-column label="身高隐藏" align="center" prop="heightHide" width="100">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.heightHide==1" type='info' >隐藏</el-tag>
          <el-tag v-else type='success' >显示</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="主办方" align="center" prop="organizer" width="180" show-overflow-tooltip="true"/>
      <el-table-column label="承办方" align="center" prop="undertake" width="180" show-overflow-tooltip="true"/>
      <el-table-column label="赞助商" align="center" prop="sponsor" width="100" show-overflow-tooltip="true"/>
      <el-table-column label="创建时间" align="center" prop="createdTime" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:competition:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:competition:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改比赛信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="主队ID" prop="mainTeamId">
          <el-input v-model="form.mainTeamId" placeholder="请输入主队ID" />
        </el-form-item>
        <el-form-item label="主队名" prop="mainTeamName">
          <el-input v-model="form.mainTeamName" placeholder="请输入主队名" />
        </el-form-item>
        <el-form-item label="客队ID" prop="guestTeamId">
          <el-input v-model="form.guestTeamId" placeholder="请输入客队ID" />
        </el-form-item>
        <el-form-item label="客队名" prop="guestTeamName">
          <el-input v-model="form.guestTeamName" placeholder="请输入客队名" />
        </el-form-item>
        <el-form-item label="赛事编号" prop="competitionCode">
          <el-input v-model="form.competitionCode" placeholder="请输入赛事编号" />
        </el-form-item>
        <el-form-item label="比赛名称" prop="competitionName">
          <el-input v-model="form.competitionName" placeholder="请输入比赛名称" />
        </el-form-item>
        <el-form-item label="是否指定对手" prop="designated">
          <el-input v-model="form.designated" placeholder="请输入是否指定对手" />
        </el-form-item>
        <el-form-item label="比赛时间" prop="competitionTime">
          <el-date-picker clearable
            v-model="form.competitionTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择比赛时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="球场ID" prop="buildingId">
          <el-input v-model="form.buildingId" placeholder="请输入球场ID" />
        </el-form-item>
        <el-form-item label="球场名称" prop="buildingName">
          <el-input v-model="form.buildingName" placeholder="请输入球场名称" />
        </el-form-item>
        <el-form-item label="比赛地址" prop="competitionAddress">
          <el-input v-model="form.competitionAddress" placeholder="请输入比赛地址" />
        </el-form-item>
        <el-form-item label="发起人ID" prop="founder">
          <el-input v-model="form.founder" placeholder="请输入发起人ID" />
        </el-form-item>
        <el-form-item label="城市编码" prop="cityCode">
          <el-input v-model="form.cityCode" placeholder="请输入城市编码" />
        </el-form-item>
        <el-form-item label="城市名称" prop="cityName">
          <el-input v-model="form.cityName" placeholder="请输入城市名称" />
        </el-form-item>
        <el-form-item label="最大参与人数" prop="maxPlayer">
          <el-input v-model="form.maxPlayer" placeholder="请输入最大参与人数" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createdTime">
          <el-date-picker clearable
            v-model="form.createdTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="最后修改时间" prop="lastUpdatedTime">
          <el-date-picker clearable
            v-model="form.lastUpdatedTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择最后修改时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="创建人" prop="createdBy">
          <el-input v-model="form.createdBy" placeholder="请输入创建人" />
        </el-form-item>
        <el-form-item label="最后修改人" prop="modifiedBy">
          <el-input v-model="form.modifiedBy" placeholder="请输入最后修改人" />
        </el-form-item>
        <el-form-item label="是否删除" prop="isDeleted">
          <el-input v-model="form.isDeleted" placeholder="请输入是否删除" />
        </el-form-item>
        <el-form-item label="经度" prop="longitude">
          <el-input v-model="form.longitude" placeholder="请输入经度" />
        </el-form-item>
        <el-form-item label="纬度" prop="latitude">
          <el-input v-model="form.latitude" placeholder="请输入纬度" />
        </el-form-item>
        <el-form-item label="备注说明" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="比赛性质" prop="competitionNature">
          <el-input v-model="form.competitionNature" placeholder="请输入比赛性质" />
        </el-form-item>
        <el-form-item label="报名开始时间" prop="enrollBeginTime">
          <el-date-picker clearable
            v-model="form.enrollBeginTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择报名开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="报名结束时间" prop="enrollEndTime">
          <el-date-picker clearable
            v-model="form.enrollEndTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择报名结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="赛事联系人" prop="contacts">
          <el-input v-model="form.contacts" placeholder="请输入赛事联系人" />
        </el-form-item>
        <el-form-item label="赛事联系人电话区号" prop="contactsAreaCode">
          <el-input v-model="form.contactsAreaCode" placeholder="请输入赛事联系人电话区号" />
        </el-form-item>
        <el-form-item label="赛事联系人电话" prop="contactsTel">
          <el-input v-model="form.contactsTel" placeholder="请输入赛事联系人电话" />
        </el-form-item>
        <el-form-item label="比赛开始时间" prop="competitionBeginTime">
          <el-date-picker clearable
            v-model="form.competitionBeginTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择比赛开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="比赛结束时间" prop="competitionEndTime">
          <el-date-picker clearable
            v-model="form.competitionEndTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择比赛结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="主办方" prop="organizer">
          <el-input v-model="form.organizer" placeholder="请输入主办方" />
        </el-form-item>
        <el-form-item label="承办方" prop="undertake">
          <el-input v-model="form.undertake" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="赛会背景图" prop="competitionBackImg">
          <el-input v-model="form.competitionBackImg" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="创建人userId" prop="createdId">
          <el-input v-model="form.createdId" placeholder="请输入创建人userId" />
        </el-form-item>
        <el-form-item label="身高隐藏  0不隐藏 1=隐藏" prop="heightHide">
          <el-input v-model="form.heightHide" placeholder="请输入身高隐藏  0不隐藏 1=隐藏" />
        </el-form-item>
        <el-form-item label="赞助商" prop="sponsor">
          <el-input v-model="form.sponsor" placeholder="请输入赞助商" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCompetition, getCompetition, delCompetition, addCompetition, updateCompetition } from "@/api/system/competition";

export default {
  name: "Competition",
  dicts: ['competition_status'],
  data() {
    return {
      imgfit:"fill",
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],

      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 比赛信息表格数据
      competitionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
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
        status: null,
        cityCode: null,
        cityName: null,
        maxPlayer: null,
        createdBy: null,
        modifiedBy: null,
        competitionNature: 1,
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
        auditStatus: null,
        heightHide: null,
        sponsor: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
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
