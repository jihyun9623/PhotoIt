<template>
  <!-- <MyStudioHeader /> -->
  <h1>배고프다..</h1>
  <!-- bestlist와 photolist를 활용하여 사진 표시, 및 클릭시 modal표시 -->
  <div class="container">
    bestlist
    <div class="best-container">
      <div class="row">
        <MyStudioEditBestItem
          v-for="(item, idx) in best"
          :key="idx"
          :id="bestid[idx]"
          :item="item"
          class="tag-item"
          @click="showBestModal(item, bestid[idx])"
        />
      </div>
    </div>

    photolist
    <div>
      <div class="row">
        <MyStudioEditBestItem
          v-for="(item, idx) in photo"
          :key="idx"
          :id="bestid[idx]"
          :item="item"
          class="tag-item"
          @click="showPhotoModal(item, bestid[idx])"
        />
      </div>
    </div>
  </div>

  <!--모달 
  <BestModal />
  <BestModal v-on:call-parent-best="closeBestModal"></BestModal>
  <PhotoModal />
  -->
</template>

<script>
import http from '@/assets/js/axios.js'
// import MyStudioHeader from '@/components/MyStudio/MyStudioHeader.vue'
// import BestModal from '@/components/MyStudioEdit/MyStudioEditBest.vue'
// import PhotoModal from '@/components/MyStudioEdit/MyStudioEditPhoto.vue'
// import MyStudioEditBestItem from '@/components/MyStudioEdit/MyStudioEditBestItem'
// import component from "component location"

export default {
  name: 'MyStudioEdit',
  components: {
    // components
    // MyStudioHeader,
    // BestModal,
    // PhotoModal,
    // MyStudioEditBestItem,
  },
  data() {
    return {
      bestModal: '',
      photoModal: '',
      bestModalOn: false,
      photoModalOn: false,
      best: '',
      bestid: '',
      photo: '',
      photoid: '',
    }
  },
  computed: {
    location() {
      return this.$store.state.mystudioedit.location
    },
    introduce() {
      return this.$store.state.mystudioedit.introduce
    },
    bestlist() {
      return this.$store.state.mystudioedit.best
    },
    photolist() {
      return this.$store.state.mystudioedit.photo
    },
  },
  method: {
    // Edit Best Mode 표시
    showBestModal() {
      this.bestModal.show()
    },
    // Edit Best Mode 표시
    showPhotoModal() {
      this.photoModal.show()
    },
    // 베스트 사진 추가
    addBest(photo_id) {
      // TODO : Best사진 추가할 수 있는지 확인 필요
      let data = {
        add_id: photo_id,
      }
      this.$store.dispatch('mystudioedit/addBestPhoto', data).then(() => {
        if (this.$store.state.mystudioedit.return) {
          this.toastSuccess('베스트 사진으로 추가하였습니다.')
          this.$router.push({ name: 'MyStudioEdit' })
        } else {
          this.toastDanger('베스트 사진 추가에 실패하였습니다.')
        }
      })
    },
    // 베스트 사진 수정
    editBest(photo_id, del_id) {
      let data = {
        add_id: photo_id,
        del_id: del_id,
      }
      this.$store.dispatch('mystudioedit/editBestPhoto', data).then(() => {
        if (this.$store.state.mystudioedit.return) {
          this.toastSuccess('베스트 사진을 교체하였습니다.')
          this.$router.push({ name: 'MyStudioEdit' })
        } else {
          this.toastDanger('베스트 사진 교체에 실패하였습니다.')
        }
      })
    },
    // 베스트 사진 삭제
    deleteBest(del_id) {
      let data = {
        del_id: del_id,
      }
      this.$store.dispatch('mystudioedit/deleteBestPhoto', data).then(() => {
        if (this.$store.state.mystudioedit.return) {
          this.toastSuccess('베스트 사진에서 제외하였습니다.')
          this.$router.push({ name: 'MyStudioEdit' })
        } else {
          this.toastDanger('베스트 사진에서 제외하는데 실패하였습니다.')
        }
      })
    },
    // 사진 업로드
    addPhoto(tags) {
      let formData = new FormData()
      formData.append('tag', tags)

      // file upload
      let attachFiles = document.querySelector('#inputFileUploadInsert')
      console.log('attachFiles : ')
      console.log(attachFiles)

      let cnt = attachFiles.files.length
      for (var i = 0; i < cnt; i++) {
        formData.append('file', attachFiles.files[i])
      }

      this.$store.dispatch('mystudioedit/addPhoto', formData).then(() => {
        if (this.$store.state.mystudioedit.return) {
          this.toastSuccess('사진을 업로드하였습니다.')
          this.$router.push({ name: 'MyStudioEdit' })
        } else {
          this.toastDanger('사진 업로드에 실패하였습니다.')
        }
      })
    },
    // 사진 삭제
    deletePhoto(del_id) {
      let data = {
        del_id: del_id,
      }
      this.$store.dispatch('mystudioedit/deletePhoto', data).then(() => {
        if (this.$store.state.mystudioedit.return) {
          this.toastSuccess('사진을 삭제하였습니다.')
          this.$router.push({ name: 'MyStudioEdit' })
        } else {
          this.toastDanger('사진을 삭제하는데 실패하였습니다.')
        }
      })
    },
    // 베스트 사진을 더 추가할 수 있는지 확인
    IsPossibleBestBeAdded() {
      if (this.$store.state.mystudioedit.photo.length > 2) {
        return false
      } else {
        return true
      }
    },
    toastSuccess(text) {
      this.$moshaToast(text, {
        type: 'success',
        position: 'bottom-right',
        timeout: 3000,
        showIcon: true,
      })
    },
    toastDanger(text) {
      this.$moshaToast(text, {
        type: 'danger',
        position: 'bottom-right',
        timeout: 3000,
        showIcon: true,
      })
    },
    getBestPhoto() {
      http.get('/studioedit/bestphoto').then((res) => {
        console.log(res)
        this.best = res.data.file
        this.bestid = res.data.id
        console.log('best')
        console.log(this.best)
      })
    },
    // 작가 전체사진 받아오기
    getPhoto() {
      http.get('/studioedit/photo').then((res) => {
        console.log(res)
        this.photo = res.data.file
        this.photoid = res.data.id
      })
    },
  },
  created() {
    this.$store.dispatch('mystudioedit/studioAuth').then(() => {
      if (!this.$store.state.mystudioedit.studioauth) {
        this.$router.push({ name: 'MainPage' })
      }
    })
    this.getBestPhoto()
    this.getPhoto()
  },
}
</script>

<style scoped>
.tag-item:hover {
  cursor: pointer;
  transform: scale(1.1);
  opacity: 0.7;
  overflow: hidden;
}
</style>
