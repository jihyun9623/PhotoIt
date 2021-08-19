<template>
  <SearchRegion />
  <div class="empty-box"></div>
  <h3 class="mb-5 pb-5">
    <span class="fw-bold">
      {{ $route.params.region }} / {{ $route.params.keyword }}
    </span>
    에 대한 검색 결과
  </h3>
  <SearchResultPhotoList
    v-if="$route.params.type === 'tag'"
    :photoList="resultTag"
  />
  <SearchResultPhotographerList
    v-else-if="$route.params.type === 'photographer'"
    :profileList="resultPhotographer"
  />
</template>

<script>
import SearchRegion from '@/components/Common/SearchRegion'
import SearchResultPhotoList from '@/components/SearchResult/SearchResultPhotoList'
import SearchResultPhotographerList from '@/components/SearchResult/SearchResultPhotographerList'

export default {
  name: 'SearchResult',
  components: {
    SearchRegion,
    SearchResultPhotoList,
    SearchResultPhotographerList,
  },
  // 데이터 변경 문제
  computed: {
    resultTag() {
      return this.$store.state.search.resultTag
    },
    resultPhotographer() {
      return this.$store.state.search.resultPhotographer
    },
  },
  // 뒤로가기 문제
  watch: {
    $route(to, from) {
      if (to.path != from.path) {
        if (this.$route.params.type === 'tag') {
          this.$store.dispatch('search/tagSearch', {
            keyword: this.$route.params.keyword,
            region: this.$route.params.region,
            type: 'tag',
          })
        } else if (this.$route.params.type === 'photographer') {
          this.$store.dispatch('search/photographerSearch', {
            keyword: this.$route.params.keyword,
            region: this.$route.params.region,
            tyle: 'photographer',
          })
        }
      }
    },
  },
  // 새로고침 문제
  created() {
    if (this.$route.params.type === 'tag') {
      this.$store.dispatch('search/tagSearch', {
        keyword: this.$route.params.keyword,
        region: this.$route.params.region,
        type: 'tag',
      })
    } else {
      this.$store.dispatch('search/photographerSearch', {
        keyword: this.$route.params.keyword,
        region: this.$route.params.region,
        tyle: 'photographer',
      })
    }
  },
  mounted() {
    window.scrollTo(0, 0)
    // 검색바가 보이도록 설정
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
