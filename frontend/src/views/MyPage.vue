<template>
  <div>
    <header id="header" class="fixed-top">
      <div
        class="container d-flex align-items-center justify-content-lg-between"
      >
        <h1 class="me-auto me-lg-0">
          <router-link to="/"
            ><img
              src="../assets/images/Logo.png"
              alt=""
              width="75"
              height="40"
              class="d-inline-block align-text-top"
          /></router-link>
        </h1>

        <li class="nav-item float-end list-unstyled">
          <ProfileIconMenu />
        </li>
      </div>
    </header>
    <section id="hero" class="d-flex align-items-center justify-content-center">
      <div class="container text-center">
        <main class="form-signin">
          <form>
            <router-link to="/">
              <img
                class="mb-3"
                src="../assets/images/Logo_vertical.png"
                alt=""
                width="800"
                height="100"
              />
            </router-link>
            <h3 class="h3 mb-4 fw-normal">My Page</h3>
            <div class="input-group mb-4">
              <span class="input-group-text" id="clsfName"
                ><img
                  src="assets/img/user/membership.png"
                  alt=""
                  style="width: 20px; height: 20px"
              /></span>
              <input
                type="text"
                class="form-control mb-0"
                aria-label="userClsfName"
                aria-describedby="userClsfName"
                v-model="userClsfName"
                disabled
              />
            </div>
            <div class="input-group mb-4">
              <span class="input-group-text" id="name"
                ><img
                  src="assets/img/user/userName.png"
                  alt=""
                  style="width: 20px; height: 20px"
              /></span>
              <input
                type="text"
                class="form-control mb-0"
                aria-label="userName"
                aria-describedby="userName"
                v-model="userName"
                disabled
              />
            </div>
            <div class="input-group mb-4">
              <span class="input-group-text" id="email"
                ><img
                  src="assets/img/user/userEmail.png"
                  alt=""
                  style="width: 20px; height: 20px"
              /></span>
              <input
                type="text"
                class="form-control mb-0"
                aria-label="userEmail"
                aria-describedby="userEmail"
                v-model="userEmail"
                disabled
              />
            </div>
            <div class="input-group mb-4">
              <span class="input-group-text" id="password"
                ><img
                  src="assets/img/user/userPassword.png"
                  alt=""
                  style="width: 20px; height: 20px"
              /></span>
              <input
                type="password"
                class="form-control mb-0"
                aria-label="userPassword"
                aria-describedby="userPassword"
                v-model="userPassword"
                :disabled="isDisabled"
              />
            </div>
            <div class="input-group mb-4">
              <span class="input-group-text" id="confirmPassword"
                ><img
                  src="assets/img/user/userPassword.png"
                  alt=""
                  style="width: 20px; height: 20px"
              /></span>
              <input
                type="password"
                class="form-control mb-0"
                aria-label="confirmPassword"
                aria-describedby="confirmPassword"
                v-model="userPassword"
                :disabled="isDisabled"
              />
            </div>
            <div class="input-group mb-4">
              <span class="input-group-text" id="phoneNumber"
                ><img
                  src="assets/img/user/phone.png"
                  alt=""
                  style="width: 20px; height: 20px"
              /></span>
              <input
                type="text"
                class="form-control mb-0"
                aria-label="userPhoneNumber"
                aria-describedby="userPhoneNumber"
                v-model="userPhoneNumber"
                :disabled="isDisabled"
              />
            </div>
            <div class="input-group mb-4">
              <span class="input-group-text" id="address"
                ><img
                  src="assets/img/user/address.png"
                  alt=""
                  style="width: 20px; height: 20px"
              /></span>
              <input
                type="text"
                class="form-control mb-0"
                aria-label="userAddress"
                aria-describedby="userAddress"
                v-model="userAddress"
                :disabled="isDisabled"
              />
            </div>

            <button
              @click="update"
              class="btn btn-lg btn-outline btn-warning"
              type="button"
              v-if="!isUpdate"
            >
              수정</button
            >&nbsp;
            <button
              @click="updateUser"
              class="btn btn-lg btn-outline btn-success"
              type="button"
              v-else
            >
              확인</button
            >&nbsp;
            <button
              @click="deleteUser"
              class="btn btn-lg btn-outline btn-danger"
              type="button"
              v-if="!isUpdate"
            >
              탈퇴
            </button>
            <br />
            <p class="mt-4 mb-3 text-muted">&copy; 2017–2021</p>
          </form>
        </main>
      </div>
    </section>
  </div>
</template>

<script>
import ProfileIconMenu from '@/components/Common/ProfileIconMenu'
// import component from "component location"

export default {
  name: 'MyPage',
  data() {
    return {
      isDisabled: true,
      isUpdate: false,

      userSeq: '',
      userName: '',
      userEmail: '',
      userPassword: '',
      userPassword2: '',
      userPhoneNumber: '',
      userAddress: '',
      userClsf: '',
      userClsfName: '',
    }
  },
  components: {
    // components
    ProfileIconMenu,
  },
}
</script>

<style scoped>
.btn-danger {
  color: #fff;
  background-color: #8a262f;
  border-color: #8a262f;
}
.btn-success {
  color: #fff;
  background-color: #3c5b3e;
  border-color: #3c5b3e;
}
.btn-warning {
  color: #fff;
  background-color: #d3a92c;
  border-color: #d3a92c;
}
.btn-warning:hover {
  color: #fff;
  background-color: #ffc451;
  border-color: #ffc451;
}
.btn-success:hover {
  color: #fff;
  background-color: #288b38;
  border-color: #288b38;
}
.btn-danger:hover {
  color: #fff;
  background-color: #d62727;
  border-color: #d62727;
}
</style>
