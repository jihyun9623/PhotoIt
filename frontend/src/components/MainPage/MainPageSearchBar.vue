<template>
  <div class="d-flex justify-content-center">
    <div class="search-style-page search-style">
      <select
        name="region"
        id="searchRegion"
        class="me-2"
        v-model="info.region"
      >
        <option value="all">전체</option>
        <option value="서울">서울</option>
        <option value="부산">부산</option>
        <option value="경기도">경기도</option>
        <option value="강원도">강원도</option>
        <option value="경상남도">경상남도</option>
        <option value="경상북도">경상북도</option>
        <option value="전라남도">전라남도</option>
        <option value="전라북도">전라북도</option>
        <option value="충청남도">충청남도</option>
        <option value="충청북도">충청북도</option>
      </select>
      <input
        type="text"
        id="integrated-search"
        v-model="something"
        placeholder="  닉네임(ex: 김작가),  #태그(ex: #웨딩)"
        @keyup.enter="integratedSearch"
      />
      <button @click="integratedSearch"><i class="fas fa-search"></i></button>
    </div>
  </div>
</template>

<script>
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
  methods: {
    integratedSearch() {
      // something이 태그인 경우
      if (this.something[0] === '#') {
        this.info.keyword = this.something
          .slice(1, this.something.length)
          .trim()
        console.log(this.info)
        this.$store.dispatch('search/tagSearch', this.info)
      }
      // something이 작가인 경우
      else {
        this.info.keyword = this.something.trim()
        this.$store.dispatch('search/photographerSearch', this.info)
      }
    },
  },
}
</script>

<style scoped>
.search-style-page {
  width: 60%;
  height: 35px;
  position: absolute;
  top: 70%;
  flex-wrap: nowrap;
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
}
button {
  /* background-color: white; */
  background-color: transparent;
  /* 배경이 나뉘면 color 수정 */
  color: #c4c4c4;
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
