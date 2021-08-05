import axios from 'axios'

// state
const state = () => ({
  regions: null,
  tags: null,
  profilePicture: null,
  nickname: null,
  contents: null,
})

// actions
const actions = {
  getRegions({ commit }) {
    axios({
      method: 'get',
      url: `http://localhost:8080/location`,
    }).then((res) => {
      console.log(res)
      // 추가해야 함!
      commit('GET_REGIONS', 'res.data.???')
    })
  },
  getTags({ commit }) {
    axios({
      method: 'get',
      url: `http://localhost:8080/tag`,
    }).then((res) => {
      console.log(res)
      // 추가해야 함!
      commit('GET_TAGS', 'res.data.???')
    })
  },
  getProfileNickname({ commit, dispatch }) {
    const id = localStorage.getItem('id')
    const payload = {
      id: id,
    }
    axios({
      method: 'get',
      url: `http://localhost:8080/profile`,
      headers: dispatch('login/getToken', { root: true }),
      data: JSON.stringify(payload),
    }).then((res) => {
      console.log(res)
      // 추가해야 함!
      commit('GET_ProfileNickname', 'res.data.???')
    })
  },
  getMainContents({ commit }) {
    axios({
      method: 'get',
      url: `http://localhost:8080/contents`,
    }).then((res) => {
      console.log(res)
      // 추가해야 함!
      commit('GET_MAIN_CONTENTS', 'res.data.???')
    })
  },
}

const mutations = {
  GET_REGIONS(state, data) {
    state.regions = data
  },
  GET_TAGS(state, data) {
    state.tags = data
  },
  GET_ProfileNickname(state, data) {
    // 확인 필요
    state.profilePicture = data.profile
    // 확인 필요
    state.nickname = data.nickName
  },
  GET_MAIN_CONTENTS(state, data) {
    // 어떤 데이터를 어떻게 나누어 저장할지 추후 수정
    state.contents = data
  },
}

export default {
  namespaced: true,
  state,
  actions,
  mutations,
}
