<template>
  <h1 class="mt-5 basicFont" style="z-index: 2">채팅방 목록</h1>
  <hr />
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
