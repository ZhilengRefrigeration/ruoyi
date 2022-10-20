<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="参数" prop="scene">
        <el-input
          v-model="queryParams.scene"
          placeholder="请输入参数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="绑定用户" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="球衣号" prop="jerseyNo">
        <el-input
          v-model="queryParams.jerseyNo"
          placeholder="请输入球衣号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="绑定球队" prop="teamName">
        <el-input
          v-model="queryParams.teamName"
          placeholder="请输入球队"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="业务分类" prop="busType">
        <el-select v-model="queryParams.busType" placeholder="请选择业务分类" clearable>
          <el-option
            v-for="dict in dict.type.wx_aqr_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="宽度" prop="width">
        <el-input
          v-model="queryParams.width"
          placeholder="请输入宽度"
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
          @click="genAqrCode"
          v-hasPermi="['system:code:genAqrCode']"
        >生成小程序二维码</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:code:add']"
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
          v-hasPermi="['system:code:edit']"
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
          v-hasPermi="['system:code:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:code:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="codeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="二维码" align="center" prop="contactTel" >
        <template slot-scope="scope">
          <el-image
            style="width: 200px; height: 100px"
            :src="scope.row.codeImgUrl"
            :preview-src-list="[scope.row.codeImgUrl]"
            :fit="imgfit"></el-image>
        </template>
      </el-table-column>
      <el-table-column label="用途说明" align="center" prop="useDesc" show-overflow-tooltip="true" />
      <el-table-column label="参数" align="center" prop="scene" />
      <el-table-column label="绑定用户" align="center" prop="userName" />
      <el-table-column label="绑定球队" align="center" prop="teamName" />
      <el-table-column label="球衣号" align="center" prop="jerseyNo" />
      <el-table-column label="业务分类" align="center" prop="busType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.wx_aqr_type" :value="scope.row.busType"/>
        </template>
      </el-table-column>
      <el-table-column label="页面路径" align="center" prop="page" show-overflow-tooltip="true"/>
      <el-table-column label="宽度" align="center" prop="width" />
      <el-table-column label="创建时间" align="center" prop="createdTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建人" align="center" prop="createdBy" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:code:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:code:remove']"
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

    <!-- 添加或修改微信用户小程序二维码对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="参数" prop="scene">
          <el-input v-model="form.scene" placeholder="请输入参数" />
        </el-form-item>
        <el-form-item label="二维码地址" prop="codeImgUrl">
          <el-input v-model="form.codeImgUrl" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户ID" />
        </el-form-item>
        <el-form-item label="业务分类" prop="busType">
          <el-select v-model="form.busType" placeholder="请选择业务分类">
            <el-option
              v-for="dict in dict.type.wx_aqr_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="页面路径" prop="page">
          <el-input v-model="form.page" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="宽度" prop="width">
          <el-input v-model="form.width" placeholder="请输入宽度" />
        </el-form-item>
        <el-form-item label="用途说明" prop="useDesc">
          <el-input v-model="form.useDesc" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


    <!-- 生成微信用户小程序二维码对话框 -->
    <el-dialog :title="aqrTitle" :visible.sync="aqrOpen" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用途说明" prop="useDesc">
          <el-input v-model="form.useDesc" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="业务分类" prop="busType">
          <el-select v-model="form.busType" placeholder="请选择业务分类">
            <el-option
              v-for="dict in dict.type.wx_aqr_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="页面路径" prop="page">
          <el-input v-model="form.page" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="参数" prop="scene">
          <el-input v-model="form.scene" placeholder="请输入参数" />
        </el-form-item>
        <el-form-item label="用户">
          <el-select v-model="form.userId" filterable placeholder="请选择用户">
            <el-option
              v-for="item in userOptions"
              :key="item.id"
              :label="item.userName"
              :value="item.id"
              :disabled="item.enabled == 0"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="球队">
          <el-select v-model="form.teamId" filterable  placeholder="请选择球队">
            <el-option
              v-for="item in teamOptions"
              :key="item.id"
              :label="item.teamName"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="球衣号" prop="jerseyNo">
          <el-input v-model="form.jerseyNo" placeholder="请输入球衣号" />
        </el-form-item>
        <el-form-item label="宽度" prop="width">
          <el-input v-model="form.width" placeholder="请输入宽度" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="aqrSubmitForm">确 定</el-button>
        <el-button @click="aqrCancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {listCode, getCode, delCode, addCode, updateCode, genAqrCode, getUserAndTeams} from "@/api/system/code";
export default {
  name: "Code",
  dicts: ['wx_aqr_type'],
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
      // 微信用户小程序二维码表格数据
      codeList: [],
      // 用户选项
      userOptions: [],
      // 球队选项
      teamOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,

      // 弹出层标题
      aqrTitle: "",
      // 是否显示弹出层
      aqrOpen: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        scene: null,
        userId: null,
        busType: null,
        page: null,
        width: null,
        useDesc: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        // scene: [
        //   { required: true, message: "参数不能为空", trigger: "blur" }
        // ],
        busType: [
          { required: true, message: "业务分类枚举不能为空", trigger: "change" }
        ],
        page: [
          { required: true, message: "页面路径不能为空", trigger: "blur" }
        ],
        useDesc: [
          { required: true, message: "用途说明不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    //this.getuserAndteamsList();
  },
  methods: {
    /** 查询微信用户小程序二维码列表 */
    getList() {
      this.loading = true;
      listCode(this.queryParams).then(response => {
        this.codeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getuserAndteamsList() {
      this.loading = true;
      getUserAndTeams().then(response => {
        this.userOptions = response.users;
        this.teamOptions = response.teams;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },

    aqrCancel() {
      this.aqrOpen = false;
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
        scene: null,
        codeImgUrl: null,
        userId: null,
        busType: null,
        page: null,
        width: null,
        useDesc: null
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
      this.title = "添加微信用户小程序二维码";
    },

    genAqrCode() {
      this.reset();
      this.aqrOpen = true;
      this.aqrTitle = "生成微信用户小程序二维码";
      getUserAndTeams().then(response => {
        this.userOptions = response.users;
        this.teamOptions = response.teams;
        this.aqrOpen = true;
        this.aqrTitle = "生成微信用户小程序二维码";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCode(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改微信用户小程序二维码";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCode(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCode(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },


    /** 提交按钮 */
    aqrSubmitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          genAqrCode(this.form).then(response => {
            this.$modal.msgSuccess("生成成功");
            this.aqrOpen = false;
            this.getList();
          });
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除微信用户小程序二维码编号为"' + ids + '"的数据项？').then(function() {
        return delCode(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/code/export', {
        ...this.queryParams
      }, `code_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
