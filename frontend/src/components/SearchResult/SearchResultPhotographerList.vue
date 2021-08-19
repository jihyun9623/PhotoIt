<template>
  <div class="container">
    <div class="row pt-5">
      <SearchResultPhotographerItem
        v-for="(profile, idx) in profileList"
        :key="idx"
        :profile="profile"
      />
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
