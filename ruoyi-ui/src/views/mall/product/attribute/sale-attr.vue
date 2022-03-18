<template>
  <div>
      <base-attr :attrtype="0"></base-attr>
  </div>
</template>

<script>

import BaseAttr from './base-attr'
export default {
  //import引入的组件需要注入到对象中才能使用
  components: {BaseAttr},
  props: {},
  name:"sale-attr",
  data() {
    //这里存放数据
    return {};
  },

  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {},

  created() {},


};
</script>
<style scoped>
</style>
