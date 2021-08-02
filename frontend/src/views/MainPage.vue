<template>
  <div>
    <MainPageHeader />
    <MainPageIntroduction />
    <h1>MainPage</h1>
  </div>
</template>

<script>
import MainPageIntroduction from '@/components/MainPage/MainPageIntroduction'
import MainPageHeader from '@/components/MainPage/MainPageHeader'

export default {
  name: 'MainPage',
  components: {
    MainPageIntroduction,
    MainPageHeader,
  },
}
</script>
