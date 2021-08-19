<template>
  <div class="col-6 col-md-3 d-flex justify-content-center mt-5">
    <router-link
      :to="{ path: `/mystudio/${profile.nickName}` }"
      class="pg-name"
    >
      <div class="card">
        <img class="img-fluid card-img" alt="Profile" :src="profile.photo" />
        <div class="card-body">
          <p class="card-text fs-6 fw-bold text-center pt-3">
            {{ profile.nickName }}
          </p>
        </div>
      </div>
    </router-link>
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
}
</script>

<style scoped>
.card-img {
  width: 20vh;
  height: 20vh;
  object-fit: cover;
  border-radius: 50%;
}
.card-img:hover {
  cursor: pointer;
  transform: scale(1.1);
  opacity: 0.7;
  overflow: hidden;
}
.card {
  border-style: none;
}
.pg-name {
  text-decoration: none;
  color: black;
}
</style>
