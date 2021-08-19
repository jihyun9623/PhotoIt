<template>
  <div>
    <a @click="EnterRoom">
      <img src="@/assets/images/send_message_icon.png" class="icon" />
    </a>
  </div>
</template>

<script>
export default {
  methods: {
    EnterRoom: function () {
      this.$router.push({
        name: 'Chat',
        query: { nickName: this.$store.state.mystudio.nickname },
      })
    },
  },
}
</script>

<style>
.icon {
  width: 45%;
}
.icon:hover {
  cursor: pointer;
}
</style>
