import { createStore } from 'vuex'
import login from './modules/login'
import search from './modules/search'
import mystudio from './modules/mystudio'
import mainpage from './modules/mainpage'
import mypage from './modules/mypage'
import mystudioedit from './modules/mystudioedit'

export default createStore({
  state: {
    location_all: null,
  },
  mutations: {
    GET_LOCATIONS: function (state, location_all) {
      state.location_all = location_all.location_list
      console.log(state.location_all)
    },
  },
  actions: {
    getLocations: function ({ commit }, location_all) {
      commit('GET_LOCATIONS', location_all)
    },
  },
  modules: {
    login,
    search,
    mystudio,
    mainpage,
    mypage,
    mystudioedit,
  },
})
