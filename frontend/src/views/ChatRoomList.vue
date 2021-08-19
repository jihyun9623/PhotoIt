<template>
  <h1 class="fontXL signupTitle">쪽지함 목록</h1>
  <hr
    align="left"
    style="
      border: solid 2px #1f3a93;
      width: 77vw;
      margin-left: 10vmax;
      opacity: 0.7;
    "
  />
  <ChatList />
</template>

<script>
import ChatList from '@/components/Chat/ChatList'
export default {
  name: 'ChatRoomList',
  components: {
    ChatList,
  },
}
</script>
