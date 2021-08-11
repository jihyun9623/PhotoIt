<template>
  <div class="col-6 col-md-3 pb-5">
    <div class="card">
      <img class="img-fluid card-img" alt="Photo" :src="photo.thumb" />
    </div>
  </div>
</template>

<script>
export default {
  name: 'SearchResultPhotoItem',
  props: {
    photo: {
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
.card-img:hover {
  cursor: pointer;
  transform: scale(1.1);
  opacity: 0.7;
  overflow: hidden;
}
</style>
