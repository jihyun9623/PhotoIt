<template>
  <div class="d-flex justify-content-center">
    <div class="search-style-page search-style">
      <select
        name="region"
        id="searchRegion"
        class="me-2 fontCafe"
        v-model="info.region"
      >
        <option value="all" class="fontCafe">&nbsp;&nbsp;전체</option>
        <option v-for="(region, idx) in regions" :key="idx" :value="region">
          {{ region }}
        </option>
      </select>
      <input
        type="text"
        id="integrated-search-main"
        :value="something"
        @input="onInputChange"
        @click="onShow"
        placeholder="닉네임(ex: 김작가),  #태그(ex: #웨딩)"
        class="fontCafe"
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
  </div>
</template>

<script>
export default {
  name: 'MainPageSearchBar',
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
.auto-complete {
  list-style: none;
  width: 77%;
  max-height: 20vh;
  background-color: white;
  margin-right: 6%;
  border: 0.1rem solid #c7c7c7;
  margin-left: auto;
  overflow: auto;
}
.auto-complete li:hover {
  background-color: rgb(143, 199, 231);
  cursor: pointer;
  overflow: auto;
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
