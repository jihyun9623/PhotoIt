import { createStore } from 'vuex'
import login from './modules/login'
import search from './modules/search'
import mystudio from './modules/mystudio'

export default createStore({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    login,
    search,
    mystudio,
  },
})
