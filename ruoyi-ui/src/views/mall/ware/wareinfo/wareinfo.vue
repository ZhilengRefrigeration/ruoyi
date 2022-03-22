<template>
  <div class="app-container">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="请输入仓库名、仓库地址、区域编码等" clearable style="width: 300px"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button-group>
          <el-button @click="getDataList()" size="mini">查询</el-button>
          <el-button type="primary" @click="addOrUpdateHandle()" size="mini">新增</el-button>
          <el-button type="danger" @click="deleteHandle()"
                     :disabled="dataListSelections.length <= 0" size="mini">批量删除
          </el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-button-group>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>

      <el-table-column
        prop="name"
        header-align="center"
        align="center"
        :show-overflow-tooltip="true"
        label="仓库名">
      </el-table-column>
      <el-table-column
        prop="address"
        header-align="center"
        align="center"
        :show-overflow-tooltip="true"
        label="仓库地址">
      </el-table-column>
      <el-table-column
        prop="areacode"
        header-align="center"
        align="center"
        :show-overflow-tooltip="true"
        label="区域编码">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
import AddOrUpdate from './wareinfo-add-or-update'
import {delWareInfo, getWareInfoList} from "@/api/mall/ware/ware-info";

export default {
  name: "Ware-info",
  data() {
    return {
      dataForm: {
        key: ''
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false
    }
  },
  components: {
    AddOrUpdate
  },
  created() {
    this.getDataList()
  },
  methods: {
    // 获取数据列表
    getDataList() {
      this.dataListLoading = true

      let params = {
        'page': this.pageIndex,
        'limit': this.pageSize,
        'key': this.dataForm.key
      }
      getWareInfoList(params).then(res => {
        this.dataList = res.page.list
        this.totalPage = res.page.totalCount
        this.dataListLoading = false
      })
    },

    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val
      this.pageIndex = 1
      this.getDataList()
    },

    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val
      this.getDataList()
    },

    // 多选
    selectionChangeHandle(val) {
      this.dataListSelections = val
    },

    // 新增 / 修改
    addOrUpdateHandle(id) {
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id)
      })
    },

    // 删除
    deleteHandle(id) {
      var ids = id ? [id] : this.dataListSelections.map(item => {
        return item.id
      })
      this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delWareInfo(ids).then(res => {
          this.$modal.notifySuccess("删除成功")
          this.getDataList()
        })
      })
    },

    /** 重置按钮操作 */
    resetQuery() {
      this.dataForm = {}
      this.pageIndex = 1;
      this.getDataList();
    },

  }
}
</script>
