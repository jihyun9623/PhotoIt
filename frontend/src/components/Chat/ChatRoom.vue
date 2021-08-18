<template>
  <div class="container">
    <div class="row">
      <div class="border border-primary col-md-4">
        {{ this.$route.query.nickName }}
      </div>
      <div class="border border-primary col-md-4 offset-md-4">
        {{ nickname }}
      </div>
    </div>
    <div v-if="messages">
      <div>
        <div v-for="m in messages" :key="m.sender">
          <div
            class="border border-primary col-md-6 offset-md-6"
            v-if="nickname === m.sender"
          >
            <!--  자기자신 오른쪽  -->
            {{ m.time[0] }}년 {{ m.time[1] }}월 {{ m.time[2] }}일
            {{ m.time[3] }}시 {{ m.time[4] }}분 <br />
            {{ m.message }}
          </div>
          <div class="border border-primary col-md-6" v-else>
            {{ m.time[0] }}년 {{ m.time[1] }}월 {{ m.time[2] }}일
            {{ m.time[3] }}시 {{ m.time[4] }}분 <br />
            {{ m.message }}
          </div>
        </div>
      </div>
    </div>
    <input
      type="text"
      class="form-control"
      v-model="message"
      v-on:keypress.enter="sendMessage"
    />
    <div class="input-group-append">
      <button class="btn btn-primary" type="button" @click="sendMessage">
        보내기
      </button>
    </div>
  </div>
</template>
<script>
import http from '@/assets/js/axios.js'

export default {
  name: 'ChatRoom',
  components: {},
  data() {
    return {
      messages: '',
      roomName: '',
      get nickname() {
        return localStorage.getItem('nickname')
      },
    }
  },
  created() {
    let data = {
      user1: localStorage.getItem('nickname'),
      user2: this.$route.query.nickName,
    }
    http.post('/chat/room', data).then((res) => {
      this.messages = res.data.chatLists
      this.roomName = res.data.roomName
    })
  },
  methods: {
    sendMessage: function () {
      console.log('??')
      let data = {
        roomName: this.roomName,
        sender: localStorage.getItem('nickname'),
        message: this.message,
      }
      var today = new Date()
      var year = today.getFullYear()
      var month = ('0' + (today.getMonth() + 1)).slice(-2)
      var day = ('0' + today.getDate()).slice(-2)
      var hours = ('0' + today.getHours()).slice(-2)
      var minutes = ('0' + today.getMinutes()).slice(-2)
      let mes = {
        sender: localStorage.getItem('nickname'),
        message: this.message,
        time: [year * 1, month * 1, day * 1, hours * 1, minutes * 1],
      }
      this.messages.push(mes)
      console.log(this.messages)
      http.post('/chat/room/chatting', data).then(() => {})
      this.message = ''
    },
  },
}
</script>

<style></style>
