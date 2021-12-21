<template>
  <!-- 클릭 시 드롭다운 -->
  <span class="dropdown me-4">
    <img
      v-if="
        profilePicture === 'string' ||
        profilePicture === 'null' ||
        profilePicture === null ||
        profilePicture === ''
      "
      src="@/assets/images/profile_default.png"
      alt="default profile"
      class="inline-block profile dropdown-toggle"
      id="profileDropdown"
      data-bs-toggle="dropdown"
      aria-expanded="false"
    />
    <img
      v-else
      :src="profilePicture"
      alt="profile"
      class="inline-block profile dropdown-toggle"
      id="profileDropdown"
      data-bs-toggle="dropdown"
      aria-expanded="false"
    />
    <ul class="dropdown-menu" aria-labelledby="profileDropdown">
      <router-link :to="{ name: 'Login' }">
        <li v-if="!isLogin" class="mb-2 mt-2">로그인</li>
      </router-link>
      <router-link :to="{ name: 'Signup' }">
        <li v-if="!isLogin" class="mb-2">회원가입</li>
      </router-link>
      <li v-if="isLogin" @click="userLogout" class="mb-2 mt-2">로그아웃</li>
      <router-link :to="{ name: 'MyPage' }">
        <li v-if="isLogin" class="mb-2">마이페이지</li>
      </router-link>
      <!-- 비작가는 마이스튜디오 메뉴가 보이면 안된다. -->
      <li class="mb-2">
        <router-link
          v-if="role === 'PG'"
          :to="{ path: `/mystudio/${nickname}` }"
        >
          마이스튜디오
        </router-link>
      </li>
      <li class="mb-2">
        <router-link
          v-if="role === 'PG'"
          :to="{ path: `/mystudioedit/${nickname}` }"
        >
          마이스튜디오 EDIT
        </router-link>
      </li>
      <!-- <li v-if="isLogin" class="mb-2"><router-link :to="{ name: '' }"> 찜 목록 </router-link></li> -->
      <router-link :to="{ name: 'ChatRoomList' }">
        <li v-if="isLogin" class="mb-2">쪽지함</li>
      </router-link>

      <router-link :to="{ name: 'FavoriteList' }">
        <li v-if="isLogin" class="mb-2">찜 목록</li>
      </router-link>
    </ul>
  </span>
</template>

<script>
export default {
  name: 'ProfileIconMenu',
  computed: {
    profilePicture() {
      // return this.$store.state.login.profile
      return this.$store.state.mainpage.profilePicture
    },
    isLogin() {
      return this.$store.state.login.isLogin
    },
    nickname() {
      return this.$store.state.login.myNickname
    },
    role() {
      return this.$store.state.login.role
    },
  },
  methods: {
    userLogout(event) {
      this.$emit('user-logout', event.target.value)
    },
  },
  created() {
    this.$store.dispatch('mainpage/getProfileNickname')
    this.$store.dispatch('login/isLoginCheck')
    this.$store.dispatch('login/isRole')
    this.$store.dispatch('login/getProfile')
    this.$store.dispatch('login/getNickname')
  },
}
</script>

<style scoped>
.dropdown {
  width: 3vw;
}
.profile {
  width: 3vw;
  height: 3vw;
  border-radius: 50%;
  overflow: hidden;
  object-fit: cover;
}
.profile:hover {
  cursor: pointer;
}
a {
  text-decoration: none;
  color: black;
}
.dropdown-menu {
  text-align: center;
}
.dropdown-menu li:hover {
  background-color: rgb(143, 199, 231);
  cursor: pointer;
}
</style>
