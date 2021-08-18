import axios from 'axios'
const serverURL = 'http://localhost:8080'
// https://i5a108.p.ssafy.io/api
// const serverURL = 'http://localhost:8080'

// axios 객체 생성
export default axios.create({
  baseURL: serverURL,
})
