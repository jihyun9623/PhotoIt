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
  detailPhotoId: null,
  detailProfile: null,
  detailPgNickname: null,
})

// actions
const actions = {
  getRegions({ commit }) {
    axios({
      method: 'get',
      // url: `http://i5a108.p.ssafy.io:8080/location`,
      url: `http://localhost:8080/location`,
    }).then((res) => {
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
        localStorage.setItem('profile', res.data.userProfile.photo)
        localStorage.setItem('nickname', res.data.userProfile.nickName)
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
    commit('GET_PG_NICKNAME', info.nickName)
    return axios({
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
  addFavorite({ commit }, info) {
    const jwt = localStorage.getItem('jwt')
    const config = {
      Authorization: jwt,
    }
    // console.log(info, config)
    axios({
      method: 'post',
      // url: `http://i5a108.p.ssafy.io:8080/fav/add`,
      url: `http://localhost:8080/fav/add`,
      headers: config,
      data: info,
    })
      .then((res) => {
        console.log(res)
        commit('ADD_FAVORITE')
      })
      .catch((err) => {
        console.log(err)
        alert('다시 시도해 주십시오')
      })
  },
  deleteFavorite({ commit }, info) {
    const jwt = localStorage.getItem('jwt')
    const config = {
      Authorization: jwt,
    }
    // console.log(info, config)
    axios({
      method: 'post',
      // url: `http://i5a108.p.ssafy.io:8080/fav/delete`,
      url: `http://localhost:8080/fav/delete`,
      headers: config,
      data: info,
    })
      .then((res) => {
        console.log(res)
        commit('DELETE_FAVORITE')
      })
      .catch((err) => {
        console.log(err)
        alert('다시 시도해 주십시오')
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
    state.contents = data
  },
  GET_DETAIL_PHOTO(state, data) {
    state.detailIsFavorite = data.favorite
    state.detailPhoto = data.origin
    state.detailOtherPhotos = data.thumbPhotoIds
    state.detailTagList = data.tagList
    state.detailPhotoId = data.photoIdx
    state.detailProfile = data.profilePhoto
  },
  GET_PG_NICKNAME(state, data) {
    state.detailPgNickname = data
  },
  ADD_FAVORITE(state) {
    state.detailIsFavorite = true
  },
  DELETE_FAVORITE(state) {
    state.detailIsFavorite = false
  },
}

export default {
  namespaced: true,
  state,
  actions,
  mutations,
}
