<template>
  <div id="app">
    <!-- <div id="nav"><router-link to="/">Mainpage</router-link> |</div> -->
    <router-view />
  </div>
  <GoTop />
  <div id="footer">
    <main-footer />
  </div>
</template>

<script>
import Footer from '@/views/Footer.vue'
import GoTop from '@/components/Common/GoTop'
import httpNoJWT from '@/assets/js/axiosNotJWT.js'
export default {
  name: 'App',
  components: {
    'main-footer': Footer,
    GoTop: GoTop,
  },
  computed: {
    isLoginCheck() {
      return this.$store.state.login.isLogin
    },
    nickname() {
      return this.$store.state.mainpage.nickname
    },
  },
  created() {
    this.$store.dispatch('mainpage/getRegions')
    this.$store.dispatch('mainpage/getTags')
    this.$store.dispatch('login/isLoginCheck')
    this.$store.dispatch('mainpage/getProfileNickname')
    this.$store.dispatch('login/getProfile')
    this.$store.dispatch('login/getNickname')
    httpNoJWT
      .get('/user/location')
      .then((res) => {
        this.$store.dispatch('getLocations', res.data)
      })
      .catch((err) => {
        console.log(err)
      })
  },
}
</script>

<style>
@import './assets/css/style.css';

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
#footer {
  margin-top: 30vh;
}
</style>
