<template>
  <div class="headerwrapper">
    <div class="headercontainer row">
      <div class="headerelement col-4">
        <router-link
          :to="{
            name: 'MyStudio',
            params: { nickname: this.$store.state.mystudio.nickname },
          }"
        >
          <img :src="pg_profPhoto" class="pg-profile" />
        </router-link>
        <div class="pg-info">
          <p class="pg-name">{{ this.$store.state.mystudio.nickname }}</p>
          <div class="pg-mentarea">
            <p class="pg-mentquote">"</p>
            <p class="pg-ment">
              {{ this.$store.state.mystudio.pg_profile.introduce }}
            </p>
            <p class="pg-mentquote">"</p>
          </div>
        </div>
      </div>
      <div class="headerelement col-2">
        <MyStudioHeaderFavorite class="col-4" />
        <MyStudioHeaderChat class="col-4" />
        <div class="col-4">
          <router-link :to="{ name: 'MainPage' }">
            <img src="@/assets/images/home_icon.png" class="icon" />
          </router-link>
        </div>
      </div>
      <div class="headerelement col-4">
        <MyStudioHeaderSearchBar />
      </div>
      <div class="headerelement col-2">
        <div class="col-6"></div>
        <div class="col-6">
          <ProfileIconMenu @user-logout="userLogout" />
        </div>
      </div>
    </div>
  </div>
  <!-- <MyStudioHeader /> -->
  <div class="fontCafe" v-if="!(this.$store.state.mystudio.photo_all == null)">
    <MyStudioBest />
    <MyStudioPhotoList />
  </div>
  <div v-else>
    <p class="d-flex justify-content-center align-content-center">
      업로드된 사진이 없습니다.
    </p>
  </div>
</template>

<script>
// import component from "component location"
// import MyStudioHeader from '@/components/MyStudio/MyStudioHeader'
import MyStudioPhotoList from '@/components/MyStudio/MyStudioPhotoList'
import MyStudioBest from '@/components/MyStudio/MyStudioBest'
import MyStudioHeaderChat from '@/components/MyStudio/MyStudioHeaderChat.vue'
import MyStudioHeaderFavorite from '@/components/MyStudio/MyStudioHeaderFavorite.vue'
import MyStudioHeaderSearchBar from '@/components/MyStudio/MyStudioHeaderSearchBar.vue'
import ProfileIconMenu from '@/components/Common/ProfileIconMenu'
import http from '@/assets/js/axios.js'

export default {
  name: 'MyStudio',
  components: {
    // components
    // MyStudioHeader,
    MyStudioPhotoList,
    MyStudioBest,
    MyStudioHeaderChat,
    MyStudioHeaderFavorite,
    MyStudioHeaderSearchBar,
    ProfileIconMenu,
  },
  computed: {
    pg_profPhoto() {
      return this.$store.state.mystudio.pg_profile.profPhoto
    },
  },
  created: function () {
    console.log('created start')
    this.$store.state.mystudio.nickname = this.$route.params.nickname
    this.$store.dispatch(
      'mystudio/pgProfile',
      this.$store.state.mystudio.nickname,
    )
    // this.pg_profPhoto = this.$store.state.mystudio.pg_profile.profPhoto
    this.$store.dispatch('mystudio/best3', this.$store.state.mystudio.nickname)
    this.$store.dispatch(
      'mystudio/photoAll',
      this.$store.state.mystudio.nickname,
    )
  },
  methods: {
    userLogout() {
      http
        .get('/user/signout')
        .then(() => {
          alert('로그아웃 되었습니다.')
          localStorage.removeItem('jwt')
          localStorage.removeItem('id')
          localStorage.removeItem('role')
          localStorage.removeItem('profile')
          localStorage.removeItem('nickname')
          this.$router.push({ name: 'MainPage' })
          if (this.$route.name === 'MainPage') {
            window.location.reload()
          }
        })
        .catch((err) => {
          console.log(err)
          alert('다시 시도해 주세요.')
        })
    },
  },
}
</script>

<style>
.headerwrapper {
  width: 100%;
  height: 72px;
  padding-left: 80px;
  padding-right: 80px;
  background: white;
  text-align: center;
  margin-top: 12px;
  margin-bottom: 12px;
}

.headercontainer {
  width: 100%;
  max-width: 1200px;
  display: inline-flex;
  flex-direction: row;
}

.headerelement {
  line-height: 100%;
  display: flex;
  justify-content: flex-right;
  align-items: center;
}

.pg-profile {
  width: 45px;
  height: 45px;
  object-fit: cover;
  cursor: pointer;
  clip-path: circle(22px at center);
  margin-right: 13px;
}

.pg-info {
  width: 100%;
}

.pg-name {
  height: 40px;
  width: 100%;
  display: flex;
  align-items: center;
  margin-bottom: 0;
  font-weight: bold;
  font-size: 20px;
}

.pg-mentarea {
  width: 100%;
  /* justify-content: space-between; */
  justify-content: flex-start;
  display: inline-flex;
  display: flex;
  align-items: center;
  height: 32px;
  margin-bottom: 0;
}

.pg-mentquote {
  display: inline-block;
  font-weight: bold;
  align-items: center;
  margin-bottom: 0;
  font-size: 35px;
  padding: 0;
  text-align: center;
}

.pg-ment {
  height: 100%;
  margin-bottom: 0;
  display: flex;
  align-items: center;
  font-size: 16px;
}
</style>
