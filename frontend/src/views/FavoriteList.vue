<template>
<<<<<<< HEAD
  <SearchRegion />
  <section class="container">
    <div class="row d-flex justify-content-between">
      <div class="empty-box"></div>
      <h1 class="mb-5">My Favorite List</h1>
      <!-- {{ favListArray }} -->
      <FavoriteListBox
        v-for="(favItem, idx) in favListArray"
        :key="idx"
        :favItem="favItem"
      />
    </div>
  </section>
=======
  <h1>FavoritList</h1>
>>>>>>> b265e36ab2382e6685df9df1c57de22ab86a1626
</template>

<script>
// import component from "component location"

export default {
  name: "FavoriteList",
  components: {
    // components
  },
};
</script>
