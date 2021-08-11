<template>
  <div class="container">
    <div class="row">
      <SearchResultPhotoItem
        v-for="(photo, idx) in photoList"
        :key="idx"
        :photo="photo"
        data-bs-toggle="modal"
        :data-bs-target="`#detailModal-${photo.photoId}`"
      />
      <!-- 모달 -->
      <div
        v-for="(photo, idx) in photoList"
        :key="idx"
        :photo="photo"
        class="modal fade"
        :id="`detailModal-${photo.photoId}`"
        tabindex="-1"
        aria-labelledby="detailModalLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body">{{ photo.photoId }}</div>
            <div class="modal-footer">
              <button
                type="button"
                class="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Close
              </button>
              <button type="button" class="btn btn-primary">
                Save changes
              </button>
            </div>
          </div>
        </div>
      </div>
      <!-- <PhotoDetail /> -->
      <!-- </div> -->
    </div>
  </div>
</template>

<script>
import SearchResultPhotoItem from '@/components/SearchResult/SearchResultPhotoItem'
export default {
  name: 'SearchResultPhotoList',
  components: {
    SearchResultPhotoItem,
  },
  props: {
    photoList: {
      type: Array,
    },
  },
}
</script>

<style></style>
