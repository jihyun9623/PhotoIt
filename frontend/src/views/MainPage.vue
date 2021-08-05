<template>
  <div>
    <MainPageHeader />
    <MainPageIntroduction />
    <!-- v-for로 보여줄 태그 개수에 맞추어서 보여주기-->
    <!-- <MainPageTagList v-for="(tagSet, idx) in tagList" :key="idx" :tagSet="tagSet" /> -->
    <MainPageTagList />
  </div>
</template>

<script>
import MainPageIntroduction from '@/components/MainPage/MainPageIntroduction'
import MainPageHeader from '@/components/MainPage/MainPageHeader'
import MainPageTagList from '@/components/MainPage/MainPageTagList'

export default {
  name: 'MainPage',
  components: {
    MainPageIntroduction,
    MainPageHeader,
    MainPageTagList,
  },
  data() {
    return {
      // 8개의 태그가 받아와지는지 확인
      tagList: () => this.contents(),
    }
  },
  computed: {
    contents() {
      return this.$store.state.mainpage.contents.tagPhotoList
    },
  },
  created() {
    this.$store.dispatch('mainpage/getRegions')
    this.$store.dispatch('mainpage/getTags')
    this.$store.dispatch('mainpage/getProfileNickname')
    this.$store.dispatch('mainpage/getMainContents')
  },
}
</script>
