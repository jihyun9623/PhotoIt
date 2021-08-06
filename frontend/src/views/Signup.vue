<template>
  <div>
    <div class="container-signup">
      <h1>Signup</h1>
      <p>
        추가할 일 <br />
        API 넘어오면 하나씩 확인하면서 수정작업
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
            v-model="emailAuth.code"
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
        v-if="credentials.passwd === passwordConfirmation"
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
        <p>지역 목록 선택</p>
        <div
          class="row"
          role="group"
          aria-label="Basic checkbox toggle button group"
        >
          <div
            class="col-3"
            v-for="location_ele in location_all"
            :key="location_ele.loca"
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

      <button
        type="button"
        class="btn btn-default"
        @click="signup(credentials)"
      >
        회원가입
      </button>
    </div>
  </div>
</template>

<script>
// import component from "component location"
import axios from 'axios'

export default {
  name: 'Signup',
  data: function () {
    return {
      credentials: {
        id: null, // email
        passwd: null, // 비밀번호
        nickname: null, // 닉네임
        pg: '', // 사진작가 여부
        location: [], // 지역 정보
        introduce: null, // 한줄 자기소개
        file: null, // 프로필 사진 파일
      },
      preview: null, // 프로필 사진 프리뷰
      emailDup: null, // 이메일 중복확인 요청 후, 중복여부 응답
      emailSend: null, // 인증 요청갔는지 확인하고 인증 입력창 띄운다.
      emailAuth: {
        id: null,
        code: null, // 유저가 입력한 인증 코드
      },
      emailAuthChk: null, // 인증번호 일치 여부 (null 이면 이메일 인증 아직 안함)
      passwordConfirmation: null, // 비밀번호 확인 입력 내용
      nicknameDup: null, // 닉네임 중복확인 요청 후, 중복 여부 응답
      dupNickname: null, // 중복확인 요청 보냈던 닉네임 따로 저장
      // 이거 location은 App.vue에서 지역목록요청으로 받고 vuex state에 저장할 것 (임시 설정 목록임)
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
        this.credentials.file = input.files[0]
        reader.readAsDataURL(input.files[0])
      }
    },
    // // emailDupCheck : 이메일 중복확인 요청
    // emailDupCheck: function () {
    //   axios({
    //     method: 'post',
    //     url: '',
    //     data: this.credentials.id,
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
    // emailAuthSend : 인증 버튼 클릭 시, 서버에 코드를 인증메일로 보내줄 것을 요청
    // emailAuthSend: function () {
    //   this.emailSend = 'true' // 전송 보내지면 인증 입력창 띄워야 함
    //   axios({
    //     method: 'post',
    //     url: 'localhost:8080/user/emailauth',
    //     data: this.credentials.id,
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
    // nicknameDupCheck: function () {
    //   axios({
    //     method: 'post',
    //     url: '',
    //     data: this.credentials.nickname,
    //   })
    //     .then(res => {
    //       this.nicknameDup = '200'
    //       this.dupNickname = this.credentials.nickname
    //     })
    //     .catch(res => {
    //       this.nicknameDup = '401'
    //     })
    // },
    //
    signup: function () {
      axios({
        method: 'post',
        url: 'http://i5a108.p.ssafy.io:8080/user/signup',
        data: this.credentials,
      })
        .then((res) => {
          console.log(res)
          // this.$router.push({ name: 'Login'})
        })
        .catch((err) => {
          console.log(err)
        })
      // if (
      //   this.emailAuthChk === 'true' &&
      //   this.emailAuth.id === this.credentials.id &&
      //   this.credentials.passwd === this.passwordConfirmation &&
      //   this.dupNickname === this.credentials.nickname
      // ) {
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
      // } else {
      //   if (!(this.emailAuth.id === this.credentials.id)) {
      //     this.warningtext = '인증한 이메일과 입력된 이메일이 다릅니다. 다시 확인해주세요.'
      //   }
      // }
    },
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
.btn-width {
  width: 100%;
}
</style>
