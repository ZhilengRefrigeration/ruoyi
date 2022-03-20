<template>
  <div class="app-container">
    <el-row>
      <el-col :span="24">
        <el-form :inline="true" :model="dataForm">
          <el-form-item label="分类">
            <category-cascader :catelogPath.sync="catelogPath"></category-cascader>
          </el-form-item>
          <el-form-item label="品牌">
            <brand-select style="width:160px"></brand-select>
          </el-form-item>
          <el-form-item label="状态">
            <el-select style="width:160px" v-model="dataForm.status" clearable>
              <el-option label="新建" :value="0"></el-option>
              <el-option label="上架" :value="1"></el-option>
              <el-option label="下架" :value="2"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="检索">
            <el-input style="width:200px" v-model="dataForm.key" clearable placeholder="请输入spu名称或描述"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button-group>
            <el-button type="primary" @click="searchSpuInfo" size="mini">查询</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-button-group>
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="24">
        <spuinfo :catId="catId"></spuinfo>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import CategoryCascader from '../../../components/mall/category-cascader'
import BrandSelect from "../../../components/mall/brand-select";
import Spuinfo from "./spuinfo";

export default {
  components: {CategoryCascader, Spuinfo, BrandSelect},
  props: {},
  name: "SpuList",
  data() {
    //这里存放数据
    return {
      catId: 0,
      catelogPath: [],
      dataForm: {
        status: "",
        key: "",
        brandId: null,
        catelogId: null
      },
      catPathSub: null,
      brandIdSub: null,

    };
  },
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
    searchSpuInfo() {
      this.PubSub.publish("dataForm", this.dataForm);
    },

    /** 重置按钮操作 */
    resetQuery() {
      this.dataForm={}
      // this.catelogPath= []
      this.$bus.$emit('clearCategoryCascader',[])
      this.$bus.$emit('clearBrandSelect',[])
    },


  },
  created() {
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
  },

};
</script>
<style scoped>
</style>
