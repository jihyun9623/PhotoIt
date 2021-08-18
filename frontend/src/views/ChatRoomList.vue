<template>
  <h1>채팅방 목록</h1>
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
