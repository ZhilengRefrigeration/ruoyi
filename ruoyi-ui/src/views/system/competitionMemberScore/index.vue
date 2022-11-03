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
      <el-form-item label="比赛节数" prop="nodeNum">
        <el-input
          v-model="queryParams.nodeNum"
          placeholder="请输入比赛节数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="球队人员ID" prop="teamUserId">
        <el-input
          v-model="queryParams.teamUserId"
          placeholder="请输入球队人员ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="球衣号" prop="jerseyNumber">
        <el-input
          v-model="queryParams.jerseyNumber"
          placeholder="请输入球衣号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="总得分" prop="totalScore">
        <el-input
          v-model="queryParams.totalScore"
          placeholder="请输入总得分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="2分球" prop="twoPoints">
        <el-input
          v-model="queryParams.twoPoints"
          placeholder="请输入2分球"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="3分球" prop="threePoints">
        <el-input
          v-model="queryParams.threePoints"
          placeholder="请输入3分球"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="罚球" prop="penalty">
        <el-input
          v-model="queryParams.penalty"
          placeholder="请输入罚球"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="篮板" prop="backboard">
        <el-input
          v-model="queryParams.backboard"
          placeholder="请输入篮板"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="前板" prop="frontPlate">
        <el-input
          v-model="queryParams.frontPlate"
          placeholder="请输入前板"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="后板" prop="backPlate">
        <el-input
          v-model="queryParams.backPlate"
          placeholder="请输入后板"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="助攻" prop="assists">
        <el-input
          v-model="queryParams.assists"
          placeholder="请输入助攻"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="抢断" prop="snatch">
        <el-input
          v-model="queryParams.snatch"
          placeholder="请输入抢断"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="盖帽" prop="block">
        <el-input
          v-model="queryParams.block"
          placeholder="请输入盖帽"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="失误" prop="fault">
        <el-input
          v-model="queryParams.fault"
          placeholder="请输入失误"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="犯规" prop="breaks">
        <el-input
          v-model="queryParams.breaks"
          placeholder="请输入犯规"
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
      <el-form-item label="是否首发球员" prop="isFirstLaunch">
        <el-input
          v-model="queryParams.isFirstLaunch"
          placeholder="请输入是否首发球员"
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
          v-hasPermi="['system:competitionMemberScore:add']"
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
          v-hasPermi="['system:competitionMemberScore:edit']"
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
          v-hasPermi="['system:competitionMemberScore:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:competitionMemberScore:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="competitionMemberScoreList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="赛事id(competition的ID)" align="center" prop="competitionId" />
      <el-table-column label="赛程id(competition_team_vs_team的ID)" align="center" prop="competitionVsId" />
      <el-table-column label="球队ID" align="center" prop="teamId" />
      <el-table-column label="球队名" align="center" prop="teamName" />
      <el-table-column label="比赛节数" align="center" prop="nodeNum" />
      <el-table-column label="球队人员ID" align="center" prop="teamUserId" />
      <el-table-column label="球衣号" align="center" prop="jerseyNumber" />
      <el-table-column label="总得分" align="center" prop="totalScore" />
      <el-table-column label="2分球" align="center" prop="twoPoints" />
      <el-table-column label="3分球" align="center" prop="threePoints" />
      <el-table-column label="罚球" align="center" prop="penalty" />
      <el-table-column label="篮板" align="center" prop="backboard" />
      <el-table-column label="前板" align="center" prop="frontPlate" />
      <el-table-column label="后板" align="center" prop="backPlate" />
      <el-table-column label="助攻" align="center" prop="assists" />
      <el-table-column label="抢断" align="center" prop="snatch" />
      <el-table-column label="盖帽" align="center" prop="block" />
      <el-table-column label="失误" align="center" prop="fault" />
      <el-table-column label="犯规" align="center" prop="breaks" />
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
      <el-table-column label="是否首发球员" align="center" prop="isFirstLaunch" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:competitionMemberScore:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:competitionMemberScore:remove']"
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

    <!-- 添加或修改赛会中-赛程-人员得分对话框 -->
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
        <el-form-item label="比赛节数" prop="nodeNum">
          <el-input v-model="form.nodeNum" placeholder="请输入比赛节数" />
        </el-form-item>
        <el-form-item label="球队人员ID" prop="teamUserId">
          <el-input v-model="form.teamUserId" placeholder="请输入球队人员ID" />
        </el-form-item>
        <el-form-item label="球衣号" prop="jerseyNumber">
          <el-input v-model="form.jerseyNumber" placeholder="请输入球衣号" />
        </el-form-item>
        <el-form-item label="总得分" prop="totalScore">
          <el-input v-model="form.totalScore" placeholder="请输入总得分" />
        </el-form-item>
        <el-form-item label="2分球" prop="twoPoints">
          <el-input v-model="form.twoPoints" placeholder="请输入2分球" />
        </el-form-item>
        <el-form-item label="3分球" prop="threePoints">
          <el-input v-model="form.threePoints" placeholder="请输入3分球" />
        </el-form-item>
        <el-form-item label="罚球" prop="penalty">
          <el-input v-model="form.penalty" placeholder="请输入罚球" />
        </el-form-item>
        <el-form-item label="篮板" prop="backboard">
          <el-input v-model="form.backboard" placeholder="请输入篮板" />
        </el-form-item>
        <el-form-item label="前板" prop="frontPlate">
          <el-input v-model="form.frontPlate" placeholder="请输入前板" />
        </el-form-item>
        <el-form-item label="后板" prop="backPlate">
          <el-input v-model="form.backPlate" placeholder="请输入后板" />
        </el-form-item>
        <el-form-item label="助攻" prop="assists">
          <el-input v-model="form.assists" placeholder="请输入助攻" />
        </el-form-item>
        <el-form-item label="抢断" prop="snatch">
          <el-input v-model="form.snatch" placeholder="请输入抢断" />
        </el-form-item>
        <el-form-item label="盖帽" prop="block">
          <el-input v-model="form.block" placeholder="请输入盖帽" />
        </el-form-item>
        <el-form-item label="失误" prop="fault">
          <el-input v-model="form.fault" placeholder="请输入失误" />
        </el-form-item>
        <el-form-item label="犯规" prop="breaks">
          <el-input v-model="form.breaks" placeholder="请输入犯规" />
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
        <el-form-item label="是否首发球员" prop="isFirstLaunch">
          <el-input v-model="form.isFirstLaunch" placeholder="请输入是否首发球员" />
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
import { listCompetitionMemberScore, getCompetitionMemberScore, delCompetitionMemberScore, addCompetitionMemberScore, updateCompetitionMemberScore } from "@/api/system/competitionMemberScore";

export default {
  name: "CompetitionMemberScore",
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
      // 赛会中-赛程-人员得分表格数据
      competitionMemberScoreList: [],
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
        nodeNum: null,
        teamUserId: null,
        jerseyNumber: null,
        totalScore: null,
        twoPoints: null,
        threePoints: null,
        penalty: null,
        backboard: null,
        frontPlate: null,
        backPlate: null,
        assists: null,
        snatch: null,
        block: null,
        fault: null,
        breaks: null,
        createdTime: null,
        lastUpdatedTime: null,
        createdBy: null,
        modifiedBy: null,
        isDeleted: null,
        isFirstLaunch: null
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
    /** 查询赛会中-赛程-人员得分列表 */
    getList() {
      this.loading = true;
      listCompetitionMemberScore(this.queryParams).then(response => {
        this.competitionMemberScoreList = response.rows;
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
        nodeNum: null,
        teamUserId: null,
        jerseyNumber: null,
        totalScore: null,
        twoPoints: null,
        threePoints: null,
        penalty: null,
        backboard: null,
        frontPlate: null,
        backPlate: null,
        assists: null,
        snatch: null,
        block: null,
        fault: null,
        breaks: null,
        createdTime: null,
        lastUpdatedTime: null,
        createdBy: null,
        modifiedBy: null,
        isDeleted: null,
        remark: null,
        isFirstLaunch: null
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
      this.title = "添加赛会中-赛程-人员得分";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCompetitionMemberScore(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改赛会中-赛程-人员得分";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCompetitionMemberScore(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCompetitionMemberScore(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除赛会中-赛程-人员得分编号为"' + ids + '"的数据项？').then(function() {
        return delCompetitionMemberScore(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/competitionMemberScore/export', {
        ...this.queryParams
      }, `competitionMemberScore_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
