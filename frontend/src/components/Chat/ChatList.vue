<template>
  <div class="chatListBorder">
    <ul class="chatListItem" v-for="n in nicks" :key="n">
      <button class="btn fontL" type="button" @click="EnterRoom">
        {{ n }}
      </button>
    </ul>
  </div>
</template>

<script>
import http from '@/assets/js/axios.js'

export default {
  name: 'ChatList',
  data() {
    return {
      nicks: null,
    }
  },
  created() {
    let data = {
      sender: localStorage.getItem('nickname'),
    }
    http
      .post('/chat/rooms', data)
      .then((res) => {
        this.nicks = res.data.nickNames
      })
      .catch((err) => {
        console.log(err)
      })
  },
  methods: {
    EnterRoom: function (n) {
      this.$router.push({
        name: 'Chat',
        query: { nickName: n.target.innerText },
      })
    },
  },
}
</script>

<style>
.chatListBorder {
  margin-left: 20vw;
  margin-right: 20vw;
  padding-top: 2vh;
  display: flex;
  justify-content: center;
}

.chatListItem {
  background-color: rgb(238, 245, 252);
  box-shadow: 5px 10px 8px rgb(81, 125, 155);
  border-radius: 30px;
  padding-top: 1vh;
  padding-bottom: 1vh;
  width: 30vw;
}
</style>
