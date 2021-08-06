<template>
  <SearchRegion />
  <div class="empty-box"></div>
  <h1>SearchResult</h1>
  <h3>
    {{ $route.params.region }} / {{ $route.params.keyword }} 에 대한 검색 결과
  </h3>
  <SearchResultPhotoList v-if="$route.params.type === 'tag'" />
  <SearchResultPhotographerList
    v-else-if="$route.params.type === 'photographer'"
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
  computed: {
    resultTag() {
      return this.$store.state.search.resultTag
    },
    resultPhotographer() {
      return this.$store.state.search.resultPhotographer
    },
  },
  // dispatch 호출을 SearchBar와 MainPageSearchBar에서 하고 있으므로 쓰지 않아도 된다.
  // 만약 axios 호출과 vuex 상태 갱신에 문제가 있다면 사용해야 하며, router 주소에 태그/작가 를 구별할 수 있는 값을 넣어야 한다.
  // created() {
  //   this.$watch(
  //     // 여기서도 디스패치 필요?
  //     () => this.$route.params,
  //     (toParams, previousParams) => {
  //       if (toParams != previousParams) {
  //         this.$store.dispatch('search/tagSearch', {
  //           keyword: this.$route.params.keyword,
  //           region: this.$route.params.region,
  //           type: 'tag',
  //         }) or
  //         this.$store.dispatch('search/photographerSearch', {
  //           keyword: this.$route.params.keyword,
  //           region: this.$route.params.region,
  //           tyle: 'photographer',
  //         })
  //       }
  //     },
  //   )
  // },
  mounted() {
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
