<template>
  <div class="bestlist">
    <img class="bestimg" :src="thumbnail" />
  </div>
</template>

<script>
export default {
  name: 'FavoriteListBoxBest',
  props: {
    best: {
      type: Object,
    },
  },
  data() {
    return {
      photoId: this.best.photoId,
      thumbnail: this.best.thumbnail,
    }
  },
}
</script>

<style scoped>
.bestlist {
  margin: 1%;
}
</style>
