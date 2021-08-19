<template>
  <div>
    <img
      v-if="detailIsFavorite && isLogin"
      src="@/assets/images/fav_red_icon.png"
      class="icon fas fa-heart favorit-icon"
      @click="deleteFavorite"
    />
    <img
      v-else-if="!detailIsFavorite && isLogin"
      src="@/assets/images/fav_black_icon.png"
      class="icon far fa-heart favorit-icon"
      @click="addFavorite"
    />
  </div>
</template>

<script>
export default {
  name: 'MyStudioHeaderFavorite',
  computed: {
    detailIsFavorite() {
      return this.$store.state.mainpage.detailIsFavorite
    },
    isLogin() {
      return this.$store.state.login.isLogin
    },
  },
  methods: {
    addFavorite() {
      this.$store.dispatch('mainpage/addFavorite', {
        pgNick: this.detailPgNickname,
        userNick: this.$store.state.login.myNickname,
      })
    },
    deleteFavorite() {
      this.$store.dispatch('mainpage/deleteFavorite', {
        pgNick: this.detailPgNickname,
        userNick: this.$store.state.login.myNickname,
      })
    },
    originShow() {
      window.open(this.detailPhoto)
    },
  },
  created() {
    // 로그인 여부 확인
    this.$store.dispatch('login/isLoginCheck')
    this.$store.dispatch('login/getNickname')
  },
}
</script>

<style>
.icon {
  width: 45%;
}
</style>
