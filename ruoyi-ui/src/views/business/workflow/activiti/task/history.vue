<template>
  <div class="app-container">

    <el-table v-loading="loading" :data="tastList">
      <el-table-column label="流程名称" align="center" prop="instanceName"/>
      <el-table-column label="任务节点名称" align="center" prop="name"/>
      <el-table-column label="创建时间" align="center" prop="createdDate"/>
      <el-table-column label="结束时间" align="center" prop="endDate"/>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="details(scope.row)"
          >详情
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 审批对话框 -->
    <el-dialog :title="title" :visible.sync="open" v-if="open" width="500px" append-to-body>
      <leaveHistoryForm :businessKey="businessKey" v-if="'leave'===definitionKey"/>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

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
import {listHistoryTask} from "@/api/business/workflow/activiti/task";
import leaveHistoryForm from "@/views/business/workflow/workflow/leave/leaveHistoryForm";

export default {
  name: "HistoryTask",
  components: {leaveHistoryForm},

  data() {
    return {
      id: '',
      definitionKey: '',
      businessKey: '',

      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },

      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,

      // 总条数
      total: 0,
      // 请假表格数据
      tastList: [],
    }
  },

  created() {
    this.getList();
  },
  methods: {
    getList() {
      this.loading = true;
      listHistoryTask(this.queryParams).then(response => {
        this.tastList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },


    /** 详情按钮操作 */
    details(row) {
      this.reset();
      this.definitionKey = row.definitionKey;
      this.businessKey = row.businessKey;
      this.id = row.id;

      this.open = true;
      this.title = "详情";
    },

    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.definitionKey = '',
        this.businessKey = '',
        this.form = {
          formData: [],
        };
      this.resetForm("form");
    },

  },

}
</script>

<style scoped>

</style>
