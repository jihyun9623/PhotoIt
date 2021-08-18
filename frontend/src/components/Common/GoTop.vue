<template>
  <i @click="goTopMove" class="fas fa-arrow-alt-circle-up gotop-btn fa-3x"></i>
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
  color: rgb(44, 7, 165);
  position: fixed;
  z-index: 100;
  bottom: 3vh;
  right: 3vw;
}
.gotop-btn:hover {
  cursor: pointer;
  color: rgb(124, 120, 120);
}
</style>
