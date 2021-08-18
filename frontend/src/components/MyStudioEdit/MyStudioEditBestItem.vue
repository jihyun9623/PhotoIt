<template>
  <div class="col-md-4">
    <div class="card p-3">
      <img class="img-fluid" :src="item" />
    </div>
  </div>
</template>

<script>
export default {
  name: 'MyStudioEditBestItem',
  props: {
    item: {
      type: String,
    },
  },
}
</script>
