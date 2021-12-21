<template>
  <div class="col-md-3 mt-3 mb-3 d-inline-block">
    <div class="card">
      <img class="img-fluid card-img" alt="Photo" :src="item" />
      <!-- 버튼에 클릭 이벤트 삽입 -> 사진 삭제 -->
      <button
        type="button"
        class="btn-close"
        aria-label="Close"
        @click="delClick"
      ></button>
      <!-- 버튼에 클릭 이벤트 삽입 -> 베스트 사진 추가 -->
      <button
        type="button"
        class="btn-add-best"
        data-bs-toggle="tooltip"
        data-bs-placement="bottom"
        title="베스트 사진으로 추가해보세요!"
        @click="addClick"
      >
        <i class="fas fa-plus"></i>
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MyStudioEditPhotoList',
  props: {
    item: {
      type: String,
    },
  },
  methods: {
    addClick() {
      this.$emit('call-add')
    },
    delClick() {
      this.$emit('call-del')
    },
  },
}
</script>

<style scoped>
.card-img {
  width: 100%;
  min-width: 15vh;
  height: 20vh;
  object-fit: cover;
}
.btn-close {
  position: absolute;
  top: 5%;
  right: 5%;
  background-color: white;
}
.btn-add-best {
  position: absolute;
  top: 5%;
  left: 5%;
  color: black;
  border: none;
  opacity: 75%;
}
.btn-add-best:hover {
  opacity: 90%;
}
</style>
