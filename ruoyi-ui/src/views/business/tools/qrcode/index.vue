<template>
  <div class="app-container">
    <div class="div1">
      <p>根据内容生成二维码</p>
    </div>

    <div>
      <el-input
        type="textarea"
        show-word-limit
        :rows="3"
        maxlength="30"
        placeholder="请输入内容"
        v-model="codeText">
      </el-input>

    </div>

    <div style="margin-top: 50px;margin-left: 35%">
      <VueQr v-if="codeText" :margin='8' :size='350' :whiteMargin="true" :logoMargin="3" :logoCornerRadius="20"
             :text='codeText'></VueQr>
    </div>

  </div>
</template>

<script>

import VueQr from 'vue-qr'

export default {
  name: "Qrcode",

  components: {VueQr},

  data() {
    return {
      codeText: null
    }
  },


}
</script>

<style scoped>
.div1 p{
  margin-bottom: 20px;
  color: #999999;
  font-size: 12px;
  margin-left: 5px;
}
</style>
