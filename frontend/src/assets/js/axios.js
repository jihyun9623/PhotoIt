import axios from 'axios'

// axios 객체 생성
export default axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    'Access-Control-Allow-Origin': '*',
    Authorization: '${jwt}',
  },
  withCredentials: true,
})
