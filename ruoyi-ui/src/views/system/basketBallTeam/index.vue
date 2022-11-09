<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="球队名称" prop="teamName">
        <el-input
          v-model="queryParams.teamName"
          placeholder="请输入球队名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="球馆id" prop="buildId">
        <el-input
          v-model="queryParams.buildId"
          placeholder="请输入球馆id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建人ID" prop="createdId">
        <el-input
          v-model="queryParams.createdId"
          placeholder="请输入创建人ID"
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
          v-hasPermi="['system:basketBallTeam:add']"
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
          v-hasPermi="['system:basketBallTeam:edit']"
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
          v-hasPermi="['system:basketBallTeam:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:basketBallTeam:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="basketBallTeamList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="球队logo" align="center" prop="avatar" >
        <template slot-scope="scope">
          <el-avatar :src="scope.row.teamLogo"></el-avatar>
        </template>
      </el-table-column>
      <el-table-column label="球队名称" align="center" prop="teamName" />
      <el-table-column label="球队简介" align="center" show-overflow-tooltip prop="teamDes" />
      <el-table-column label="球馆id" align="center" prop="buildId" />
      <el-table-column label="球馆名称" show-overflow-tooltip align="center" prop="buildingName" />
      <el-table-column label="创建人ID" align="center" prop="createdId" />
      <el-table-column label="球队联系人电话" align="center" prop="contactTel" />
      <el-table-column label="球队图片" align="center" prop="defaultPicture" >
        <template slot-scope="scope">
        <el-image
          style="width: 200px; height: 100px"
          :src="scope.row.defaultPicture"
          :preview-src-list="[scope.row.defaultPicture]"
          :fit="imgfit"></el-image>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createdTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建人" align="center" show-overflow-tooltip prop="createdBy" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:basketBallTeam:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:basketBallTeam:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-user-solid"
            @click="handleTeamMembers(scope.row)"
            v-hasPermi="['system:teamMembers:list']"
          >球队成员</el-button>
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

    <!-- 添加或修改球队管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="删除" prop="isDeleted">
          <el-input v-model="form.isDeleted" placeholder="请输入删除" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createdTime">
          <el-date-picker clearable
            v-model="form.createdTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="创建人" prop="createdBy">
          <el-input v-model="form.createdBy" placeholder="请输入创建人" />
        </el-form-item>
        <el-form-item label="更新人" prop="modifiedBy">
          <el-input v-model="form.modifiedBy" placeholder="请输入更新人" />
        </el-form-item>
        <el-form-item label="更新时间" prop="lastUpdatedTime">
          <el-date-picker clearable
            v-model="form.lastUpdatedTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择更新时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="球队名称" prop="teamName">
          <el-input v-model="form.teamName" placeholder="请输入球队名称" />
        </el-form-item>
        <el-form-item label="球队简介" prop="teamDes">
          <el-input v-model="form.teamDes" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="球馆id" prop="buildId">
          <el-input v-model="form.buildId" placeholder="请输入球馆id" />
        </el-form-item>
        <el-form-item label="球队图片" prop="defaultPicture">
          <el-input v-model="form.defaultPicture" placeholder="请输入球队图片" />
        </el-form-item>
        <el-form-item label="球馆名称" prop="buildingName">
          <el-input v-model="form.buildingName" placeholder="请输入球馆名称" />
        </el-form-item>
        <el-form-item label="创建人ID" prop="createdId">
          <el-input v-model="form.createdId" placeholder="请输入创建人ID" />
        </el-form-item>
        <el-form-item label="球队联系人电话" prop="contactTel">
          <el-input v-model="form.contactTel" placeholder="请输入球队联系人电话" />
        </el-form-item>
        <el-form-item label="球队logo" prop="teamLogo">
          <el-input v-model="form.teamLogo" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 球队成员管理对话框 -->
    <el-dialog :title="teamMembersTitle" :visible.sync="teamMembersOpen" width="700px" append-to-body>
      <el-table :data="teamMembersList">
        <el-table-column label="头像" align="center" prop="avatar"  >
          <template slot-scope="scope">
            <el-avatar :src="scope.row.avatar"></el-avatar>
          </template>
        </el-table-column>
        <el-table-column label="微信昵称" align="center" prop="userName" ></el-table-column>
        <el-table-column label="成员名称" align="center" prop="realName" ></el-table-column>
        <el-table-column label="球衣号" align="center" prop="jerseyNumber" ></el-table-column>
        <el-table-column label="场上位置" align="center" prop="teamPosition" ></el-table-column>
        <el-table-column label="身高(cm)" align="center" prop="height" ></el-table-column>
        <el-table-column label="体重(kg)" align="center" prop="weight" ></el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>

<script>
import {
  listBasketBallTeam,
  getBasketBallTeam,
  delBasketBallTeam,
  addBasketBallTeam,
  updateBasketBallTeam,
  listTeamMembers
} from "@/api/system/basketBallTeam";

export default {
  name: "BasketBallTeam",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      imgfit:"fill",
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 球队管理表格数据
      basketBallTeamList: [],
      // 弹出层标题
      title: "",
      teamMembersTitle: "",
      teamMembersList:[],
      teamMembersOpen:false,
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        teamName: null,
        teamDes: null,
        buildId: null,
        createdId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        createdId: [
          { required: true, message: "创建人ID不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询球队管理列表 */
    getList() {
      this.loading = true;
      listBasketBallTeam(this.queryParams).then(response => {
        this.basketBallTeamList = response.rows;
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
        isDeleted: null,
        createdTime: null,
        createdBy: null,
        modifiedBy: null,
        lastUpdatedTime: null,
        teamName: null,
        teamDes: null,
        remark: null,
        buildId: null,
        defaultPicture: null,
        buildingName: null,
        createdId: null,
        contactTel: null,
        teamLogo: null
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
      this.title = "添加球队管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getBasketBallTeam(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改球队管理";
      });
    },
    /** 修改按钮操作 */
    handleTeamMembers(row) {
      const id = row.id || this.ids
      const param = {
        teamId:id
      }
      listTeamMembers(param).then(response => {
        console.info(response)
        this.teamMembersList = response.rows;
        this.teamMembersOpen = true;
        this.teamMembersTitle = "球队成员";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateBasketBallTeam(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBasketBallTeam(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除球队管理编号为"' + ids + '"的数据项？').then(function() {
        return delBasketBallTeam(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/basketBallTeam/export', {
        ...this.queryParams
      }, `basketBallTeam_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
