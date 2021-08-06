<template>
  <div class="card">
    <img class="img-fluid rounded-circle" alt="Profile" :src="thumb" />
    <div class="card-body">
      <p class="card-text fs-6 fw-bold text-center">{{ nickName }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SearchResultPhotographerItem',
  props: {
    profile: {
      type: Object,
    },
  },
  data() {
    return {
      thumb: this.profile.thumb,
      nickName: this.profile.nickName,
    }
  },
}
</script>

<style></style>
