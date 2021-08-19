<template>
  <h1 class="fontXL signupTitle">쪽지함</h1>
  <hr
    align="left"
    style="
      border: solid 2px #1f3a93;
      width: 77vw;
      margin-left: 10vmax;
      opacity: 0.7;
    "
  />
  <ChatRoom />
</template>

<script>
// import component from "component location"
import ChatRoom from '@/components/Chat/ChatRoom'

export default {
  name: 'Chat',
  components: {
    ChatRoom,
  },
}
</script>
