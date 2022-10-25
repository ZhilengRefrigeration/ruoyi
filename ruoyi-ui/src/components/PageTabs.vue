<template>
  <div class="tabs">
    <ul :style="{ width: isCollapse ? 'calc(100% - 64px)' : 'calc(100% - 200px)' }">
      <li
        class="tabs-li"
        v-for="(item, index) in tabdate.tabname"
        :class="{ active: isActive(index) }"
        :key="index"
        @click="gotonext(index)"
      >
        <!-- @contextmenu.prevent="onContext($event, item)" -->
        <span class="tabs-li-title" >{{ item }}</span>
      </li>
    </ul>
  </div>
</template>

<script>
import { mapGetters, mapMutations } from 'vuex'

export default {
  props: {
    tabdate: {
      type: Object,
      default: () => {},
    },
  },
  data() {
    return {
      collapse: false,
    }
  },
  computed: {
    showTabs() {
      return this.tabList.length > 0
    },
    ...mapGetters({

    }),
  },
  created(){
      // console.log(this.tabname)
  },
  methods: {
    isActive(path) {
      return path === this.tabdate.activecontent
    },
    onContext(e, item) {
      console.log(e)
    },
    gotonext(index) {
        this.tabdate.activecontent = index
        this.$emit('tabchange',index)
    },
  },
}
</script>

<style scoped>

.tabs {
  position: relative;
  height: 39px;
  padding-right: 20px;
  background-color: #ffffff00;
}
/*
.tabs:after {
  content: '';
  position: absolute;
  width: 100%;
  height: 1px;
  background: #d9d9d9;
  top: 44px;
} */

.tabs ul {
  box-sizing: border-box;
  width: 100%;
  height: 100%;
  display: flex;
  position: relative;
  padding-left: 0px;
}

.tabs-li {
  width: 100px;
  height: 40px;
  margin: 0 5px;
  border-radius: 5px;
  font-size: 12px;
  cursor: pointer;
  line-height: 38px;
  border: 1px solid #d9d9d9;
  border-bottom-left-radius: 0px;
  border-bottom-right-radius: 0px;
  border-width: 1px 1px 0;
  background: #ffffff;
  text-align: center;
  padding: 0 5px 0 12px;
  vertical-align: middle;
  color: #333;
  -webkit-transition: all 0.3s ease-in;
  -o-transition: all 0.3s ease-in;
  transition: all 0.3s ease-in;
}

.tabs-li.active {
  border: 1px solid #d9d9d9;
  border-width: 1px 1px 0;
  background-color: #006CE1;
  position: relative;
  bottom: 4px
}

.tabs-li::marker {
  color: #ffffff00;
}

.tabs-li.active:after {
  position: absolute;
  width: 100%;
  height: 1px;
  background: #f2f2f2;
  top: 28px;
  margin-left: calc(-100% + 5px);
  z-index: 1000;
}

.tabs-li:not(.active):hover {
  background: #f8f8f8;
}

.tabs-li-title {
  /*float: left;*/
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  margin-right: 5px;
  color: #2D4E83;
}

.tabs-li.active .tabs-li-title,
.tabs-li:not(.active) .tabs-li-title:hover {
  color: #FFFFFF;
}

.tabs-close-box {
  display: none;
  cursor: pointer;
  position: absolute;
  box-sizing: border-box;
  text-align: center;
  border: 1px solid #d9d9d9;
  border-radius: 8px;
  background: #fff;
  z-index: 1000;
  box-shadow: 2px 2px 4px rgba(0, 0, 0, 0.2);
}

.context-menu-item {
  color: #666666;
  font-size: 12px;
  padding: 5px 10px;
  border-bottom: 1px solid #d9d9d9;
}

.context-menu-item:nth-last-child(1) {
  border: none;
}

.context-menu-item:hover {
  background: #93c1da;
  color: #fff;
}

.tab-btn {
  padding: 0 5px;
  font-size: 16px;
  font-weight: bold;
  line-height: 29px;
  cursor: pointer;
  position: absolute;
  right: 5px;
}

.tabs-close-box:before {
  box-sizing: content-box;
  width: 0px;
  height: 0px;
  position: absolute;
  top: -16px;
  right: 38px;
  padding: 0;
  border-bottom: 8px solid #ffffff;
  border-top: 8px solid transparent;
  border-left: 8px solid transparent;
  border-right: 8px solid transparent;
  display: block;
  content: '';
  z-index: 12;
}
.tabs-close-box:after {
  box-sizing: content-box;
  width: 0px;
  height: 0px;
  position: absolute;
  top: -18px;
  right: 37px;
  padding: 0;
  border-bottom: 9px solid #cccccc;
  border-top: 9px solid transparent;
  border-left: 9px solid transparent;
  border-right: 9px solid transparent;
  display: block;
  content: '';
  z-index: 10;
}
</style>
