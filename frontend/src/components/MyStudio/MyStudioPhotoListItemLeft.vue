<template>
  <div class="image image-2">
    <div class="img img-vert">
      <div class="inner">
        <img :src="photo1" class="photo_ele" alt="" />
      </div>
    </div>
  </div>
  <div class="image image-4">
    <div class="img img-hor">
      <div class="inner">
        <img :src="photo2" class="photo_ele" alt="" />
      </div>
    </div>
  </div>
  <div class="image image-6">
    <div class="img img-square">
      <div class="inner">
        <img :src="photo3" class="photo_ele" alt="" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MyStudioPhotoListItem',
  data: function () {
    return {
      photo1: this.photos.data[0],
      photo2: this.photos.data[1],
      photo3: this.photos.data[2],
    }
  },
  props: {
    photos: Object,
  },
}
</script>

<style>
.photo_ele {
  object-fit: cover;
}
</style>
