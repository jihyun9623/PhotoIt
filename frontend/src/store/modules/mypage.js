import http from '@/assets/js/axios.js'

// mypage_info
const state = () => ({
  email: 'A108@ssafy.com',
  nickName: 'Patabear',
  profilePhoto: '../assets/images/profile_default.png',
  isPhotoGrapher: false,
  location: '',
  introduce: '',

  returnNickname: false,
  return: '',
})

// mutations
const mutations = {
  SET_USER_INFO(state, payload) {
    state.email = payload.email
    state.nickName = payload.nickName
    state.profilePhoto = payload.profilePhoto
    state.isPhotoGrapher = payload.isPhotoGrapher
    state.location = payload.location
    state.introduce = payload.introduce
  },
  SET_IS_PHOTOGRAPHER(state) {
    state.isPhotoGrapher = 'true'
  },
  SET_RETURN_NICKNAME(state, payload) {
    state.returnNickname = payload.return
  },
  SET_RETURN(state, payload) {
    state.return = payload.return
  },
}

// actions
const actions = {
  // 회원정보 수정
  setUserInfo({ commit }, data) {
    console.log(data)
    return http
      .put('/mypage', data)
      .then((res) => {
        console.log(res)
        localStorage.removeItem('nickname')
        localStorage.removeItem('jwt')
        localStorage.removeItem('role')
        localStorage.setItem('nickname', res.data.nickname)
        localStorage.setItem('jwt', res.data.jwt)
        localStorage.setItem('role', res.data.role)
        window.location.reload()
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
  // 회원정보 삭제
  deleteUser({ commit }) {
    return http
      .delete('/mypage')
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
  // 닉네임 중복체크
  nickNameCheck({ commit }, data) {
    return http
      .post('/mypage/nicknameCheck', data)
      .then((res) => {
        console.log(res)
        commit('SET_RETURN_NICKNAME', {
          return: true,
        })
      })
      .catch((err) => {
        console.log(err)
        commit('SET_RETURN_NICKNAME', {
          return: false,
        })
      })
  },
  // 프로필 사진 업로드
  uploadProfilePhoto({ commit }, data) {
    return http
      .post('/mypage/editphoto', data)
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
  // 작가로 권한 상승 요청
  upgradeToPg({ commit }) {
    return http
      .post('/mypage/pg', localStorage.getItem('nickname'))
      .then((res) => {
        console.log(res)
        commit('SET_IS_PHOTOGRAPHER')
      })
      .catch((err) => {
        console.log(err)
      })
  },
}

export default {
  namespaced: true,
  state,
  actions,
  mutations,
}
