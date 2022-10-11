import defaultSettings from '@/settings'

const { sideTheme, showSettings, topNav, tagsView, fixedHeader, sidebarLogo, dynamicTitle } = defaultSettings

const storageSetting = JSON.parse(localStorage.getItem('layout-setting')) || ''

import Vue from 'vue'
storageSetting.theme&&setThemeColor(storageSetting.theme)

const state = {
  title: '',
  theme: storageSetting.theme || '#409EFF',
  sideTheme: storageSetting.sideTheme || sideTheme,
  showSettings: showSettings,
  topNav: storageSetting.topNav === undefined ? topNav : storageSetting.topNav,
  tagsView: storageSetting.tagsView === undefined ? tagsView : storageSetting.tagsView,
  fixedHeader: storageSetting.fixedHeader === undefined ? fixedHeader : storageSetting.fixedHeader,
  sidebarLogo: storageSetting.sidebarLogo === undefined ? sidebarLogo : storageSetting.sidebarLogo,
  dynamicTitle: storageSetting.dynamicTitle === undefined ? dynamicTitle : storageSetting.dynamicTitle
}
const mutations = {
  CHANGE_SETTING: (state, { key, value }) => {
    if (state.hasOwnProperty(key)) {
      state[key] = value
      if(key==='theme'){
        setThemeColor(value)
      }
    }
  }
}

const actions = {
  // 修改布局设置
  changeSetting({ commit }, data) {
    commit('CHANGE_SETTING', data)
  },
  // 设置网页标题
  setTitle({ commit }, title) {
    state.title = title
  }
}

function setThemeColor(val,oldColor){
  let chalk=''
  let theme=''
  const ORIGINAL_THEME = '#409EFF'
  const version = require('element-ui/package.json').version
  setTheme(val)
  async function setTheme(val) {
    const oldVal = chalk ? theme : ORIGINAL_THEME
    if (typeof val !== 'string') return
    const themeCluster = getThemeCluster(val.replace('#', ''))
    const originalCluster = getThemeCluster(oldVal.replace('#', ''))

    const getHandler = (variable, id) => {
      return () => {
        const originalCluster = getThemeCluster(ORIGINAL_THEME.replace('#', ''))
        const newStyle = updateStyle(Vue[variable], originalCluster, themeCluster)

        let styleTag = document.getElementById(id)
        if (!styleTag) {
          styleTag = document.createElement('style')
          styleTag.setAttribute('id', id)
          document.head.appendChild(styleTag)
        }
        styleTag.innerText = newStyle
      }
    }

    if (!chalk) {
      const url = `https://unpkg.com/element-ui@${version}/lib/theme-chalk/index.css`
      await getCSSString(url, 'chalk')
    }
    const chalkHandler = getHandler('chalk', 'chalk-style')

    chalkHandler()
    const styles = [].slice.call(document.querySelectorAll('style'))
      .filter(style => {
        const text = style.innerText
        return new RegExp(oldVal, 'i').test(text) && !/Chalk Variables/.test(text)
      })
    styles.forEach(style => {
      const { innerText } = style
      if (typeof innerText !== 'string') return
      style.innerText = updateStyle(innerText, originalCluster, themeCluster)
    })
  }

  function updateStyle(style, oldCluster, newCluster) {
    let newStyle = style
    oldCluster.forEach((color, index) => {
      newStyle = newStyle.replace(new RegExp(color, 'ig'), newCluster[index])
    })
    return newStyle
  }

  function getCSSString(url, variable) {
    return new Promise(resolve => {
      const xhr = new XMLHttpRequest()
      xhr.onreadystatechange = () => {
        if (xhr.readyState === 4 && xhr.status === 200) {
          Vue[variable] = xhr.responseText.replace(/@font-face{[^}]+}/, '')
          resolve()
        }
      }
      xhr.open('GET', url)
      xhr.send()
    })
  }

  function getThemeCluster(theme) {
    const tintColor = (color, tint) => {
      let red = parseInt(color.slice(0, 2), 16)
      let green = parseInt(color.slice(2, 4), 16)
      let blue = parseInt(color.slice(4, 6), 16)

      if (tint === 0) { // when primary color is in its rgb space
        return [red, green, blue].join(',')
      } else {
        red += Math.round(tint * (255 - red))
        green += Math.round(tint * (255 - green))
        blue += Math.round(tint * (255 - blue))

        red = red.toString(16)
        green = green.toString(16)
        blue = blue.toString(16)

        return `#${red}${green}${blue}`
      }
    }

    const shadeColor = (color, shade) => {
      let red = parseInt(color.slice(0, 2), 16)
      let green = parseInt(color.slice(2, 4), 16)
      let blue = parseInt(color.slice(4, 6), 16)

      red = Math.round((1 - shade) * red)
      green = Math.round((1 - shade) * green)
      blue = Math.round((1 - shade) * blue)

      red = red.toString(16)
      green = green.toString(16)
      blue = blue.toString(16)

      return `#${red}${green}${blue}`
    }

    const clusters = [theme]
    for (let i = 0; i <= 9; i++) {
      clusters.push(tintColor(theme, Number((i / 10).toFixed(2))))
    }
    clusters.push(shadeColor(theme, 0.1))
    return clusters
  }
}


export default {
  namespaced: true,
  state,
  mutations,
  actions
}

