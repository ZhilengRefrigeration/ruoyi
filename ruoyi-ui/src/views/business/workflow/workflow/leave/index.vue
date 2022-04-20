<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="请假类型" prop="type">
        <el-select style="width: 150px" v-model="queryParams.type" placeholder="请选择请假类型" clearable size="small"
                   @change="handleQuery">
          <el-option
            v-for="dict in dict.type.activiti_leave_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="状态" prop="state">
        <el-select style="width: 150px" v-model="queryParams.state" placeholder="请选择状态" clearable size="small"
                   @change="handleQuery">
          <el-option
            v-for="dict in dict.type.activiti_flow_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['workflow:leave:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['workflow:leave:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="leaveList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="请假类型" align="center" prop="type" :show-overflow-tooltip="true">

        <template slot-scope="scope">
          <dict-tag :options="dict.type.activiti_leave_type" :value="scope.row.type"/>
        </template>

      </el-table-column>
      <el-table-column label="标题" align="center" prop="title" :show-overflow-tooltip="true"/>
      <el-table-column label="原因" align="center" prop="reason" :show-overflow-tooltip="true"/>
      <el-table-column label="任务节点名称" align="center" prop="taskName" :show-overflow-tooltip="true"/>

      <el-table-column label="创建者" align="center" prop="createName" :show-overflow-tooltip="true"/>
      <el-table-column label="开始时间" align="center" prop="leaveStartTime" width="180" :show-overflow-tooltip="true">

      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="leaveEndTime" width="180" :show-overflow-tooltip="true">

      </el-table-column>

      <el-table-column label="状态" align="center" prop="state" :show-overflow-tooltip="true">

        <template slot-scope="scope">
          <dict-tag :options="dict.type.activiti_flow_type" :value="scope.row.state"/>
        </template>

      </el-table-column>
      <el-table-column label="操作" align="center" width="200px" class-name="small-padding fixed-width" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <el-button v-if="'2'===scope.row.state"
                     size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="handleUpdate(scope.row)"
                     v-hasPermi="['workflow:leave:edit']"
          >修改
          </el-button>

          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="historyFory(scope.row)"
            v-hasPermi="['workflow:leave:query']"
          >审批详情
          </el-button>

          <el-button v-if="'0'===scope.row.state"
                     size="mini"
                     type="text"
                     icon="el-icon-edit"
                     @click="checkTheSchedule(scope.row)"
                     v-hasPermi="['workflow:leave:edit']"
          >查看进度
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

    <el-dialog
      :visible.sync="modelVisible"
      title="进度查询"
      width="1680px"
      append-to-body
    >
      <div style="position:relative;height: 100%;">
        <iframe
          id="iframe"
          :src="modelerUrl"
          frameborder="0"
          width="100%"
          height="720px"
          scrolling="auto"
        ></iframe>
      </div>
    </el-dialog>

    <!-- 查看详细信息话框 -->
    <el-dialog :title="title" :visible.sync="open2" width="480px" append-to-body>
      <leaveHistoryForm :businessKey="businessKey" v-if="open2"/>
      <div slot="footer" class="dialog-footer">
        <el-button @click="open2=!open2">关闭</el-button>
      </div>
    </el-dialog>

    <el-dialog :title="title" :visible.sync="open" width="530px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="请假类型" prop="type">
          <el-select v-model="form.type"  placeholder="请选择请假类型" @change="chooseMedicine" style="width: 150px">
            <el-option
              v-for="dict in dict.type.activiti_leave_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="标题" prop="title" style="width: 460px">
          <el-input disabled v-model="form.title"/>
        </el-form-item>
        <el-form-item label="原因" prop="reason">
          <el-input v-model="form.reason" :rows="4" type="textarea" style="width: 380px" placeholder="请输入原因"/>
        </el-form-item>

        <el-form-item label="选择时间" prop="betDateTime">
          <el-date-picker
            style="width: 380px"
            v-model="form.betDateTime"
            type="datetimerange"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            :default-time="['12:00:00']">
          </el-date-picker>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <at-button-group :gap="5">
          <at-button hollow type="success" @click="submitForm">确 定</at-button>
          <at-button hollow @click="cancel">取 消</at-button>
        </at-button-group>
      </div>
    </el-dialog>
  </div>
</template>

<script>


import leaveHistoryForm from "./leaveHistoryForm";
import {
  addLeave,
  delLeave,
  exportLeave,
  getLeave,
  listLeave,
  updateLeave
} from "@/api/business/workflow/workflow/leave";
import {getDefinitionsByInstanceId} from "@/api/business/workflow/activiti/definition";

export default {
  name: 'Leave',
  components: {leaveHistoryForm},
  dicts: ['activiti_leave_type', 'activiti_flow_type'],
  data() {
    return {
      modelVisible: false,
      modelerUrl: '',
      userName: '',
      createName: '',
      businessKey: '',
      //用户信息
      user: {},
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
      // 请假表格数据
      leaveList: [],
      // 弹出层标题
      title: '',
      // 是否显示弹出层
      open: false,
      open2: false,
      // 请假类型字典
      typeOptions: [],
      // 状态字典
      stateOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        type: null,
        title: null,
        reason: null,
        leaveStartTime: null,
        leaveEndTime: null,
        instanceId: null,
        state: null,
        createBy: null
      },
      // 表单参数
      form: {
        //时间日期选择器时间区间
        betDateTime: []
      },
      // 表单校验
      rules: {
        type: [
          {required: true, message: '请假类型不能为空', trigger: 'change'}
        ],
        title: [
          {required: true, message: '标题不能为空', trigger: 'blur'}
        ],
        reason: [
          {required: true, message: '原因不能为空', trigger: 'blur'}
        ],
        betDateTime: [
          {required: true, message: '时间不能为空', trigger: 'blur'}
        ]
      }
    }
  },
  created() {
    this.getList()
  },

  methods: {
    /** 查询请假列表 */
    getList() {
      this.loading = true
      listLeave(this.queryParams).then(response => {
        this.leaveList = response.rows
        this.total = response.total
        this.loading = false
      })
    },


    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        type: null,
        title: null,
        reason: null,
        leaveStartTime: null,
        leaveEndTime: null,
        instanceId: null,
        state: null,
        createBy: null,
        createTime: null,
        updateTime: null
      }
      this.resetForm('form')
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.createName = this.$store.getters.nickName

      this.reset()
      this.open = true
      this.title = '添加请假'
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      getLeave(row.id).then(response => {
        this.form = response.data
        this.open = true
        this.title = '修改请假'
      })
    },
    /** 审批详情 */
    historyFory(row) {
      this.businessKey = row.id
      this.open2 = true
      this.title = '审批详情'

    },
    /** 进度查看 */
    checkTheSchedule(row) {
      getDefinitionsByInstanceId(row.instanceId).then(response => {
        let data = response.data
        // this.url = '/bpmnjs/index.html?type=lookBpmn&deploymentFileUUID='+data.deploymentID+'&deploymentName='+ encodeURI(data.resourceName);
        this.modelerUrl = '/bpmnjs/index.html?type=lookBpmn&instanceId=' + row.instanceId + '&deploymentFileUUID=' + data.deploymentID + '&deploymentName=' + encodeURI(data.resourceName);
        this.modelVisible = true
      })


    },
    /** 提交按钮 */
    submitForm() {
      this.$refs['form'].validate(valid => {
        if (valid) {

          this.form.leaveStartTime=this.form.betDateTime[0]
          this.form.leaveEndTime=this.form.betDateTime[1]

          if (this.form.id != null) {
            updateLeave(this.form).then(response => {
              this.$Message.success('修改成功')
              this.open = false
              this.getList()
            })
          } else {
            addLeave(this.form).then(response => {
              this.$Message.success('新增成功')
              this.open = false
              this.getList()
            })
          }
        }
      })
    },

    /** 导出按钮操作 */
    handleExport() {
      let _that =this
      this.$confirm('是否确认导出所有请假数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function () {

        _that.download('workflow/workflow/leave/export', {
          ..._that.queryParams
        }, `leave_${new Date().getTime()}.xlsx`)

      })
    },

    chooseMedicine() {
      this.form.title = this.createName + "的" + this.form.type + "申请";
    }
  }

}
</script>
