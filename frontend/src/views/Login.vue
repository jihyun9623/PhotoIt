<template>
  <section>
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
            >이메일(아이디)</label
          >
          <input
            type="email"
            class="form-control"
            id="email"
            placeholder="hanq@naver.com"
            v-model="credentials.id"
            @keyup.enter="userLogin"
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
            placeholder="q1w2e3r4!"
            v-model="credentials.passwd"
            @keyup.enter="userLogin"
          />
        </div>
        <div class="align-items-center mb-4 mt-5">
          <button class="btn login-form-btn mb-2 login-btn" @click="userLogin">
            로그인
          </button>
          <button
            type="button"
            class="btn login-form-btn mb-2 btnOutline fontCafe"
          >
            <router-link :to="{ name: 'Signup' }"> 회원가입 </router-link>
          </button>
        </div>
      </div>
      <!-- <div class="mt-4 d-flex justify-content-evenly">
        <button class="btn login-find-btn">
          <router-link :to="{ name: 어딘가 }">
            <span>아이디 찾기</span>
          </router-link>
        </button>
        <button class="btn login-find-btn">
          <router-link :to="{ name: 어딘가 }">
            <span>비밀번호 찾기</span>
          </router-link>
        </button>
      </div> -->
    </div>
  </section>
</template>

<script>
import httpNoJWT from '@/assets/js/axiosNotJWT.js'

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
      httpNoJWT
        .post('/user/signin', this.credentials)
        .then((res) => {
          console.log(res)
          localStorage.setItem('jwt', res.data.jwt)
          localStorage.setItem('id', res.data.id)
          localStorage.setItem('role', res.data.role)
          this.$emit('login')
          this.$store.dispatch('login/isLoginCheck')
          this.$store.dispatch('login/isRole')
          window.location.reload()
          this.$router.push({ name: 'MainPage' })
        })
        .catch((err) => {
          console.log(err)
          alert('로그인 정보가 잘못되었습니다.')
        })
    },
  },
  created() {
    this.$store.dispatch('login/isLoginCheck')
    if (this.$store.state.login.isLogin) {
      this.$router.push({ name: 'MainPage' })
    }
  },
  mounted() {
    window.scrollTo(0, 0)
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
.loginBox {
  position: absolute;
}
input {
  border-style: none none solid none;
  border-width: 2px;
}
.login-form-btn {
  /* background-color: #c4c4c4; */
  width: 95%;
  box-shadow: inset 0px 0px rgba(255, 255, 255, 0);
}
.login-btn {
  border: 1px solid #3a539b;
  color: #3a539b;
}
.login-btn:hover {
  border: 1px solid #4b77be;
  background-color: #4b77be;
  color: white;
}
.login-find-btn {
  background-color: #ffffff;
  width: 95%;
  box-shadow: inset 0px 0px rgba(255, 255, 255, 0);
}
.logo-box {
  box-sizing: content-box;
  width: 30%;
  /* height: 100px; */
  /* background-color: #c4c4c4; */
  margin: 5% auto;
}
.form-control:focus {
  border-color: #c4c4c4;
  box-shadow: inset 0px 0px rgba(255, 255, 255, 0);
}
.btnOutline a {
  text-decoration: none;
  color: white;
}
</style>
