import http from '@/assets/js/axios.js'

// mypage_info
const state = () => ({
  favList: null,
  // isFavorite: false,
})

// mutations
const mutations = {
  GET_FAVORITE_LIST(state, favList) {
    state.favList = favList
  },
  IS_FAVORITE(state, isFavorite) {
    state.isFavorite = isFavorite
  },
}

// actions
const actions = {
  getFavList({ commit }) {
    return http
      .get('/fav/list/' + localStorage.getItem('nickname'))
      .then((res) => {
        console.log(res.data.favList.favList)
        commit('GET_FAVORITE_LIST', res.data.favList.favList)
      })
  },
  // isFavorite({ commit }, info) {
  //   http.get(`/fav/check/${info.userNick}/${info.pgNick}`).then((res) => {
  //     console.log(res.data)
  //     commit('IS_FAVORITE', res.data.favList.favList)
  //   })
  // },
}

export default {
  namespaced: true,
  state,
  actions,
  mutations,
}
