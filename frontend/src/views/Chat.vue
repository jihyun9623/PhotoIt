<template>
  <h1>Chat</h1>
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
