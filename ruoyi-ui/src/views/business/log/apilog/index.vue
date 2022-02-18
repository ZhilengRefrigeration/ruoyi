<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="70px">
      <el-form-item label="接口名称" prop="apiName">
        <el-select
          v-model="queryParams.apiName"
          placeholder="请输入"
          clearable
          size="small"
          style="width: 150px">
          <el-option
            v-for="index in apiName"
            :key="index"
            :label="index"
            :value="index"/>
        </el-select>
      </el-form-item>

      <el-form-item label="请求URL" prop="url">
        <el-input
          v-model="queryParams.url"
          placeholder="请输入请求URL"
          clearable
          size="small"
          maxlength="100"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="请求参数" prop="request">
        <el-input
          v-model="queryParams.request"
          placeholder="请输入请求参数"
          clearable
          size="small"
          maxlength="1000"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="响应参数" prop="response">
        <el-input
          v-model="queryParams.response"
          placeholder="请输入响应参数"
          clearable
          size="small"
          maxlength="15000"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="是否成功" prop="isSuccess">
        <el-select
          v-model="queryParams.isSuccess"
          placeholder="请输入"
          clearable
          size="small"
          style="width: 150px">
          <el-option
            v-for="dict in dict.type.request_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"/>
        </el-select>
      </el-form-item>

      <el-form-item label="请求方法" prop="method">
        <el-select
          v-model="queryParams.method"
          placeholder="请输入"
          clearable
          size="small"
          style="width: 150px">
          <el-option
            v-for="dict in dict.type.request_method"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"/>
        </el-select>
      </el-form-item>


      <el-form-item label="创建时间">
        <el-date-picker
          v-model="daterangeCreateTime"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          :picker-options="pickerOptions"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>


      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['log:apilog:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['log:apilog:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="logList" border height="585" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="接口名称" align="center" prop="apiName" :show-overflow-tooltip="true"/>
      <el-table-column label="请求URL" align="center" prop="url" :show-overflow-tooltip="true"/>
      <el-table-column label="请求方法" align="center" prop="method" :show-overflow-tooltip="true"/>
      <el-table-column label="请求参数" align="center" prop="request" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <span>{{ scope.row.request !== "" ? scope.row.request : "-" }}</span>
        </template>
      </el-table-column>
      <el-table-column label="响应参数" align="center" prop="response" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <span>{{ scope.row.response !== "" ? scope.row.response : "-" }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" :show-overflow-tooltip="true"/>
      <el-table-column label="是否请求成功" align="center" prop="isSuccess" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <el-tag :type="scope.row.isSuccess=== 1 ?'success':'danger'" size="small">
            {{ scope.row.isSuccess === 1 ? '成功' : '失败' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-tooltip class="item" effect="dark" content="点击查看json格式" placement="top-start">
            <el-button circle
                       type=""
                       icon="el-icon-view"
                       @click="handleView(scope.row,scope.index)"
                       v-hasPermi="['log:apilog:query']"
            ></el-button>
          </el-tooltip>
          <el-button
            circle
            type="danger"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['log:apilog:remove']"
          >
          </el-button>
        </template>
      </el-table-column>
    </el-table>


    <el-dialog title="内容详细" :visible.sync="open" width="700px" append-to-body>
      <el-row>
        <el-col :span="24">
          请求参数：
        </el-col>
        <el-col :span="24">
          <json-viewer :value=request
                       :expand-depth=5
                       copyable
                       boxed
                       sort></json-viewer>
        </el-col>
        <el-col :span="24">
          响应参数：
        </el-col>
        <el-col :span="24">
          <json-viewer :value="response"
                       :expand-depth=5
                       copyable
                       boxed
                       sort></json-viewer>
        </el-col>
      </el-row>
      <div slot="footer" class="dialog-footer">
        <el-button @click="open = false">关 闭</el-button>
      </div>
    </el-dialog>

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
import {listLog, delLog, getApiName} from "@/api/business/log/apilog";
import {pickerOptions} from "@/layout/mixin/PickerOptions"

export default {
  name: "Apilog",
  dicts: ['request_status', 'request_method'],
  mixins: [pickerOptions],
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
      // 日志表格数据
      logList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数  需要给一个null值，否则input框等无法输入
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        apiName: null,
        url: null,
        request: null,
        response: null,
        isSuccess: null,
        method: null,
      },
      // 表单参数
      form: {},

      //json格式数据
      request: {},
      response: {},

      // 表单校验
      rules: {},

      //检查查询范围
      daterangeCreateTime: [],

      //api名称
      apiName: [],
    };
  },
  created() {
    this.getList();
    this.getApiName()
  },

  methods: {
    //获取所有api名称
    getApiName() {
      getApiName().then(res => {
        this.apiName = res.data
      })

    },

    /** 查询日志列表 */
    getList() {
      this.loading = true;
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.createTime = this.daterangeCreateTime[0];
        this.queryParams.endCreateTime = this.daterangeCreateTime[1];
      }
      listLog(this.queryParams).then(response => {
        this.logList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 详细按钮操作 */
    handleView(row) {
      this.open = true;
      this.form = row;
      try {
        this.request = JSON.parse(this.form.request)
        this.response = JSON.parse(this.form.response)
      } catch (err) {
        this.open = false;
        this.$notify({
          title: '警告',
          message: '参数不是json格式！！！',
          type: 'warning'
        });

      }

    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeCreateTime = [];
      this.queryParams.createTime = null
      this.queryParams.endCreateTime = null
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },

    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除日志编号为"' + ids + '"的数据项？').then(function () {
        return delLog(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('log/apilog/export', {
        ...this.queryParams
      }, `log_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
