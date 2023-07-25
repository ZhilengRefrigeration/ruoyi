<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="赛事id(competition的ID)" prop="competitionId">
        <el-input
          v-model="queryParams.competitionId"
          placeholder="请输入赛事id(competition的ID)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="主队ID" prop="mainTeamId">
        <el-input
          v-model="queryParams.mainTeamId"
          placeholder="请输入主队ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="主队名" prop="mainTeamName">
        <el-input
          v-model="queryParams.mainTeamName"
          placeholder="请输入主队名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客队ID" prop="guestTeamId">
        <el-input
          v-model="queryParams.guestTeamId"
          placeholder="请输入客队ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客队名" prop="guestTeamName">
        <el-input
          v-model="queryParams.guestTeamName"
          placeholder="请输入客队名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="比赛时间" prop="competitionTime">
        <el-date-picker clearable
          v-model="queryParams.competitionTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择比赛时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="球场ID" prop="buildingId">
        <el-input
          v-model="queryParams.buildingId"
          placeholder="请输入球场ID"
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
      <el-form-item label="比赛地址" prop="competitionAddress">
        <el-input
          v-model="queryParams.competitionAddress"
          placeholder="请输入比赛地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="比赛分组(A,B,C)" prop="competitionGroup">
        <el-input
          v-model="queryParams.competitionGroup"
          placeholder="请输入比赛分组(A,B,C)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createdTime">
        <el-date-picker clearable
          v-model="queryParams.createdTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="最后修改时间" prop="lastUpdatedTime">
        <el-date-picker clearable
          v-model="queryParams.lastUpdatedTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择最后修改时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="创建人" prop="createdBy">
        <el-input
          v-model="queryParams.createdBy"
          placeholder="请输入创建人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最后修改人" prop="modifiedBy">
        <el-input
          v-model="queryParams.modifiedBy"
          placeholder="请输入最后修改人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否删除" prop="isDeleted">
        <el-input
          v-model="queryParams.isDeleted"
          placeholder="请输入是否删除"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="主队得分" prop="mainTeamScore">
        <el-input
          v-model="queryParams.mainTeamScore"
          placeholder="请输入主队得分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客队得分" prop="guestTeamScore">
        <el-input
          v-model="queryParams.guestTeamScore"
          placeholder="请输入客队得分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="系统生成的赛程的批次号" prop="batchNumber">
        <el-input
          v-model="queryParams.batchNumber"
          placeholder="请输入系统生成的赛程的批次号"
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
          v-hasPermi="['system:competitionTeamVsTeam:add']"
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
          v-hasPermi="['system:competitionTeamVsTeam:edit']"
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
          v-hasPermi="['system:competitionTeamVsTeam:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:competitionTeamVsTeam:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="competitionTeamVsTeamList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="赛事id(competition的ID)" align="center" prop="competitionId" />
      <el-table-column label="主队ID" align="center" prop="mainTeamId" />
      <el-table-column label="主队名" align="center" prop="mainTeamName" />
      <el-table-column label="客队ID" align="center" prop="guestTeamId" />
      <el-table-column label="客队名" align="center" prop="guestTeamName" />
      <el-table-column label="比赛时间" align="center" prop="competitionTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.competitionTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="球场ID" align="center" prop="buildingId" />
      <el-table-column label="球场名称" align="center" prop="buildingName" />
      <el-table-column label="比赛地址" align="center" prop="competitionAddress" />
      <el-table-column label="比赛分组(A,B,C)" align="center" prop="competitionGroup" />
      <el-table-column label="状态-1=已取消； 0=报名中，1=比赛中；2=已结束" align="center" prop="status" />
      <el-table-column label="创建时间" align="center" prop="createdTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="最后修改时间" align="center" prop="lastUpdatedTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lastUpdatedTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建人" align="center" prop="createdBy" />
      <el-table-column label="最后修改人" align="center" prop="modifiedBy" />
      <el-table-column label="是否删除" align="center" prop="isDeleted" />
      <el-table-column label="备注说明" align="center" prop="remark" />
      <el-table-column label="主队得分" align="center" prop="mainTeamScore" />
      <el-table-column label="客队得分" align="center" prop="guestTeamScore" />
      <el-table-column label="比赛类型：循环赛，淘汰赛" align="center" prop="vsType" />
      <el-table-column label="系统生成的赛程的批次号" align="center" prop="batchNumber" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:competitionTeamVsTeam:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:competitionTeamVsTeam:remove']"
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

    <!-- 添加或修改赛会中-球队VS球队关系对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="赛事id(competition的ID)" prop="competitionId">
          <el-input v-model="form.competitionId" placeholder="请输入赛事id(competition的ID)" />
        </el-form-item>
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
        <el-form-item label="比赛分组(A,B,C)" prop="competitionGroup">
          <el-input v-model="form.competitionGroup" placeholder="请输入比赛分组(A,B,C)" />
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
        <el-form-item label="备注说明" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="主队得分" prop="mainTeamScore">
          <el-input v-model="form.mainTeamScore" placeholder="请输入主队得分" />
        </el-form-item>
        <el-form-item label="客队得分" prop="guestTeamScore">
          <el-input v-model="form.guestTeamScore" placeholder="请输入客队得分" />
        </el-form-item>
        <el-form-item label="系统生成的赛程的批次号" prop="batchNumber">
          <el-input v-model="form.batchNumber" placeholder="请输入系统生成的赛程的批次号" />
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
import { listCompetitionTeamVsTeam, getCompetitionTeamVsTeam, delCompetitionTeamVsTeam, addCompetitionTeamVsTeam, updateCompetitionTeamVsTeam } from "@/api/system/competitionTeamVsTeam";

export default {
  name: "CompetitionTeamVsTeam",
  data() {
    return {
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
      // 赛会中-球队VS球队关系表格数据
      competitionTeamVsTeamList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        competitionId: null,
        mainTeamId: null,
        mainTeamName: null,
        guestTeamId: null,
        guestTeamName: null,
        competitionTime: null,
        buildingId: null,
        buildingName: null,
        competitionAddress: null,
        competitionGroup: null,
        status: null,
        createdTime: null,
        lastUpdatedTime: null,
        createdBy: null,
        modifiedBy: null,
        isDeleted: null,
        mainTeamScore: null,
        guestTeamScore: null,
        vsType: null,
        batchNumber: null,
        orderByColumn:"id",
        isAsc:"desc",
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
    /** 查询赛会中-球队VS球队关系列表 */
    getList() {
      this.loading = true;
      listCompetitionTeamVsTeam(this.queryParams).then(response => {
        this.competitionTeamVsTeamList = response.rows;
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
        competitionId: null,
        mainTeamId: null,
        mainTeamName: null,
        guestTeamId: null,
        guestTeamName: null,
        competitionTime: null,
        buildingId: null,
        buildingName: null,
        competitionAddress: null,
        competitionGroup: null,
        status: 0,
        createdTime: null,
        lastUpdatedTime: null,
        createdBy: null,
        modifiedBy: null,
        isDeleted: null,
        remark: null,
        mainTeamScore: null,
        guestTeamScore: null,
        vsType: null,
        batchNumber: null
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
      this.title = "添加赛会中-球队VS球队关系";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCompetitionTeamVsTeam(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改赛会中-球队VS球队关系";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCompetitionTeamVsTeam(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCompetitionTeamVsTeam(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除赛会中-球队VS球队关系编号为"' + ids + '"的数据项？').then(function() {
        return delCompetitionTeamVsTeam(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/competitionTeamVsTeam/export', {
        ...this.queryParams
      }, `competitionTeamVsTeam_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
