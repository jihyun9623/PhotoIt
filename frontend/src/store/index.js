import { createStore } from 'vuex'
import login from './modules/login'
import search from './modules/search'
import mainpage from './modules/mainpage'
import mypage from './modules/mypage'

export default createStore({
  state: {},
  mutations: {},
  actions: {},
  mypage_info: {},
  modules: {
    login,
    search,
    mainpage,
    mypage,
  },
})
