<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="查询条件" prop="condition">
        <el-input
          v-model="queryParams.condition"
          prefix-icon="el-icon-refresh"
          placeholder="请输入文章标题、文章内容、文章来源等"
          clearable
          maxlength="100"
          style="width: 500px"
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
          @change="dateQuery"
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
          v-hasPermi="['webmagic:weixinsougou:remove']"
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
          v-hasPermi="['webmagic:weixinsougou:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      ref="tables"
      v-loading="loading"
      :data="weixinsougouList"
      @selection-change="handleSelectionChange"
      :default-sort="defaultSort"
      @sort-change="handleSortChange">

      <el-table-column type="expand">
        <template slot-scope="props">

          <el-form label-position="left" class="demo-table-expand">
            <el-form-item label="文章标题">
              <a :href=props.row.url target="_blank">{{ props.row.title }}</a>
            </el-form-item>
            <el-form-item label="文章内容">
              <span>{{ props.row.content }}</span>
            </el-form-item>
          </el-form>

        </template>
      </el-table-column>

      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="文章标题" align="center" prop="title" :show-overflow-tooltip="true"/>
      <el-table-column label="简略的内容" align="center" prop="content" :show-overflow-tooltip="true"/>
      <el-table-column label="文章来源" align="center" prop="source" :show-overflow-tooltip="true"/>
      <el-table-column label="创建时间"
                       align="center"
                       prop="createTime"
                       :show-overflow-tooltip="true"
                       sortable="custom"
                       :sort-orders="['descending', 'ascending']"
      />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['webmagic:weixinsougou:remove']"
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
import {listWeixinsougou, delWeixinsougou} from "@/api/business/webmagic/weixinsougou/weixinsougou";
import {pickerOptions} from "@/layout/mixin/PickerOptions";

export default {
  name: "Weixinsougou",
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
      // 爬虫微信搜狗搜索表格数据
      weixinsougouList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,

      // 默认排序
      defaultSort: {prop: 'createTime', order: 'descending'},

      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        content: null,
        source: null,
        createTime: null,
        condition: null,  //综合条件
        isAsc: null,
        orderByColumn: null
      },


      //检查查询范围
      daterangeCreateTime: [],

      // 表单参数
      form: {},
      // 表单校验
      rules: {},

      //标记排序重置（修复点击重置请求两次接口BUG）
      sortStatus: true,
    };
  },
  created() {
    this.resetSort()
    this.getList();
  },
  methods: {
    /** 查询爬虫微信搜狗搜索列表 */
    getList() {
      this.loading = true;
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.createTime = this.daterangeCreateTime[0];
        this.queryParams.endCreateTime = this.daterangeCreateTime[1];
      }


      listWeixinsougou(this.queryParams).then(response => {
        this.weixinsougouList = response.data.dataList;
        this.total = response.data.totalCount;
        this.loading = false;
      });
    },

    /** 排序触发事件 */
    handleSortChange(column, prop, order) {
      this.queryParams.isAsc = column.order;
      //点击重置的时候不再次请求接口
      if (this.sortStatus) {
        this.getList();
      }
    },

    //重置排序
    resetSort() {
      this.queryParams.isAsc = this.defaultSort.order
      this.queryParams.orderByColumn = this.defaultSort.prop
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },

    dateQuery() {
      //清空时间参数
      this.queryParams.createTime=null
      this.queryParams.endCreateTime=null

      this.handleQuery();
    },

    /** 重置按钮操作 */
    resetQuery() {
      this.resetSort()
      this.daterangeCreateTime = [];
      this.queryParams.createTime = null
      this.queryParams.endCreateTime = null

      this.sortStatus=false
      this.$refs.tables.sort(this.defaultSort.prop, this.defaultSort.order)
      this.sortStatus=true

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
      this.$modal.confirm('是否确认删除爬虫微信搜狗搜索编号为"' + ids + '"的数据项？').then(function () {
        return delWeixinsougou(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('webmagic/weixin_sougou/export', {
        ...this.queryParams
      }, `weixinsougou_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

<style scoped>

</style>
