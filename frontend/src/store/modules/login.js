import axios from 'axios'

// state
const state = () => ({
  TOKEN: null,
})

// actions
const actions = {
  saveToken({ commit }, credentials) {
    axios({
      method: 'post',
      url: 'http://localhost:8080/user/signiin',
      // JSON 파일로 전송
      data: JSON.stringify(credentials),
    })
      .then((res) => {
        console.log(res)
        commit('SAVE_TOKEN', res.data.token)
        this.$emit('login')
        this.$router.push({ name: 'MainPage' })
      })
      .catch((err) => {
        console.log(err)
        alert('로그인 정보가 잘못되었습니다.')
      })
  },
  deleteToken({ commit }) {
    commit('DELETE_TOKEN')
  },
}

const mutations = {
  SAVE_TOKEN(state, token) {
    state.TOKEN = token
  },
  DELETE_TOKEN(state) {
    state.TOKEN = null
  },
}

export default {
  state,
  actions,
  mutations,
}
