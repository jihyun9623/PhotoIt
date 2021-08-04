import { createStore } from 'vuex'
import login from './modules/login'
import search from './modules/search'
import mainpage from './modules/mainpage'

export default createStore({
  state: {},
  mutations: {},
  actions: {},
  modules: {
    login,
    search,
    mainpage,
  },
})
