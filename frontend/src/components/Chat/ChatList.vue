<template>
  <div>
    <ul v-for="n in nicks" :key="n">
      <button class="btn btn-primary" type="button" @click="EnterRoom">
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
    http.post('/chat/rooms', data).then((res) => {
      this.nicks = res.data.nickNames
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
