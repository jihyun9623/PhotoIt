<template>
  <div class="col-md-4 mt-3 mb-3 d-inline-block">
    <div v-if="bestPhoto" class="card real-photo">
      <img class="img-fluid card-img" alt="Best Photo" :src="bestPhoto" />
      <!-- 버튼에 클릭 이벤트 삽입 -> 베스트 사진 삭제 -->
      <button type="button" class="btn-close" aria-label="Close"></button>
    </div>
    <div v-else class="card empty-photo">
      <div class="fake-box card-img"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MyStudioEditBest',
  props: {
    bestPhoto: {
      type: String,
    },
  },
}
</script>

<style scoped>
.card-img {
  width: 100%;
  min-width: 15vh;
  height: 25vh;
  object-fit: cover;
}
.card.real {
  border-style: none;
}
.fake-box {
  border-style: 1rem solid black;
}
.btn-close {
  position: absolute;
  top: 5%;
  right: 5%;
  background-color: white;
}
</style>
