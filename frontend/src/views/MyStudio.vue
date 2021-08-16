<template>
  <MyStudioHeader />
  <MyStudioBest />
  <MyStudioPhotoList />
</template>

<script>
// import component from "component location"
import MyStudioHeader from '@/components/MyStudio/MyStudioHeader.vue'
import MyStudioPhotoList from '@/components/MyStudio/MyStudioPhotoList.vue'
import MyStudioBest from '@/components/MyStudio/MyStudioBest.vue'

export default {
  name: 'MyStudio',
  components: {
    // components
    MyStudioHeader,
    MyStudioPhotoList,
    MyStudioBest,
  },
  data: function () {
    return {}
  },
  created: function () {
    console.log('created start')
    this.$store.state.mystudio.nickname = this.$route.params.nickname
    this.$store.dispatch(
      'mystudio/pgProfile',
      this.$store.state.mystudio.nickname,
    )
    this.$store.dispatch('mystudio/best3', this.$store.state.mystudio.nickname)
    this.$store.dispatch(
      'mystudio/photoAll',
      this.$store.state.mystudio.nickname,
    )
  },
}
</script>
