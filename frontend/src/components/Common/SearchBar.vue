<template>
  <!-- <div class="d-flex justify-content-center"> -->
  <div
    class="search-style-header inline-block fontCafe"
    v-show="isSearchHeaderShow"
  >
    <select
      name="region"
      id="searchRegion"
      class="me-2 selectpicker"
      v-model="info.region"
    >
      <option value="all">전체</option>
      <option v-for="(region, idx) in regions" :key="idx" :value="region">
        {{ region }}
      </option>
    </select>
    <input
      type="text"
      id="integrated-search"
      v-model="something"
      placeholder="닉네임(ex: 김작가),  #태그(ex: #웨딩)"
      @keyup.enter="integratedSearch"
    />
    <button @click="integratedSearch"><i class="fas fa-search"></i></button>
  </div>
  <!-- </div> -->
</template>

<script>
import { mapState } from 'vuex'
export default {
  name: 'SearchBar',
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
    ...mapState({
      isSearchHeaderShow: (state) => state.search.isSearchHeaderShow,
    }),
    regions() {
      return this.$store.state.mainpage.regions
    },
  },
  methods: {
    integratedSearch() {
      // something이 태그인 경우
      if (this.something[0] === '#') {
        this.info.keyword = this.something
          .slice(1, this.something.length)
          .trim()
        this.$store.dispatch('search/tagSearch', this.info)
        this.$router.push(
          `/search/${this.info.keyword}/${this.info.region}/tag`,
        )
      }
      // something이 작가인 경우
      else {
        this.info.keyword = this.something.trim()
        this.$store.dispatch('search/photographerSearch', this.info)
        this.$router.push(
          `/search/${this.info.keyword}/${this.info.region}/photographer`,
        )
      }
    },
  },
}
</script>

<style scoped>
.search-style-header {
  width: 60%;
  height: 35px;
  /* position: absolute; */
}
input {
  width: 80%;
  height: 100%;
  border: solid 1px #c4c4c4;
  border-radius: 30px;
  padding-top: 1px;
  padding-bottom: 0;
  outline: none;
  text-indent: 2%;
}
button {
  background-color: transparent;
  border: none;
  height: 100%;
  padding-top: 1px;
}
button:hover {
  color: rgb(238, 94, 94);
  border: none;
  height: 100%;
  padding-top: 1px;
}
select {
  width: 13%;
  height: 100%;
  border: solid 1px #c4c4c4;
}
</style>
