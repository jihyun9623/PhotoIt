<template>
  <SearchRegion />
  <div class="empty-box"></div>
  <h1>My Favorite List</h1>
  <!-- <div class="favbox" v-for="(fav, idx) in favlist" :key="idx" :fav="fav"> -->
  <FavoriteListBox
    v-for="(favNick, favBest, i) in (pgNicklist, bestPhotoslist)"
    :favNick="favNick"
    :favBest="favBest"
    :key="i"
  />
  <!-- </div> -->
</template>

<script>
import SearchRegion from '@/components/Common/SearchRegion'
import FavoriteListBox from '@/components/FavoriteList/FavoriteListBox'
// import axios from 'axios'

export default {
  name: 'FavoriteList',
  components: {
    SearchRegion,
    FavoriteListBox,
  },
  // methods: {},
  computed: {
    pgNicklist() {
      return this.$store.state.favorite.pgNick
    },
    bestPhotoslist() {
      return this.$store.state.favorite.bestPhotos
    },
  },
  created() {
    this.$store.dispatch('favorite/getFavList').then(() => {
      if (this.$store.state.favorite.pgNick != null) {
        console.log('Vue에서 성공')
      } else {
        alert('Vue에서 실패')
      }
    })
  },
  mounted() {
    this.$store.state.search.isSearchHeaderShow = true
    window.scrollTo(0, 0)
  },
  // props: {},
}
</script>
<style scoped>
.empty-box {
  height: 10vh;
}
.favbox {
  margin: 10px;
}
</style>
