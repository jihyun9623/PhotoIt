import http from '@/assets/js/axios.js'
import httpNoJWT from '@/assets/js/axiosNotJWT.js'

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
    httpNoJWT.get('/location').then((res) => {
      commit('GET_REGIONS', res.data.locationList)
    })
  },
  getTags({ commit }) {
    httpNoJWT.get('/tag').then((res) => {
      console.log(res)
      commit('GET_TAGS', res.data.tagList)
    })
  },
  getProfileNickname({ commit }) {
    http
      .post('/profile')
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
    httpNoJWT.get('/contents').then((res) => {
      console.log(res)
      commit('GET_MAIN_CONTENTS', res.data.tagPhotoList)
    })
  },
  getDetailPhotos({ commit }, info) {
    commit('GET_PG_NICKNAME', info.nickName)
    return httpNoJWT
      .post('/detail', info)
      .then((res) => {
        console.log(res)
        commit('GET_DETAIL_PHOTO', res.data)
      })
      .catch((err) => {
        console.log(err)
      })
  },
  addFavorite({ commit }, info) {
    http
      .post('/fav/add', info)
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
    http
      .post('/fav/delete', info)
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
