import { createRouter, createWebHistory } from 'vue-router'
import MainPage from '@/views/MainPage.vue'
import Login from '@/views/Login.vue'
import SearchResult from '@/views/SearchResult.vue'
import MyPage from '@/views/MyPage.vue'
import Signup from '@/views/Signup.vue'
import FavoriteList from '@/views/FavoriteList.vue'
import MyStudio from '@/views/MyStudio.vue'

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
    path: '/search/:keyword/:region/:type',
    name: 'SearchResult',
    component: SearchResult,
  },
  {
    path: '/mypage',
    name: 'MyPage',
    component: MyPage,
  },
  {
    path: '/signup',
    name: 'Signup',
    component: Signup,
  },
  {
    path: '/favlist',
    name: 'FavoriteList',
    component: FavoriteList,
  },
  {
    path: '/mystudio/:nickname',
    name: 'MyStudio',
    component: MyStudio,
    props: true,
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

export default router
