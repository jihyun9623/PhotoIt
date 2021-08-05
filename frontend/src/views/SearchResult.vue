<template>
  <SearchRegion />
  <div class="empty-box"></div>
  <h1>SearchResult</h1>
</template>

<script>
import SearchRegion from '@/components/Common/SearchRegion'

export default {
  name: 'SearchResult',
  components: {
    SearchRegion,
  },
  mounted() {
    this.$store.state.search.isSearchHeaderShow = true
  },
}
</script>
<style scoped>
/* nav 태그의 position이 'fixed' 이기 때문에 공간을 먹어버리는 문제 해결 */
.empty-box {
  height: 15vh;
}
</style>
