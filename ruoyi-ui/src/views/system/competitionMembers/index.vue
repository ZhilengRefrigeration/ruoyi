<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="角色编码" prop="roleCode">
        <el-input
          v-model="queryParams.roleCode"
          placeholder="请输入角色编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="比赛ID" prop="competitionId">
        <el-input
          v-model="queryParams.competitionId"
          placeholder="请输入比赛ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="参赛球队id" prop="competitionTeamId">
        <el-input
          v-model="queryParams.competitionTeamId"
          placeholder="请输入参赛球队id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="比赛得分" prop="score">
        <el-input
          v-model="queryParams.score"
          placeholder="请输入比赛得分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="总罚球" prop="penalty">
        <el-input
          v-model="queryParams.penalty"
          placeholder="请输入总罚球"
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
      <el-form-item label="总犯规" prop="breaks">
        <el-input
          v-model="queryParams.breaks"
          placeholder="请输入总犯规"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="总篮板球" prop="rebound">
        <el-input
          v-model="queryParams.rebound"
          placeholder="请输入总篮板球"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="总盖帽" prop="block">
        <el-input
          v-model="queryParams.block"
          placeholder="请输入总盖帽"
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
      <el-form-item label="competition_of_team主键ID" prop="competitionOfTeamId">
        <el-input
          v-model="queryParams.competitionOfTeamId"
          placeholder="请输入competition_of_team主键ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="比赛性质" prop="competitionNature">
        <el-input
          v-model="queryParams.competitionNature"
          placeholder="请输入比赛性质"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="真实姓名" prop="realName">
        <el-input
          v-model="queryParams.realName"
          placeholder="请输入真实姓名"
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
      <el-form-item label="证件号码" prop="idCardNo">
        <el-input
          v-model="queryParams.idCardNo"
          placeholder="请输入证件号码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系电话" prop="contactsTel">
        <el-input
          v-model="queryParams.contactsTel"
          placeholder="请输入联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系人" prop="contacts">
        <el-input
          v-model="queryParams.contacts"
          placeholder="请输入联系人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系人区号" prop="contactsAreaCode">
        <el-input
          v-model="queryParams.contactsAreaCode"
          placeholder="请输入联系人区号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="个人照片" prop="personalPhoto">
        <el-input
          v-model="queryParams.personalPhoto"
          placeholder="请输入个人照片"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="isFirstLaunch">
        <el-input
          v-model="queryParams.isFirstLaunch"
          placeholder="请输入${comment}"
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
          v-hasPermi="['system:competitionMembers:add']"
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
          v-hasPermi="['system:competitionMembers:edit']"
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
          v-hasPermi="['system:competitionMembers:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:competitionMembers:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="competitionMembersList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="角色编码" align="center" prop="roleCode" />
      <el-table-column label="比赛ID" align="center" prop="competitionId" />
      <el-table-column label="参赛球队id" align="center" prop="competitionTeamId" />
      <el-table-column label="比赛得分" align="center" prop="score" />
      <el-table-column label="总罚球" align="center" prop="penalty" />
      <el-table-column label="2分球" align="center" prop="twoPoints" />
      <el-table-column label="3分球" align="center" prop="threePoints" />
      <el-table-column label="总犯规" align="center" prop="breaks" />
      <el-table-column label="总篮板球" align="center" prop="rebound" />
      <el-table-column label="总盖帽" align="center" prop="block" />
      <el-table-column label="是否删除" align="center" prop="isDeleted" />
      <el-table-column label="创建人" align="center" prop="createdBy" />
      <el-table-column label="最后修改人" align="center" prop="modifiedBy" />
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
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="人员类型：0=球员；1=队长" align="center" prop="userType" />
      <el-table-column label="competition_of_team主键ID" align="center" prop="competitionOfTeamId" />
      <el-table-column label="比赛性质" align="center" prop="competitionNature" />
      <el-table-column label="真实姓名" align="center" prop="realName" />
      <el-table-column label="球衣号" align="center" prop="jerseyNumber" />
      <el-table-column label="证件类型" align="center" prop="idType" />
      <el-table-column label="证件号码" align="center" prop="idCardNo" />
      <el-table-column label="联系电话" align="center" prop="contactsTel" />
      <el-table-column label="联系人" align="center" prop="contacts" />
      <el-table-column label="联系人区号" align="center" prop="contactsAreaCode" />
      <el-table-column label="个人照片" align="center" prop="personalPhoto" />
      <el-table-column label="${comment}" align="center" prop="isFirstLaunch" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:competitionMembers:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:competitionMembers:remove']"
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

    <!-- 添加或修改比赛参与人员对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户ID" />
        </el-form-item>
        <el-form-item label="角色编码" prop="roleCode">
          <el-input v-model="form.roleCode" placeholder="请输入角色编码" />
        </el-form-item>
        <el-form-item label="比赛ID" prop="competitionId">
          <el-input v-model="form.competitionId" placeholder="请输入比赛ID" />
        </el-form-item>
        <el-form-item label="参赛球队id" prop="competitionTeamId">
          <el-input v-model="form.competitionTeamId" placeholder="请输入参赛球队id" />
        </el-form-item>
        <el-form-item label="比赛得分" prop="score">
          <el-input v-model="form.score" placeholder="请输入比赛得分" />
        </el-form-item>
        <el-form-item label="总罚球" prop="penalty">
          <el-input v-model="form.penalty" placeholder="请输入总罚球" />
        </el-form-item>
        <el-form-item label="2分球" prop="twoPoints">
          <el-input v-model="form.twoPoints" placeholder="请输入2分球" />
        </el-form-item>
        <el-form-item label="3分球" prop="threePoints">
          <el-input v-model="form.threePoints" placeholder="请输入3分球" />
        </el-form-item>
        <el-form-item label="总犯规" prop="breaks">
          <el-input v-model="form.breaks" placeholder="请输入总犯规" />
        </el-form-item>
        <el-form-item label="总篮板球" prop="rebound">
          <el-input v-model="form.rebound" placeholder="请输入总篮板球" />
        </el-form-item>
        <el-form-item label="总盖帽" prop="block">
          <el-input v-model="form.block" placeholder="请输入总盖帽" />
        </el-form-item>
        <el-form-item label="是否删除" prop="isDeleted">
          <el-input v-model="form.isDeleted" placeholder="请输入是否删除" />
        </el-form-item>
        <el-form-item label="创建人" prop="createdBy">
          <el-input v-model="form.createdBy" placeholder="请输入创建人" />
        </el-form-item>
        <el-form-item label="最后修改人" prop="modifiedBy">
          <el-input v-model="form.modifiedBy" placeholder="请输入最后修改人" />
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
        <el-form-item label="competition_of_team主键ID" prop="competitionOfTeamId">
          <el-input v-model="form.competitionOfTeamId" placeholder="请输入competition_of_team主键ID" />
        </el-form-item>
        <el-form-item label="比赛性质" prop="competitionNature">
          <el-input v-model="form.competitionNature" placeholder="请输入比赛性质" />
        </el-form-item>
        <el-form-item label="真实姓名" prop="realName">
          <el-input v-model="form.realName" placeholder="请输入真实姓名" />
        </el-form-item>
        <el-form-item label="球衣号" prop="jerseyNumber">
          <el-input v-model="form.jerseyNumber" placeholder="请输入球衣号" />
        </el-form-item>
        <el-form-item label="证件号码" prop="idCardNo">
          <el-input v-model="form.idCardNo" placeholder="请输入证件号码" />
        </el-form-item>
        <el-form-item label="联系电话" prop="contactsTel">
          <el-input v-model="form.contactsTel" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="联系人" prop="contacts">
          <el-input v-model="form.contacts" placeholder="请输入联系人" />
        </el-form-item>
        <el-form-item label="联系人区号" prop="contactsAreaCode">
          <el-input v-model="form.contactsAreaCode" placeholder="请输入联系人区号" />
        </el-form-item>
        <el-form-item label="个人照片" prop="personalPhoto">
          <el-input v-model="form.personalPhoto" placeholder="请输入个人照片" />
        </el-form-item>
        <el-form-item label="${comment}" prop="isFirstLaunch">
          <el-input v-model="form.isFirstLaunch" placeholder="请输入${comment}" />
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
import { listCompetitionMembers, getCompetitionMembers, delCompetitionMembers, addCompetitionMembers, updateCompetitionMembers } from "@/api/system/competitionMembers";

export default {
  name: "CompetitionMembers",
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
      // 比赛参与人员表格数据
      competitionMembersList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        roleCode: null,
        competitionId: null,
        competitionTeamId: null,
        score: null,
        penalty: null,
        twoPoints: null,
        threePoints: null,
        breaks: null,
        rebound: null,
        block: null,
        isDeleted: null,
        createdBy: null,
        modifiedBy: null,
        createdTime: null,
        lastUpdatedTime: null,
        status: null,
        userType: null,
        competitionOfTeamId: null,
        competitionNature: null,
        realName: null,
        jerseyNumber: null,
        idType: null,
        idCardNo: null,
        contactsTel: null,
        contacts: null,
        contactsAreaCode: null,
        personalPhoto: null,
        isFirstLaunch: null,
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
    /** 查询比赛参与人员列表 */
    getList() {
      this.loading = true;
      listCompetitionMembers(this.queryParams).then(response => {
        this.competitionMembersList = response.rows;
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
        userId: null,
        roleCode: null,
        competitionId: null,
        competitionTeamId: null,
        score: null,
        penalty: null,
        twoPoints: null,
        threePoints: null,
        breaks: null,
        rebound: null,
        block: null,
        isDeleted: null,
        createdBy: null,
        modifiedBy: null,
        createdTime: null,
        lastUpdatedTime: null,
        status: 0,
        userType: null,
        competitionOfTeamId: null,
        competitionNature: null,
        realName: null,
        jerseyNumber: null,
        idType: null,
        idCardNo: null,
        contactsTel: null,
        contacts: null,
        contactsAreaCode: null,
        personalPhoto: null,
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
      this.title = "添加比赛参与人员";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCompetitionMembers(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改比赛参与人员";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCompetitionMembers(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCompetitionMembers(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除比赛参与人员编号为"' + ids + '"的数据项？').then(function() {
        return delCompetitionMembers(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/competitionMembers/export', {
        ...this.queryParams
      }, `competitionMembers_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
