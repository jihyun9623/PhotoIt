<template>
<<<<<<< HEAD
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
=======
  <h1>MyStudio1</h1>
>>>>>>> b265e36ab2382e6685df9df1c57de22ab86a1626
</template>

<script>
// import component from "component location"

export default {
  name: "MyStudio",
  components: {
    // components
  },
};
</script>
