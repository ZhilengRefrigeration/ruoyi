<template>
  <!-- 导入表 -->
  <el-dialog title="导入表" :visible.sync="visible" width="1200px" top="5vh" append-to-body>
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true">
      <el-form-item label="库名称" prop="schemaName">
        <el-select v-model="queryParams.schemaName" placeholder="请选择库名称">
          <el-option
            v-for="item in schemaList"
            :key="item"
            :label="item"
            :value="item">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="表名称" prop="tableName">
        <el-input
          v-model="queryParams.tableName"
          placeholder="请输入表名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="表描述" prop="tableComment">
        <el-input
          v-model="queryParams.tableComment"
          placeholder="请输入表描述"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-row>
      <el-table @row-click="clickRow" ref="table" :data="dbTableList" @selection-change="handleSelectionChange" height="260px">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="schemaName" label="库名称" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="tableName" label="表名称" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="tableComment" label="表描述" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column prop="createTime" label="创建时间"></el-table-column>
        <el-table-column prop="updateTime" label="更新时间"></el-table-column>
      </el-table>
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    </el-row>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="handleImportTable">确 定</el-button>
      <el-button @click="visible = false">取 消</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { listSchema, listDbTable, importTable } from "@/api/tool/gen";
export default {
  data() {
    return {
      // 遮罩层
      visible: false,
      // 选中数组值
      tables: [],
      // 总条数
      total: 0,
      // 表数据
      dbTableList: [],
      // 数据库列表
      schemaList: [],
      // 上一次选中数据库的名称，这个名称会在点搜索按钮的时候同步为 queryParams.tableName
      preTableSchema: undefined,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        schemaName: undefined,
        tableName: undefined,
        tableComment: undefined
      }
    };
  },
  methods: {
    // 显示弹框
    show() {
      this.getSchemaList();
      this.visible = true;
    },
    clickRow(row) {
      this.$refs.table.toggleRowSelection(row);
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.tables = selection.map(item => item.tableName);
    },
    getSchemaList() {
      // 首先获取数据库信息
      listSchema().then(schemaRes => {
        // 至少有一个数据库
        if (schemaRes.code === 200) {
          this.schemaList = schemaRes.data;
          this.queryParams.schemaName = schemaRes.data[0];
          this.getList();
        }
      })
    },
    // 查询表数据
    getList() {
      // 查询之前先要将原有的选中的表空间清空
      this.tables = []
      // 同步preSchemaList
      this.preTableSchema = this.queryParams.schemaName
      listDbTable(this.queryParams).then(res => {
        if (res.code === 200) {
          this.dbTableList = res.rows;
          this.total = res.total;
        }
      });
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
    /** 导入按钮操作 */
    handleImportTable() {
      const tableNames = this.tables.join(",");
      if (tableNames == "") {
        this.$modal.msgError("请选择要导入的表");
        return;
      }
      importTable({ tables: tableNames }, this.preTableSchema).then(res => {
        this.$modal.msgSuccess(res.msg);
        if (res.code === 200) {
          this.visible = false;
          this.$emit("ok");
        }
      });
    }
  }
};
</script>
