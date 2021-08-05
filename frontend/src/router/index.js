import { createRouter, createWebHistory } from 'vue-router'
import MainPage from '@/views/MainPage.vue'
import Login from '@/views/Login.vue'
import SearchResult from '@/views/SearchResult.vue'
import MyPage from '@/views/MyPage.vue'

const routes = [
  {
    path: '/',
    name: 'MainPage',
    component: MainPage,
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/search',
    name: 'SearchResult',
    component: SearchResult,
  },
  {
    path: '/mypage',
    name: 'MyPage',
    component: MyPage,
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router
