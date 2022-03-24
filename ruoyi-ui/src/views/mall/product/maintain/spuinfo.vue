<template>
  <div class="app-container">
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;"
    >
      <el-table-column type="selection" header-align="center" align="center" width="50"></el-table-column>
      <el-table-column prop="spuName" header-align="center" align="center" label="名称" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="spuDescription" header-align="center" align="center" label="描述" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="catalogName" header-align="center" align="center" label="分类" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="brandName" header-align="center" align="center" label="品牌" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="weight" header-align="center" align="center" width="80px" label="重量(kg)" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="publishStatus" header-align="center" width="80px" align="center" label="上架状态" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.publishStatus === 0">新建</el-tag>
          <el-tag v-if="scope.row.publishStatus === 1">已上架</el-tag>
          <el-tag v-if="scope.row.publishStatus === 2">已下架</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" header-align="center" align="center" label="创建时间" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="updateTime" header-align="center" align="center" label="修改时间" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column fixed="right" header-align="center" align="center" width="150" label="操作" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.publishStatus === 0"
            type="text"
            size="small"
            @click="productUp(scope.row.id)"
          >上架</el-button>
          <el-button type="text" size="small" @click="attrUpdateShow(scope.row)">规格</el-button>
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
import {getSpuList} from "@/api/mall/product/spu-info";

export default {
  data() {
    return {
      dataSub: null,
      dataForm: {},
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false
    };
  },
  props: {
    catId: {
      type: Number,
      default: 0
    },
  },
  components: {},
  created() {
    this.getDataList();
  },
  methods: {
    productUp(id) {
      this.$http({
        url: this.$http.adornUrl("/product/spuinfo/" + id + "/up"),
        method: "post"
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message({
            message: "操作成功",
            type: "success",
            duration: 1500,
            onClose: () => {
              this.getDataList();
            }
          });
        } else {
          this.$message.error(data.msg);
        }
      });
    },

    attrUpdateShow(row) {
      this.$router.push({
        path: "/mall/product/mall-attribute/spu-attribute",
        query: { spuId: row.id, catalogId: row.catalogId }
      });
    },
    // 获取数据列表
    getDataList() {
      this.dataListLoading = true;
      let param = {};
      Object.assign(param, this.dataForm, {
        page: this.pageIndex,
        limit: this.pageSize
      });
      getSpuList(param).then(res =>{
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

    },

    //重置值
    rest() {
      this.pageIndex = 1;
      this.getDataList();
    },


  },

  mounted() {
    this.dataSub = PubSub.subscribe("dataForm", (msg, val) => {
      this.dataForm = val;
      this.getDataList();
    });
  },

  beforeDestroy() {
    PubSub.unsubscribe(this.dataSub);
  }
};
</script>
