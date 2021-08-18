import httpNoJWT from '@/assets/js/axiosNotJWT.js'

// state
const state = () => ({
  resultTag: null,
  resultPhotographer: null,
  isSearchHeaderShow: false,
})

// actions
const actions = {
  tagSearch({ commit }, info) {
    console.log(info)
    httpNoJWT
      .get('/search/tag/' + info.keyword + '/' + info.region, info)
      .then((res) => {
        console.log(res)
        commit('TAG_SEARCH', res.data.photoList)
      })
      .catch((err) => {
        console.log(err)
        alert('다시 시도해주십시오.')
      })
  },
  photographerSearch({ commit }, info) {
    httpNoJWT
      .get('/search/pg/' + info.keyword + '/' + info.region, info)
      .then((res) => {
        console.log(res)
        commit('PHOTOGRAPHER_SEARCH', res.data.profileList)
      })
      .catch((err) => {
        console.log(err)
        alert('다시 시도해주십시오.')
      })
  },
  searchBarViewTrue({ commit }) {
    commit('SEARCH_BAR_VIEW_TRUE')
  },
  searchBarViewFalse({ commit }) {
    commit('SEARCH_BAR_VIEW_FALSE')
  },
}

const mutations = {
  TAG_SEARCH(state, data) {
    state.resultTag = data
  },
  PHOTOGRAPHER_SEARCH(state, data) {
    state.resultPhotographer = data
  },
  SEARCH_BAR_VIEW_TRUE(state) {
    state.isSearchHeaderShow = true
  },
  SEARCH_BAR_VIEW_FALSE(state) {
    state.isSearchHeaderShow = false
  },
}

export default {
  namespaced: true,
  state,
  actions,
  mutations,
}
