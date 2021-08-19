<template>
  <div
    id="carouselExampleIndicators"
    class="carousel slide"
    data-bs-ride="carousel"
  >
    <div class="carousel-indicators">
      <button
        type="button"
        data-bs-target="#carouselExampleIndicators"
        data-bs-slide-to="0"
        class="active"
        aria-current="true"
        aria-label="Slide 1"
      ></button>
      <button
        v-if="best2"
        type="button"
        data-bs-target="#carouselExampleIndicators"
        data-bs-slide-to="1"
        aria-label="Slide 2"
      ></button>
      <button
        v-if="best3"
        type="button"
        data-bs-target="#carouselExampleIndicators"
        data-bs-slide-to="2"
        aria-label="Slide 3"
      ></button>
    </div>
    <div class="carousel-inner best3-all">
      <div v-if="best1" class="carousel-item active">
        <img :src="best1" class="d-block best3" />
      </div>
      <div v-if="best2" class="carousel-item">
        <img :src="best2" class="d-block best3" />
      </div>
      <div v-if="best3" class="carousel-item">
        <img :src="best3" class="d-block best3" />
      </div>
    </div>
    <button
      class="carousel-control-prev"
      type="button"
      data-bs-target="#carouselExampleIndicators"
      data-bs-slide="prev"
    >
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button
      class="carousel-control-next"
      type="button"
      data-bs-target="#carouselExampleIndicators"
      data-bs-slide="next"
    >
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
    <div v-if="this.$store.state.mystudio.best3 == null">
      {{ bestNoneMessage }}
    </div>
  </div>
</template>

<script>
export default {
  name: 'MyStudioBest',
  data: function () {
    return {
      best1: null,
      best2: null,
      best3: null,
      bestNoneMessage:
        '작가가 선정한 best3 사진이 없어 작가의 랜덤한 사진이 제공됩니다.',
    }
  },
  created: function () {
    if (this.$store.state.mystudio.best3 == null) {
      console.log('0')
      this.best1 =
        this.$store.state.mystudio.photo_all[
          Math.floor(
            Math.random() * this.$store.state.mystudio.photo_all.length,
          )
        ]
    } else if (this.$store.state.mystudio.best3.length == 3) {
      console.log('3')
      this.best3 = this.$store.state.mystudio.best3[2]
      this.best2 = this.$store.state.mystudio.best3[1]
      this.best1 = this.$store.state.mystudio.best3[0]
    } else if (this.$store.state.mystudio.best3.length == 2) {
      console.log('2')
      this.best2 = this.$store.state.mystudio.best3[1]
      this.best1 = this.$store.state.mystudio.best3[0]
    } else if (this.$store.state.mystudio.best3.length == 1) {
      console.log('1')
      this.best1 = this.$store.state.mystudio.best3[0]
    }
  },
}
</script>

<style>
.best3-all {
  max-width: 100%;
  max-height: 841px;
  object-fit: cover;
}
.best3-all .best3 {
  width: 100%;
  height: 841px;
  object-fit: cover;
  object-position: center center;
  margin: auto;
}
</style>
