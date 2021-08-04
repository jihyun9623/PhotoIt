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
      method: 'post',
      url: `http://localhost:8080/search/tag/${info.keyword}/${info.region}`,
      data: JSON.stringify(info),
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
      method: 'post',
      url: `http://localhost:8080/search/pg/${info.keyword}/${info.region}`,
      data: JSON.stringify(info),
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
}

const mutations = {
  TAG_SEARCH(state, data) {
    state.resultTag = data
  },
  PHOTOGRAPHER_SEARCH(state, data) {
    state.resultPhotographer = data
  },
}

export default {
  namespaced: true,
  state,
  actions,
  mutations,
}
