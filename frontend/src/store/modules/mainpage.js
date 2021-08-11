import axios from 'axios'

// state
const state = () => ({
  regions: null,
  tags: null,
  profilePicture: null,
  nickname: null,
  contents: null,
  detailPhoto: null,
  detailOtherPhotos: null,
  detailIsFavorite: null,
  detailTagList: null,
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
      Authorization: `JWT ${jwt}`,
    }
    axios({
      method: 'post',
      // url: `http://i5a108.p.ssafy.io:8080/profile`,
      url: `http://localhost:8080/profile`,
      headers: config,
    })
      .then((res) => {
        console.log(res)
        commit('GET_PROFILE_NICKNAME', res.data.userProfile)
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
  getDetailPhotos({ commit }, info) {
    axios({
      method: 'post',
      // url: `http://i5a108.p.ssafy.io:8080/detail`,
      url: `http://localhost:8080/detail`,
      data: info,
    })
      .then((res) => {
        console.log(res)
        commit('GET_DETAIL_PHOTO', res.data)
      })
      .catch((err) => {
        console.log(err)
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
  GET_PROFILE_NICKNAME(state, data) {
    state.profilePicture = data.photo
    state.nickname = data.nickName
  },
  GET_MAIN_CONTENTS(state, data) {
    // 어떤 데이터를 어떻게 나누어 저장할지 추후 수정
    state.contents = data
  },
  GET_DETAIL_PHOTO(state, data) {
    state.isFavorite = data.isFavorite
    state.detailPhoto = data.origin
    state.detailOtherPhotos = data.thumbPhotoIds
    state.detailTagList = data.tagList
  },
}

export default {
  namespaced: true,
  state,
  actions,
  mutations,
}
