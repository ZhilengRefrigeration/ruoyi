<template>
  <div class="app-container">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input size="mini" v-model="dataForm.key" placeholder="请输入品牌名、介绍、检索首字母等" clearable style="width: 300px"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button-group>
          <el-button size="mini" @click="handleQuery()">查询</el-button>
          <el-button
            type="primary"
            size="mini"
            @click="addOrUpdateHandle()"
          >新增
          </el-button>
          <el-button
            type="danger"
            size="mini"
            @click="deleteHandle()"
            :disabled="dataListSelections.length <= 0"
          >批量删除
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
      style="width: 100%;"
    >
      <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
      <el-table-column prop="name" header-align="center" align="center" label="品牌名" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="logo" header-align="center" align="center" label="品牌logo" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <img :src="scope.row.logo" style="width: 40px; height: 30px" alt=""/>
        </template>
      </el-table-column>
      <el-table-column prop="descript" header-align="center" align="center" label="介绍" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="showStatus" header-align="center" align="center" label="显示状态">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.showStatus"
            active-color="#13ce66"
            inactive-color="#ff4949"
            :active-value="1"
            :inactive-value="0"
            @change="updateBrandStatus(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column prop="firstLetter" header-align="center" align="center" label="检索首字母"></el-table-column>
      <el-table-column prop="sort" header-align="center" align="center" label="排序"></el-table-column>
      <el-table-column fixed="right" header-align="center" align="center" width="250" label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="updateCatelogHandle(scope.row.brandId)">关联分类</el-button>
          <el-button type="text" size="small" @click="addOrUpdateHandle(scope.row.brandId)">修改</el-button>
          <el-button type="text" size="small" @click="deleteHandle(scope.row.brandId)">删除</el-button>
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

    <el-dialog title="关联分类" :visible.sync="cateRelationDialogVisible" width="30%" @close="closeDialog">
      <div style="margin-bottom: 10px">
        <el-popover placement="right-end" v-model="popCatelogSelectVisible">
          <category-cascader :catelogPath.sync="catelogPath"></category-cascader>
          <div style="text-align: right; margin: 0">
            <el-button size="mini" type="text" @click="popCatelogSelectVisible = false">取消</el-button>
            <el-button type="primary" size="mini" @click="addCatelogSelect">确定</el-button>
          </div>
          <el-button slot="reference" icon="el-icon-circle-plus-outline" size="mini">新增关联</el-button>
        </el-popover>
      </div>
      <el-table :data="cateRelationTableData" style="width: 100%">
        <el-table-column prop="brandName" label="品牌名"></el-table-column>
        <el-table-column prop="catelogName" label="分类名"></el-table-column>
        <el-table-column fixed="right" header-align="center" align="center" label="操作">
          <template slot-scope="scope">
            <el-button
              type="text"
              size="small"
              @click="deleteCateRelationHandle(scope.row.id,scope.row.brandId)"
            >移除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cateRelationDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="cateRelationDialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import AddOrUpdate from "./brand-add-or-update";
import {editBrand, getBrandList,delBrand} from "@/api/mall/product/brand";
import CategoryCascader from "../../components/mall/category-cascader"
import {
  addCategoryBrandRelation,
  categoryBrandRelationList,
  delCategoryBrandRelation
} from "@/api/mall/product/category-relation"

export default {
  name: "Brand",
  data() {
    return {
      dataForm: {
        key: ""
      },
      brandId: 0,
      catelogPath: [],
      dataList: [],
      cateRelationTableData: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false,
      cateRelationDialogVisible: false,
      popCatelogSelectVisible: false
    };
  },
  components: {
    AddOrUpdate,
    CategoryCascader
  },
  created() {
    this.getDataList();
  },
  methods: {
    addCatelogSelect() {
      this.popCatelogSelectVisible = false;
      let data ={
        brandId: this.brandId,
        catelogId: this.catelogPath[this.catelogPath.length - 1]
      }
      addCategoryBrandRelation(data).then(res =>{
        this.getCateRelation();
      })
    },

    deleteCateRelationHandle(id, brandId) {
      delCategoryBrandRelation([id]).then(res =>{
        this.getCateRelation();
      })
    },

    updateCatelogHandle(brandId) {
      this.cateRelationDialogVisible = true;
      this.brandId = brandId;
      this.getCateRelation();
    },


    getCateRelation() {
      let data ={
        brandId: this.brandId
      }
      categoryBrandRelationList(data).then(res =>{
        this.cateRelationTableData = res.page;
      })
    },

    // 获取数据列表
    getDataList() {
      this.dataListLoading = true;
      let params = {
        page: this.pageIndex,
        limit: this.pageSize,
        key: this.dataForm.key
      }
      getBrandList(params).then(res => {
        this.dataListLoading = false;
        this.totalPage = res.page.totalCount;
        this.dataList = res.page.list;
      })
    },

    updateBrandStatus(data) {
      //发送请求修改状态
      editBrand(data).then(res => {
        this.$modal.notifySuccess("修改成功");
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
          return item.brandId;
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
        this.$modal.loading("请稍候...");
        delBrand(ids).then(res => {
          this.$modal.notifySuccess("删除成功");
          this.getDataList();
          this.$modal.closeLoading()
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

    //关闭对话框时清空级联分类
    closeDialog() {
      this.catelogPath=[]
    },
  }
};
</script>
