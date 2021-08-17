// import axios from 'axios'
const state = {
  isLogin: false,
  role: '',
  profile: '',
  myNickname: '',
}

// actions
const actions = {
  isLoginCheck({ commit }) {
    const jwt = localStorage.getItem('jwt')
    if (jwt) {
      commit('IS_LOGIN')
    } else {
      commit('IS_LOGOUT')
    }
  },
  isRole({ commit }) {
    const role = localStorage.getItem('role')
    if (role) {
      commit('IS_ROLE', role)
    }
  },
  getProfile({ commit }) {
    const profile = localStorage.getItem('profile')
    if (profile) {
      commit('GET_PROFILE', profile)
    } else {
      commit('GET_PROFILE', '')
    }
  },
  getNickname({ commit }) {
    const nickname = localStorage.getItem('nickname')
    if (nickname) {
      commit('GET_NICKNAME', nickname)
    } else {
      commit('GET_NICKNAME', '')
    }
  },
  deleteToken({ commit }) {
    localStorage.removeItem('jwt')
    localStorage.removeItem('id')
    commit('IS_LOGOUT')
    this.$router.push({ name: 'MainPage' })
  },
}

// mutations
const mutations = {
  IS_LOGIN(state) {
    state.isLogin = true
  },
  IS_LOGOUT(state) {
    state.isLogin = false
  },
  IS_ROLE(state, role) {
    state.role = role
  },
  GET_PROFILE(state, profile) {
    state.profile = profile
  },
  GET_NICKNAME(state, nickname) {
    state.myNickname = nickname
  },
}

export default {
  namespaced: true,
  state,
  actions,
  mutations,
}
