<template>
  <div class="col-md-3">
    <div class="card">
      <img class="img-fluid card-img" alt="Photo" :src="item.thumbnail" />
    </div>
  </div>
</template>

<script>
export default {
  name: 'MainPageTagItem',
  props: {
    item: {
      type: Object,
    },
  },
}
</script>

<style scoped>
.card-img {
  width: 100%;
  height: 20vh;
  object-fit: cover;
}
</style>
