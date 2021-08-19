<template>
  <div class="col-md-3 mb-3 me-1 ms-1 d-inline-block big-box">
    <div v-if="photo" class="card real">
      <img class="img-fluid card-img" alt="Best Photo" :src="photo" />
    </div>
    <div v-else class="card empty">
      <div class="fake-box card-img"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'FavoriteListBoxBest',
  props: {
    photo: {
      type: String,
    },
  },
}
</script>

<style scoped>
.card-img {
  width: 100%;
  min-width: 15vh;
  height: 15vh;
  object-fit: cover;
}
.card.real {
  border-style: none;
}
.fake-box {
  border-style: 1rem solid black;
}
.big-box {
  overflow: hidden;
}
</style>
