<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="ID" prop="id">
        <el-input
          v-model="queryParams.id"
          placeholder="请输入ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="名称" prop="buildingName">
        <el-input
          v-model="queryParams.buildingName"
          placeholder="请输入名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="地址" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="城市" prop="cityName">
        <el-input
          v-model="queryParams.cityName"
          placeholder="请输入城市"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否开放" prop="isOpen">
        <el-input
          v-model="queryParams.isOpen"
          placeholder="请输入是否开放"
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
          v-hasPermi="['system:WxBuilding:add']"
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
          v-hasPermi="['system:WxBuilding:edit']"
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
          v-hasPermi="['system:WxBuilding:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:WxBuilding:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="WxBuildingList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />

      <el-table-column label="球队图片" align="center" prop="contactTel" >
        <template slot-scope="scope">
          <el-image
            style="width: 200px; height: 100px"
            :src="scope.row.defaultPicture"
            :preview-src-list="[scope.row.defaultPicture]"
            :fit="imgfit"></el-image>
        </template>
      </el-table-column>
      <el-table-column label="名称" align="center" prop="buildingName" show-overflow-tooltip/>
<!--      <el-table-column label="经度" align="center" prop="longitude" show-overflow-tooltip/>
      <el-table-column label="纬度" align="center" prop="latitude" show-overflow-tooltip/>-->
<!--      <el-table-column label="省" align="center" prop="cityName" show-overflow-tooltip/>
      <el-table-column label="市" align="center" prop="cityName" />
      <el-table-column label="区县编码" align="center" prop="countyCode" />-->
      <el-table-column label="城市" align="center" prop="cityName" />
      <el-table-column label="是否支持在线" align="center" prop="isSupportlive" />
      <el-table-column label="球馆状态" align="center" prop="status" />
      <el-table-column label="地址" align="center" prop="address" show-overflow-tooltip/>
      <el-table-column label="备注" align="center" prop="remark" show-overflow-tooltip/>
      <el-table-column label="是否开放" align="center" prop="isOpen" />
<!--      <el-table-column label="人均价格" align="center" prop="mittelkurs" show-overflow-tooltip/>-->
      <el-table-column label="微信管理员二维码" align="center" show-overflow-tooltip>
      <template slot-scope="scope">
        <el-image
          style="width: 100px; height: 100px"
          :src="scope.row.chatGroupUrl"
          :preview-src-list="[scope.row.chatGroupUrl]"
          :fit="imgfit"></el-image>
      </template>
      </el-table-column>
      <el-table-column label="创建人ID" align="center" prop="createdId" />
<!--      <el-table-column label="描述" align="center" prop="desc" show-overflow-tooltip/>-->
      <el-table-column label="创建时间" align="center" prop="createdTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="创建人" align="center" prop="createdBy" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:WxBuilding:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:WxBuilding:remove']"
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

    <!-- 添加或修改球场管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="buildingName">
          <el-input v-model="form.buildingName" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="经度" prop="longitude">
          <el-input v-model="form.longitude" placeholder="请输入经度" />
        </el-form-item>
        <el-form-item label="纬度" prop="latitude">
          <el-input v-model="form.latitude" placeholder="请输入纬度" />
        </el-form-item>
        <el-form-item label="省" prop="provinceCode">
          <el-input v-model="form.provinceCode" placeholder="请输入省" />
        </el-form-item>
        <el-form-item label="市" prop="cityCode">
          <el-input v-model="form.cityCode" placeholder="请输入市" />
        </el-form-item>
        <el-form-item label="区县编码" prop="countyCode">
          <el-input v-model="form.countyCode" placeholder="请输入区县编码" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="城市" prop="cityName">
          <el-input v-model="form.cityName" placeholder="请输入城市" />
        </el-form-item>
        <el-form-item label="默认图片" prop="defaultPicture">
          <el-input v-model="form.defaultPicture" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="是否支持在线" prop="isSupportlive">
          <el-input v-model="form.isSupportlive" placeholder="请输入是否支持在线" />
        </el-form-item>
        <el-form-item label="拒绝原因" prop="rejectReason">
          <el-input v-model="form.rejectReason" placeholder="请输入拒绝原因" />
        </el-form-item>
        <el-form-item label="是否开放" prop="isOpen">
          <el-input v-model="form.isOpen" placeholder="请输入是否开放" />
        </el-form-item>
        <el-form-item label="人均价格" prop="mittelkurs">
          <el-input v-model="form.mittelkurs" placeholder="请输入人均价格" />
        </el-form-item>
        <el-form-item label="微信管理员二维码路径" prop="chatGroupUrl">
          <el-input v-model="form.chatGroupUrl" placeholder="请输入微信管理员二维码路径" />
        </el-form-item>
        <el-form-item label="描述" prop="desc">
          <el-input v-model="form.desc" type="textarea" placeholder="请输入内容" />
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
import { listWxBuilding, getWxBuilding, delWxBuilding, addWxBuilding, updateWxBuilding } from "@/api/system/WxBuilding";

export default {
  name: "WxBuilding",
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
      // 球场管理表格数据
      WxBuildingList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        createdTime: null,
        createdBy: null,
        buildingName: null,
        address: null,
        longitude: null,
        latitude: null,
        provinceCode: null,
        cityCode: null,
        countyCode: null,
        remark: null,
        cityName: null,
        status: null,
        isOpen: null,
        createdId: null,
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
    /** 查询球场管理列表 */
    getList() {
      this.loading = true;
      listWxBuilding(this.queryParams).then(response => {
        this.WxBuildingList = response.rows;
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
        buildingName: null,
        address: null,
        longitude: null,
        latitude: null,
        provinceCode: null,
        cityCode: null,
        countyCode: null,
        remark: null,
        cityName: null,
        defaultPicture: null,
        isSupportlive: null,
        status: 0,
        rejectReason: null,
        isOpen: null,
        mittelkurs: null,
        chatGroupUrl: null,
        createdId: null,
        desc: null
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
      this.title = "添加球场管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getWxBuilding(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改球场管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateWxBuilding(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWxBuilding(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除球场管理编号为"' + ids + '"的数据项？').then(function() {
        return delWxBuilding(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/WxBuilding/export', {
        ...this.queryParams
      }, `WxBuilding_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
