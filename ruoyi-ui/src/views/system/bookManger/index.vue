<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="客户名" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入客户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户级别" prop="userType">
        <el-select v-model="queryParams.userType" placeholder="请选择客户级别" clearable>
          <el-option
            v-for="dict in dict.type.customer_level"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="手机号码" prop="phoneNumber">
        <el-input
          v-model="queryParams.phoneNumber"
          placeholder="请输入手机号码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预约状态" prop="status">
        <el-select v-model="queryParams.makerStatus" placeholder="请选择到店状态" clearable>
          <el-option
            v-for="dict in dict.type.maker_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['system:customer:add']"
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
          v-hasPermi="['system:customer:edit']"
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
          v-hasPermi="['system:customer:remove']"
        >删除</el-button>
      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:customer:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns" :pageName="$options.name" ></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="customerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="客户姓名" align="center" prop="userName" width="120" v-if="columns[1].visible" show-overflow-tooltip />
      <el-table-column label="客户性别" align="center" prop="sex"  show-overflow-tooltip >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.sex"/>
        </template>
      </el-table-column>
      <el-table-column label="手机号码" align="center" prop="phoneNumber" width="110"  v-if="columns[5].visible" show-overflow-tooltip />
      <el-table-column label="意向级别" align="center" prop="intentionLevel"   show-overflow-tooltip >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.customer_level" :value="scope.row.intentionLevel"/>
        </template>
      </el-table-column>
      <el-table-column label="预约时间" class-name="specialColor" align="center" prop="appointmentTime" width="180"  v-if="columns[30].visible" show-overflow-tooltip >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.appointmentTime, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="到店时间" class-name="specialColor" align="center" prop="arrivalTime" show-overflow-tooltip width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.arrivalTime, '{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预约状态" align="center" prop="status" width="100" v-if="columns[11].visible" show-overflow-tooltip >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.maker_status" :value="scope.row.makerStatus"/>
        </template>
      </el-table-column>
<!--      <el-table-column label="备注" align="center" prop="remark" show-overflow-tooltip v-if="columns[19].visible" />-->
      <el-table-column label="操作" width="160" align="center" fixed="right" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-popconfirm title="是否确认到店？"  @confirm="popConfirm(scope.row)"  @cancel="popCancel" >
            <el-button v-if="scope.row.makerStatus =='waitStore'" size="mini" type="text" icon="el-icon-edit" slot="reference">确认到店</el-button>
          </el-popconfirm>
<!--          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:customer:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:customer:remove']"
          >取消</el-button>-->
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

    <!-- 添加或修改客户信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="60%" append-to-body>
      <el-form ref="form" :model="form" :inline="true"  :rules="rules" label-width="110px">
        <el-form-item label="下单日期" prop="orderDate">
          <el-date-picker clearable
                          v-model="form.orderDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择下单日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="客户姓名" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入客户姓名" />
        </el-form-item>
        <el-form-item label="客户状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择客户状态">
            <el-option
              v-for="dict in dict.type.customer_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="客户性别" prop="sex">
          <el-select v-model="form.sex" placeholder="请选择客户性别">
            <el-option
              v-for="dict in dict.type.sys_user_sex"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="客户手机" prop="phoneNumber">
          <el-input v-model="form.phoneNumber" placeholder="请输入客户手机号码" />
        </el-form-item>
        <el-form-item label="客户微信" prop="wechat">
          <el-input v-model="form.wechat" placeholder="请输入客户微信" />
        </el-form-item>
        <el-form-item label="客户级别" prop="userType">
          <el-select v-model="form.userType" placeholder="请选择客户级别">
            <el-option
              v-for="dict in dict.type.customer_level"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="用户邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入用户邮箱" />
        </el-form-item>

        <el-form-item label="线索渠道" prop="clueChannel">
          <el-select v-model="form.clueChannel" placeholder="请选择线索渠道">
            <el-option
              v-for="dict in dict.type.clue_channels"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="信息来源" prop="dataSource">
          <el-select v-model="form.dataSource" placeholder="请选择信息来源">
            <el-option
              v-for="dict in dict.type.customer_source"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="客户居住" prop="liveAddress">
          <el-input v-model="form.liveAddress" placeholder="请输入客户居住" />
        </el-form-item>
        <el-form-item label="到店状态" prop="status">
          <el-select v-model="form.storeStatus" placeholder="请选择到店状态">
            <el-option
              v-for="dict in dict.type.to_store_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="已有车型" prop="existModels">
          <el-input v-model="form.existModels" placeholder="请输入已有车型" />
        </el-form-item>
        <el-form-item label="意向车型" prop="intentionCarModels">
          <el-input v-model="form.intentionCarModels" placeholder="请输入意向车型" />
        </el-form-item>
<!--        <el-form-item label="是否试驾" prop="isTestDrive">
          <el-input v-model="form.isTestDrive" placeholder="请输入是否试驾" />
        </el-form-item>
        <el-form-item label="是否报价" prop="isOffer">
          <el-input v-model="form.isOffer" placeholder="请输入是否报价" />
        </el-form-item>
        <el-form-item label="是否金融" prop="isFinance">
          <el-input v-model="form.isFinance" placeholder="请输入是否金融" />
        </el-form-item>
        -->
        <el-form-item label="预计到店" prop="preToStoreDate">
          <el-date-picker clearable
                          v-model="form.preToStoreDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择预计到店">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="4S店" prop="storeName">
          <el-input v-model="form.storeName" placeholder="请输入4S店" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="未订车原因" prop="unBookingCarReason">
          <el-input v-model="form.unBookingCarReason" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 添加客户跟进记录Drawer 抽屉 -->
    <el-drawer :title="followTitle" :before-close="handleDrawerClose" :visible.sync="drawer" size="50%">
      <div style="position: fixed;top: 40px;left: 65%;z-index: 100;font-size: smaller">
        <el-radio-group v-model="reverse">
          <el-radio :label="true">倒序</el-radio>
          <el-radio :label="false">正序</el-radio>
        </el-radio-group>
        <el-button size="mini" type="primary" icon="el-icon-edit" style="position: relative;left: 100px" @click="handleDrawerAddFollowUp" >新增</el-button>
      </div>
      <el-timeline :reverse="reverse">
        <el-timeline-item placement="top"   v-for="(follow, index) in followUpList" :key="index" :timestamp="follow.followUpDate">
          <el-card shadow="hover">
            <p><el-tag>跟进方式：</el-tag>
              <el-select v-model="follow.followUpMethod" disabled>
                <el-option
                  v-for="dict in dict.type.follow_up_method"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                />
              </el-select>
            </p>
            <p><el-tag>级别：</el-tag> {{follow.followLevel}} </p>
            <h4><el-tag>记录：</el-tag> {{follow.followUpRecord}}</h4>
            <p> <el-tag>再次预约到店日期：</el-tag> {{follow.preToStoreDate}} </p>
            <p>提交于 {{follow.createTime}}</p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
      <el-drawer title="新增跟进日志" :append-to-body="true" :visible.sync="innerDrawer">
        <el-form ref="followForm" :model="followForm" :rules="followRules" label-width="140px">
          <el-form-item label="跟进日期" prop="followUpDate">
            <el-date-picker clearable
                            v-model="followForm.followUpDate"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="请选择跟进日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="跟进方式" prop="followUpMethod">
            <el-select v-model="followForm.followUpMethod" placeholder="请选择跟进方式" clearable>
              <el-option
                v-for="dict in dict.type.follow_up_method"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="跟进记录" prop="followUpRecord">
            <el-input v-model="followForm.followUpRecord" type="textarea" placeholder="请输入内容" />
          </el-form-item>
          <el-form-item label="级别" prop="followLevel">
            <el-select v-model="followForm.followLevel" placeholder="请选择级别" clearable>
              <el-option
                v-for="dict in dict.type.customer_level"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="再次预约到店日期" prop="preToStoreDate">
            <el-date-picker clearable
                            v-model="followForm.preToStoreDate"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="请选择再次预约到店日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input v-model="followForm.remark" type="textarea" placeholder="请输入内容" />
          </el-form-item>
        </el-form>
        <div style="text-align: center">
          <el-button type="primary" @click="submitFollowForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-drawer>

    </el-drawer>
  </div>
</template>

<script>
import {
  listCustomerMaker,
  getCustomer,
  delCustomer,
  confirmToStore,
  addCustomer,
  updateCustomer,
  addCustomerFollowRecerd, updateCustomerFollowRecerd, listCustomerFollow
} from "@/api/system/customer";
import Data from "@/views/system/dict/data";

export default {
  name: "bookManagerCustomer",
  dicts: ['maker_status', 'customer_source','customer_status', 'sys_user_sex', 'customer_level', 'clue_channels','follow_result','follow_up_method'],
  data() {
    return {
      drawer:false,
      reverse: true,
      innerDrawer:false,
      dafaultValue:null,
      customerId:null,
      // 遮罩层
      loading: true,
      followTitle:null,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedFollowUp: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 客户信息表格数据
      customerList: [],
      // 跟进模块-客户跟进记录表格数据
      followUpList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userName: null,
        userType: null,
        phoneNumber: null,
        clueChannel: null,
        dataSource: null,
        status: null,
        wechat: null,
        intentionCarModels: null,
        preToStoreDate: null,
        orderDate: null,
        orderByColumn:'appointment_time',
        isAsc:'desc'
      },
      // 表单参数
      form: {

      },
      followForm:{
        followUpDate:null,
        customerId:null,
      },
      // 表单校验
      rules: {
        userName: [
          { required: true, message: "客户名不能为空", trigger: "blur" }
        ],
        orderDate:[
          { required: true, message: "下单日期不能为空", trigger: "blur" }
        ],
        phoneNumber: [
          { required: true, message: "手机号码不能为空", trigger: "blur" }
        ],
        dataSource: [
          { required: true, message: "信息来源不能为空", trigger: "change" }
        ],
        intentionCarModels: [
          { required: true, message: "意向车型不能为空", trigger: "blur" }
        ],
      },
      // 跟进表单校验
      followRules: {
        followUpDate: [
          { required: true, message: "跟进日期不能为空", trigger: "blur" }
        ],
        followUpRecord:[
          { required: true, message: "跟进记录不能为空", trigger: "blur" }
        ],
        followLevel: [
          { required: true, message: "级别不能为空", trigger: "blur" }
        ],
        followUpMethod:[
          { required: true, message: "跟进方式不能为空", trigger: "blur" }
        ],
        followResult:[
          { required: true, message: "跟进结果不能为空", trigger: "blur" }
        ]
      },
// 列表的列的显示隐藏设置
      columns:[
        { key: 0, label: `客户ID`, visible: true },
        { key: 1, label: `客户名`, visible: true },
        { key: 2, label: `客户昵称`, visible: true },
        { key: 3, label: `客户级别`, visible: true },
        { key: 4, label: `用户邮箱`, visible: true },
        { key: 5, label: `手机号码`, visible: true },
        { key: 6, label: `客户性别`, visible: true },
        { key: 7, label: `头像地址`, visible: true },
        { key: 8, label: `线索渠道`, visible: true },
        { key: 9, label: `信息来源`, visible: true },
        { key: 10, label: `客户居住`, visible: true },
        { key: 11, label: `到店状态`, visible: true },
        { key: 12, label: `删除标志`, visible: true },
        { key: 13, label: `最后登录IP`, visible: true },
        { key: 14, label: `最后登录时间`, visible: true },
        { key: 15, label: `创建者`, visible: true },
        { key: 16, label: `创建时间`, visible: true },
        { key: 17, label: `更新者`, visible: true },
        { key: 18, label: `更新时间`, visible: true },
        { key: 19, label: `备注`, visible: true },
        { key: 20, label: `微信号`, visible: true },
        { key: 21, label: `购车类型`, visible: true },
        { key: 22, label: `已有车辆`, visible: true },
        { key: 23, label: `是否评估`, visible: true },
        { key: 24, label: `意向车型`, visible: true },
        { key: 25, label: `对比车型`, visible: true },
        { key: 26, label: `是否试驾`, visible: true },
        { key: 27, label: `是否报价`, visible: true },
        { key: 28, label: `是否金融`, visible: true },
        { key: 29, label: `未订车原因`, visible: true },
        { key: 30, label: `预计到店`, visible: true },
        { key: 31, label: `最后到店`, visible: true },
        { key: 32, label: `4S店`, visible: true },
        { key: 33, label: `下单日期`, visible: true },
        { key: 34, label: `跟进次数`, visible: true },
        { key: 35, label: `最新跟进日`, visible: true },
        { key: 36, label: `最新跟进级别`, visible: true },
        { key: 37, label: `建议下次跟进日`, visible: true },
        { key: 38, label: `跟进超期`, visible: true },
        { key: 39, label: `客户状态`, visible: true },
      ],
    };
  },
  created() {
    this.getList();
    this.dafaultValue = new Date;
    var columns1 = JSON.parse(localStorage.getItem(this.$options.name));
    if(columns1){
      this.columns = columns1;
    }
  },
  methods: {
    /** 查询客户信息列表 */
    getList() {
      this.loading = true;
      this.queryParams.status = 'potential';
      listCustomerMaker(this.queryParams).then(response => {
        this.customerList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询客户跟进信息列表 */
    getFollowList(customerId) {
      this.loading = true;
      let queryParams = {
        customerId:customerId,
        pageNum:1,
        pageSize:1000,
      }
      listCustomerFollow(queryParams).then(response => {
        this.followUpList = response.rows;
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
        userName: null,
        nickName: null,
        userType: null,
        email: null,
        phoneNumber: null,
        sex: null,
        avatar: null,
        clueChannel: null,
        dataSource: null,
        liveAddress: null,
        status: null,
        delFlag: null,
        loginIp: null,
        loginDate: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        remark: null,
        wechat: null,
        buyCarType: null,
        existModels: null,
        isAssessment: 0,
        intentionCarModels: null,
        contrastCarModels: null,
        isTestDrive: null,
        isOffer: null,
        isFinance: null,
        unBookingCarReason: null,
        preToStoreDate: null,
        lastToStoreDate: null,
        storeName: null,
        orderDate: null
      };
      this.followUpList = [];
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
      this.form.status = 'potential';
      this.open = true;
      this.title = "添加客户信息";
    },
    popConfirm(row){
      let param = {
          id : row.id,
          makerStatus:'alreadyStore',
          arrivalTime:this.getDateYYYYMMddHHMMSS()
      }
      confirmToStore(param).then(response => {
        this.$modal.msgSuccess("操作成功");
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
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCustomer(id).then(response => {
        this.form = response.data;
        this.followUpList = response.data.followUpList;
        this.open = true;
        this.title = "修改客户信息";
      });
    },
    /**跟进按钮**/
    handleFollow(row){
      this.drawer = true;
      this.customerId = row.id;
      this.followTitle = row.userName + " 跟进记录";
      this.getFollowList(this.customerId);
    },
    handleDrawerAddFollowUp(){
      this.innerDrawer = true;
      this.followForm = {};
      this.followForm.followUpDate = new Date();
      this.followForm.customerId = this.customerId;
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.followUpList = this.followUpList;
          if (this.form.id != null) {
            updateCustomer(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCustomer(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    submitFollowForm(){
      this.$refs["followForm"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCustomerFollowRecerd(this.followForm).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getFollowList(this.customerId);
            });
          } else {
            addCustomerFollowRecerd(this.followForm).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getFollowList(this.customerId);
              this.innerDrawer = false;
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除客户信息编号为"' + ids + '"的数据项？').then(function() {
        return delCustomer(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 跟进模块-客户跟进记录序号 */
    rowFollowUpIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 跟进模块-客户跟进记录添加按钮操作 */
    handleAddFollowUp() {
      let obj = {};
      obj.followUpDate = "";
      obj.followUpRecord = "";
      obj.preToStoreDate = "";
      obj.remark = "";
      obj.followLevel = "";
      this.followUpList.push(obj);
    },
    /** 跟进模块-客户跟进记录删除按钮操作 */
    handleDeleteFollowUp() {
      if (this.checkedFollowUp.length == 0) {
        this.$modal.msgError("请先选择要删除的跟进模块-客户跟进记录数据");
      } else {
        const followUpList = this.followUpList;
        const checkedFollowUp = this.checkedFollowUp;
        this.followUpList = followUpList.filter(function(item) {
          return checkedFollowUp.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleFollowUpSelectionChange(selection) {
      this.checkedFollowUp = selection.map(item => item.index)
    },
    handleDrawerClose(done){
      this.getList()
      this.drawer = false;
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/customer/export', {
        ...this.queryParams
      }, `customer_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
<style rel="stylesheet/scss" lang="scss">
.specialColor{
  color:red;
}
.title {
  margin: 0px auto 30px auto;
  text-align: center;
  color: #707070;
}

.login-form {
  border-radius: 6px;
  background: #ffffff;
  width: 400px;
  padding: 25px 25px 5px 25px;
  .el-input {
    height: 38px;
    input {
      height: 38px;
    }
  }
  .input-icon {
    height: 39px;
    width: 14px;
    margin-left: 2px;
  }
}
.login-tip {
  font-size: 13px;
  text-align: center;
  color: #bfbfbf;
}
.login-code {
  width: 33%;
  height: 38px;
  float: right;
  img {
    cursor: pointer;
    vertical-align: middle;
  }
}
.el-login-footer {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: #fff;
  font-family: Arial;
  font-size: 12px;
  letter-spacing: 1px;
}
.login-code-img {
  height: 38px;
}
</style>
