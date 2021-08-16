<template>
  <router-link
    :to="{ name: 'MainPage' }"
    class="
      router-link-style
      d-flex
      justify-content-start
      ps-4
      align-items-center
    "
  >
    <img src="../../assets/images/Logo_ver2.png" alt="" class="logo" />
  </router-link>
</template>

<script>
export default {
  name: 'Logo',
}
</script>

<style scpoed>
.logo {
  /* 추후 수정 */
  height: 45px;
  z-index: 1;
}
.router-link-style {
  display: inline-block;
  height: 100%;
}
</style>
