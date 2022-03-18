<template>
  <div>
    <el-dialog :close-on-click-modal="false" :visible.sync="visible" @closed="dialogClose">
      <el-dialog width="40%" title="选择属性" :visible.sync="innerVisible" append-to-body>
        <div>
          <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
            <el-form-item>
              <el-input v-model="dataForm.key" placeholder="请输入属性名、可选值等" clearable></el-input>
            </el-form-item>
            <el-form-item>
              <el-button @click="getDataList()">查询</el-button>
            </el-form-item>
          </el-form>
          <el-table
            :data="dataList"
            border
            v-loading="dataListLoading"
            @selection-change="innerSelectionChangeHandle"
            style="width: 100%;"
          >
            <el-table-column type="selection" header-align="center" align="center"></el-table-column>
            <el-table-column prop="attrName" header-align="center" align="center" label="属性名"></el-table-column>
            <el-table-column prop="icon" header-align="center" align="center" label="属性图标">
              <template slot-scope="scope">
                <svg-icon :icon-class="scope.row.icon"/>
              </template>
            </el-table-column>
            <el-table-column prop="valueSelect" header-align="center" align="center" label="可选值列表">
              <template slot-scope="scope">
                <el-tooltip placement="left">
                  <div slot="content">
                    <span v-for="(i,index) in scope.row.valueSelect.split(';')" :key="index">{{ i }}<br/></span>
                  </div>
                  <el-tag>{{ scope.row.valueSelect.split(";")[0] + " ..." }}</el-tag>
                </el-tooltip>
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
        </div>
        <div slot="footer" class="dialog-footer">
          <el-button @click="innerVisible = false">取 消</el-button>
          <el-button type="primary" @click="submitAddRealtion">确认新增</el-button>
        </div>
      </el-dialog>
      <el-row>
        <el-col :span="24">
          <el-button-group style="margin-bottom: 10px">
            <el-button type="primary" size="mini" @click="addRelation">新建关联</el-button>
            <el-button
              type="danger"
              size="mini"
              @click="batchDeleteRelation"
              :disabled="dataListSelections.length <= 0"
            >批量删除</el-button>
          </el-button-group>
          <el-table
            :data="relationAttrs"
            style="width: 100%"
            @selection-change="selectionChangeHandle"
            border
          >
            <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
            <el-table-column prop="attrName" label="属性名"></el-table-column>
            <el-table-column prop="valueSelect" label="可选值">
              <template slot-scope="scope">
                <el-tooltip placement="top">
                  <div slot="content">
                    <span v-for="(i,index) in scope.row.valueSelect.split(';')" :key="index">
                      {{i}}
                      <br />
                    </span>
                  </div>
                  <el-tag>{{scope.row.valueSelect.split(";")[0]+" ..."}}</el-tag>
                </el-tooltip>
              </template>
            </el-table-column>
            <el-table-column fixed="right" header-align="center" align="center" label="操作">
              <template slot-scope="scope">
                <el-button type="text" size="small" @click="relationRemove(scope.row.attrId)">移除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>

import {addRelation, attrNoRelation, attrRelation} from "@/api/mall/product/attr-group";
import {deleteRelation} from "@/api/mall/product/attr";

export default {
  components: {},
  props: {},
  data() {
    //这里存放数据
    return {
      attrGroupId: 0,
      visible: false,
      innerVisible: false,
      relationAttrs: [],
      dataListSelections: [],
      dataForm: {
        key: ""
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      innerdataListSelections: []
    };
  },
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
    selectionChangeHandle(val) {
      this.dataListSelections = val;
    },
    innerSelectionChangeHandle(val) {
      this.innerdataListSelections = val;
    },
    addRelation() {
      this.getDataList();
      this.innerVisible = true;
    },

    //批量移出
    batchDeleteRelation(val) {
      let postData = [];
      this.dataListSelections.forEach(item => {
        postData.push({ attrId: item.attrId, attrGroupId: this.attrGroupId });
      });

      this.deleteRelation(postData)
    },


    //移除关联
    relationRemove(attrId) {
      let data = [];
      data.push({ attrId, attrGroupId: this.attrGroupId });

      this.deleteRelation(data)
    },

    //真正的删除
    deleteRelation(ids) {
      deleteRelation(ids).then(res =>{
        this.$modal.notifySuccess("删除成功")
        this.init(this.attrGroupId);
      })
    },

    //批量保存
    submitAddRealtion() {
      this.innerVisible = false;
      //准备数据
      if (this.innerdataListSelections.length > 0) {
        let postData = [];
        this.innerdataListSelections.forEach(item => {
          postData.push({ attrId: item.attrId, attrGroupId: this.attrGroupId });
        });
        addRelation(postData).then(res =>{
          this.$modal.notifySuccess("新增关联成功")
          this.$emit("refreshData");
          this.init(this.attrGroupId);
        })
      }
    },

    init(id) {
      this.attrGroupId = id || 0;
      this.visible = true;

      attrRelation(this.attrGroupId).then(res =>{
        this.relationAttrs = res.data;
      })
    },

    dialogClose() {},

    // 获取数据列表
    getDataList() {
      this.dataListLoading = true;
      let params ={
        page: this.pageIndex,
        limit: this.pageSize,
        key: this.dataForm.key
      }
      attrNoRelation(this.attrGroupId,params).then(res =>{
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
    }
  }
};
</script>
<style scoped>
</style>
