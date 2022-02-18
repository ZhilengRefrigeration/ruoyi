<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="文案标签" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择文案标签" @change="handleQuery" clearable size="small">
          <el-option
            v-for="index in typeList"
            :key="index"
            :label="index"
            :value="index"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="文案主题" prop="theme">
        <el-input
          v-model="queryParams.theme"
          placeholder="请输入文案主题"
          clearable
          size="small"
          maxlength="50"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
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
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="daterangeCreateTime"
          size="small"
          style="width: 320px"
          value-format="yyyy-MM-dd"
          format="yyyy 年 MM 月 dd 日"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :picker-options="pickerOptions"
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
          v-hasPermi="['webmagic:copyWritingNetwork:remove']"
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
          v-hasPermi="['webmagic:copyWritingNetwork:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="copyWritingNetworkList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="文案标签" align="center" prop="type" :show-overflow-tooltip="true" width="120px"/>
      <el-table-column label="文案主题" align="center" prop="theme" :show-overflow-tooltip="true" width="200px"/>
      <el-table-column label="文案内容" align="center" prop="content" :show-overflow-tooltip="true"/>
      <el-table-column label="创建时间" align="center" prop="createTime" width="250px" :show-overflow-tooltip="true">
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="150px">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['webmagic:copyWritingNetwork:remove']"
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

import {
  listCopyWritingNetwork,
  delCopyWritingNetwork,
  getType
} from "@/api/business/webmagic/copywritingnetwork/copyWritingNetwork"
import {pickerOptions} from "@/layout/mixin/PickerOptions";

export default {
  name: "CopyWritingNetwork",
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
      // 文案网表格数据
      copyWritingNetworkList: [],
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
        type: null,
        theme: null,
        content: null,
        createTime: null
      },
      // 表单参数
      form: {},

      //类型集合
      typeList:[],

    };
  },
  created() {
    this.getList();
    this.getType();
  },
  methods: {
    //获取类型
    getType() {
      getType().then(res =>{
        this.typeList=res.data
      })
    },

    /** 查询文案网列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.createTime = this.daterangeCreateTime[0];
        this.queryParams.endCreateTime = this.daterangeCreateTime[1];
      }
      listCopyWritingNetwork(this.queryParams).then(response => {
        this.copyWritingNetworkList = response.rows;
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
      this.$modal.confirm('是否确认删除文案网编号为"' + ids + '"的数据项？').then(function () {
        return delCopyWritingNetwork(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },


    /** 导出按钮操作 */
    handleExport() {
      this.download('webmagic/copyWritingNetwork/export', {
        ...this.queryParams
      }, `copyWritingNetwork_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>

<style scoped>

</style>
