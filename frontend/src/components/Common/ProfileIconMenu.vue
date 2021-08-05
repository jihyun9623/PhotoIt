<template>
  <div class="inline-block profile fs-6">프로필</div>
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
