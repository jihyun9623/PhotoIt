<template>
  <div class="col-md-6 fav-box mb-3 mt-4 d-inline">
    <div class="d-flex justify-content-between mt-3">
      <router-link
        class="go-mystudio"
        :to="{ path: `/mystudio/${favItem[0]}` }"
      >
        <p class="fw-bold ps-2 pe-2">@ {{ favItem[0] }}</p>
      </router-link>
      <button type="button" class="btn-close" @click="deleteFavorite"></button>
    </div>
    <div>
      <FavoriteListBoxBest
        v-for="(n, idx) in 3"
        :key="idx"
        :photo="favItem[1][n - 1]"
      />
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
    favItem: {
      type: Object,
    },
  },
  methods: {
    deleteFavorite() {
      this.$store
        .dispatch('mainpage/deleteFavorite', {
          pgNick: this.favItem[0],
          userNick: this.$store.state.login.myNickname,
        })
        .then(() => {
          this.$store.dispatch('favorite/getFavList')
          window.location.reload()
        })
      // await this.$store.dispatch('mainpage/deleteFavorite', {
      //   pgNick: this.favItem[0],
      //   userNick: this.$store.state.login.myNickname,
      // })
    },
  },
}
</script>

<style scoped>
.fav-box {
  border: 2px dashed;
  border-radius: 1rem;
  width: 45%;
}
.go-mystudio {
  text-decoration: none;
  color: rgb(149, 149, 149);
}
.go-mystudio:hover {
  /* text-decoration: underline; */
  color: black;
}
</style>
