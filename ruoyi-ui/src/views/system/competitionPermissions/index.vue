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
      <el-form-item label="用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户手机号" prop="userTel">
        <el-input
          v-model="queryParams.userTel"
          placeholder="请输入用户手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户姓名" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态(sys_data_status)" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态(sys_data_status)" clearable>
          <el-option
            v-for="dict in dict.type.sys_data_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="是否删除" prop="isDeleted">
        <el-input
          v-model="queryParams.isDeleted"
          placeholder="请输入是否删除"
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
          v-hasPermi="['system:competitionPermissions:add']"
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
          v-hasPermi="['system:competitionPermissions:edit']"
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
          v-hasPermi="['system:competitionPermissions:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:competitionPermissions:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"  :pageName="$options.name" ></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="competitionPermissionsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id"  v-if="columns[0].visible" show-overflow-tooltip />
      <el-table-column label="赛事id(competition的ID)" align="center" prop="competitionId" v-if="columns[2].visible" show-overflow-tooltip />
      <el-table-column label="用户ID" align="center" prop="userId" v-if="columns[3].visible" show-overflow-tooltip />
      <el-table-column label="用户手机号" align="center" prop="userTel" v-if="columns[4].visible" show-overflow-tooltip />
      <el-table-column label="用户姓名" align="center" prop="userName" v-if="columns[5].visible" show-overflow-tooltip />
      <el-table-column label="状态(sys_data_status)" align="center" prop="status" v-if="columns[6].visible" show-overflow-tooltip >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_data_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="是否删除" align="center" prop="isDeleted" v-if="columns[10].visible" show-overflow-tooltip />
      <el-table-column label="备注说明" align="center" prop="remark" v-if="columns[11].visible" show-overflow-tooltip />
      <el-table-column label="能操作的功能" align="center" prop="canSetType" v-if="columns[12].visible" show-overflow-tooltip />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:competitionPermissions:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:competitionPermissions:remove']"
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

    <!-- 添加或修改赛会-权限分享对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="赛事id(competition的ID)" prop="competitionId">
          <el-input v-model="form.competitionId" placeholder="请输入赛事id(competition的ID)" />
        </el-form-item>
        <el-form-item label="用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户ID" />
        </el-form-item>
        <el-form-item label="用户手机号" prop="userTel">
          <el-input v-model="form.userTel" placeholder="请输入用户手机号" />
        </el-form-item>
        <el-form-item label="用户姓名" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入用户姓名" />
        </el-form-item>
        <el-form-item label="状态(sys_data_status)" prop="status">
          <el-select v-model="form.status" placeholder="请选择状态(sys_data_status)">
            <el-option
              v-for="dict in dict.type.sys_data_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否删除" prop="isDeleted">
          <el-input v-model="form.isDeleted" placeholder="请输入是否删除" />
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
import { listCompetitionPermissions, getCompetitionPermissions, delCompetitionPermissions, addCompetitionPermissions, updateCompetitionPermissions } from "@/api/system/competitionPermissions";

export default {
  name: "CompetitionPermissions",
  dicts: ['sys_data_status'],
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
      // 赛会-权限分享表格数据
      competitionPermissionsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        competitionId: null,
        userId: null,
        userTel: null,
        userName: null,
        status: null,
        isDeleted: null,
        canSetType: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        competitionId: [
          { required: true, message: "赛事id(competition的ID)不能为空", trigger: "blur" }
        ],
        userTel: [
          { required: true, message: "用户手机号不能为空", trigger: "blur" }
        ],
        userName: [
          { required: true, message: "用户姓名不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态(sys_data_status)不能为空", trigger: "change" }
        ],
      },
      // 列表的列的显示隐藏设置
      columns:[
        { key: 0, label: `id`, visible: true },
        { key: 1, label: `创建时间`, visible: true },
        { key: 2, label: `赛事id(competition的ID)`, visible: true },
        { key: 3, label: `用户ID`, visible: true },
        { key: 4, label: `用户手机号`, visible: true },
        { key: 5, label: `用户姓名`, visible: true },
        { key: 6, label: `状态(sys_data_status)`, visible: true },
        { key: 7, label: `最后修改时间`, visible: true },
        { key: 8, label: `创建人`, visible: true },
        { key: 9, label: `最后修改人`, visible: true },
        { key: 10, label: `是否删除`, visible: true },
        { key: 11, label: `备注说明`, visible: true },
        { key: 12, label: `能操作的功能`, visible: true },
      ],
    };
  },
  created() {
    this.getList();
    //列表分页列的动态显示配置
    var columns = JSON.parse(localStorage.getItem(this.$options.name));
    if(columns){
      this.columns = columns;
    }
  },
  methods: {
    /** 查询赛会-权限分享列表 */
    getList() {
      this.loading = true;
      listCompetitionPermissions(this.queryParams).then(response => {
        this.competitionPermissionsList = response.rows;
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
        createTime: null,
        competitionId: null,
        userId: null,
        userTel: null,
        userName: null,
        status: null,
        updateTime: null,
        createBy: null,
        updateBy: null,
        isDeleted: null,
        remark: null,
        canSetType: null
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
      this.title = "添加赛会-权限分享";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCompetitionPermissions(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改赛会-权限分享";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCompetitionPermissions(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCompetitionPermissions(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除赛会-权限分享编号为"' + ids + '"的数据项？').then(function() {
        return delCompetitionPermissions(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/competitionPermissions/export', {
        ...this.queryParams
      }, `competitionPermissions_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
