import http from '@/assets/js/axios.js'

// mypage_info
const state = () => ({
  favlist: [],
})

// mutations
const mutations = {
  GET_FAVORITE_LIST(state, payload) {
    // state.pgNick = payload.pgNick
    // state.bestPhotos = payload.bestPhotos
    state.favList = payload.favList
  },
}

// actions
const actions = {
  getFavList({ commit }) {
    return http
      .get('/fav/list/' + localStorage.getItem('nickname'))
      .then((res) => {
        console.log('API에서 성공')
        // console.log(localStorage.getItem('nickname'))
        // console.log(res.data.favNicklist.pgNick)
        // console.log(res.data.favBestlist.bestPhotos)
        console.log(res.data.favList.favList)
        // console.log('끝')
        commit('GET_FAVORITE_LIST', {
          favList: res.data.favList.favList,
          // pgNick: res.data.favNicklist.pgNick,
          // bestPhotos: res.data.favBestlist.bestPhotos,
        })
      })
  },
}

export default {
  namespaced: true,
  state,
  actions,
  mutations,
}
