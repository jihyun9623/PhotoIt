import http from '@/common/axios.js'

// mypage_info
const state = () => ({
  email: 'A108@ssafy.com',
  nickName: 'Patabear',
  profilePhoto: '',
  isPhotoGrapher: false,
  location: '',
  introduce: '',
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
        profilePhoto: res.profilephoto,
        isPhotoGrapher: res.pg,
        location: res.location,
        introduce: res.introduce,
      })
    })
  },
  // 회원정보 수정
  setUserInfo(data) {
    http
      .put('/mypage', JSON.stringify(data))
      .then((res) => {
        console.log(res)
        return true
      })
      .catch((err) => {
        console.log(err)
        return false
      })
  },
  // 회원정보 삭제
  deleteUser() {
    http
      .delete('/mypage')
      .then(() => {
        return true
      })
      .catch((err) => {
        console.log(err)
        return false
      })
  },
  // 닉네임 중복체크
  nickNameCheck(data) {
    http
      .post('/mypage/nicknameCheck', JSON.stringify(data))
      .then((res) => {
        console.log(res)
        return true
      })
      .catch((err) => {
        console.log(err)
        return false
      })
  },
  // 프로필 사진 업로드
  uploadProfilePhoto(data) {
    http
      .post('/mypage/editphoto', JSON.stringify(data))
      .then(() => {
        return true
      })
      .catch(() => {
        return false
      })
  },
  // 작가로 권한 상승 요청
  upgradeToPg({ commit }) {
    http
      .post('/mypage/pg', localStorage.getItem('nickname'))
      .then(() => {
        commit('SET_IS_PHOTOGRAPHER')
        return true
      })
      .catch(() => {
        return false
      })
  },
}

export default {
  namespaced: true,
  state,
  actions,
  mutations,
}
