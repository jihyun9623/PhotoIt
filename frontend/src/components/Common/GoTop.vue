<template>
  <div style="background-color: white">
    <i
      @click="goTopMove"
      class="fas fa-arrow-alt-circle-up gotop-btn fa-3x"
    ></i>
  </div>
</template>

<script>
export default {
  name: 'GoTop',
  methods: {
    goTopMove() {
      window.scrollTo(0, 0)
    },
  },
}
</script>

<style scoped>
.gotop-btn {
  color: #1f3a93;
  position: fixed;
  z-index: 100;
  bottom: 3vh;
  right: 3vw;
}
.gotop-btn:hover {
  cursor: pointer;
  color: #4b77be;
}
</style>
