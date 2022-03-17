<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="6">
        <category @tree-node-click="treenodeclick"></category>
      </el-col>
      <el-col :span="18">
        <div class="mod-config">
          <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
            <el-form-item>
              <el-input v-model="dataForm.key" placeholder="请输入组名、描述、分类id等" style="width: 300px" clearable></el-input>
            </el-form-item>
            <el-form-item>
              <el-button-group>
                <el-button @click="getDataList()">查询</el-button>
                <el-button type="success" @click="getAllDataList()">查询全部</el-button>
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
            <el-table-column prop="attrGroupName" header-align="center" align="center" label="组名"></el-table-column>
            <el-table-column prop="sort" header-align="center" align="center" label="排序"></el-table-column>
            <el-table-column prop="descript" header-align="center" align="center" label="描述"></el-table-column>
            <el-table-column prop="icon" header-align="center" align="center" label="组图标"></el-table-column>
            <el-table-column prop="catelogId" header-align="center" align="center" label="所属分类id"></el-table-column>
            <el-table-column
              fixed="right"
              header-align="center"
              align="center"
              width="150"
              label="操作"
            >
              <template slot-scope="scope">
                <el-button type="text" size="small" @click="relationHandle(scope.row.attrGroupId)">关联</el-button>
                <el-button
                  type="text"
                  size="small"
                  @click="addOrUpdateHandle(scope.row.attrGroupId)"
                >修改
                </el-button>
                <el-button type="text" size="small" @click="deleteHandle(scope.row.attrGroupId)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>

          <pagination
            v-show="totalPage>0"
            :total="totalPage"
            :page.sync="pageIndex"
            :limit.sync="pageSize"
            @pagination="getDataList"
          />
          <!-- 弹窗, 新增 / 修改 -->
          <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>

          <!-- 修改关联关系 -->
          <!--                  <relation-update v-if="relationVisible" ref="relationUpdate" @refreshData="getDataList"></relation-update>-->
        </div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
/**
 * 父子组件传递数据
 * 1)、子组件给父组件传递数据，事件机制；
 *    子组件给父组件发送一个事件，携带上数据。
 * // this.$emit("事件名",携带的数据...)
 */
import Category from '../../../components/mall/category'

import {getAttrGroupList, delAttrGroup} from "@/api/mall/product/attr-group";

import AddOrUpdate from "./attrgroup-add-or-update";
// import RelationUpdate from "./attr-group-relation";

export default {
  components: {Category, AddOrUpdate, /*RelationUpdate*/},
  props: {},
  data() {
    return {
      catId: 0,
      dataForm: {
        key: ""
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false,
      relationVisible: false
    };
  },
  created() {
    this.getDataList();
  },
  methods: {
    //处理分组与属性的关联
    relationHandle(groupId) {
      this.relationVisible = true;
      this.$nextTick(() => {
        this.$refs.relationUpdate.init(groupId);
      });
    },

    //感知树节点被点击
    treenodeclick(data, node, component) {
      if (node.level === 3) {
        this.catId = data.catId;
        this.getDataList(); //重新查询
      }
    },
    getAllDataList() {
      this.catId = 0;
      this.getDataList();
    },
    // 获取数据列表
    getDataList() {
      this.dataListLoading = true;

      let params = {
        page: this.pageIndex,
        limit: this.pageSize,
        key: this.dataForm.key,
        catelogId: this.catId,
      }
      getAttrGroupList(params).then(res => {
        this.dataListLoading = false;
        this.dataList = res.page.list
      })
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
        return item.attrGroupId;
      });
      this.$confirm(
        `确定对[id=${ids.join(",")}]进行[${id ? "删除" : "批量删除"}]操作?`,
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }
      ).then(() => {
        delAttrGroup(ids).then(res => {
          this.$modal.notifySuccess("删除成功");
          this.getDataList();
        })
      });
    },

    /** 重置按钮操作 */
    resetQuery() {
      this.dataForm={}
      this.handleQuery();
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.pageIndex = 1;
      this.getDataList();
    },
  }
};
</script>
<style scoped>
</style>
