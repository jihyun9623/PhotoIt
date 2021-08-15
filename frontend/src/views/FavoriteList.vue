<template>
  <SearchRegion />
  <div class="container">
    <div class="empty-box"></div>
    <h1>FavoriteList</h1>
    <div
      class="favbox"
      v-for="(pgnick, idx) in pgFavList"
      :key="idx"
      :pgFav="pgFav"
    >
      <FavoriteListBox />
    </div>
    <FavoriteListBox />
  </div>
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
  props: {
    pgFavList: {
      type: Array,
    },
  },
  mounted() {
    this.$store.state.search.isSearchHeaderShow = true
  },
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
