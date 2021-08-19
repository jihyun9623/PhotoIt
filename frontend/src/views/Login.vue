<template>
  <section>
<<<<<<< HEAD
    <div class="login-form container">
      <div class="row fontCafe">
        <!-- Logo-->
        <router-link :to="{ name: 'MainPage' }">
          <img
            src="@/assets/images/Logo_ver2.png"
            alt="logo"
            class="logo-box pb-4"
          />
        </router-link>
        <div class="mb-3 fontCafe">
          <label for="id" class="form-label d-flex justify-content-start"
=======
    <div class="login-form container mt-5">
      <form class="row">
        <!-- Logo 자리 -->
        <div class="logo-box mb-5">로고 자리</div>
        <div class="mb-3">
          <label for="email" class="form-label d-flex justify-content-start"
>>>>>>> b265e36ab2382e6685df9df1c57de22ab86a1626
            >이메일(아이디)</label
          >
          <input
            type="email"
            class="form-control"
            id="email"
            placeholder="user@example.com"
            v-model="credentials.email"
            @keyup.enter="login"
          />
        </div>
        <div class="mb-3 fontCafe">
          <label for="password" class="form-label d-flex justify-content-start"
            >비밀번호</label
          >
          <input
            type="password"
            class="form-control"
            id="password"
            placeholder="비밀번호"
            v-model="credentials.password"
            @keyup.enter="login"
          />
        </div>
<<<<<<< HEAD
        <div class="align-items-center mb-4 mt-5">
          <button class="btn login-form-btn mb-2 login-btn" @click="userLogin">
            로그인
          </button>
          <button
            type="button"
            class="btn login-form-btn mb-2 btnOutline fontCafe"
          >
            <router-link :to="{ name: 'Signup' }"> 회원가입 </router-link>
=======
        <div class="align-items-center mb-4 mt-2">
          <button class="btn login-form-btn mb-2" @click="login">로그인</button>
          <button type="button" class="btn login-form-btn mb-2">
            <!-- <router-link :to="{ name: Signup }"> -->
            <span>회원가입</span>
            <!-- </router-link> -->
>>>>>>> b265e36ab2382e6685df9df1c57de22ab86a1626
          </button>
        </div>
      </form>
      <div class="mt-4 d-flex justify-content-evenly">
        <button class="btn login-find-btn">
          <!-- <router-link :to="{ name: 어딘가 }"> -->
          <span>아이디 찾기</span>
          <!-- </router-link> -->
        </button>
        <button class="btn login-find-btn">
          <!-- <router-link :to="{ name: 어딘가 }"> -->
          <span>비밀번호 찾기</span>
          <!-- </router-link> -->
        </button>
      </div>
    </div>
  </section>
</template>

<script>
// import component from "component location"

export default {
  name: 'Login',
  data() {
    return {
      credentials: {
        email: null,
        password: null,
      },
    }
  },
  method: {
    login() {
      // store의 login 모듈의 saveToken 실행
      this.$store.dispatch('login/saveToken', this.credentials)
    },
  },
}
</script>

<style scoped>
.login-form {
  max-width: 600px;
  margin: auto;
  padding: 70px 40px;
  color: #001557;
  font-weight: bold;
  border-radius: 0.5rem;
  border: 1px solid;
  margin-top: 20vh;
}
<<<<<<< HEAD
.loginBox {
  position: absolute;
}
=======

>>>>>>> b265e36ab2382e6685df9df1c57de22ab86a1626
input {
  border-style: none none solid none;
  border-width: 2px;
}
.login-form-btn {
  background-color: #c4c4c4;
  width: 95%;
  box-shadow: inset 0px 0px rgba(255, 255, 255, 0);
}
<<<<<<< HEAD
.login-btn {
  border: 1px solid #3a539b;
  color: #3a539b;
}
.login-btn:hover {
  border: 1px solid #4b77be;
  background-color: #4b77be;
  color: white;
}
=======
>>>>>>> b265e36ab2382e6685df9df1c57de22ab86a1626
.login-find-btn {
  background-color: #ffffff;
  width: 95%;
  box-shadow: inset 0px 0px rgba(255, 255, 255, 0);
}
.logo-box {
  box-sizing: content-box;
  width: 90%;
  height: 100px;
  background-color: #c4c4c4;
  margin: 0px auto;
}
.form-control:focus {
  border-color: #c4c4c4;
  box-shadow: inset 0px 0px rgba(255, 255, 255, 0);
}
<<<<<<< HEAD
.btnOutline a {
  text-decoration: none;
  color: white;
}
=======
>>>>>>> b265e36ab2382e6685df9df1c57de22ab86a1626
</style>
