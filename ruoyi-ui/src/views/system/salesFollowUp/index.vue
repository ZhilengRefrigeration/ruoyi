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
      <el-table-column label="客户级别" align="center" prop="userType"  v-if="columns[3].visible" show-overflow-tooltip >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.customer_level" :value="scope.row.userType"/>
        </template>
      </el-table-column>
      <el-table-column label="手机号码" align="center" prop="phoneNumber" width="110"  v-if="columns[5].visible" show-overflow-tooltip />
      <el-table-column label="线索渠道" align="center" prop="clueChannel"  v-if="columns[8].visible" show-overflow-tooltip >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.clue_channels" :value="scope.row.clueChannel"/>
        </template>
      </el-table-column>
      <el-table-column label="信息来源" align="center" prop="dataSource" v-if="columns[9].visible" show-overflow-tooltip >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.customer_source" :value="scope.row.dataSource"/>
        </template>
      </el-table-column>
      <el-table-column label="订单日期" align="center" prop="orderDate" width="120" v-if="columns[33].visible" show-overflow-tooltip >
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
      <el-table-column label="跟进备注" align="center" prop="remark" width="120" />
      <el-table-column label="计划跟进日期" align="center" prop="planFollowUpDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.planFollowUpDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="实际跟进日期" align="center" prop="actualFollowUpDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.actualFollowUpDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="跟进方式" align="center" prop="followUpMethod" >
        <template slot-scope="scope">
          <dict-tag :options="dict.type.follow_up_method" :value="scope.row.followUpMethod"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="160" align="center" fixed="right" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleFollow(scope.row)"
            v-hasPermi="['system:customer:edit']"
          >回访</el-button>
          <!--   <el-button
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
            >删除</el-button>-->
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
<!--        <el-form-item label="信息来源" prop="dataSource">
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
        </el-form-item>-->
<!--        <el-form-item label="到店状态" prop="status">
          <el-select v-model="form.storeStatus" placeholder="请选择到店状态">
            <el-option
              v-for="dict in dict.type.to_store_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>-->
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
<!--        <el-form-item label="最后到店" prop="lastToStoreDate">
          <el-date-picker clearable
                          v-model="form.lastToStoreDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择最后到店">
          </el-date-picker>
        </el-form-item>-->
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
            <p><el-tag>跟进方式:</el-tag> {{follow.followUpMethod}} </p>
            <p> <el-tag>跟进目的:</el-tag> {{follow.objective}} </p>
            <p><el-tag>跟进记录:</el-tag> {{follow.followUpRecord}}</p>
            <p>提交于 {{follow.createTime}}</p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
      <el-drawer title="新增跟进日志" :append-to-body="true" :visible.sync="innerDrawer">
        <el-form ref="followForm" :model="followForm" :rules="followRules" label-width="140px">
          <el-form-item label="计划跟进日期" prop="followUpDate">
            <el-date-picker clearable disabled
                            v-model="followForm.followUpDate"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="请选择跟进日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="跟进备注" prop="followUpRecord">
            <el-input v-model="followForm.remark" disabled placeholder="请输入内容" />
          </el-form-item>
          <el-divider content-position="left">订单车型</el-divider>
            <el-descriptions direction="vertical" :column="6" border size="mini">
<!--              <el-descriptions-item label="状态">正常</el-descriptions-item>-->
              <el-descriptions-item label="VIN" show-overflow-tooltip>{{followForm.carVin}}</el-descriptions-item>
              <el-descriptions-item label="车辆信息" show-overflow-tooltip>{{followForm.carInfo}}</el-descriptions-item>
            </el-descriptions>
          <el-divider></el-divider>
          <el-form-item label="跟进目的" prop="objectiveList">
            <el-select v-model="followForm.objectiveList" multiple placeholder="请选择">
              <el-option
                v-for="item in objectiveList"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
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
import { getCustomerOrderPage, getCustomerOrder, delCustomerOrder, addCustomerOrder, updateCustomerOrder } from "@/api/system/customerOrder";
import {
  getCustomer,
  delCustomer,
  addCustomer,
  updateCustomer,
  addCustomerFollowRecerd, updateCustomerFollowRecerd, listCustomerFollow
} from "@/api/system/customer";
export default {
  name: "salesFollowCustomer",
  dicts: ['to_store_status', 'customer_source','customer_status', 'sys_user_sex', 'customer_level', 'clue_channels','follow_result','car_status','follow_up_method'],
  data() {
    return {
      drawer:false,
      reverse: true,
      innerDrawer:false,
      dafaultValue:null,
      customerId:null,
      customerRow:null,
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
        orderByColumn:'out_date',
        isAsc:'desc'
      },
      // 表单参数
      form: {

      },
      objectiveList:[
        {label:"三包政策",value:"三包政策"},
        {label:"操作说明",value:"操作说明"},
        {label:"7日电访说明",value:"7日电访说明"},
        {label:"首保关怀提醒",value:"首保关怀提醒"},
      ],
      followForm:{
        followUpDate:null,
        customerId:null,
        objectiveList:[]
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
        followUpMethod:[
          { required: true, message: "跟进方式不能为空", trigger: "blur" }
        ],
        objectiveList:[
          { type: 'array', required: true, message: '请至少选择一个跟进目的', trigger: 'change' }
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
      this.queryParams.status = 'order';
      getCustomerOrderPage(this.queryParams).then(response => {
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
        followType:'order'
      }
      let dictType = this.dict.type;
      listCustomerFollow(queryParams).then(response => {
        this.followUpList = response.rows;
        //赋值
        for (let follow of this.followUpList) {
          let followUpMethod = this.getDictLableByVal(dictType.follow_up_method,follow.followUpMethod);
          follow.followUpMethod = followUpMethod;
        }
        this.total = response.total;
        this.loading = false;
      });
    },
    //跟进字典val获取描述
    getDictLableByVal(dictData,val){
      let lable = '';
      dictData.map(i =>{
        if (i.value == val) {
          lable = i.label
        }
      })
      return lable
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
      this.customerRow = row;
      this.customerId = row.id;
      this.followTitle = row.userName + " 跟进记录";
      this.getFollowList(this.customerId);
    },
    handleDrawerAddFollowUp(){
      this.innerDrawer = true;
      this.followForm = {};
      this.followForm.followUpDate = new Date();
      this.followForm.customerId = this.customerId;
      this.followForm.remark = "回访任务-7日回访";
      this.followForm.carVin = this.customerRow.carVin;
      this.followForm.carInfo = this.customerRow.carInfo;
      this.followForm.planFollowUpDate = this.customerRow.planFollowUpDate;
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
            this.followForm.objective = this.followForm.objectiveList.join(",");
            this.followForm.followType=this.customerRow.status;
            this.followForm.customerId=this.customerId;
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
