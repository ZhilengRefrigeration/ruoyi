<template>
  <div class="app-container">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form :inline="true" :model="dataForm">
        <el-form-item label="分类">
          <category-cascader :catelogPath.sync="catelogPath"></category-cascader>
        </el-form-item>
        <el-form-item label="品牌">
          <brand-select style="width:160px"></brand-select>
        </el-form-item>
        <el-form-item label="价格">
          <el-input-number style="width:140px" v-model="dataForm.price.min" :min="0"></el-input-number>
          -
          <el-input-number style="width:140px" v-model="dataForm.price.max" :min="0"></el-input-number>
        </el-form-item>
        <el-form-item label="检索">
          <el-input maxlength="100" style="width:300px" v-model="dataForm.key" clearable
                    placeholder="请输入sku名称或描述、标题、副标题等"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button size="mini" type="primary" @click="searchSkuInfo">查询</el-button>
          <el-button size="mini" icon="el-icon-refresh" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;"
      @expand-change="getSkuDetails"
    >
      <el-table-column type="expand">
        <template slot-scope="scope">
          <el-form label-position="left" class="demo-table-expand">
            <el-row :gutter="20">
              <el-col :span="8">
                <el-form-item label="商品标题">
                  <span>{{ scope.row.skuTitle }}</span>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="商品副标题">
                  <span>{{ scope.row.skuSubtitle }}</span>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="商品描述">
                  <span>{{ scope.row.skuDesc }}</span>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="8">
                <el-form-item label="分类名称">
                  <span>{{ scope.row.catalogName }}</span>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="spu名称">
                  <span>{{ scope.row.spuName }}</span>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="品牌名称">
                  <span>{{ scope.row.brandName }}</span>
                </el-form-item>
              </el-col>
            </el-row>

          </el-form>
        </template>
      </el-table-column>
      <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
      <el-table-column prop="skuName" header-align="center" align="center" label="名称"></el-table-column>
      <el-table-column prop="skuDefaultImg" header-align="center" align="center" label="默认图片">
        <template slot-scope="scope">
          <img :src="scope.row.skuDefaultImg" style="width:80px;height:80px;"/>
        </template>
      </el-table-column>
      <el-table-column prop="price" header-align="center" align="center" label="价格"></el-table-column>
      <el-table-column prop="saleCount" header-align="center" align="center" label="销量"></el-table-column>
      <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="previewHandle(scope.row.skuId)">预览</el-button>
          <el-button type="text" size="small" @click="commentHandle(scope.row.skuId)">评论</el-button>
          <el-dropdown
            @command="handleCommand(scope.row,$event)"
            size="small"
            split-button
            type="text"
          >
            更多
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="uploadImages">上传图片</el-dropdown-item>
              <el-dropdown-item command="seckillSettings">参与秒杀</el-dropdown-item>
              <el-dropdown-item command="reductionSettings">满减设置</el-dropdown-item>
              <el-dropdown-item command="discountSettings">折扣设置</el-dropdown-item>
              <el-dropdown-item command="memberPriceSettings">会员价格</el-dropdown-item>
              <el-dropdown-item command="stockSettings">库存管理</el-dropdown-item>
              <el-dropdown-item command="couponSettings">优惠劵</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
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
</template>

<script>
import CategoryCascader from '../../../components/mall/category-cascader'
import BrandSelect from "../../../components/mall/brand-select";
import {getSkuList} from "@/api/mall/product/sku-info";

export default {
  data() {
    return {
      catPathSub: null,
      brandIdSub: null,
      dataForm: {
        key: "",
        brandId: 0,
        catelogId: 0,
        price: {
          min: 0,
          max: 0
        }
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false,
      catelogPath: []
    };
  },
  components: {
    CategoryCascader,
    BrandSelect
  },
  created() {
    this.getDataList();
  },
  methods: {
    getSkuDetails(row, expand) {
      //sku详情查询
    },

    //处理更多指令
    handleCommand(row, command) {
      if ("stockSettings" === command) {
        this.$router.push({path: "/ware-sku", query: {skuId: row.skuId}});
      }
    },

    searchSkuInfo() {
      this.getDataList();
    },
    // 获取数据列表
    getDataList() {
      this.dataListLoading = true;
      let params = {
        page: this.pageIndex,
        limit: this.pageSize,
        key: this.dataForm.key,
        catelogId: this.dataForm.catelogId,
        brandId: this.dataForm.brandId,
        min: this.dataForm.price.min,
        max: this.dataForm.price.max
      }
      getSkuList(params).then(res => {
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


    /** 重置按钮操作 */
    resetQuery() {
      this.dataForm = {
        price: {
          min: 0,
          max: 0
        }
      }
      this.$bus.$emit('clearCategoryCascader', [])
      this.$bus.$emit('clearBrandSelect', [])

      this.pageIndex = 1
      this.getDataList()
    },
  },

  mounted() {
    this.catPathSub = PubSub.subscribe("catPath", (msg, val) => {
      this.dataForm.catelogId = val[val.length - 1];
    });
    this.brandIdSub = PubSub.subscribe("brandId", (msg, val) => {
      this.dataForm.brandId = val;
    });
  },
  beforeDestroy() {
    PubSub.unsubscribe(this.catPathSub);
    PubSub.unsubscribe(this.brandIdSub);
  }
};
</script>
