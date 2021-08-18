import http from '@/assets/js/axios.js'
import axios from 'axios'

// mypage_info
const state = () => ({
  studioauth: false,
  best: '',
  bestid: '',
  photo: '',
  photoid: '',
  location: '',
  introduce: '',
  return: '',
})

// mutations
const mutations = {
  SET_STUDIOAUTH(state) {
    state.studioauth = true
  },
  SET_LOCATION_INTRODUCE(state, payload) {
    state.location = payload.location
    state.introduce = payload.introduce
  },
  SET_BEST(state, payload) {
    state.best = payload.best
    state.bestid = payload.bestid
  },
  SET_PHOTO(state, payload) {
    state.photo = payload.photo
    state.photoid = payload.photoid
  },
  SET_RETURN(state, payload) {
    state.return = payload.return
  },
}

// actions
const actions = {
  // 마이스튜디오 접속시 본인 확인
  studioAuth({ commit }) {
    return http
      .get('/studioedit/studioauth' + localStorage.getItem('nickname'))
      .then((res) => {
        console.log(res)
        commit('SET_STUDIOAUTH')
      })
  },
  // 작가 프로필 받아오기
  getProfile({ commit }) {
    http.get('/studioedit/pgprofile').then((res) => {
      console.log(res)
      commit('SET_LOCATION_INTRODUCE', {
        location: res.data.location,
        introduce: res.data.introduce,
      })
    })
  },
  // 베스트 3 사진 받아오기
  getBestPhoto({ commit }) {
    http.get('/studioedit/bestphoto').then((res) => {
      console.log(res)
      commit('SET_BEST', {
        best: res.data.file,
        bestid: res.data.id,
      })
    })
  },
  // 작가 전체사진 받아오기
  getPhoto({ commit }) {
    http.get('/studioedit/photo').then((res) => {
      console.log(res)
      commit('SET_PHOTO', {
        photo: res.data.file,
        photoid: res.data.id,
      })
    })
  },
  // 베스트 사진 추가 (파라미터 사진 ID)
  addBestPhoto({ commit }, data) {
    return http
      .post('/studioedit/bestphoto', data)
      .then((res) => {
        console.log(res)
        commit('SET_RETURN', {
          return: true,
        })
      })
      .catch((err) => {
        console.log(err)
        commit('SET_RETURN', {
          return: false,
        })
      })
  },
  // 베스트 사진 수정 (파라미터 사진 ID)
  editBestPhoto({ commit }, data) {
    return http
      .put('/studioedit/bestphoto', data)
      .then((res) => {
        console.log(res)
        commit('SET_RETURN', {
          return: true,
        })
      })
      .catch((err) => {
        console.log(err)
        commit('SET_RETURN', {
          return: false,
        })
      })
  },
  // 베스트 사진 삭제 (파라미터 사진 ID)
  deleteBestPhoto({ commit }, data) {
    return http
      .delete('/studioedit/bestphoto', data)
      .then((res) => {
        console.log(res)
        commit('SET_RETURN', {
          return: true,
        })
      })
      .catch((err) => {
        console.log(err)
        commit('SET_RETURN', {
          return: false,
        })
      })
  },
  // 전체사진 추가 (파일 업로드 필요)
  // TODO 요거 짜기
  addPhoto({ commit }, data) {
    const jwt = localStorage.getItem('jwt')
    const config = {
      ContentType: 'mulitpart/form-data',
      Authorization: jwt,
    }
    return axios({
      method: 'post',
      url: http.serverURL + '/studioedit/photo',
      headers: config,
      data: data,
    })
      .then((res) => {
        console.log(res)
        commit('SET_RETURN', {
          return: true,
        })
      })
      .catch((err) => {
        console.log(err)
        commit('SET_RETURN', {
          return: false,
        })
      })
  },
  // 전체사진 삭제 (파라미터 사진 ID)
  deletePhoto({ commit }, data) {
    return http
      .delete('/studioedit/photo', data)
      .then((res) => {
        console.log(res)
        commit('SET_RETURN', {
          return: true,
        })
      })
      .catch((err) => {
        console.log(err)
        commit('SET_RETURN', {
          return: false,
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
