<template>
  <div class="search-style-page search-style">
    <input
      type="text"
      id="integrated-search-main"
      v-model="something"
      placeholder="#태그(ex: #웨딩)"
      @keyup.enter="mystudioSearch"
    />
    <button @click="mystudioSearch"><i class="fas fa-search"></i></button>
  </div>
</template>

<script>
export default {
  name: 'MainPageSearchBar',
  data() {
    return {
      something: null,
      info: {
        region: 'all',
        keyword: null,
      },
    }
  },
  computed: {
    regions() {
      return this.$store.state.mainpage.regions
    },
  },
  methods: {
    mystudioSearch() {
      // something이 태그인 경우
      this.info.keyword = this.something.slice(1, this.something.length).trim()
      this.$store.dispatch('mystudio/pgSearch', this.info)
      // if (this.something[0] === '#') {
      //   this.$store.dispatch('search/tagSearch', this.info)
      //   this.$router.push(
      //     `/search/${this.info.keyword}/${this.info.region}/tag`,
      //   )
      // }
      // // something이 작가인 경우
      // else {
      //   this.info.keyword = this.something.trim()
      //   this.$store.dispatch('search/photographerSearch', this.info)
      //   this.$router.push(
      //     `/search/${this.info.keyword}/${this.info.region}/photographer`,
      //   )
      // }
    },
  },
}
</script>

<style scoped>
.search-style-page {
  width: 100%;
}
input {
  width: 80%;
  height: 100%;
  /* border: none; */
  border: solid 1px #c4c4c4;
  border-radius: 30px;
  padding-top: 1px;
  padding-bottom: 0;
  outline: none;
  text-indent: 2%;
}
button {
  /* background-color: white; */
  background-color: transparent;
  /* 배경이 나뉘면 color 수정 */
  /* color: #c4c4c4; */
  border: none;
  height: 100%;
  padding-top: 1px;
}
button:hover {
  color: #ffffff;
  border: none;
  height: 100%;
  padding-top: 1px;
}
select {
  width: 13%;
  height: 100%;
  border: #c4c4c4;
}
</style>
