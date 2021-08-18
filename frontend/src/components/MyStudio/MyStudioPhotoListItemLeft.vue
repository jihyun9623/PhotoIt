<template>
  <div class="image image-2">
    <div class="img img-vert">
      <div
        class="inner"
        data-aos="fade-up"
        data-aos-anchor-placement="top-bottom"
      >
        <img :src="photo1" class="photo_ele" alt="" />
      </div>
    </div>
  </div>
  <div class="image image-4">
    <div class="img img-hor">
      <div
        class="inner"
        data-aos="fade-up"
        data-aos-anchor-placement="top-bottom"
      >
        <img :src="photo2" class="photo_ele" alt="" />
      </div>
    </div>
  </div>
  <div class="image image-6">
    <div class="img img-square">
      <div
        class="inner"
        data-aos="fade-up"
        data-aos-anchor-placement="top-bottom"
      >
        <img :src="photo3" class="photo_ele" alt="" />
      </div>
    </div>
  </div>
</template>

<script>
import AOS from 'aos'
import 'aos/dist/aos.css'

AOS.init()

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
