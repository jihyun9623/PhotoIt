import axios from 'axios'

// actions
const actions = {
  saveToken(credentials) {
    axios({
      method: 'post',
      url: 'http://localhost:8080/user/signiin',
      // JSON 파일로 전송
      data: JSON.stringify(credentials),
    })
      .then((res) => {
        // console.log(res)
        // res.data.tokek, res.data.id가 맞는지 확인
        localStorage.setItem('jwt', res.data.token)
        localStorage.setItem('id', res.data.id)
        this.$emit('login')
        this.$router.push({ name: 'MainPage' })
      })
      .catch((err) => {
        console.log(err)
        alert('로그인 정보가 잘못되었습니다.')
      })
  },
  deleteToken() {
    localStorage.removeItem('jwt')
    localStorage.removeItem('id')
    this.$router.push({ name: 'MainPage' })
  },
  getToken() {
    const token = localStorage.getItem('jwt')
    const config = {
      Authorization: `JWT ${token}`,
    }
    return config
  },
}

export default {
  actions,
}
