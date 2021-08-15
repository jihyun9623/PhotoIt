<template>
  <div class="favlistbox">
    <!-- <div class="head"> -->
    <div class="pgNick">
      <p>aaaaa</p>
    </div>
    <!-- <div class="deleteFav"><button>x</button></div> -->
    <!-- </div> -->
    <div
      class="bestphoto"
      v-for="(photo, idx) in photoList"
      :key="idx"
      :best="best"
    >
      <FavoriteListBoxBest />
    </div>
  </div>
</template>

<script>
import FavoriteListBoxBest from '@/components/FavoriteList/FavoriteListBoxBest'

export default {
  name: 'FavoriteListBox',
  components: {
    FavoriteListBoxBest,
  },
  props: {
    pgFav: {
      type: Object,
    },
    photoList: {
      type: Array,
    },
  },
  data() {
    return {
      nickName: this.pgFav.nickName,
    }
  },
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
