<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="父id" prop="pid">
        <el-input
          v-model="queryParams.pid"
          placeholder="请输入父id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="简称" prop="shortname">
        <el-input
          v-model="queryParams.shortname"
          placeholder="请输入简称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="名称" prop="cityname">
        <el-input
          v-model="queryParams.cityname"
          placeholder="请输入名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="全称" prop="mergerName">
        <el-input
          v-model="queryParams.mergerName"
          placeholder="请输入全称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="层级 0 1 2 省市区县" prop="level">
        <el-input
          v-model="queryParams.level"
          placeholder="请输入层级 0 1 2 省市区县"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="拼音" prop="pinyin">
        <el-input
          v-model="queryParams.pinyin"
          placeholder="请输入拼音"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="长途区号" prop="code">
        <el-input
          v-model="queryParams.code"
          placeholder="请输入长途区号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="邮编" prop="zipCode">
        <el-input
          v-model="queryParams.zipCode"
          placeholder="请输入邮编"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="首字母" prop="first">
        <el-input
          v-model="queryParams.first"
          placeholder="请输入首字母"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="经度" prop="longitude">
        <el-input
          v-model="queryParams.longitude"
          placeholder="请输入经度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="纬度" prop="latitude">
        <el-input
          v-model="queryParams.latitude"
          placeholder="请输入纬度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="isDeleted">
        <el-input
          v-model="queryParams.isDeleted"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="createdTime">
        <el-date-picker clearable
          v-model="queryParams.createdTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择${comment}">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="${comment}" prop="createdBy">
        <el-input
          v-model="queryParams.createdBy"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="modifiedBy">
        <el-input
          v-model="queryParams.modifiedBy"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="lastUpdatedTime">
        <el-date-picker clearable
          v-model="queryParams.lastUpdatedTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择${comment}">
        </el-date-picker>
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
          v-hasPermi="['system:area:add']"
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
          v-hasPermi="['system:area:edit']"
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
          v-hasPermi="['system:area:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:area:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="areaList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="父id" align="center" prop="pid" />
      <el-table-column label="简称" align="center" prop="shortname" />
      <el-table-column label="名称" align="center" prop="cityname" />
      <el-table-column label="全称" align="center" prop="mergerName" />
      <el-table-column label="层级 0 1 2 省市区县" align="center" prop="level" />
      <el-table-column label="拼音" align="center" prop="pinyin" />
      <el-table-column label="长途区号" align="center" prop="code" />
      <el-table-column label="邮编" align="center" prop="zipCode" />
      <el-table-column label="首字母" align="center" prop="first" />
      <el-table-column label="经度" align="center" prop="longitude" />
      <el-table-column label="纬度" align="center" prop="latitude" />
      <el-table-column label="${comment}" align="center" prop="isDeleted" />
      <el-table-column label="${comment}" align="center" prop="createdTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="${comment}" align="center" prop="createdBy" />
      <el-table-column label="${comment}" align="center" prop="modifiedBy" />
      <el-table-column label="${comment}" align="center" prop="lastUpdatedTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.lastUpdatedTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:area:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:area:remove']"
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

    <!-- 添加或修改【请填写功能名称】对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="父id" prop="pid">
          <el-input v-model="form.pid" placeholder="请输入父id" />
        </el-form-item>
        <el-form-item label="简称" prop="shortname">
          <el-input v-model="form.shortname" placeholder="请输入简称" />
        </el-form-item>
        <el-form-item label="名称" prop="cityname">
          <el-input v-model="form.cityname" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="全称" prop="mergerName">
          <el-input v-model="form.mergerName" placeholder="请输入全称" />
        </el-form-item>
        <el-form-item label="层级 0 1 2 省市区县" prop="level">
          <el-input v-model="form.level" placeholder="请输入层级 0 1 2 省市区县" />
        </el-form-item>
        <el-form-item label="拼音" prop="pinyin">
          <el-input v-model="form.pinyin" placeholder="请输入拼音" />
        </el-form-item>
        <el-form-item label="长途区号" prop="code">
          <el-input v-model="form.code" placeholder="请输入长途区号" />
        </el-form-item>
        <el-form-item label="邮编" prop="zipCode">
          <el-input v-model="form.zipCode" placeholder="请输入邮编" />
        </el-form-item>
        <el-form-item label="首字母" prop="first">
          <el-input v-model="form.first" placeholder="请输入首字母" />
        </el-form-item>
        <el-form-item label="经度" prop="longitude">
          <el-input v-model="form.longitude" placeholder="请输入经度" />
        </el-form-item>
        <el-form-item label="纬度" prop="latitude">
          <el-input v-model="form.latitude" placeholder="请输入纬度" />
        </el-form-item>
        <el-form-item label="${comment}" prop="isDeleted">
          <el-input v-model="form.isDeleted" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="createdTime">
          <el-date-picker clearable
            v-model="form.createdTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择${comment}">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="${comment}" prop="createdBy">
          <el-input v-model="form.createdBy" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="modifiedBy">
          <el-input v-model="form.modifiedBy" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="lastUpdatedTime">
          <el-date-picker clearable
            v-model="form.lastUpdatedTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择${comment}">
          </el-date-picker>
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
import { listArea, getArea, delArea, addArea, updateArea } from "@/api/system/area";

export default {
  name: "Area",
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
      // 【请填写功能名称】表格数据
      areaList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        pid: null,
        shortname: null,
        cityname: null,
        mergerName: null,
        level: null,
        pinyin: null,
        code: null,
        zipCode: null,
        first: null,
        longitude: null,
        latitude: null,
        isDeleted: null,
        createdTime: null,
        createdBy: null,
        modifiedBy: null,
        lastUpdatedTime: null,
        orderByColumn:"id",
        isAsc:"desc",
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询【请填写功能名称】列表 */
    getList() {
      this.loading = true;
      listArea(this.queryParams).then(response => {
        this.areaList = response.rows;
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
        pid: null,
        shortname: null,
        cityname: null,
        mergerName: null,
        level: null,
        pinyin: null,
        code: null,
        zipCode: null,
        first: null,
        longitude: null,
        latitude: null,
        isDeleted: null,
        createdTime: null,
        createdBy: null,
        modifiedBy: null,
        lastUpdatedTime: null
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
      this.title = "添加【请填写功能名称】";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getArea(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改【请填写功能名称】";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateArea(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addArea(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除【请填写功能名称】编号为"' + ids + '"的数据项？').then(function() {
        return delArea(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/area/export', {
        ...this.queryParams
      }, `area_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
