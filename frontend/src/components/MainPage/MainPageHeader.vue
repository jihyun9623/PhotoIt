<template>
  <nav class="header pb-2 pt-2">
    <!-- 프로필, 로고 추가 -->
    <!-- <h1>네브바</h1> -->
    <SearchBar />
  </nav>
</template>

<script>
import SearchBar from '@/components/Common/SearchBar'
// import { mapState } from 'vuex'
export default {
  name: 'MainPageHeader',
  components: {
    SearchBar,
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
</style>
