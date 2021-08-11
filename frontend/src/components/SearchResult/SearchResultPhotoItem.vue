<template>
  <div class="card">
    <img class="img-fluid" alt="Photo" :src="thumb" />
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
  data() {
    return {
      photoId: this.photo.photoId,
      thumb: this.photo.thumb,
      nickName: this.photo.nickName,
    }
  },
}
</script>

<style></style>
