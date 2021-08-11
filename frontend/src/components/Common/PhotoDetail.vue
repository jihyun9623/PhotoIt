<template>
  <div
    v-for="(item, idx) in items"
    :item="item"
    :key="idx"
    class="modal fade"
    :id="`detailModal-${item.photoId}`"
    tabindex="-1"
    aria-labelledby="detailModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-body d-flex justify-content-center">
          <img :src="detailPhoto" alt="detail" class="modal-img" />
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
          >
            Close
          </button>
          <button type="button" class="btn btn-primary">Save changes</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PhotoDetail',
  props: {
    items: {
      type: Object,
    },
  },
  computed: {
    detailPhoto() {
      return this.$store.state.mainpage.detailPhoto
    },
  },
}
</script>

<style scoped>
.modal-img {
  max-width: 40vw;
  max-height: 50vh;
  object-fit: contain;
}
.modal-dialog {
  max-height: 50vh;
  max-width: 45vw;
}
.modal-content {
  background-color: black;
}
.modal-footer {
  border-style: none;
}
</style>
