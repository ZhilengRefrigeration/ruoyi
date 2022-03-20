<template>
  <div>
    <el-select placeholder="请选择" v-model="brandId" filterable clearable>
      <el-option
        v-for="item in brands"
        :key="item.brandId"
        :label="item.brandName"
        :value="item.brandId"
      ></el-option>
    </el-select>
  </div>
</template>

<script>

import {catelogList} from "@/api/mall/product/brand";

export default {
  components: {},
  props: {},
  data() {
    //这里存放数据
    return {
      catId: 0,
      brands: [
        {
          label: "a",
          value: 1
        }
      ],
      brandId: "",
      subscribe: null
    };
  },
  computed: {},
  //监控data中的数据变化
  watch: {
    brandId(val) {
      this.PubSub.publish("brandId", val);
    }
  },
  //方法集合
  methods: {
    getCatBrands() {
      let catId = {
        brand: this.catId
      }
      catelogList(catId).then(res => {
        this.brands = res.data;
      })
    },

  },

  created() {
  },
  mounted() {
    //监听三级分类消息的变化
    this.subscribe = PubSub.subscribe("catPath", (msg, val) => {
      this.catId = val[val.length - 1];
      this.getCatBrands();
    });

  },

  beforeDestroy() {
    PubSub.unsubscribe(this.subscribe); //销毁订阅
  },

};
</script>
<style scoped>
</style>
