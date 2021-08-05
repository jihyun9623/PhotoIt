<template>
  <section>
    <div class="login-form container mt-5">
      <div class="row">
        <!-- Logo 자리 -->
        <div class="logo-box mb-5">로고 자리</div>
        <div class="mb-3">
          <label for="id" class="form-label d-flex justify-content-start"
            >이메일(아이디)</label
          >
          <input
            type="email"
            class="form-control"
            id="email"
            placeholder="user@example.com"
            v-model="credentials.id"
            @keyup.enter="userLogin"
          />
        </div>
        <div class="mb-3">
          <label for="password" class="form-label d-flex justify-content-start"
            >비밀번호</label
          >
          <input
            type="password"
            class="form-control"
            id="password"
            placeholder="비밀번호"
            v-model="credentials.passwd"
            @keyup.enter="userLogin"
          />
        </div>
        <div class="align-items-center mb-4 mt-2">
          <button class="btn login-form-btn mb-2" @click="userLogin">
            로그인
          </button>
          <button type="button" class="btn login-form-btn mb-2 signup-btn">
            <router-link :to="{ name: 'Signup' }"> 회원가입 </router-link>
          </button>
        </div>
      </div>
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
import axios from 'axios'

export default {
  name: 'Login',
  data() {
    return {
      credentials: {
        id: null,
        passwd: null,
      },
    }
  },
  methods: {
    userLogin() {
      // store의 login 모듈의 saveToken 실행
      // this.$store.dispatch('login/saveToken', this.credentials)
      console.log(this.credentials)
      axios({
        method: 'post',
        url: 'http://localhost:8080/user/signin',
        data: this.credentials,
      })
        .then((res) => {
          // console.log(res)
          // res.data.token, res.data.id가 맞는지 확인
          localStorage.setItem('jwt', res.data.token)
          localStorage.setItem('id', res.data.id)
          this.$emit('login')
          this.$router.push({ name: 'MainPage' })
        })
        .catch((err) => {
          console.log(err)
          alert('로그인 정보가 잘못되었습니다.')
        })
    },
  },
}
</script>

<style scoped>
.login-form {
  max-width: 600px;
  margin: auto;
  padding: 20px;
  color: #000000;
  font-weight: bold;
}

input {
  border-style: none none solid none;
  border-width: 2px;
}
.login-form-btn {
  background-color: #c4c4c4;
  width: 95%;
  box-shadow: inset 0px 0px rgba(255, 255, 255, 0);
}
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
.signup-btn a {
  text-decoration: none;
  color: black;
}
</style>
