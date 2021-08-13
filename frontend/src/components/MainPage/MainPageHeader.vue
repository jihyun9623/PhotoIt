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
  </nav>
</template>

<script>
import SearchBar from '@/components/Common/SearchBar'
import Logo from '@/components/Common/Logo'
import ProfileIconMenu from '@/components/Common/ProfileIconMenu'

export default {
  name: 'MainPageHeader',
  components: {
    SearchBar,
    Logo,
    ProfileIconMenu,
  },
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
