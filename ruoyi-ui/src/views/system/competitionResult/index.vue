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
      <el-form-item label="赛程id(competition_team_vs_team的ID)" prop="competitionVsId">
        <el-input
          v-model="queryParams.competitionVsId"
          placeholder="请输入赛程id(competition_team_vs_team的ID)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="球队ID" prop="teamId">
        <el-input
          v-model="queryParams.teamId"
          placeholder="请输入球队ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="球队名" prop="teamName">
        <el-input
          v-model="queryParams.teamName"
          placeholder="请输入球队名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="比赛节数1计分" prop="oneNodeScore">
        <el-input
          v-model="queryParams.oneNodeScore"
          placeholder="请输入比赛节数1计分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="比赛节数2计分" prop="twoNodeScore">
        <el-input
          v-model="queryParams.twoNodeScore"
          placeholder="请输入比赛节数2计分"
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
      <el-form-item label="比赛节数3计分" prop="threeNodeScore">
        <el-input
          v-model="queryParams.threeNodeScore"
          placeholder="请输入比赛节数3计分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="比赛节数4计分" prop="fourNodeScore">
        <el-input
          v-model="queryParams.fourNodeScore"
          placeholder="请输入比赛节数4计分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="比赛节数5计分" prop="fiveNodeScore">
        <el-input
          v-model="queryParams.fiveNodeScore"
          placeholder="请输入比赛节数5计分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="比赛节数6计分" prop="sixNodeScore">
        <el-input
          v-model="queryParams.sixNodeScore"
          placeholder="请输入比赛节数6计分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="比赛积分" prop="integral">
        <el-input
          v-model="queryParams.integral"
          placeholder="请输入比赛积分"
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
          v-hasPermi="['system:competitionResult:add']"
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
          v-hasPermi="['system:competitionResult:edit']"
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
          v-hasPermi="['system:competitionResult:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:competitionResult:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="competitionResultList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="赛事id(competition的ID)" align="center" prop="competitionId" />
      <el-table-column label="赛程id(competition_team_vs_team的ID)" align="center" prop="competitionVsId" />
      <el-table-column label="球队ID" align="center" prop="teamId" />
      <el-table-column label="球队名" align="center" prop="teamName" />
      <el-table-column label="比赛节数1计分" align="center" prop="oneNodeScore" />
      <el-table-column label="比赛节数2计分" align="center" prop="twoNodeScore" />
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
      <el-table-column label="比赛节数3计分" align="center" prop="threeNodeScore" />
      <el-table-column label="比赛节数4计分" align="center" prop="fourNodeScore" />
      <el-table-column label="比赛节数5计分" align="center" prop="fiveNodeScore" />
      <el-table-column label="比赛节数6计分" align="center" prop="sixNodeScore" />
      <el-table-column label="比赛积分" align="center" prop="integral" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:competitionResult:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:competitionResult:remove']"
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

    <!-- 添加或修改赛会中-赛程结果记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="赛事id(competition的ID)" prop="competitionId">
          <el-input v-model="form.competitionId" placeholder="请输入赛事id(competition的ID)" />
        </el-form-item>
        <el-form-item label="赛程id(competition_team_vs_team的ID)" prop="competitionVsId">
          <el-input v-model="form.competitionVsId" placeholder="请输入赛程id(competition_team_vs_team的ID)" />
        </el-form-item>
        <el-form-item label="球队ID" prop="teamId">
          <el-input v-model="form.teamId" placeholder="请输入球队ID" />
        </el-form-item>
        <el-form-item label="球队名" prop="teamName">
          <el-input v-model="form.teamName" placeholder="请输入球队名" />
        </el-form-item>
        <el-form-item label="比赛节数1计分" prop="oneNodeScore">
          <el-input v-model="form.oneNodeScore" placeholder="请输入比赛节数1计分" />
        </el-form-item>
        <el-form-item label="比赛节数2计分" prop="twoNodeScore">
          <el-input v-model="form.twoNodeScore" placeholder="请输入比赛节数2计分" />
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
        <el-form-item label="比赛节数3计分" prop="threeNodeScore">
          <el-input v-model="form.threeNodeScore" placeholder="请输入比赛节数3计分" />
        </el-form-item>
        <el-form-item label="比赛节数4计分" prop="fourNodeScore">
          <el-input v-model="form.fourNodeScore" placeholder="请输入比赛节数4计分" />
        </el-form-item>
        <el-form-item label="比赛节数5计分" prop="fiveNodeScore">
          <el-input v-model="form.fiveNodeScore" placeholder="请输入比赛节数5计分" />
        </el-form-item>
        <el-form-item label="比赛节数6计分" prop="sixNodeScore">
          <el-input v-model="form.sixNodeScore" placeholder="请输入比赛节数6计分" />
        </el-form-item>
        <el-form-item label="比赛积分" prop="integral">
          <el-input v-model="form.integral" placeholder="请输入比赛积分" />
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
import { listCompetitionResult, getCompetitionResult, delCompetitionResult, addCompetitionResult, updateCompetitionResult } from "@/api/system/competitionResult";

export default {
  name: "CompetitionResult",
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
      // 赛会中-赛程结果记录表格数据
      competitionResultList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        competitionId: null,
        competitionVsId: null,
        teamId: null,
        teamName: null,
        oneNodeScore: null,
        twoNodeScore: null,
        competitionGroup: null,
        status: null,
        createdTime: null,
        lastUpdatedTime: null,
        createdBy: null,
        modifiedBy: null,
        isDeleted: null,
        threeNodeScore: null,
        fourNodeScore: null,
        fiveNodeScore: null,
        sixNodeScore: null,
        integral: null,
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
    /** 查询赛会中-赛程结果记录列表 */
    getList() {
      this.loading = true;
      listCompetitionResult(this.queryParams).then(response => {
        this.competitionResultList = response.rows;
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
        competitionVsId: null,
        teamId: null,
        teamName: null,
        oneNodeScore: null,
        twoNodeScore: null,
        competitionGroup: null,
        status: 0,
        createdTime: null,
        lastUpdatedTime: null,
        createdBy: null,
        modifiedBy: null,
        isDeleted: null,
        remark: null,
        threeNodeScore: null,
        fourNodeScore: null,
        fiveNodeScore: null,
        sixNodeScore: null,
        integral: null
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
      this.title = "添加赛会中-赛程结果记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCompetitionResult(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改赛会中-赛程结果记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCompetitionResult(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCompetitionResult(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除赛会中-赛程结果记录编号为"' + ids + '"的数据项？').then(function() {
        return delCompetitionResult(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/competitionResult/export', {
        ...this.queryParams
      }, `competitionResult_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
