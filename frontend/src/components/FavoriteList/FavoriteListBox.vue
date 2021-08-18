<template>
  <div class="favlistbox">
    <!-- <div class="head"> -->
    <!-- <router-link
      :to="{ path: `/mystudio/${profile.nickName}` }"
      class="pg-name"
    >
      <div class="card">
        <img class="img-fluid card-img" alt="Profile" :src="profile.photo" />
        <div class="card-body">
          <p class="card-text fs-6 fw-bold text-center pt-3">
            {{ profile.nickName }}
          </p>
        </div>
      </div>
    </router-link> -->
    <!-- <div class="pgNick"> -->
    <!-- <p>{{ favNick }}</p>
    <a>{{ favBest }}</a> -->

    <p>{{ fav }}</p>
    <a>{{ Object.keys(fav) }}</a>
    <p>{{ Object.values(fav)[0] }}</p>

    <!-- </div> -->
    <!-- <div class="deleteFav"><button>x</button></div> -->
    <!-- </div> -->
    <!-- <div
      class="bestphoto"
      v-for="(photo, idx) in photoList"
      :key="idx"
      :best="best"
    >
      <FavoriteListBoxBest />
    </div> -->
  </div>
</template>

<script>
// import FavoriteListBoxBest from '@/components/FavoriteList/FavoriteListBoxBest'

export default {
  name: 'FavoriteListBox',
  components: {
    // FavoriteListBoxBest,
  },
  props: {
    fav: Array,
    // favNick: Array,
    // favBest: Array,
  },
  created() {},
  // data() {
  //   return {
  //     favList: this.$state.
  //   }
  // },
}
</script>

<style scoped>
.favlistbox {
  border: solid 1px;
  border-radius: 1rem;
  width: 45%;
}
.head {
  display: flex;
}
.pgNick {
  margin: 5px;
}
.bestphoto {
  margin: 5px;
}
.bestphoto {
  padding: 1%;
  display: flex;
}
</style>
