<template>
  <SearchRegion />
  <section class="container">
    <div class="row d-flex justify-content-between">
      <div class="empty-box"></div>
      <h1>My Favorite List</h1>
      <!-- {{ favListArray }} -->
      <FavoriteListBox
        v-for="(favItem, idx) in favListArray"
        :key="idx"
        :favItem="favItem"
      />
    </div>
  </section>
</template>

<script>
import SearchRegion from '@/components/Common/SearchRegion'
import FavoriteListBox from '@/components/FavoriteList/FavoriteListBox'

export default {
  name: 'FavoriteList',
  components: {
    SearchRegion,
    FavoriteListBox,
  },
  data() {
    return {
      favListArray: null,
    }
  },
  computed: {
    favList() {
      return this.$store.state.favorite.favList
    },
    isFavorite() {
      return this.$store.state.favorite.isFavorite
    },
  },
  watch: {
    favList() {
      console.log(this.favList)
      this.objectToArray()
    },
  },
  methods: {
    objectToArray() {
      if (this.favList) {
        this.favListArray = Object.entries(this.favList)
        // console.log(this.favListArray)
      }
    },
  },
  created() {
    if (!this.$store.state.login.isLogin) {
      alert('접근 권한이 없습니다.')
      this.$router.push({ name: 'MainPage' })
    }
    this.$store.dispatch('favorite/getFavList')
    this.$store.dispatch('login/isLoginCheck')
  },
  mounted() {
    this.$store.state.search.isSearchHeaderShow = true
    window.scrollTo(0, 0)
    // console.log(this.favList)
    // this.objectToArray()
  },
}
</script>
<style scoped>
.empty-box {
  height: 10vh;
}
</style>
