<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="api名称" prop="apiName">
        <el-select
          v-model="queryParams.apiName"
          placeholder="请输入"
          clearable
          size="small"
          @change="handleQuery"
          style="width: 180px">
          <el-option
            v-for="index in apiName"
            :key="index"
            :label="index"
            :value="index"/>
        </el-select>
      </el-form-item>

      <el-form-item label="状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="请输入"
          clearable
          size="small"
          @change="handleQuery"
          style="width: 150px">
          <el-option
            v-for="dict in dict.type.request_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"/>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['warning:apiwarning:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['warning:apiwarning:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="apiwarningList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="API名称" align="center" prop="apiName" :show-overflow-tooltip="true"/>
      <el-table-column label="API地址" align="center" prop="apiUrl" :show-overflow-tooltip="true"/>
      <el-table-column label="API总请求次数" align="center" prop="totalCount" :show-overflow-tooltip="true"/>
      <el-table-column label="请求耗费时间" align="center" prop="requestTime" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <span>{{ scope.row.requestTime + "ms" }}</span>
        </template>
      </el-table-column>
      <el-table-column label="每天限制次数" align="center" prop="limitCount" :show-overflow-tooltip="true"/>

      <el-table-column label="API每天请求次数" align="center" prop="dayCount"/>
      <el-table-column label="调用时间" align="center" prop="updateTime" width="180"/>

      <el-table-column label="API状态" align="center" prop="status" width="100">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status===1?'success':'danger'">
            {{ scope.row.status === 1 ? '正常' : '异常' }}
          </el-tag>
        </template>
      </el-table-column>


      <el-table-column label="创建时间" align="center" prop="createTime" width="150" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button circle
                     type="primary"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['warning:apiwarning:edit']"
          ></el-button>
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

    <!-- 添加或修改API预警对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="请求次数" prop="limitCount">
          <el-input v-model.number="form.limitCount" placeholder="请输入api限制请求次数每天"/>
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
import {
  getApiwarningInfo,
  updateApiwarningInfo,
  listApiwarningInfo,
  getApiName
} from "@/api/business/warning/apiwarning";

export default {
  name: "Apiwarning",

  dicts: ['request_status'],

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
      // API预警表格数据
      apiwarningList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        apiName: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        limitCount: [
          {required: true, message: "请求次数", trigger: "blur"},
          {type: 'number', min: 0, max: 9999, message: '必须数字！且数字在 0 到 9999 之间！', trigger: 'blur'}
        ],
      },

      //api名称
      apiName: [],
    };
  },
  created() {
    this.getList();
    this.getApiName();
  },
  methods: {
    //获取所有api名称
    getApiName() {
      getApiName().then(res => {
        this.apiName = res.data
      })
    },

    /** 查询API预警列表 */
    getList() {
      this.loading = true;
      listApiwarningInfo(this.queryParams).then(response => {
        this.apiwarningList = response.rows;
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
        apiName: null,
        apiUrl: null,
        totalCount: null,
        requestTime: null,
        limitCount: null,
        createTime: null,
        dayCount: null,
        updateTime: null
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
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getApiwarningInfo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改API预警";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateApiwarningInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },

    /** 导出按钮操作 */
    handleExport() {
      this.download('warning/apiwarning/export', {
        ...this.queryParams
      }, `apiwarning_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
