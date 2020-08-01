import { login, logout, getInfo, refreshToken as refreshTokenFunc } from '@/api/login'
import { getToken, setToken, removeToken,
  setRefreshToken, removeRefreshToken,
  setExpiresIn, removeExpiresIn
} from '@/utils/auth'

/**
 * 存储token
 * @param commit
 * @param res
 */
function storeToken(commit, resolve, res) {
  setToken(res.access_token)
  commit('SET_TOKEN', res.access_token)

  // 存储refresh_token expires_in
  // console.log(`获取[刷新令牌]成功了 === `, res.refresh_token)
  setRefreshToken(res.refresh_token)
  commit('SET_REFRESH_TOKEN', res.refresh_token)

  const expires_in_time = new Date().getTime() + res.expires_in * 1000
  // console.log(`获取[访问令牌]成功了，过期日期 === `, new Date(expires_in_time))
  setExpiresIn(expires_in_time)
  commit('SET_EXPIRES_IN', expires_in_time)

  resolve()
}

const user = {
  state: {
    token: getToken(),
    name: '',
    avatar: '',
    roles: [],
    permissions: []
  },

  mutations: {
    SET_EXPIRES_IN: (state, v) => {
      state.expires_in = v
    },
    SET_REFRESH_TOKEN: (state, v) => {
      state.refresh_token = v
    },
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_PERMISSIONS: (state, permissions) => {
      state.permissions = permissions
    }
  },


  actions: {

    // 刷新
    RefreshToken({ commit }, refreshTokenParams) {
      // console.log(`进入src/store/modules/user.js执行[刷新token]`)
      const refreshToken = refreshTokenParams.refreshToken
      return new Promise((resolve, reject) => {
        refreshTokenFunc(refreshToken).then(res => {
        debugger
          // console.log(`调用[刷新token]接口，返回参数 === `, res)

          storeToken(commit, resolve, res)

        }).catch(error => {
          reject(error)

          // console.log(`可能refresh_token已过期！`, error)

          // 清空

          // console.log(`清空鉴权信息`)
          commit('SET_TOKEN', '')
          commit('SET_REFRESH_TOKEN', '')
          commit('SET_EXPIRES_IN', 0)
          commit('SET_ROLES', [])
          commit('SET_PERMISSIONS', [])
          removeToken()
          removeRefreshToken()
          removeExpiresIn()

        })
      })
    },

    // 登录
    Login({ commit }, userInfo) {
      const username = userInfo.username.trim()
      const password = userInfo.password
      const code = userInfo.code
      const uuid = userInfo.uuid
      return new Promise((resolve, reject) => {
        login(username, password, code, uuid).then(res => {

          storeToken(commit, resolve, res)

        }).catch(error => {
          reject(error)
        })
      })
    },

    // 获取用户信息
    GetInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        getInfo(state.token).then(res => {
          const user = res.user
          const avatar = user.avatar == "" ? require("@/assets/image/profile.jpg") : process.env.VUE_APP_BASE_API + user.avatar;
          if (res.roles && res.roles.length > 0) { // 验证返回的roles是否是一个非空数组
            commit('SET_ROLES', res.roles)
            commit('SET_PERMISSIONS', res.permissions)
          } else {
            commit('SET_ROLES', ['ROLE_DEFAULT'])
          }
          commit('SET_NAME', user.userName)
          commit('SET_AVATAR', avatar)
          resolve(res)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 退出系统
    LogOut({ commit, state }) {
      return new Promise((resolve, reject) => {
        logout(state.token).then(() => {
          commit('SET_TOKEN', '')
          commit('SET_ROLES', [])
          commit('SET_PERMISSIONS', [])
          removeToken()
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 前端 登出
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
      })
    }
  }
}

export default user
