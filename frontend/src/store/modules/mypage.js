import http from '@/common/axios.js'

// mypage_info
const state = () => ({
  email: 'A108@ssafy.com',
  nickName: 'Patabear',
  profilePhoto: '../assets/images/Logo.png',
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
  getUserInfo({ commit }) {
    // TO-DO : 로컬스토리지 확인후 아래 보낼내용 수정하기
    //http.get('/mypage/' + localStorage.getItem('nickName')).then((res) => {
    http.get('/mypage/' + 'user').then((res) => {
      console.log('UserInfoData :')
      console.log(res)
      commit('SET_USER_INFO', {
        email: res.id,
        nickName: res.nickname,
        profilePhoto: res.photo,
        isPhotoGrapher: res.pg,
        location: res.location,
        introduce: res.introduce,
      })
    })
  },
  // 회원정보 수정
  setUserInfo({ commit }, data) {
    return http
      .put('/mypage', JSON.stringify(data))
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
      .post('/mypage/nicknameCheck', JSON.stringify(data))
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
      .post('/mypage/editphoto', JSON.stringify(data))
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