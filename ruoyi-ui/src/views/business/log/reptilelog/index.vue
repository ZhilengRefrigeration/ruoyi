<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :rules="queryRules" :inline="true" v-show="showSearch"
             label-width="68px">
      <el-form-item label="爬虫名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入爬虫名称"
          clearable
          maxlength="20"
          size="small"
          @keyup.enter.native="handleQuery('queryForm')"
        />
      </el-form-item>
      <el-form-item label="爬虫地址" prop="url">
        <el-input
          v-model="queryParams.url"
          placeholder="请输入爬虫地址"
          clearable
          maxlength="100"
          size="small"
          @keyup.enter.native="handleQuery('queryForm')"
        />
      </el-form-item>
      <el-form-item label="请求时间" prop="beginRequestTime">
        <el-input
          v-model.number="queryParams.beginRequestTime"
          placeholder="单位：秒"
          maxlength="9"
          size="small"
          style="width: 90px"
          @keyup.enter.native="handleQuery('queryForm')"
        />
      </el-form-item>
      <el-form-item prop="endRequestTime">
        -
        <el-input
          v-model.number="queryParams.endRequestTime"
          placeholder="单位：秒"
          size="small"
          maxlength="9"
          style="width: 90px"
          @keyup.enter.native="handleQuery('queryForm')"
        />
      </el-form-item>

      <el-form-item label="创建时间">
        <el-date-picker
          v-model="daterangeCreateTime"
          size="small"
          :picker-options="pickerOptions"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          @change="dateQuery('queryForm')"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery('queryForm')">搜索</el-button>
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
          v-hasPermi="['log:webmagicLog:remove']"
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
          v-hasPermi="['log:webmagicLog:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="webmagicLogList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="爬虫名称" align="center" prop="name"/>
      <el-table-column label="爬虫地址" align="center" prop="url" :show-overflow-tooltip="true"/>
      <el-table-column label="请求时间(秒)" align="center" prop="requestTime">
        <template slot-scope="scope">
          <span>{{
              scope.row.requestTime < 1000
                ?
                scope.row.requestTime + '毫秒'
                :
                Math.round(scope.row.requestTime / 1000) + '秒'
            }}</span>
        </template>
      </el-table-column>
      <el-table-column label="复杂度" align="center" prop="complexRate">
        <template slot-scope="scope">
          <span>{{ scope.row.complexRate }}次</span>
        </template>
      </el-table-column>

      <el-table-column label="创建时间" align="center" prop="createTime" :show-overflow-tooltip="true"/>

      <el-table-column label="请求结果" align="center" prop="status">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status===1?'success':'danger'">
            {{ scope.row.status === 1 ? '成功' : '失败' }}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['log:webmagicLog:remove']"
          >删除
          </el-button>
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
import {listWebmagicLog, delWebmagicLog} from "@/api/business/log/reptilelog";
import {pickerOptions} from "@/layout/mixin/PickerOptions"

export default {
  name: "ReptileLog",
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
      // 爬虫日志表格数据
      webmagicLogList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 创建时间时间范围
      daterangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        url: null,
        requestTime: null,
        createTime: null,
        beginRequestTime: null,
        endRequestTime: null,

      },
      // 表单参数
      form: {},

      // 表单校验
      queryRules: {
        beginRequestTime: [
          {type: 'number',min: 0, max: 200000, message: '必须数字！且数字在 0 到 200000 之间！', trigger: 'blur'}
        ],
        endRequestTime: [
          {type: 'number',min: 0, max: 200000, message: '必须数字！且数字在 0 到 200000 之间！', trigger: 'blur'}
        ],
      },

    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询爬虫日志列表 */
    getList() {
      this.loading = true;
      if (null != this.daterangeCreateTime && '' !== this.daterangeCreateTime) {
        this.queryParams.createTime = this.daterangeCreateTime[0];
        this.queryParams.endCreateTime = this.daterangeCreateTime[1];
      }
      let beginRequestTime = this.queryParams.beginRequestTime;
      let endRequestTime = this.queryParams.endRequestTime;
      if (beginRequestTime != null && '' !== beginRequestTime && endRequestTime != null && '' !== endRequestTime) {
        this.queryParams.beginRequestTime = beginRequestTime * 1000
        this.queryParams.endRequestTime = endRequestTime * 1000
      }

      listWebmagicLog(this.queryParams).then(response => {
        this.webmagicLogList = response.rows;
        this.total = response.total;
        this.loading = false;
      }).catch(err =>{
        this.loading = false;
      });

      //回显的时候正常回显
      if (beginRequestTime != null && '' !== beginRequestTime && endRequestTime != null && '' !== endRequestTime) {
        this.queryParams.beginRequestTime = beginRequestTime;
        this.queryParams.endRequestTime = endRequestTime;
      }
    },

    /** 搜索按钮操作 */
    handleQuery(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.queryParams.pageNum = 1;
          this.getList();
        } else {
          return false;
        }
      });
    },

    dateQuery(formName) {
      //清空时间参数
      this.queryParams.createTime=null
      this.queryParams.endCreateTime=null

      this.handleQuery(formName);
    },

    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeCreateTime = [];
      this.queryParams.createTime = null
      this.queryParams.endCreateTime = null

      //这个方法封装了重置属性
      this.resetForm("queryForm");
      this.handleQuery("queryForm");
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
      this.$modal.confirm('是否确认删除爬虫日志编号为"' + ids + '"的数据项？').then(function () {
        return delWebmagicLog(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('log/reptileLog/export', {
        ...this.queryParams
      }, `webmagicLog_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
