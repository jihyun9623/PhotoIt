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
  border: solid 2px;
  margin-left: 20vw;
  margin-right: 20vw;
}

.chatListItem {
  background-color: rgb(228, 224, 224);
  border-radius: 30px;
  padding-top: 2vh;
  padding-bottom: 2vh;
}
</style>
