<template>
  <div>

  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'work',
  components: {

  },
  data() {
    return {

    }
  },
  computed: {
    ...mapGetters({
      codeCombo: 'getCodeCombo',
      permissionList: 'getPermission',
      userInfo: 'getUserInfo',
    }),
  },
  activated() {

  },
  created() {

  },
  methods: {

  },
}
</script>

<style lang="scss" scoped>
.layout-box-main {
  padding: 0;
}
.home_wrap {
  background: #eef2fb;
  padding: 20px;
  min-width: 1200px;
}

.bg_wrap {
  position: relative;
  // width: 67%;
  // margin: 30px auto 0;
  width: 100%;
  height: calc(100vh - 1.26rem);
  // margin: -.2rem;
  background-image: url('../../assets/img/home/home_bg.png');
  background-size: 100% 100%;
  .home_bg {
    width: 100%;
    display: block;
    height: auto;
  }
  .txt_wrap {
    position: absolute;
    top: 1rem;
    left: 0.34rem;
    .txt_one {
      font-size: 0.3rem;
      line-height: 0.36rem;
      font-weight: bold;
    }
    .txt_two {
      font-size: 0.24rem;
      line-height: 0.28rem;
      margin-top: 0.1rem;
      margin-left: 0.06rem;
    }
  }
}
</style>
