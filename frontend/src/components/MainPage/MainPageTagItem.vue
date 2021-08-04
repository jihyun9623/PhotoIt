<template>
  <div class="card">
    <img
      class="img-fluid"
      alt="100%x280"
      src="https://images.unsplash.com/photo-1532781914607-2031eca2f00d?ixlib=rb-0.3.5&amp;q=80&amp;fm=jpg&amp;crop=entropy&amp;cs=tinysrgb&amp;w=1080&amp;fit=max&amp;ixid=eyJhcHBfaWQiOjMyMDc0fQ&amp;s=7c625ea379640da3ef2e24f20df7ce8d"
    />
    <!-- <img class="img-fluid" alt="100%x280" :src="thumbnail" /> -->
  </div>
</template>

<script>
export default {
  name: 'MainPageTagItem',
  props: {
    item: {
      type: Object,
    },
  },
  data() {
    return {
      // thumbnail: this.item.thumbnail,
      // nickName: this.item.nickName,
    }
  },
}
</script>

<style></style>
