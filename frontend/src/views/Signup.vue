<template>
  <div>
    <div class="container-signup">
      <h1>Signup</h1>
      <input
        style="display: none"
        type="file"
        @change="profilePreview"
        ref="fileInput"
      />
      <img
        v-if="preview"
        @click="$refs.fileInput.click()"
        :src="preview"
        class="profile-preview"
      />
      <img
        v-else
        @click="$refs.fileInput.click()"
        src="@/assets/images/profile_default.png"
        class="profile-preview"
      />

      <br />

      <br />
      <p class="signup-title">아이디 (이메일)</p>
      <div class="row">
        <div class="col-10">
          <input
            type="email"
            placeholder="user@gmail.com"
            class="form-control-plaintext"
            id="email"
            v-model="credentials.id"
          />
        </div>
        <button
          v-if="emailDup === null || emailDup === '401' || emailDup === '100'"
          class="btn btn-primary col-2"
          @click="emailDupCheck"
        >
          중복확인
        </button>
        <button
          v-else-if="emailDup === '200'"
          class="btn btn-primary col-2"
          @click="emailAuthSend"
        >
          인증
        </button>
      </div>
      <p
        v-if="emailDup === '401' && emailAuthChk === null"
        class="warning-text"
      >
        "이미 존재하는 이메일입니다." <br />
      </p>
      <p
        v-else-if="emailDup === '200' && emailAuthChk === null"
        class="warning-text"
      >
        "해당 이메일을 사용할 수 있습니다. 인증 버튼을 눌러주세요."
      </p>
      <p
        v-else-if="emailDup === '100' && emailAuthChk === null"
        class="warning-text"
      >
        "이메일을 입력해주세요"
      </p>
      <div v-if="emailSend === 'true'" class="row">
        <div class="col-10">
          <input
            type="text"
            placeholder="인증코드를 입력해주세요"
            class="form-control-plaintext"
            id="emailAuth"
            v-model="emailAuth.code"
          />
        </div>
        <button class="btn btn-primary col-2" @click="emailAuthCheck">
          확인
        </button>
      </div>
      <!-- <p class="warning-text">
        [인증 일치 문구] null일때는 안뜨고 <br />
        emailAuthChk = 401 일 때는 "인증코드가 일치하지 않습니다."<br />
        emailAuthChk = 200 일 때는 "인증에 성공하셨습니다."
      </p> -->
      <p v-if="emailAuthChk === '401'" class="warning-text">
        "인증코드가 일치하지 않습니다." <br />
      </p>
      <p v-if="emailAuthChk === '200'" class="warning-text">
        "인증에 성공하셨습니다."
      </p>
      <hr class="my-hr" />
      <p class="warning-text">
        - 한 번 만든 이메일 아이디는 변경할 수 없으니, 오타가 없도록 신중히
        확인해주세요. <br />
        - 생성한 이메일로 작가/모델 매칭과 관련한 알림을 받아볼 수 있습니다.
      </p>
      <p class="signup-title">비밀번호</p>
      <div>
        <input
          type="password"
          placeholder="비밀번호 (8 ~ 13자리)"
          class="form-control-plaintext"
          id="password"
          v-model="credentials.passwd"
        />
      </div>

      <div>
        <input
          type="password"
          placeholder="비밀번호 확인"
          class="form-control-plaintext"
          id="passwordConfirmation"
          v-model="passwordConfirmation"
        />
      </div>
      <p
        v-if="
          credentials.passwd === passwordConfirmation &&
          credentials.passwd != null
        "
        class="warning-text"
      >
        비밀번호가 일치합니다.
      </p>
      <p v-else-if="credentials.passwd != null" class="warning-text">
        비밀번호가 일치하지 않습니다.
      </p>
      <hr class="my-hr" />

      <p class="signup-title">닉네임</p>
      <div class="row">
        <div class="col-10">
          <input
            type="nickname"
            placeholder="닉네임을 입력해 주세요."
            class="form-control-plaintext"
            id="nickname"
            v-model="credentials.nickname"
          />
        </div>
        <button class="btn btn-primary col-2" @click="nicknameDupCheck">
          중복확인
        </button>
      </div>
      <p v-if="nicknameDup === '401'" class="warning-text">
        "이미 존재하는 닉네임입니다." <br />
      </p>
      <p v-else-if="nicknameDup === '200'" class="warning-text">
        "해당 닉네임을 사용할 수 있습니다."
      </p>
      <p v-else-if="nicknameDup === '100'" class="warning-text">
        "닉네임을 입력해주세요"
      </p>
      <hr class="my-hr" />

      <p class="signup-title">작가 여부</p>
      <div class="photographer-check">
        <div
          class="btn-group"
          role="group"
          aria-label="Basic radio toggle button group"
        >
          <input
            type="radio"
            v-model="credentials.pg"
            value="true"
            class="btn-check"
            name="btnradio"
            id="btnradio1"
            autocomplete="off"
          />
          <label class="btn btn-outline-primary" for="btnradio1"
            >작가입니다.</label
          >

          <input
            type="radio"
            v-model="credentials.pg"
            value="false"
            class="btn-check"
            name="btnradio"
            id="btnradio2"
            autocomplete="off"
          />
          <label class="btn btn-outline-primary" for="btnradio2"
            >작가가 아닙니다.</label
          >
        </div>
      </div>
      <div v-if="credentials.pg === 'true'">
        <p></p>
        <p class="signup-title">작가 한마디</p>
        <div class="row">
          <div>
            <input
              type="profile"
              placeholder="작가 한마디를 입력해 주세요."
              class="form-control-plaintext"
              id="profile"
              v-model="credentials.profile"
            />
          </div>
        </div>
        <p></p>
        <p class="signup-title">지역 목록 선택</p>
        <div
          class="row"
          role="group"
          aria-label="Basic checkbox toggle button group"
        >
          <div
            class="col-3"
            v-for="location_ele in $store.state.location_all"
            :key="location_ele"
          >
            <input
              type="checkbox"
              class="btn-check"
              :id="location_ele"
              autocomplete="off"
              :value="location_ele"
              v-model="credentials.location"
            />
            <label
              class="btn btn-outline-primary btn-width"
              :for="location_ele"
            >
              {{ location_ele }}
            </label>
          </div>
        </div>
      </div>
      <hr class="my-hr" />

      <!-- Button trigger modal -->
      <button
        type="button"
        class="btn btn-default"
        @click="signup(credentials)"
        data-bs-toggle="modal"
        data-bs-target="#exampleModal"
      >
        회원가입
      </button>

      <!-- Modal -->
      <div
        class="modal fade"
        id="exampleModal"
        tabindex="-1"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog">
          <div class="modal-content" v-if="signupstate === 'fail'">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLabel">회원가입 실패!</h5>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body">
              {{ warningtext }}
            </div>
            <div class="modal-footer">
              <button
                type="button"
                class="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Close
              </button>
            </div>
          </div>
          <div class="modal-content" v-else-if="signupstate === 'success'">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLabel">회원가입 성공!</h5>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body">회원가입에 성공하셨습니다.</div>
            <div class="modal-footer">
              <button
                type="button"
                class="btn btn-secondary"
                data-bs-dismiss="modal"
              >
                Close
              </button>
              <button
                type="button"
                class="btn btn-primary"
                @click="gotoLogin()"
                data-bs-dismiss="modal"
              >
                로그인 페이지로
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import component from "component location"
import httpNoJWT from '@/assets/js/axiosNotJWT.js'

export default {
  name: 'Signup',
  data: function () {
    return {
      credentials: {
        id: null, // email
        location: [], // 지역 정보
        nickname: null, // 닉네임
        passwd: null, // 비밀번호
        pg: '', // 사진작가 여부
        file: null, // 프로필 사진 파일
        profile: '작가 한마디를 입력하지 않았습니다.', // 한줄 자기소개
      },
      preview: null, // 프로필 사진 프리뷰
      emailDup: null, // 이메일 중복확인 요청 후, 중복여부 응답
      emailDupid: null,
      emailSend: null, // 인증 요청갔는지 확인하고 인증 입력창 띄운다.
      emailAuth: {
        id: null,
        code: null, // 유저가 입력한 인증 코드
      },
      emailAuthChk: null, // 인증번호 일치 여부 (null 이면 이메일 인증 아직 안함)
      passwordConfirmation: null, // 비밀번호 확인 입력 내용
      nicknameDup: null, // 닉네임 중복확인 요청 후, 중복 여부 응답
      dupNickname: null, // 중복확인 요청 보냈던 닉네임 따로 저장
      signupstate: null,
      warningtext: null,
    }
  },
  methods: {
    profilePreview: function (event) {
      var input = event.target
      if (input.files) {
        var reader = new FileReader()
        reader.onload = (e) => {
          this.preview = e.target.result
        }
        this.credentials.file = input.files[0]
        reader.readAsDataURL(input.files[0])
      }
    },
    // emailDupCheck : 이메일 중복확인 요청
    emailDupCheck: function () {
      httpNoJWT
        .post('/user/emaildup', this.credentials)
        .then((res) => {
          console.log(res)
          if (this.credentials.id == null) {
            this.emailDup = '100'
          } else {
            if (res.data.statusCode == 200) {
              this.emailDup = '200'
              this.emailDupid = this.credentials.id
            } else if (res.data.statusCode == 401) {
              this.emailDup = '401'
            }
          }
        })
        .catch((err) => {
          console.log(err)
          this.emailDup = '401'
        })
    },
    //
    // emailAuthSend : 인증 버튼 클릭 시, 서버에 코드를 인증메일로 보내줄 것을 요청
    emailAuthSend: function () {
      this.emailSend = 'true' // 전송 보내지면 인증 입력창 띄워야 함
      httpNoJWT
        .post('/user/emailauth', { id: this.credentials.id })
        .then((res) => {
          console.log(res)
        })
        .catch((err) => {
          console.log(err)
        })
    },
    //
    // // emailAuthCheck : 입력한 코드 확인 요청
    // emailAuthCheck: function () {
    //   this.emailAuth.id = this.credentials.id
    //   // 인증 때 이메일도 함께 보내야하고, 나중에 인증한 메일과 회원가입창 메일이 같은지 확인해야 한다.
    //   axios({
    //     method: 'post',
    //     url: '',
    //     data: this.emailAuth, // 유저가 입력한 인증코드를 보낸다.
    //   })
    //     .then(res => {
    //       console.log(res)
    //       this.emailAuthChk = true
    //       this.emailAuth.id = this.credentials.id
    // 이부분 제대로 동작하나 확인할 것.
    // 인증을 완료한 메일과 입력창에 입력해둔 메일이 같아야 회원가입이 가능하도록
    //     })
    //     .catch(err => {
    //       console.log(err)
    //       this.emailAuthChk = false
    //     })
    // },
    //
    nicknameDupCheck: function () {
      httpNoJWT
        .post('/user/nicknameCheck', this.credentials)
        .then((res) => {
          console.log(res)
          if (this.credentials.nickname == null) {
            this.nicknameDup = '100'
          } else {
            if (res.data.statusCode == 200) {
              this.nicknameDup = '200'
              this.dupNickname = this.credentials.nickname
            } else if (res.data.statusCode == 401) {
              this.nicknameDup = '401'
            }
          }
        })
        .catch((err) => {
          console.log(err)
          this.nicknameDup = '401'
        })
    },
    signup: function () {
      // this.emailAuthChk === 'true' && // 이메일 인증 통과했다면
      // this.emailAuth.id === this.credentials.id && // 인증된 이메일과 입력된 이메일이 같다면
      if (
        this.emailDupid === this.credentials.id && // 중복체크한 이메일과 입력된 이메일이 같다면
        this.credentials.passwd === this.passwordConfirmation && // 비밀번호 확인이 같다면
        this.dupNickname === this.credentials.nickname // 중복체크한 닉네임과 입력된 닉네임이 같다면
      ) {
        this.signupstate = 'success'
        httpNoJWT
          .post('/user/signup', this.credentials)
          .then((res) => {
            console.log(res)
          })
          .catch((err) => {
            console.log(err)
          })
      } else {
        this.signupstate = 'fail'
        if (!(this.emailDupid === this.credentials.id)) {
          this.warningtext =
            '중복체크한 이메일과 입력된 이메일이 다릅니다. 다시 확인해주세요.'
        } else if (!(this.credentials.passwd === this.passwordConfirmation)) {
          this.warningtext = '비밀번호가 다릅니다.'
        } else if (!(this.dupNickname === this.credentials.nickname)) {
          this.warningtext =
            '중복체크한 닉네임과 입력된 닉네임이 다릅니다. 다시 확인해주세요.'
        }
        console.log(this.warningtext)
      }
    },
    gotoLogin: function () {
      this.$router.push({ name: 'Login' })
    },
  },
  components: {
    // components
  },
  mounted() {
    window.scrollTo(0, 0)
  },
}
</script>

<style scoped>
.container-signup {
  max-width: 600px;
  margin: auto;
  padding: 20px;
}

.profile-preview {
  width: 200px;
  height: 200px;
  object-fit: cover;
  cursor: pointer;
  clip-path: circle(95px at center);
}
.signup-title {
  text-align: left;
  font-size: 17px;
  font-weight: bold;
}
.warning-text {
  text-align: left;
  font-size: 12px;
}
.form-control-plaintext {
  font-size: 15px;
  font-weight: bold;
  border: 0px;
  margin-bottom: 5px;
  border-width: 1px 0;
  padding-right: 0;
  padding-left: 0;
  background-color: #f7f7f7;
  text-indent: 2%;
}
.form-control-plaintext:hover {
  font-size: 15px;
  font-weight: bold;
  border: 0px;
  margin-bottom: 5px;
  border-width: 1px 0;
  padding-right: 0;
  padding-left: 0;
  background-color: #e7e7e7;
}
.my-hr {
  height: 2px;
  margin: 5px 0px;
}
.btn {
  font-weight: bold;
}
.btn-default {
  color: #595959;
  background-color: #f0f0f0;
  width: 100%;
}
.btn-default:hover {
  color: #fff;
  background-color: #c4c4c4;
}
.btn-default:active {
  color: #fff;
  background-color: #0095f6;
}
.photographer-container {
  padding: 0 5px;
}
.photographer-check {
  padding: 0 5px;
}
.btn-dropdown {
  color: #595959;
  background-color: #f0f0f0;
  width: 100%;
}
.btn-group {
  width: 100%;
}
.btn-width {
  width: 100%;
}
</style>
