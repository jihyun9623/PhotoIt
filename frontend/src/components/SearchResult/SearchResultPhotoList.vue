<template>
  <div class="container">
    <div class="row">
      <div
        class="col-6 col-md-3"
        v-for="(photo, idx) in photoList"
        :key="idx"
        :photo="photo"
      >
        <SearchResultPhotoItem />
      </div>
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
