<template>
  <div class="container">
    <div class="row">
      <div
        class="col-6 col-md-3"
        v-for="(profile, idx) in profileList"
        :key="idx"
        :profile="profile"
      >
        <SearchResultPhotographerItem />
      </div>
    </div>
  </div>
</template>

<script>
import SearchResultPhotographerItem from '@/components/SearchResult/SearchResultPhotographerItem'
export default {
  name: 'SearchResultPhotographerList',
  components: {
    SearchResultPhotographerItem,
  },
  props: {
    profileList: {
      type: Array,
    },
  },
}
</script>

<style></style>
