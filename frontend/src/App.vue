<template>
  <div id="app">
    <!-- <div id="nav"><router-link to="/">Mainpage</router-link> |</div> -->
    <router-view />
  </div>
  <div id="footer">
    <main-footer />
  </div>
</template>

<script>
import Footer from '@/views/Footer.vue'

export default {
  name: 'App',
  computed: {
    isLoginCheck() {
      return this.$store.state.login.isLogin
    },
  },
  created() {
    this.$store.dispatch('mainpage/getRegions')
    // 로그인 여부 확인, 로그인 했을 때만 프로필과 닉네임을 요청한다.
    this.$store.dispatch('login/isLoginCheck')
    if (this.$store.state.login.isLogin) {
      this.$store.dispatch('mainpage/getProfileNickname')
    }
  },
  components: {
    'main-footer': Footer,
  },
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
