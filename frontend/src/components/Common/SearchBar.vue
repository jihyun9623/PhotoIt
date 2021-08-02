<template>
  <div class="d-flex justify-content-center">
    <div class="search-style">
      <select name="region" id="search-region" class="me-2">
        <option value="all">전체</option>
      </select>
      <input
        type="text"
        id="integrated-search"
        v-model="something"
        placeholder="  김작가 #웨딩 #풍경 (※작가 닉네임은 필수가 아닙니다.)"
        @keyup.enter="integratedSearch"
      />
      <button><i class="fas fa-search"></i></button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'SearchBar',
  data() {
    return {
      something: null,
      info: {
        region: null,
        keyword: null,
      },
    }
  },
}
</script>

<style scoped>
.search-style {
  width: 60%;
  height: 35px;
  position: absolute;
  top: 70%;
}
input {
  width: 80%;
  height: 100%;
  border: none;
  padding-top: 1px;
  padding-bottom: 0;
}
button {
  background-color: white;
  border: none;
  height: 100%;
  padding-top: 1px;
}
select {
  width: 13%;
  height: 100%;
  border: none;
}
</style>
