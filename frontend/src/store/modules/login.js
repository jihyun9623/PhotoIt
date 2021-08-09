// import axios from 'axios'
const state = {
  isLogin: false,
}

// actions
const actions = {
  isLoginCheck({ commit }) {
    const id = localStorage.getItem('id')
    if (id) {
      commit('IS_LOGIN')
    } else {
      commit('IS_LOGOUT')
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
}

export default {
  namespaced: true,
  state,
  actions,
  mutations,
}
