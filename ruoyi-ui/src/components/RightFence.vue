<template>
  <div class="right-fence-box">
    <div class="content">
      右侧边栏111

    </div>

  </div>
</template>

<script>
export default {
  data() {
    return {}
  },
}
</script>

<style  lang="scss">
//右侧边栏布局
.right-fence-box {
  //   width: 423px;
  width: 25.5%;
  min-height: calc(100% - 100px);
  border-radius: 6px;
  box-shadow: 0px 1px 8px 0px rgba(0, 0, 0, 0.1);
  border-radius: 6px;
  background: #ffffff;
  position: absolute;
  overflow-y: auto;
  right: 24px;
  top: 24px;
  .content{
    margin-top: 20%;
    text-align: center;
  }
  .tab{
    font-size: 2px;
    background: red;
  }
}
</style>
