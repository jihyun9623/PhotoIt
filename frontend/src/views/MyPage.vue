<template>
  <div>
    <!-- NavBar 위치에 해당하는 헤더 -->
    <header id="header" class="fixed-top">
      <div
        class="d-flex align-items-center justify-content-lg-between ps-3 pt-2"
      >
        <h1 class="me-auto me-lg-0 p-3">
          <router-link to="/"
            ><img
              src="../assets/images/Logo.png"
              width="75"
              height="45"
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
          <h3 class="h3 mb-3">
            <!-- 이미지 넣을 위치 -->
            <img
              :src="formProfilePhoto"
              width="200"
              height="200"
              class="d-inline-block align-text-top border"
            />
          </h3>
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
            />
            <button
              @click="checkNickname"
              class="btn btn-lg btn-outline btn-primary"
              type="button"
            >
              닉네임 중복체크
            </button>
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
              :class="{
                'is-valid': isUserPasswordFocusAndValid,
                'is-invalid': isUserPasswordFocusAndInvalid,
              }"
              @input="validatePassword"
              @focus="isUserPasswordFocus = true"
            />
            <div class="valid-feedback">사용 가능한 비밀번호입니다.</div>
            <div class="invalid-feedback">
              1개 이상의 대소문자, 특수문자, 숫자를 포함하고 8자리 이상이여야
              합니다.
            </div>
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
              :class="{
                'is-valid': isSamePasswordValid,
                'is-invalid': isSamePasswordInvalid,
              }"
              @input="checkSamePassword"
              @focus="isUserPasswordCheckFocus = true"
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
              v-model="PG"
              disabled
            />
            <button
              @click="upgradeToPhotoGrapher"
              class="btn btn-lg btn-outline btn-primary"
              type="button"
              v-if="!formPgCheck"
            >
              작가로 전환하기
            </button>
          </div>
          <!-- 작가 자기소개 -->
          <div class="input-group mb-4" v-if="formPgCheck">
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
          <div class="input-group mb-5" v-if="formPgCheck">
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
            >
              회원 탈퇴
            </button>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <button
              @click="updateUser"
              class="btn btn-lg btn-outline btn-warning"
              type="button"
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
  created() {
    this.$store.dispatch('mypage/getUserInfo')
  },
  mounted() {
    if (this.$store.state.mypage.isPhotoGrapher) this.PG = '작가입니다.'
    else this.PG = '작가가 아닙니다.'
  },
  data() {
    return {
      isUserPasswordValid: false,

      isUserPasswordFocus: false,
      isUserPasswordCheckFocus: false,

      formEmail: this.$store.state.mypage.email,
      formNickname: this.$store.state.mypage.nickName,
      formProfilePhoto: this.$store.state.mypage.profilePhoto,
      formPasswd: '',
      formPasswdCheck: '',
      formPgCheck: this.$store.state.mypage.isPhotoGrapher,
      formIntroduce: this.$store.state.mypage.introduce,
      formLocation: this.$store.state.mypage.location,

      PG: '',
      nicknameOrigin: this.$store.state.mypage.nickName,
    }
  },
  methods: {
    // 회원정보 수정
    updateUser() {
      if (this.nicknameOrigin != this.formNickname) {
        if (!this.$store.state.mypage.returnNickname) {
          this.toastDanger('닉네임 중복검사를 해주세요.')
          return false
        }
      } else if (!(this.formPasswd == '')) {
        if (!this.isUserPasswordFocusAndValid || !this.isSamePasswordValid) {
          this.toastDanger(
            '비밀번호는 영문,숫자,특수문자를 포함하여 동일하게 입력해주세요.',
          )
          return false
        }
      }
      let data = {
        passwd: this.formPasswd,
        nickname: this.formNickname,
        pg: this.formPgCheck,
        location: this.formLocation,
        introduce: this.formIntroduce,
      }
      this.$store.dispatch('mypage/setUserInfo', data).then(() => {
        if (this.$store.state.mypage.return) {
          this.toastSuccess('회원정보가 수정되었습니다.')
          this.$router.push({ name: 'MyPage' })
        } else {
          this.toastDanger('회원정보를 수정하지 못하였습니다.')
        }
      })
    },
    // 회원정보 삭제
    deleteUser() {
      this.$store.dispatch('mypage/deleteUser').then(() => {
        if (this.$store.state.mypage.return) {
          this.toastSuccess('회원탈퇴가 완료되었습니다.')
          this.$router.push({ name: 'MainPage' })
        } else {
          this.toastDanger('회원탈퇴를 실패하였습니다.')
        }
      })
    },
    // 닉네임 중복 확인
    checkNickname() {
      if (this.nicknameOrigin == this.formNickname) {
        this.toastSuccess('닉네임이 변경되지 않았어요.')
        return false
      }
      this.$store.dispatch('mypage/nickNameCheck').then(() => {
        if (this.$store.state.mypage.returnNickname) {
          this.toastSuccess('사용가능한 닉네임입니다.')
        } else {
          this.toastDanger('이미 존재하는 닉네임입니다.')
        }
      })
    },
    // 프로필 사진 업로드
    uploadProfilePhoto() {
      let data = { file: this.formProfilePhoto }
      this.$store.dispatch('mypage/uploadProfilePhoto', data).then(() => {
        if (this.$store.state.mypage.return) {
          this.toastSuccess('프로필 사진이 수정되었습니다.')
        } else {
          this.toastDanger('프로필 사진 수정에 실패했습니다.')
        }
      })
    },
    // 작가로 권한 상승 요청
    upgradeToPhotoGrapher() {
      this.$store.dispatch('mypage/upgradeToPg').then(() => {
        if (this.$store.state.mypage.isPhotoGrapher) {
          this.toastSuccess('지역과 한줄소개를 작성 후 수정버튼을 눌러주세요.')
          this.formPgCheck = true
          this.PG = '작가입니다.'
        } else {
          this.toastDanger('작가로 전환에 실패하였습니다.')
        }
      })
    },
    // 패스워드 양식 확인
    validatePassword() {
      let patternEngAtLeastOne = new RegExp(/[a-zA-Z]+/) // + for at least one
      let patternSpeAtLeastOne = new RegExp(/[~!@#$%^&*()_+|<>?:{}]+/) // + for at least one
      let patternNumAtLeastOne = new RegExp(/[0-9]+/) // + for at least one

      this.isUserPasswordValid =
        patternEngAtLeastOne.test(this.formPasswd) &&
        patternSpeAtLeastOne.test(this.formPasswd) &&
        patternNumAtLeastOne.test(this.formPasswd) &&
        this.formPasswd.length >= 8
          ? true
          : false
    },
    toastSuccess(text) {
      this.$moshaToast(text, {
        type: 'success',
        position: 'bottom-right',
        timeout: 3000,
        showIcon: true,
      })
    },
    toastDanger(text) {
      this.$moshaToast(text, {
        type: 'danger',
        position: 'bottom-right',
        timeout: 3000,
        showIcon: true,
      })
    },
  },
  computed: {
    // 패스워드 양식 확인 및 표시용
    isUserPasswordFocusAndInvalid() {
      return this.isUserPasswordFocus && !this.isUserPasswordValid
    },
    isUserPasswordFocusAndValid() {
      return this.isUserPasswordFocus && this.isUserPasswordValid
    },
    // 패스워드 동일 확인 및 표시용
    isSamePasswordValid() {
      return (
        this.isUserPasswordCheckFocus && this.formPasswd == this.formPasswdCheck
      )
    },
    isSamePasswordInvalid() {
      return (
        this.isUserPasswordCheckFocus && this.formPasswd != this.formPasswdCheck
      )
    },
  },
  components: {
    ProfileIconMenu,
  },
}
</script>
