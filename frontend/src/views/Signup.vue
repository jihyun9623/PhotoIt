<template>
  <div>
    <div class="container-signup">
      <h1>Signup</h1>
      <p>
        이메일 인증 <br />
        인증 버튼 누르면 인증번호 입력창
      </p>
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
        <div class="col-10">
          <input
            type="email"
            placeholder="user@gmail.com"
            class="form-control-plaintext"
            id="email"
            v-model="credentials.email"
          />
        </div>
        <button
          v-if="emailDup === null || emailDup === '401'"
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
          class="btn-group"
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
          <label class="btn btn-outline-primary" for="btnradio1"
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
          <label class="btn btn-outline-primary" for="btnradio2"
            >작가가 아닙니다.</label
          >
        </div>
      </div>
      <div v-if="credentials.photographer === 'true'">
        <p>지역 목록 선택</p>
        <div
          class="btn-group"
          role="group"
          aria-label="Basic checkbox toggle button group"
        >
          <input
            type="checkbox"
            class="btn-check"
            id="btncheck1"
            autocomplete="off"
            value="checkbox 1"
            v-model="credentials.location"
          />
          <label class="btn btn-outline-primary" for="btncheck1"
            >Checkbox 1</label
          >

          <input
            type="checkbox"
            class="btn-check"
            id="btncheck2"
            autocomplete="off"
            value="checkbox 2"
            v-model="credentials.location"
          />
          <label class="btn btn-outline-primary" for="btncheck2"
            >Checkbox 2</label
          >

          <input
            type="checkbox"
            class="btn-check"
            id="btncheck3"
            autocomplete="off"
            value="checkbox 3"
            v-model="credentials.location"
          />
          <label class="btn btn-outline-primary" for="btncheck3"
            >Checkbox 3</label
          >
        </div>
      </div>
      <hr class="my-hr" />

      <button type="button" class="btn btn-default">회원가입</button>
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
      },
      preview: null,
      profile: null,
      emailDup: null,
      emailSend: null,
      emailAuth: null,
      emailAuthChk: null,
      authedEmail: null,
      passwordConfirmation: null,
      nicknameDup: null,
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
        this.profile = input.files[0]
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
    //   axios({
    //     method: 'post',
    //     url: '',
    //     data: this.credentials,
    //   })
    //     .then(res => {
    //       this.$router.push({ name: 'Login'})
    //     })
    //     .catch(err => {
    //       console.log(err)
    //     })
    // }
  },

  components: {
    // components
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
</style>
