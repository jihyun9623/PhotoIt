<template>
  <div>
    <SearchRegion />
    <div id="bg" class="empty-box fontCafe"></div>
    <h1 class="mypageTitle">마이페이지</h1>
    <hr
      align="left"
      style="
        border: solid 2px #0000ff;
        width: 77vw;
        margin-left: 8vmax;
        color: #0000ff;
      "
    />
    <!-- 메인 컨테이너 -->
    <section
      class="
        d-flex
        align-items-center
        justify-content-center
        pt-3
        mt-3
        mainContainer
      "
    >
      <div class="container text-center fontCafe">
        <form>
          <h3 class="h3 mt-3 mb-5">
            <!-- 이미지 넣을 위치 -->
            <img
              :src="formProfilePhoto"
              alt="profile"
              width="200"
              height="200"
              class="d-inline-block align-text-top border"
            />
          </h3>
          <hr />
          <!-- 아이디 -->
          <div class="input-group mb-4 mt-4">
            <span class="col-1"></span>
            <span class="col-3 justify-content-center mypageForm fontCafe"
              >이메일</span
            >
            <input
              type="text"
              class="form-control mb-0 mypageForm"
              aria-label="formEmail"
              aria-describedby="formEmail"
              v-model="formEmail"
              disabled
            />
            <span class="col-4"></span>
          </div>
          <!-- 닉네임 -->
          <div class="input-group mb-4">
            <span class="col-1"></span>
            <span class="col-3 justify-content-center mypageForm fontCafe"
              >닉네임</span
            >
            <input
              type="text"
              class="form-control mb-0 col-4 fontCafe"
              aria-label="formNickname"
              aria-describedby="formNickname"
              v-model="formNickname"
            />
            <span class="col-1"></span>
            <button
              @click="checkNickname"
              class="btn btn-lg btn-outline-primary fontCafe col-2"
              style="font-weight: bold"
              type="button"
              autocomplete="off"
            >
              닉네임 중복체크
            </button>
            <span class="col-1"></span>
          </div>
          <!-- 비밀번호 -->
          <div class="input-group mb-4 mt-4">
            <span class="col-1"></span>
            <span class="col-3 justify-content-center mypageForm fontCafe"
              >비밀번호 변경</span
            >
            <input
              type="password"
              class="form-control mb-0 fontCafe"
              aria-label="formPasswd"
              aria-describedby="formPasswd"
              autocomplete="off"
              v-model="formPasswd"
              :class="{
                'is-valid': isUserPasswordFocusAndValid,
                'is-invalid': isUserPasswordFocusAndInvalid,
              }"
              @input="validatePassword"
              @focus="isUserPasswordFocus = true"
            />
            <span class="col-4"></span>
            <div class="pt-1 valid-feedback fontS">
              사용 가능한 비밀번호입니다.
            </div>
            <div class="pt-1 invalid-feedback fontS">
              1개 이상의 대소문자, 특수문자, 숫자를 포함하고 8자리 이상이여야
              합니다.
            </div>
          </div>
          <!-- 비밀번호 재확인 -->
          <div class="input-group mb-4">
            <span class="col-1"></span>
            <span class="col-3 justify-content-center mypageForm fontCafe"
              >비밀번호 변경 확인</span
            >
            <input
              type="password"
              class="form-control mb-0 fontCafe"
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
            <span class="col-4"></span>
          </div>
          <!-- <hr
            class="dotLine"
            style="
              border: none;
              border-top: 2.5px dotted blue;
              color: #fff;
              background-color: #fff;
              height: 1px;
              width: 95%;
              margin-left: 7%;
            "
          /> -->
          <!-- 작가여부-->
          <div class="input-group mb-4 mt-4">
            <span class="col-1"></span>
            <span class="col-3 justify-content-center mypageForm fontCafe"
              >작가 여부</span
            >
            <input
              type="text"
              class="form-control mb-0 fontCafe"
              aria-label="formPgCheck"
              aria-describedby="formPgCheck"
              v-model="PG"
              disabled
            />
            <span class="col-3"></span>
            <button
              @click="upgradeToPhotoGrapher"
              class="btn btn-lg btn-outline-primary fontCafe col-2"
              style="font-weight: bold"
              type="button"
              v-if="!formPgCheck"
            >
              작가로 전환하기
            </button>
            <span class="col-1"></span>
          </div>
          <!-- 작가 자기소개 -->
          <div class="input-group mb-4" v-if="formPgCheck">
            <span class="col-1"></span>
            <span class="col-3 justify-content-center mypageForm fontCafe"
              >작가 한 줄 소개</span
            >
            <input
              type="text"
              class="form-control mb-0"
              aria-label="formIntroduce"
              aria-describedby="formIntroduce"
              v-model="formIntroduce"
            />
            <span class="col-4"></span>
          </div>
          <!-- 작가 지역 -->
          <div class="input-group mb-5" v-if="formPgCheck">
            <span class="col-1"></span>
            <span class="col-3 justify-content-center mypageForm fontCafe"
              >작가 활동 지역</span
            >
            <input
              type="text"
              class="form-control mb-0"
              aria-label="formLocation"
              aria-describedby="formLocation"
              v-model="formLocation"
            />
            <span class="col-4"></span>
          </div>
          <hr />
          <div class="d-flex justify-content-end mt-5">
            <button
              @click="updateUser"
              class="btn btn-lg btn-outline-warning fontCafe"
              style="font-weight: bold"
              type="button"
            >
              회원 정보 수정
            </button>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <button
              @click="deleteUser"
              class="btn btn-lg btn-outline-danger fontCafe"
              style="font-weight: bold"
              type="button"
            >
              회원 탈퇴
            </button>
            <span class="col-2"></span>
          </div>
          <br />
        </form>
      </div>
    </section>
  </div>
</template>

<script>
import SearchRegion from '@/components/Common/SearchRegion'
import http from '@/assets/js/axios.js'

export default {
  name: 'MyPage',
  components: {
    SearchRegion,
  },
  data() {
    return {
      isUserPasswordValid: false,
      isUserPasswordFocus: false,
      isUserPasswordCheckFocus: false,
      formEmail: '',
      formNickname: '',
      formProfilePhoto: '',
      formPasswd: '',
      formPasswdCheck: '',
      formPgCheck: '',
      formIntroduce: '',
      formLocation: '',
      PG: '',
      nicknameOrigin: '',
      isPhotoGrapher: true,
    }
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
  methods: {
    getUserInfo() {
      http.get('/mypage').then((res) => {
        console.log('UserInfoData :')
        console.log(res)
        this.formEmail = res.data.id
        this.formNickname = res.data.nickname
        this.formProfilePhoto = res.data.photo
        this.formPgCheck = res.data.pg
        this.formIntroduce = res.data.introduce
        this.formLocation = res.data.location
        this.nicknameOrigin = res.data.nickname
        // 작가 여부 판별
        if (res.data.pg) this.PG = '작가입니다.'
        else this.PG = '작가가 아닙니다.'
      })
    },
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
        profile: this.formIntroduce,
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
      this.$store
        .dispatch('mypage/nickNameCheck', {
          nickname: this.formNickname,
        })
        .then(() => {
          if (this.$store.state.mypage.returnNickname) {
            this.toastSuccess('사용가능한 닉네임입니다.')
          } else {
            this.toastDanger('이미 존재하는 닉네임입니다.')
          }
        })
    },
    // 프로필 사진 업로드
    uploadProfilePhoto() {
      let data = new FormData()

      // file upload
      let attachFiles = document.querySelector('#inputFileUploadInsert')
      console.log('attachFiles : ')
      console.log(attachFiles)

      if (attachFiles.files.length > 0) {
        data.append('file', attachFiles.files[0])
      } else {
        this.toastDanger('프로필 사진 수정에 실패했습니다.')
        return false
      }
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
  created() {
    this.getUserInfo()
    this.$store.dispatch('login/isLoginCheck')
    if (!this.$store.state.login.isLogin) {
      alert('접근 권한이 없습니다.')
      this.$router.push({ name: 'MainPage' })
    }
  },
  mounted() {
    // 검색바가 보이도록 설정
    this.$store.state.search.isSearchHeaderShow = true

    window.scrollTo(0, 0)
  },
}
</script>
<style scoped>
/* nav 태그의 position이 'fixed' 이기 때문에 공간을 먹어버리는 문제 해결 */
.empty-box {
  height: 15vh;
}
</style>
