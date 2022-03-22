<template>
  <el-tree
    :data="menus"
    :props="defaultProps"
    @node-click="nodeClick"
    node-key="catId"
    highlight-current
    ref="menuTree">
  </el-tree>
</template>

<script>
import {getMenus} from "@/api/mall/product/category";

export default {
  name: "category",

  data() {
    return {
      menus: [],
      expandedKey: [],
      defaultProps: {
        children: "children",
        label: "name"
      },
    };
  },

  created() {
    this.getMenus()
  },

  methods:{
    getMenus() {
      this.$modal.loading("请稍候...");
      getMenus().then(res => {
        this.$modal.closeLoading()
        this.menus = res.page;
      })
    },

    nodeClick(data,node,component) {
      //向父组件发送事件
      this.$emit("tree-node-click",data,node,component)
    },

  }
}
</script>

<style scoped>

</style>
