<template>
  <div>
<<<<<<< HEAD
    <h1 class="fontXL signupTitle">회원 가입</h1>
    <hr
      align="left"
      style="
        border: solid 2px #1f3a93;
        width: 77vw;
        margin-left: 10vmax;
        opacity: 0.7;
      "
    />
    <div class="container-signup fontCafe">
=======
    <div class="container-signup">
      <h1>Signup</h1>
      <p>
        추가할 일 <br />
        API 넘어오면 하나씩 확인하면서 수정작업
      </p>
>>>>>>> b265e36ab2382e6685df9df1c57de22ab86a1626
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
        src="@/assets/profile_default.png"
        class="profile-preview"
      />

      <br />

      <br />
      <p class="signup-title">아이디 (이메일)</p>
      <div class="row">
        <div class="col-10 fontCafe">
          <input
            type="email"
            placeholder="user@gmail.com"
            class="form-control-plaintext"
            id="email"
            v-model="credentials.email"
          />
        </div>
        <button
<<<<<<< HEAD
          v-if="emailDup === null || emailDup === '401' || emailDup === '100'"
          class="btn btn-outline-primary col-2 btnPrimary"
=======
          v-if="emailDup === null || emailDup === '401'"
          class="btn btn-primary col-2"
>>>>>>> b265e36ab2382e6685df9df1c57de22ab86a1626
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
      <p class="warning-text">
        [중복체크 확인 문구 구역] null일때는 안뜨고 <br />
        emailDup = 401 일때는 "이미 존재하는 이메일입니다." <br />
        emailDup = 200 일 때는 "해당 이메일을 사용할 수 있습니다."
      </p>
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
      <div v-if="emailSend === 'true'" class="row">
        <div class="col-10">
          <input
            type="text"
            placeholder="인증코드를 입력해주세요"
            class="form-control-plaintext"
            id="emailAuth"
            v-model="emailAuth"
          />
        </div>
        <button class="btn btn-primary col-2" @click="emailAuthCheck">
          확인
        </button>
      </div>
      <p class="warning-text">
        [인증 일치 문구] null일때는 안뜨고 <br />
        emailAuthChk = 401 일 때는 "인증코드가 일치하지 않습니다."<br />
        emailAuthChk = 200 일 때는 "인증에 성공하셨습니다."
      </p>
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
          v-model="credentials.password"
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
        v-if="credentials.password === passwordConfirmation"
        class="warning-text"
      >
        비밀번호가 일치합니다.
      </p>
      <p v-else class="warning-text">비밀번호가 일치하지 않습니다.</p>
      <hr class="my-hr" />

      <p class="signup-title">닉네임</p>
      <div class="row">
        <div class="col-10 fontCafe">
          <input
            type="nickname"
            placeholder="닉네임을 입력해 주세요."
            class="form-control-plaintext"
            id="nickname"
            v-model="credentials.nickname"
          />
        </div>
        <button
          class="btn btn-outline-primary col-2 btnPrimary"
          @click="nicknameDupCheck"
        >
          중복확인
        </button>
      </div>
      <p class="warning-text">
        [중복체크 확인 문구 구역] null일때는 안뜨고 <br />
        nicknameDup = 401 일때는 "이미 존재하는 닉네임입니다." <br />
        nicknameDup = 200 일 때는 "해당 닉네임을 사용할 수 있습니다."
      </p>
      <p v-if="nicknameDup === '401'" class="warning-text">
        "이미 존재하는 닉네임입니다." <br />
      </p>
      <p v-else-if="nicknameDup === '200'" class="warning-text">
        "해당 닉네임을 사용할 수 있습니다."
      </p>
      <hr class="my-hr" />

      <p class="signup-title">작가 여부</p>
      <div class="photographer-check">
        <div
          class="btn-group fontCafe"
          role="group"
          aria-label="Basic radio toggle button group"
        >
          <input
            type="radio"
            v-model="credentials.photographer"
            value="true"
            class="btn-check"
            name="btnradio"
            id="btnradio1"
            autocomplete="off"
          />
          <label class="btn btn-outline-primary btnPrimary" for="btnradio1"
            >작가입니다.</label
          >

          <input
            type="radio"
            v-model="credentials.photographer"
            value="false"
            class="btn-check"
            name="btnradio"
            id="btnradio2"
            autocomplete="off"
          />
          <label class="btn btn-outline-primary btnPrimary" for="btnradio2"
            >작가가 아닙니다.</label
          >
        </div>
      </div>
<<<<<<< HEAD
      <div v-if="credentials.pg === 'true'">
        <p></p>
        <p class="signup-title fontCafe">작가 한마디</p>
        <div class="row">
          <div>
            <input
              type="profile"
              placeholder="작가 한마디를 입력해 주세요."
              class="form-control-plaintext fontCafe"
              id="profile"
              v-model="credentials.profile"
            />
          </div>
        </div>
        <p></p>
        <p class="signup-title fontCafe">지역 목록 선택</p>
=======
      <div v-if="credentials.photographer === 'true'">
        <p>지역 목록 선택</p>
>>>>>>> b265e36ab2382e6685df9df1c57de22ab86a1626
        <div
          class="row"
          role="group"
          aria-label="Basic checkbox toggle button group"
        >
          <div
<<<<<<< HEAD
            class="col-3 fontCafe"
            v-for="location_ele in $store.state.location_all"
            :key="location_ele"
=======
            class="col-3"
            v-for="location_ele in location_all"
            :key="location_ele.loca"
>>>>>>> b265e36ab2382e6685df9df1c57de22ab86a1626
          >
            <input
              type="checkbox"
              class="btn-check"
              :id="location_ele.loca"
              autocomplete="off"
              :value="location_ele.loca"
              v-model="credentials.location"
            />
            <label
              class="btn btn-outline-primary btn-width"
              :for="location_ele.loca"
            >
              {{ location_ele.loca }}
            </label>
          </div>
        </div>
      </div>
      <hr class="my-hr" />

<<<<<<< HEAD
      <!-- Button trigger modal -->
      <button
        type="button"
        class="btn btnOutline"
        style="width: 100%"
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
                class="btn btn-primary btnOutline"
                @click="gotoLogin()"
                data-bs-dismiss="modal"
              >
                로그인 페이지로
              </button>
            </div>
          </div>
        </div>
      </div>
=======
      <button type="button" class="btn btn-default">회원가입</button>
>>>>>>> b265e36ab2382e6685df9df1c57de22ab86a1626
    </div>
  </div>
</template>

<script>
// import component from "component location"
// import axios from 'axios'

export default {
  name: 'Signup',
  data: function () {
    return {
      credentials: {
        email: null,
        password: null,
        nickname: null,
        photographer: '',
        location: [],
        introduce: null,
        profile: null,
      },
      preview: null,
      emailDup: null,
      emailSend: null,
      emailAuth: null,
      emailAuthChk: null,
      authedEmail: null,
      passwordConfirmation: null,
      nicknameDup: null,
      dupNickname: null,
      location_all: [
        { loca: '서울' },
        { loca: '부산' },
        { loca: '경기도' },
        { loca: '강원도' },
        { loca: '경상남도' },
        { loca: '경상북도' },
        { loca: '전라남도' },
        { loca: '전라북도' },
        { loca: '충청남도' },
        { loca: '충청북도' },
      ],
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
        this.credentials.profile = input.files[0]
        reader.readAsDataURL(input.files[0])
      }
    },
    // // emailDupCheck : 이메일 중복확인 요청
    // emailDupCheck: function () {
    //   axios({
    //     method: 'post',
    //     url: '',
    //     data: this.credentials.email,
    //   })
    //     .then(res => {
    //       console.log(res)
    //       this.emailDup = '200'
    //     })
    //     .catch(res => {
    //       console.log(err)
    //       this.emailDup = '401'
    //     })
    // },
    //
<<<<<<< HEAD
    // emailAuthSend : 인증 버튼 클릭 시, 서버에 코드를 인증메일로 보내줄 것을 요청
    emailAuthSend: function () {
      httpNoJWT
        .post('/user/emailauth', { id: this.credentials.id })
        .then((res) => {
          this.emailSend = 'true' // 전송 보내지면 인증 입력창 띄워야 함
          console.log(res)
        })
        .catch((err) => {
          console.log(err)
        })
    },
=======
    // // emailAuthSend : 서버에 코드를 인증메일로 보내줄 것을 요청
    // emailAuthSend: function () {
    //   this.emailSend = 'true'
    //   axios({
    //     method: 'post',
    //     url: '',
    //     data: this.credentials.email,
    //   })
    //     .then(res => {
    //       console.log(res)
    //     })
    //     .catch(res => {
    //       console.log(err)
    //     })
    // },
>>>>>>> b265e36ab2382e6685df9df1c57de22ab86a1626
    //
    // // emailAuthCheck : 입력한 코드 확인 요청
    // emailAuthCheck: function () {
    //   axios({
    //     method: 'post',
    //     url: '',
    //     data: this.emailAuth,
    //   })
    //     .then(res => {
    //       console.log(res)
    //       this.emailAuthChk = true
    //       this.authedEmail = this.credentials.email
    // 이부분 제대로 동작하나 확인할 것.
    // 인증을 완료한 메일과 입력창에 입력해둔 메일이 같아야 회원가입이 가능하도록
    //     })
    //     .catch(err => {
    //       console.log(err)
    //       this.emailAuthChk = false
    //     })
    // },
    //
    // nicknameDupCheck: function () {
    //   axios({
    //     method: 'post',
    //     url: '',
    //     data: this.credentials.nickname,
    //   })
    //     .then(res => {
    //       this.nicknameDup = '200'
    //     })
    //     .catch(res => {
    //       this.nicknameDup = '401'
    //     })
    // },
    //
    // signup: function () {
    //   if (
    //     this.emailAuthChk === 'true' &&
    //     this.authedEmail === this.credentials.email &&
    //     this.credentials.password === this.passwordConfirmation &&
    //     this.dupNickname === this.credentials.nickname
    //   ) {
    //     axios({
    //       method: 'post',
    //       url: '',
    //       data: this.credentials,
    //     })
    //       .then(res => {
    //         this.$router.push({ name: 'Login'})
    //       })
    //       .catch(err => {
    //         console.log(err)
    //       })
    //   } else {
    //     if (!(this.authedEmail === this.credentials.email)) {
    //       this.warningtext = '인증한 이메일과 입력된 이메일이 다릅니다. 다시 확인해주세요.'
    //     }
    //   }
    // },
  },

  components: {
    // components
  },
<<<<<<< HEAD
  created() {
    this.$store.dispatch('login/isLoginCheck')
    if (this.$store.state.login.isLogin) {
      this.$router.push({ name: 'MainPage' })
    }
  },
  mounted() {
    window.scrollTo(0, 0)
  },
=======
>>>>>>> b265e36ab2382e6685df9df1c57de22ab86a1626
}
</script>

<style scoped>
.container-signup {
  max-width: 600px;
  margin: auto;
  padding: 50px 20px 20px 20px;
  /* padding: 20px; */
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
}
.my-hr {
  height: 2px;
  margin: 5px 0px;
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
