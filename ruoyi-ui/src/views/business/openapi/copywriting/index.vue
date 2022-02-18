<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="文案内容" prop="content">
        <el-input
          v-model="queryParams.content"
          placeholder="请输入文案内容"
          clearable
          size="small"
          maxlength="100"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文案来源" prop="source">
        <el-input
          v-model="queryParams.source"
          placeholder="请输入文案来源"
          clearable
          size="small"
          maxlength="50"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文案类型" prop="type">
        <el-select
          v-model="queryParams.type"
          placeholder="请选择文案类型"
          @change="handleQuery"
          clearable
          size="small">
          <el-option
            v-for="dict in dict.type.copywriting_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
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
          @change="handleQuery"
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
          v-hasPermi="['openapi:copywriting:remove']"
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
          v-hasPermi="['openapi:copywriting:export']"
        >导出
        </el-button>
      </el-col>

      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          @click="delRepeatCopyWriting"
          v-hasPermi="['openapi:copywriting:remove']"
        >删除重复文案
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="copyWritingList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="文案内容" align="center" prop="content" :show-overflow-tooltip="true" width="700px"/>
      <el-table-column label="文案来源" align="center" prop="source" :show-overflow-tooltip="true"/>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180" :show-overflow-tooltip="true">
      </el-table-column>
      <el-table-column label="文案类型" align="center" prop="type" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.copywriting_type" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120px">
        <template slot-scope="scope">
          <el-tooltip class="item" effect="dark" content="点击查看详情" placement="top-start">
            <el-button circle
                       type=""
                       icon="el-icon-view"
                       @click="handleView(scope.row,scope.index)"
                       v-hasPermi="['openapi:copywriting:query']"
            ></el-button>
          </el-tooltip>
          <el-tooltip class="item" effect="dark" content="点击删除" placement="top-start">
            <el-button circle
                       type="danger"
                       icon="el-icon-delete"
                       @click="handleDelete(scope.row)"
                       v-hasPermi="['openapi:copywriting:remove']"
            ></el-button>
          </el-tooltip>
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

    <!-- 操作日志详细 -->
    <el-dialog title="内容详细" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" label-width="100px" size="mini">
        <el-row>
          <el-col :span="24">
            <el-form-item label="文案内容：">{{ form.content }}</el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="open = false">关 闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {listCopyWriting, delRepeatCopyWriting, delCopyWriting} from "@/api/business/openapi/copywriting";
import {pickerOptions} from "@/layout/mixin/PickerOptions"

export default {
  name: "CopyWriting",
  dicts: ['copywriting_type'],
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
      // 文案api，通过api获取文案信息表格数据
      copyWritingList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 文案类型时间范围
      daterangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        content: null,
        source: null,
        createTime: null,
        type: null
      },
      // 表单参数
      form: {},


    };
  },
  created() {
    this.getList();
  },
  methods: {
    //删除重复文案
    delRepeatCopyWriting() {
      delRepeatCopyWriting().then(res => {
        this.$modal.msgSuccess("删除" + res.data + "条");
        this.getList();
      })
    },

    /** 详细按钮操作 */
    handleView(row) {
      this.open = true;
      this.form = row;
    },

    /** 查询文案api，通过api获取文案信息列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.createTime = this.daterangeCreateTime[0];
        this.queryParams.endCreateTime = this.daterangeCreateTime[1];
      }
      listCopyWriting(this.queryParams).then(response => {
        this.copyWritingList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
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
      this.$modal.confirm('是否确认删除文案api，通过api获取文案信息编号为"' + ids + '"的数据项？').then(function () {
        return delCopyWriting(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('openapi/copyWriting/export', {
        ...this.queryParams
      }, `copyWriting_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
