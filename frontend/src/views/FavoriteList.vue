<template>
  <SearchRegion />
  <div class="empty-box"></div>
  <h1>My Favorite List</h1>
  <!-- <div class="favbox" v-for="(fav, idx) in favlist" :key="idx" :fav="fav"> -->
  <!-- <FavoriteListBox
    v-for="(favNick, favBest, i) in (pgNicklist, bestPhotoslist)"
    :favNick="favNick"
    :favBest="favBest"
    :key="i"
  /> -->
  <FavoriteListBox v-for="(fav, i) in favList" :fav="fav" :key="i" />
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
    favList() {
      // let favlist = this.$store.state.favorite.favList
      // let key = Object.keys(this.$store.state.favorite.favList)
      // let value = Object.values(this.$store.state.favorite.favList)
      // console.log(key)
      // console.log(value)

      let nicklist = this.$store.state.favorite.pgNick
      // let bestlist = this.$store.state.favorite.bestPhotos
      let favList = []
      for (let i in nicklist) {
        let temp = []
        temp.push(nicklist[i])
        favList.push(temp)
      }
      // console.log(favList)
      // for (let i = 0; i < favList.length; i++) {
      //   let temp = []
      //   temp.push(bestlist[i])
      //   console.log(favList)
      //   favList.splice(i, 1, [favList[i]] + [temp])
      // }
      console.log(favList)
      return this.$store.state.favorite.favList
    },
    // pgNicklist() {
    //   return this.$store.state.favorite.pgNick
    // },
    // bestPhotoslist() {
    //   return this.$store.state.favorite.bestPhotos
    // },
  },
  created() {
    this.$store.dispatch('favorite/getFavList').then(() => {
      if (this.$store.state.favorite.favList != null) {
        console.log('Vue에서 성공')
        // console.log(this.$store.state.favorite.bestPhotos[0])
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
