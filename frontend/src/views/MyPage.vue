<template>
  <div>
    <!-- NavBar 위치에 해당하는 헤더 -->
    <header id="header" class="fixed-top">
      <div
        class="d-flex align-items-center justify-content-lg-between ps-3 pt-2"
      >
        <h1 class="me-auto me-lg-0">
          <router-link to="/"
            ><img
              src="../assets/images/Logo.png"
              width="75"
              height="40"
              class="d-inline-block align-text-top"
          /></router-link>
        </h1>
        <ProfileIconMenu />
      </div>
    </header>
    <!-- 메인 컨테이너 -->
    <section class="d-flex align-items-center justify-content-center pt-5 mt-5">
      <div class="container text-center">
        <form>
          <h3 class="h3 mb-5 border">프로필사진</h3>
          <hr />
          <!-- 아이디 -->
          <div class="input-group mb-4 mt-5">
            <span class="input-group-text col-4 justify-content-center"
              >이메일</span
            >
            <input
              type="text"
              class="form-control mb-0"
              aria-label="formEmail"
              aria-describedby="formEmail"
              v-model="formEmail"
              disabled
            />
          </div>
          <!-- 닉네임 -->
          <div class="input-group mb-5">
            <span class="input-group-text col-4 justify-content-center"
              >닉네임</span
            >
            <input
              type="text"
              class="form-control mb-0"
              aria-label="formNickname"
              aria-describedby="formNickname"
              v-model="formNickname"
              disabled
            />
          </div>
          <hr />
          <!-- 비밀번호 -->
          <div class="input-group mb-4 mt-5">
            <span class="input-group-text col-4 justify-content-center"
              >비밀번호</span
            >
            <input
              type="text"
              class="form-control mb-0"
              aria-label="formPasswd"
              aria-describedby="formPasswd"
              v-model="formPasswd"
            />
          </div>
          <!-- 비밀번호 재확인 -->
          <div class="input-group mb-5">
            <span class="input-group-text col-4 justify-content-center"
              >비밀번호 재확인</span
            >
            <input
              type="text"
              class="form-control mb-0"
              aria-label="formPasswdCheck"
              aria-describedby="formPasswdCheck"
              v-model="formPasswdCheck"
            />
          </div>
          <hr />
          <!-- 작가여부-->
          <div class="input-group mb-4 mt-5">
            <span class="input-group-text col-4 justify-content-center"
              >작가여부</span
            >
            <input
              type="text"
              class="form-control mb-0"
              aria-label="formPgCheck"
              aria-describedby="formPgCheck"
              v-model="formPgCheck"
              disabled
            />
            <button
              @click="switchToPg"
              class="btn btn-lg btn-outline btn-primary"
              type="button"
              v-if="!formPgCheck"
            >
              작가로 전환하기
            </button>
          </div>
          <!-- 작가 자기소개 -->
          <div class="input-group mb-4">
            <span class="input-group-text col-4 justify-content-center"
              >작가 자기소개</span
            >
            <input
              type="text"
              class="form-control mb-0"
              aria-label="formIntroduce"
              aria-describedby="formIntroduce"
              v-model="formIntroduce"
            />
          </div>
          <!-- 작가 지역 -->
          <div class="input-group mb-5">
            <span class="input-group-text col-4 justify-content-center"
              >작가 지역</span
            >
            <input
              type="text"
              class="form-control mb-0"
              aria-label="formLocation"
              aria-describedby="formLocation"
              v-model="formLocation"
            />
          </div>
          <hr />
          <div class="d-flex justify-content-end mt-5">
            <button
              @click="deleteUser"
              class="btn btn-lg btn-outline btn-danger"
              type="button"
              v-if="!isUpdate"
            >
              회원 탈퇴
            </button>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <button
              @click="update"
              class="btn btn-lg btn-outline btn-warning"
              type="button"
              v-if="!isUpdate"
            >
              회원 정보 수정
            </button>
          </div>
          <br />
          <p class="mt-4 mb-3 text-muted">&copy; 2017–2021</p>
        </form>
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
