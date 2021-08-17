<template>
  <nav
    class="header pb-2 pt-2 d-flex justify-content-between align-items-center"
  >
    <div class="logo-box inline-block">
      <Logo />
    </div>
    <SearchBar />
    <ProfileIconMenu @user-logout="userLogout" />
  </nav>
</template>

<script>
import SearchBar from '@/components/Common/SearchBar'
import Logo from '@/components/Common/Logo'
import ProfileIconMenu from '@/components/Common/ProfileIconMenu'
import axios from 'axios'

export default {
  name: 'MainPageHeader',
  components: {
    SearchBar,
    Logo,
    ProfileIconMenu,
  },
  methods: {
    userLogout() {
      const jwt = localStorage.getItem('jwt')
      const config = {
        Authorization: jwt,
      }
      axios({
        method: 'get',
        // url: 'http://i5a108.p.ssafy.io:8080/user/signout',
        url: 'http://localhost:8080/user/signout',
        headers: config,
      })
        .then(() => {
          alert('로그아웃 되었습니다.')
          localStorage.removeItem('jwt')
          localStorage.removeItem('id')
          localStorage.removeItem('role')
          localStorage.removeItem('profile')
          localStorage.removeItem('nickname')
          this.$router.push({ name: 'MainPage' })
          if (this.$route.name === 'MainPage') {
            window.location.reload()
          }
        })
        .catch((err) => {
          console.log(err)
          alert('다시 시도해 주세요.')
        })
    },
  },
}
</script>

<style scoped>
.header {
  position: fixed;
  width: 100%;
  background-color: transparent;
  z-index: 10;
  height: 10%;
}
.logo-box {
  height: 100%;
}
</style>
