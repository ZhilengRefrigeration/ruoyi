<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="主队名" prop="mainTeamName">
        <el-input
          v-model="queryParams.mainTeamName"
          placeholder="请输入主队名"
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
      <el-form-item label="比赛地址" prop="competitionAddress">
        <el-input
          v-model="queryParams.competitionAddress"
          placeholder="请输入比赛地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="赛事联系人" prop="contacts">
        <el-input
          v-model="queryParams.contacts"
          placeholder="请输入赛事联系人"
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
          v-hasPermi="['system:vs:add']"
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
          v-hasPermi="['system:vs:edit']"
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
          v-hasPermi="['system:vs:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:vs:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="vsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="主队名" align="center" prop="mainTeamName" width="180" show-overflow-tooltip/>
      <el-table-column label="客队名" align="center" prop="guestTeamName" width="180" show-overflow-tooltip/>
      <el-table-column label="是否指定对手" align="center" prop="designated" width="110" >
        <template slot-scope="scope">
          <el-tag style="color: crimson" v-if="scope.row.designated==1">是</el-tag>
          <el-tag v-else style="color: darkgrey">否</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="比赛时间" align="center" prop="competitionTime" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.competitionTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="球场名称" align="center" prop="buildingName" width="150" show-overflow-tooltip/>
      <el-table-column label="比赛地址" align="center" prop="competitionAddress" width="150" show-overflow-tooltip/>
      <el-table-column label="比赛状态" align="center" prop="status" width="100" >
        <template slot-scope="scope">
          <el-tag style="color: #0956f3" v-if="scope.row.status==0">约战中</el-tag>
          <el-tag style="color: #60ec10" v-if="scope.row.status==1">已应战</el-tag>
          <el-tag style="color: #6b5c5f" v-if="scope.row.status==2">已结束</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="最大参与人数" align="center" prop="maxPlayer" width="120" />
      <el-table-column label="创建时间" align="center" prop="createdTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注说明" align="center" prop="remark" width="180" show-overflow-tooltip/>
      <el-table-column label="身高隐藏 " align="center" prop="heightHide" width="100" >
        <template slot-scope="scope">
          <el-tag v-if="scope.row.heightHide==1" type='info' >隐藏</el-tag>
          <el-tag v-else type='success' >显示</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:vs:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:vs:remove']"
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

    <!-- 添加或修改约战对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
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
        <el-form-item label="经度" prop="longitude">
          <el-input v-model="form.longitude" placeholder="请输入经度" />
        </el-form-item>
        <el-form-item label="纬度" prop="latitude">
          <el-input v-model="form.latitude" placeholder="请输入纬度" />
        </el-form-item>
        <el-form-item label="备注说明" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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
import { listVs, getVs, delVs, addVs, updateVs } from "@/api/system/vs";

export default {
  name: "Vs",
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
      // 约战表格数据
      vsList: [],
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
        createdTime: null,
        lastUpdatedTime: null,
        createdBy: null,
        modifiedBy: null,
        isDeleted: null,
        longitude: null,
        latitude: null,
        competitionNature: 0,
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
    /** 查询约战列表 */
    getList() {
      this.loading = true;
      listVs(this.queryParams).then(response => {
        this.vsList = response.rows;
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
      this.title = "添加约战";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getVs(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改约战";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateVs(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addVs(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除约战编号为"' + ids + '"的数据项？').then(function() {
        return delVs(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/vs/export', {
        ...this.queryParams
      }, `vs_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
