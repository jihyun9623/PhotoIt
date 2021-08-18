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
        type="button"
        data-bs-target="#carouselExampleIndicators"
        data-bs-slide-to="1"
        aria-label="Slide 2"
      ></button>
      <button
        type="button"
        data-bs-target="#carouselExampleIndicators"
        data-bs-slide-to="2"
        aria-label="Slide 3"
      ></button>
    </div>
    <div class="carousel-inner best3-all">
      <div class="carousel-item active">
        <img :src="best1" class="d-block best3" />
      </div>
      <div class="carousel-item">
        <img :src="best2" class="d-block best3" />
      </div>
      <div class="carousel-item">
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
  </div>
</template>

<script>
export default {
  data: function () {
    return {
      best1: this.$store.state.mystudio.best3[0],
      best2: this.$store.state.mystudio.best3[1],
      best3: this.$store.state.mystudio.best3[2],
    }
  },
}
</script>

<style>
.best3-all {
  max-width: 100%;
  max-height: 865px;
}
.best3-all .best3 {
  object-fit: contain;
  max-width: 100%;
  margin: auto;
}
</style>
