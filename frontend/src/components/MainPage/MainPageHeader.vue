<template>
  <nav
    class="header pb-2 pt-2 d-flex justify-content-between align-items-center"
  >
    <div class="logo-box inline-block">
      <Logo />
    </div>
    <SearchBar />
    <ProfileIconMenu />
    <router-link :to="{ path: '/login' }"> 로그인 </router-link>
    <router-link :to="{ path: '/signup' }"> 회원가입_임시 </router-link>
    <!--아래 버튼 삭제 가능-->
    <button class="btn login-form-btn mb-2" @click="userLogout">
      로그아웃
    </button>
  </nav>
</template>

<script>
import SearchBar from '@/components/Common/SearchBar'
import Logo from '@/components/Common/Logo'
import ProfileIconMenu from '@/components/Common/ProfileIconMenu'
import axios from 'axios' // 이거도 삭제 가능...!

export default {
  name: 'MainPageHeader',
  components: {
    SearchBar,
    Logo,
    ProfileIconMenu,
  },
  // --------여기부터 삭제 가능...!------
  methods: {
    userLogout() {
      const jwt = localStorage.getItem('jwt')
      const config = {
        Authorization: `JWT ${jwt}`,
      }
      axios({
        method: 'get',
        url: 'http://i5a108.p.ssafy.io:8080/user/signout',
        data: this.credentials,
        headers: config,
      })
        .then(() => {
          alert('로그아웃!')
          localStorage.removeItem('jwt')
          localStorage.removeItem('id')
          this.$router.push({ name: 'MainPage' })
        })
        .catch((err) => {
          console.log(err)
          alert('로그인 정보가 잘못되었습니다.')
        })
    },
  },
  //------------------------------------
}
</script>

<style scoped>
.header {
  position: fixed;
  width: 100%;
  background-color: transparent;
  z-index: 1;
  height: 10%;
}
.logo-box {
  height: 100%;
}
</style>
