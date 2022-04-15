<template>
  <div class="">
    <el-button type="text" @click="autoFormat" style="margin-left: 60px;margin-bottom: 5px;margin-top: 5px">格式化</el-button>
    <el-button type="text" @click="clear" style="margin-left: 60px;margin-bottom: 5px;margin-top: 5px">清空</el-button>
    <div class="editor-container">
      <json-editor ref="jsonEditor" v-model="value"/>
    </div>
  </div>
</template>

<script>
import JsonEditor from '@/components/JsonEdit'


export default {
  name: 'JsonEdit',
  components: {JsonEditor},

  data() {
    return {
      value: JSON.parse("{\"jsonData\":\"test\"}")
    }
  },

  methods: {
    //格式化
    autoFormat() {
      this.$refs.jsonEditor.$emit("autoFormat",this.value)    //子组件$on中的名字
    },

    //清空
    clear() {
      this.value={}
    },
  },
}
</script>

<style scoped>
.editor-container {
  position: relative;
  height: 100%;
}
</style>

