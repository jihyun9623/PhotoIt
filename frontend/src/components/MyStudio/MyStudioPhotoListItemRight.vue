<template>
  <div class="image image-1">
    <div class="img img-hor">
      <div
        class="inner"
        data-aos="fade-up"
        data-aos-anchor-placement="top-bottom"
      >
        <img class="photo_ele" :src="photo1" alt="" />
      </div>
    </div>
  </div>
  <div class="image image-3">
    <div class="img img-square">
      <div
        class="inner"
        data-aos="fade-up"
        data-aos-anchor-placement="top-bottom"
      >
        <img :src="photo2" class="photo_ele" alt="" />
      </div>
    </div>
  </div>
  <div class="image image-5">
    <div class="img img-vert">
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
      photo1: this.photos1.data[0],
      photo2: this.photos1.data[1],
      photo3: this.photos1.data[2],
    }
  },
  props: {
    photos1: Object,
  },
}
</script>

<style>
.photo_ele {
  min-height: 100%;
  max-width: 100%;
  object-fit: cover;
}
</style>
