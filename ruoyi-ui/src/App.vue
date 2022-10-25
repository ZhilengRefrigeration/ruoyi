<template>
  <div id="app">
    <router-view />
    <theme-picker />
  </div>
</template>

<script>
import ThemePicker from "@/components/ThemePicker";
import WaterMark from '@/plugins/waterMark.js'

export default {
  name: "App",
  components: { ThemePicker },
    metaInfo() {
        return {
            title: this.$store.state.settings.dynamicTitle && this.$store.state.settings.title,
            titleTemplate: title => {
                return title ? `${title} - ${process.env.VUE_APP_TITLE}` : process.env.VUE_APP_TITLE
            }
        }
    },
  mounted() {
    // this.initWartHeight = document.body.clientHeight
    // this.initWaterMark(this.initWartHeight)
    // window.addEventListener('scroll', this.handleScroll, true)
  },
  methods: {
    //添加水印
    initWaterMark(height) {
      let userInfo = this.$store.state.user.userInfo,
        _time = this.$global.createTime('day'),
        _str = '测试角色' + ' / ' + '测试用户' + ' / ' + _time
      console.log(userInfo, '水印')
      WaterMark(_str, height)
    },
    handleScroll() {
      this.$nextTick(() => {
        let height = document.getElementById('app-main').scrollHeight
        console.log('内容区高度', height)
        if (height && this.initWartHeight != height) {
          this.initWartHeight = height
          this.initWaterMark(height)
        }
      })
    },
    // 根据当前路由设置hasOpen
    isOpenIframePage() {
      console.log(this.componentsArr, 'this.componentsArr')
      const target = this.componentsArr.find(item => {
        console.log(item, 'item')
        return item.path === this.$route.query.path
      })
      if (target && !target.hasOpen) {
        target.hasOpen = true
      }
    },
    // 遍历路由的所有页面，把含有iframeComponent标识的收集起来
    getComponentsArr() {
      // debugger
      const router = this.$router
      const routes = router.options.routes
      // console.log(router.options.routes, '打印 router.options.routes')
      const iframeArr = [] //routes.filter(item => item.iframeComponent)
      const [{ iframeComponent = null } = {}] = iframeArr || []
      // if (sessionStorage.getItem('iframeMenuList')) return false
      // 处理单客画像客户明细切换页签改变问题
      const [{ name: custRouteName, path, component }] = routes.filter(item => item.name === 'uCustView')
      const custComponent = { name: custRouteName, menuUrl: path, component }
      // console.log(custViews, 'custViews')
      if (sessionStorage.getItem('iframeMenuList')) {
        const iframeMenuList = JSON.parse(sessionStorage.getItem('iframeMenuList')) || []
        // console.log(routes,iframeArr,'iframeArr')
        console.log(iframeMenuList, 'options 1111')

        return [...iframeMenuList, custComponent].map(item => {
          const name = item.menuId || item.menuUrl.replace('/', '')
          return {
            name,
            path: item.menuUrl,
            menuUrl: item.menuUrl,
            hasOpen: false, // 是否打开过，默认false
            component: iframeComponent, // 组件文件的引用
            // fullPath: item.fullPath,
          }
        })
      }
      return false
    },
    // 初始化iframe
    initIframe() {
      // debugger
      const componentsArr = this.getComponentsArr()
      console.log(componentsArr, 'componentsArr')
      if (componentsArr) {
        componentsArr.forEach(item => {
          Vue.component(item.name, item.component)
        })
        this.componentsArr = componentsArr
        console.log(this.componentsArr, 'this.componentsArr')
        // 判断当前路由是否iframe页
        this.isOpenIframePage()
      }
    },
  },
};
</script>
<style scoped>
#app .theme-picker {
  display: none;
}
</style>
