<template>
  <div class="app-container">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key" placeholder="请输入等级名称、备注等" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button-group>
          <el-button @click="getDataList()">查询</el-button>
          <el-button
            type="primary"
            @click="addOrUpdateHandle()"
          >新增
          </el-button>
          <el-button
            type="danger"
            @click="deleteHandle()"
            :disabled="dataListSelections.length <= 0"
          >批量删除
          </el-button>
          <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
        </el-button-group>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;"
    >
      <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
      <el-table-column prop="name" header-align="center" align="center" label="等级名称"></el-table-column>
      <el-table-column prop="growthPoint" header-align="center" align="center" label="所需成长值"></el-table-column>
      <el-table-column prop="defaultStatus" header-align="center" align="center" label="默认等级">
        <template slot-scope="scope">
          <i class="el-icon-success" v-if="scope.row.defaultStatus===1"></i>
          <i class="el-icon-error" v-else></i>
        </template>
      </el-table-column>
      <el-table-column prop="freeFreightPoint" header-align="center" align="center" label="免运费标准"></el-table-column>
      <el-table-column
        prop="commentGrowthPoint"
        header-align="center"
        align="center"
        label="评价获取成长值"
      ></el-table-column>
      <el-table-column label="特权" align="center">
        <el-table-column
          prop="priviledgeFreeFreight"
          header-align="center"
          align="center"
          label="免邮特权"
        >
          <template slot-scope="scope">
            <i class="el-icon-success" v-if="scope.row.priviledgeFreeFreight===1"></i>
            <i class="el-icon-error" v-else></i>
          </template>
        </el-table-column>
        <el-table-column
          prop="priviledgeMemberPrice"
          header-align="center"
          align="center"
          label="会员价格特权"
        >
          <template slot-scope="scope">
            <i class="el-icon-success" v-if="scope.row.priviledgeMemberPrice===1"></i>
            <i class="el-icon-error" v-else></i>
          </template>
        </el-table-column>
        <el-table-column
          prop="priviledgeBirthday"
          header-align="center"
          align="center"
          label="生日特权"
        >
          <template slot-scope="scope">
            <i class="el-icon-success" v-if="scope.row.priviledgeBirthday===1"></i>
            <i class="el-icon-error" v-else></i>
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column prop="note" header-align="center" align="center" label="备注"></el-table-column>
      <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
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
      layout="total, sizes, prev, pager, next, jumper"
    ></el-pagination>
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
import AddOrUpdate from "./memberlevel-add-or-update";
import {delMemberLevel, getMemberLevelList} from "@/api/mall/member/level";

export default {
  name:"Member-Level",
  data() {
    return {
      dataForm: {
        key: ""
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false
    };
  },
  components: {
    AddOrUpdate
  },
  created() {
    this.getDataList();
  },
  methods: {
    // 获取数据列表
    getDataList() {
      this.dataListLoading = true;
      let data = {
        page: this.pageIndex,
        limit: this.pageSize,
        key: this.dataForm.key
      }
      getMemberLevelList(data).then(res => {
        this.dataList = res.page.list;
        this.totalPage = res.page.totalCount;
        this.dataListLoading = false;
      })

    },
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList();
    },

    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList();
    },

    // 多选
    selectionChangeHandle(val) {
      this.dataListSelections = val;
    },
    // 新增 / 修改
    addOrUpdateHandle(id) {
      this.addOrUpdateVisible = true;
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id);
      });
    },

    // 删除
    deleteHandle(id) {
      var ids = id ? [id] : this.dataListSelections.map(item => {
        return item.id;
      });
      this.$confirm(
        `确定对[id=${ids.join(",")}]进行[${id ? "删除" : "批量删除"}]操作?`, "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      ).then(() => {

        delMemberLevel(ids).then(res => {
          this.$modal.notifySuccess("删除成功")
          this.getDataList();
        })

      });
    },

    /** 重置按钮操作 */
    resetQuery() {
      this.dataForm = {}
      this.catId = 0
      this.pageIndex = 1;
      this.getDataList();
    },

  }
};
</script>
