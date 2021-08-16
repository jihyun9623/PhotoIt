<template>
  <div class="container">
    <div class="row">
      <SearchResultPhotoItem
        v-for="(photo, idx) in photoList"
        :key="idx"
        :id="`id-${photo.photoId}`"
        :photo="photo"
        @click="photoDetail(photo.nickName, photo.thumb, photo.photoId)"
      />
      <!-- 모달 -->
      <PhotoDetail />
    </div>
  </div>
</template>

<script>
import SearchResultPhotoItem from '@/components/SearchResult/SearchResultPhotoItem'
import PhotoDetail from '@/components/Common/PhotoDetail'
export default {
  name: 'SearchResultPhotoList',
  components: {
    SearchResultPhotoItem,
    PhotoDetail,
  },
  props: {
    photoList: {
      type: Array,
    },
  },
  data() {
    return {
      modalToggle: false,
    }
  },
  methods: {
    async photoDetail(photoNickname, photoThumbnail, photoDetailId) {
      if (this.modalToggle === false) {
        let id = ''
        this.$store.dispatch('login/isLoginCheck')
        if (this.$store.state.login.isLogin) {
          id = localStorage.getItem('id')
        }
        const nickName = photoNickname
        const thumbnail = photoThumbnail
        await this.$store.dispatch('mainpage/getDetailPhotos', {
          id: id,
          nickName: nickName,
          thumbnail: thumbnail,
        })
        // axios 호출이 끝난 다음에야 DOM을 붙인다.
        // console.log(this.$store.state.mainpage.detailPhotoId, photoDetailId)
        const tagItem = document.querySelector(`#id-${photoDetailId}`)
        tagItem.setAttribute('data-bs-toggle', 'modal')
        tagItem.setAttribute('data-bs-target', `#detailModal-${photoDetailId}`)
        this.modalToggle = true
        tagItem.click()
      } else {
        this.modalToggle = false
      }
    },
  },
}
</script>

<style></style>
