<template>
  <div class="inline-block profile fs-6">프로필</div>
  <!-- 클릭 시 드롭다운 -->
  <!-- <img src="{{ profilePicture }}" alt="" class="inline-block profile" /> -->
</template>

<script>
export default {
  name: 'ProfileIconMenu',
  computed: {
    profilePicture() {
      return this.$store.state.mainpage.profilePicture
    },
  },
  created() {
    this.$store.dispatch('mainpage/getProfileNickname')
  },
}
</script>

<style scoped>
.profile {
  width: 100px;
  height: 100%;
  background-color: gray;
  clip-path: circle(30%);
  /* 나중에 지우기 */
  line-height: 70px;
}
</style>
