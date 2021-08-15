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
      // url: `http://i5a108.p.ssafy.io:8080/location`,
      url: `http://localhost:8080/location`,
    }).then((res) => {
      console.log(res)
      commit('GET_REGIONS', res.data.locationList)
    })
  },
  getTags({ commit }) {
    axios({
      method: 'get',
      // url: `http://i5a108.p.ssafy.io:8080/tag`,
      url: `http://localhost:8080/tag`,
    }).then((res) => {
      console.log(res)
      commit('GET_TAGS', res.data.tagList)
    })
  },
  getProfileNickname({ commit }) {
    const jwt = localStorage.getItem('jwt')
    const config = {
      Authorization: jwt,
    }
    axios({
      method: 'post',
      // url: `http://i5a108.p.ssafy.io:8080/profile`,
      url: `http://localhost:8080/profile`,
      headers: config,
    })
      .then((res) => {
        console.log(res)
        commit('GET_ProfileNickname', res.data.userProfile)
      })
      .catch((err) => {
        console.log(err)
      })
  },
  getMainContents({ commit }) {
    axios({
      method: 'get',
      // url: `http://i5a108.p.ssafy.io:8080/contents`,
      url: `http://localhost:8080/contents`,
    }).then((res) => {
      console.log(res)
      commit('GET_MAIN_CONTENTS', res.data.tagPhotoList)
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
    state.profilePicture = data.photo
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
