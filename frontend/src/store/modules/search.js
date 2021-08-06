import axios from 'axios'

// state
const state = () => ({
  resultTag: null,
  resultPhotographer: null,
  isSearchHeaderShow: false,
})

// actions
const actions = {
  tagSearch({ commit }, info) {
    axios({
      method: 'get',
      url: `http://i5a108.p.ssafy.io:8080/search/tag/${info.keyword}/${info.region}`,
      data: info,
    })
      .then((res) => {
        console.log(res)
        // 추가해야 함!
        commit('TAG_SEARCH', 'res.data.???')
        this.$router.push({ name: 'SearchResult' })
      })
      .catch((err) => {
        console.log(err)
        alert('다시 시도해주십시오.')
      })
  },
  photographerSearch({ commit }, info) {
    axios({
      method: 'get',
      url: `http://i5a108.p.ssafy.io:8080/search/pg/${info.keyword}/${info.region}`,
      data: info,
    })
      .then((res) => {
        console.log(res)
        // 추가해야 함!
        commit('PHOTOGRAPHER_SEARCH', 'res.data.???')
        this.$router.push({ name: 'SearchResult' })
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
