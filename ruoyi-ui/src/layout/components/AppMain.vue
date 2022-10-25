<template>
  <section class="app-main">
    <div class="left-main">
      <transition name="fade-transform" mode="out-in">
        <keep-alive :include="cachedViews">
          <router-view v-if="!$route.meta.link" :key="key" />
        </keep-alive>
      </transition>
    </div>
    <RightFence></RightFence>
    <iframe-toggle />
  </section>
</template>

<script>
import iframeToggle from "./IframeToggle/index"
import RightFence from '@/components/RightFence.vue'

export default {
  name: 'AppMain',
  components: { iframeToggle, RightFence },
  computed: {
    cachedViews() {
      return this.$store.state.tagsView.cachedViews
    },
    key() {
      return this.$route.path
    }
  }
}
</script>

<style lang="scss" scoped>
.app-main {
  /* 50= navbar  50  */
  background-color: #EBEFFB;
  height: calc(100vh - 50px);
  width: 100%;
  overflow-y: auto;
  position: relative;
}
.left-main{
  width: calc(75% - 40px);
}

.fixed-header + .app-main {
  padding-top: 50px;
}

.hasTagsView {
  .app-main {
    /* 84 = navbar + tags-view = 50 + 34 */
    height: calc(100vh - 80px);
  }

  .fixed-header + .app-main {
    padding-top: 24px;
  }
}
</style>

<style lang="scss">
// fix css style bug in open el-dialog
.el-popup-parent--hidden {
  .fixed-header {
    padding-right: 17px;
  }
}
</style>
