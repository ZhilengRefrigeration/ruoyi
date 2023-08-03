<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="客户" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入客户"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户手机" prop="phoneNumber">
        <el-input
          v-model="queryParams.phoneNumber"
          placeholder="请输入客户手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订车时间" prop="orderDate">
        <el-date-picker clearable
          v-model="queryParams.orderDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择订车时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="车辆详细" prop="carInfo">
        <el-input
          v-model="queryParams.carInfo"
          placeholder="请输入车辆详细"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车辆VIN" prop="carVin">
        <el-input
          v-model="queryParams.carVin"
          placeholder="请输入车辆VIN"
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
<!--      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:customerOrder:add']"
        >新增</el-button>
      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:customerOrder:edit']"
        >修改</el-button>
      </el-col>
<!--      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:customerOrder:remove']"
        >删除</el-button>
      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:customerOrder:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="customerOrderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="客户" align="center" prop="userName" />
      <el-table-column label="客户手机" align="center" prop="phoneNumber" />
      <el-table-column label="客户性别" align="center" prop="sex"  show-overflow-tooltip >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.sex"/>
        </template>
      </el-table-column>
      <el-table-column label="线索渠道" align="center" prop="clueChannel"  show-overflow-tooltip >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.clue_channels" :value="scope.row.clueChannel"/>
        </template>
      </el-table-column>
      <el-table-column label="信息来源" align="center" prop="dataSource" show-overflow-tooltip >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.customer_source" :value="scope.row.dataSource"/>
        </template>
      </el-table-column>
      <el-table-column label="订车时间" align="center" prop="orderDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.orderDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="车辆详细" align="center" prop="carInfo" width="280"/>
      <el-table-column label="车辆VIN" align="center" prop="carVin" width="220" />
      <el-table-column label="车辆状态" align="center" prop="carStatus" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.car_status" :value="scope.row.carStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="出库日期" align="center" prop="outDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.outDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="创建者" align="center" prop="createBy" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新者" align="center" prop="updateBy" />
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>-->
      <el-table-column label="备注" align="center" prop="remark" show-overflow-tooltip/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:customerOrder:edit']"
          >修改</el-button>
          <el-popconfirm title="是否确认出库？"  @confirm="popConfirm(scope.row)"  @cancel="popCancel" >
            <el-button v-if="scope.row.carStatus =='notOut'" size="mini" type="text" icon="el-icon-check" slot="reference">确认出库</el-button>
          </el-popconfirm>
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

    <!-- 添加或修改客户-订车对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
<!--        <el-form-item label="客户id" prop="customerId">
          <el-input v-model="form.customerId" placeholder="请输入客户id" />
        </el-form-item>-->
        <el-form-item label="订车时间" prop="orderDate">
          <el-date-picker clearable
            v-model="form.orderDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择订车时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="车辆详细" prop="carInfo">
          <el-input v-model="form.carInfo" placeholder="请输入车辆详细" />
        </el-form-item>
        <el-form-item label="车辆VIN" prop="carVin">
          <el-input v-model="form.carVin" placeholder="请输入车辆VIN" />
        </el-form-item>
        <el-form-item label="车辆状态" prop="carStatus">
          <el-select v-model="form.carStatus" placeholder="请选择车辆状态">
            <el-option
              v-for="dict in dict.type.car_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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
import { confirmToOut,getCustomerOrderPage, getCustomerOrder, delCustomerOrder, addCustomerOrder, updateCustomerOrder } from "@/api/system/customerOrder";

export default {
  name: "CustomerOrder",
  dicts: ['to_store_status', 'customer_source','customer_status', 'sys_user_sex', 'customer_level', 'clue_channels','follow_result','car_status'],
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
      // 客户-订车表格数据
      customerOrderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        customerId: null,
        orderDate: null,
        carInfo: null,
        carVin: null,
        carStatus: null,
        orderByColumn:'t.order_date',
        isAsc:'desc'
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
    /** 查询客户-订车列表 */
    getList() {
      this.loading = true;
      getCustomerOrderPage(this.queryParams).then(response => {
        this.customerOrderList = response.rows;
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
        customerId: null,
        orderDate: null,
        carInfo: null,
        carVin: null,
        carStatus: "0",
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null
      };
      this.resetForm("form");
    },
    popConfirm(row){
      let param = {
        id : row.id,
        carStatus:'outbound',
        outDate:this.getDateYYYYMMddHHMMSS()
      }
      confirmToOut(param).then(response => {
        this.$modal.msgSuccess("出库成功");
        this.open = false;
        this.getList();
      });
    },
    popCancel(){
      console.log('取消')
    },
    getDateYYYYMMddHHMMSS(){
      const date = new Date();
      const month = (date.getMonth() + 1).toString().padStart(2, '0');
      const strDate = date.getDate().toString().padStart(2, '0');
      const starHours = date.getHours().toString().padStart(2, '0');
      const starMinutes = date.getMinutes().toString().padStart(2, '0');
      const starSeconds = date.getSeconds().toString().padStart(2, '0');
      return `${date.getFullYear()}-${month}-${strDate} ${starHours}:${starMinutes}:${starSeconds}`;
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
      this.title = "添加客户-订车";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCustomerOrder(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改客户-订车";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCustomerOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCustomerOrder(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除客户-订车编号为"' + ids + '"的数据项？').then(function() {
        return delCustomerOrder(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/customerOrder/export', {
        ...this.queryParams
      }, `customerOrder_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
