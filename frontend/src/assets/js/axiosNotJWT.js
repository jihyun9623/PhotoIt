import axios from 'axios'
const serverURL = 'http://localhost:8080'

// axios 객체 생성
export default axios.create({
  baseURL: serverURL,
})
