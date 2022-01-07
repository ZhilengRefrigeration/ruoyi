<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="api名称" prop="apiName">
        <el-input
          v-model="queryParams.apiName"
          placeholder="请输入api名称"
          clearable
          size="small"
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
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['warning:warning:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="warningList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="api名称" align="center" prop="apiName" width="200px"/>
      <el-table-column label="预警类型" align="center" prop="warningType" width="100px"/>
      <el-table-column label="预警等级" align="center" prop="warningLevel" width="130px">
        <template slot-scope="scope">
          <el-tag type="info" v-if="scope.row.warningLevel==='普通'">
            {{ scope.row.warningLevel }}
          </el-tag>
          <el-tag type="warning" v-if="scope.row.warningLevel==='警告'">
            {{ scope.row.warningLevel }}
          </el-tag>
          <el-tag type="danger" v-if="scope.row.warningLevel==='严重'">
            {{ scope.row.warningLevel }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="预警记录信息" align="center" prop="warningMessage" :show-overflow-tooltip="true"/>
      <el-table-column label="限定值" align="center" prop="limitValue" width="100px"/>
      <el-table-column label="实际值" align="center" prop="realValue" width="100px"/>
      <el-table-column label="预警时间" align="center" prop="createTime" width="180"/>
      <el-table-column label="是否处理" align="center" prop="handle" width="100px">
        <template slot-scope="scope">
          <el-tag :type="scope.row.handle==='1'?'success':'danger'" size="small">
            {{ scope.row.handle === 1 ? '是' : '否' }}
          </el-tag>
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

  </div>
</template>

<script>
import {listApiwarning} from "@/api/business/warning/apiwarning";

export default {
  name: "Warning",
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
      // api预警表格数据
      warningList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        apiName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询api预警列表 */
    getList() {
      this.loading = true;
      listApiwarning(this.queryParams).then(response => {
        this.warningList = response.rows;
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
        warningType: null,
        warningLevel: null,
        warningMessage: null,
        limitValue: null,
        realValue: null,
        handle: null,
        createTime: null
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


    /** 导出按钮操作 */
    handleExport() {
      this.download('warning/apiwarning/apiwarnexport', {
        ...this.queryParams
      }, `warning_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
