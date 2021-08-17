<template>
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
      :value="something"
      @input="onInputChange"
      @click="onShow"
      placeholder="닉네임(ex: 김작가),  #태그(ex: #웨딩)"
      @keyup.enter="integratedSearch"
    />
    <button @click="integratedSearch"><i class="fas fa-search"></i></button>
    <!-- 검색어 후보 -->
    <div v-show="isShow && tagFilterList" class="auto-complete">
      <li
        v-for="(tag, idx) in tagFilterList"
        :key="idx"
        class="ps-3 pe-3 pt-1 pb-1 d-flex justify-content-between"
        @click="selectTag(tag)"
      >
        <span>
          <i class="fas fa-search fa-xs search-icon me-1"></i>
          {{ tag }}
        </span>
        <span>
          <i class="fas fa-mouse fa-xs click-icon"></i>
        </span>
      </li>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  name: 'SearchBar',
  data() {
    return {
      isShow: true,
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
    tagList() {
      return this.$store.state.mainpage.tags
    },
    // 태그 검색어 자동 완성
    tagFilterList() {
      if (this.something && this.something[0] === '#') {
        const str = this.something.slice(1, this.something.length).trim()
        return this.tagList.filter((tag) => tag.match(str))
      }
      return ''
    },
  },
  methods: {
    onInputChange(event) {
      this.something = event.target.value
    },
    onShow() {
      this.isShow = !this.isShow
    },
    selectTag(tag) {
      this.something = ''
      this.info.keyword = tag
      this.isShow = false
      this.$store.dispatch('search/tagSearch', this.info)
      this.$router.push(`/search/${this.info.keyword}/${this.info.region}/tag`)
    },
    integratedSearch() {
      this.isShow = false
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
  created() {
    this.isShow = false
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
  color: rgb(49, 76, 233);
  border: none;
  height: 100%;
  padding-top: 1px;
}
select {
  width: 13%;
  height: 100%;
  border: solid 1px #c4c4c4;
}
.auto-complete {
  list-style: none;
  width: 77%;
  height: auto;
  background-color: white;
  margin-right: 6%;
  border: 0.1rem solid #c7c7c7;
  margin-left: auto;
}
.auto-complete li:hover {
  background-color: rgb(143, 199, 231);
  cursor: pointer;
}
.search-icon {
  background-color: rgb(184, 196, 207);
  color: white;
  padding: 0.3em;
  border-radius: 50%;
  box-shadow: 0px 0px 2px #888;
}
.click-icon {
  color: rgba(11, 11, 110, 0.541);
}
</style>
