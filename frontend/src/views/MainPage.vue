<template>
  <div>
    <MainPageHeader />
    <MainPageIntroduction />
    <!-- v-for로 보여줄 태그 개수에 맞추어서 보여주기-->
    <MainPageTagList
      v-for="(tagSet, idx) in contents"
      :key="idx"
      :tagSet="tagSet"
      class="mb-5 mt-3"
    />
    <!-- <MainPageTagList /> -->
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
  computed: {
    contents() {
      return this.$store.state.mainpage.contents
    },
    isLoginCheck() {
      return this.$store.state.login.isLogin
    },
  },
  created() {
    // this.$store.dispatch('mainpage/getRegions')
    this.$store.dispatch('mainpage/getTags')
    this.$store.dispatch('mainpage/getMainContents')
    // 로그인 여부 확인, 로그인 했을 때만 프로필과 닉네임을 요청한다.
    this.$store.dispatch('login/isLoginCheck')
    if (this.isLoginCheck === true) {
      this.$store.dispatch('mainpage/getProfileNickname')
    }
  },
}
</script>
