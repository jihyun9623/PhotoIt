<template>
  <!-- <MyStudioHeader /> -->
  <!-- bestlist와 photolist를 활용하여 사진 표시, 및 클릭시 modal표시 -->
  <router-link :to="{ path: `/mystudio/${nickname}` }">
    <i
      data-bs-toggle="tooltip"
      data-bs-placement="bottom"
      title="마이 스튜디오로 돌아가기"
      class="
        fas
        fa-arrow-circle-left fa-3x
        mt-4
        mb-5
        ms-4
        ps-2
        pt-1
        btn-go-back
        float-start
      "
    ></i>
  </router-link>
  <div class="container">
    <h1 class="pt-5 mt-5 float-start">BEST 3</h1>
    <section class="best-container row mb-5">
      <!-- 베스트 사진 삭제는 MyStudioEditBest에서 하기 -->
      <MyStudioEditBest
        v-for="(n, idx) in 3"
        :key="idx"
        :best-photo="best[idx]"
        @click="bestClick(bestid[idx])"
      />
    </section>
    <div class="d-flex justify-content-between pt-5">
      <h1>PHOTO LIST</h1>
      <button
        class="btn upload-btn me-3 mb-3"
        data-bs-toggle="modal"
        data-bs-target="#UploadModal"
        @click="showModal()"
      >
        사진 업로드
      </button>
    </div>
    <UploadModal v-on:call-parent-close="closeModal"></UploadModal>
    <section class="photo-container row flex-wrap">
      <!-- 베스트 사진 삭제는 MyStudioEditPhotoList에서 하기 -->
      <MyStudioEditPhotoList
        v-for="(item, idx) in photo"
        :key="idx"
        :id="photoid[idx]"
        :item="photo[idx]"
        @click="photoClick(photoid[idx])"
      />
    </section>
  </div>
</template>

<script>
import http from '@/assets/js/axios.js'
import UploadModal from '@/components/MyStudioEdit/MyStudioEditUploadModal.vue'
import MyStudioEditBest from '@/components/MyStudioEdit/MyStudioEditBest.vue'
import MyStudioEditPhotoList from '@/components/MyStudioEdit/MyStudioEditPhotoList.vue'

export default {
  name: 'MyStudioEdit',
  components: {
    UploadModal,
    MyStudioEditBest,
    MyStudioEditPhotoList,
  },
  data() {
    return {
      modal: false,
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
    nickname() {
      return this.$store.state.login.myNickname
    },
  },
  methods: {
    // Edit Best Mode 표시
    showModal() {
      this.modal = true
    },
    closeModal() {
      this.modal = false
    },
    bestClick(id) {
      console.log(id)
      if (confirm('베스트 사진에서 삭제할까요?')) {
        this.deleteBest(id)
      }
    },
    photoClick(id) {
      console.log(id)
      if (confirm('베스트 사진에 추가할까요?')) {
        this.addBest(id)
      } else {
        if (confirm('사진을 영구 삭제할까요?')) {
          this.deletePhoto(id)
        }
      }
    },
    // 베스트 사진 추가
    addBest(photo_id) {
      // TODO : Best사진 추가할 수 있는지 확인 필요
      let data = {
        add_id: photo_id,
        del_id: '',
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
        add_id: '',
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
        add_id: '',
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
      if (this.$store.state.mystudioedit.best.length > 2) {
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
        console.log('bestphoto axios')
        this.best = res.data.files
        this.bestid = res.data.id
        console.log(this.best)
      })
    },
    // 작가 전체사진 받아오기
    getPhoto() {
      http.get('/studioedit/photo').then((res) => {
        console.log(res)
        this.photo = res.data.files
        this.photoid = res.data.id
      })
    },
  },
  created() {
    this.getBestPhoto()
    this.getPhoto()
    this.$store.dispatch('mystudioedit/studioAuth').then(() => {
      if (!this.$store.state.mystudioedit.studioauth) {
        this.$router.push({ name: 'MainPage' })
      }
    })
  },
}
</script>

<style scoped>
.best-container {
  border: 5px solid darkblue;
  height: 30vh;
  width: 100%;
}
.photo-container {
  border: 5px solid darkblue;
  height: auto;
  width: 100%;
}
.upload-btn {
  background-color: #4b77be;
  color: white;
}
.upload-btn:hover {
  opacity: 80%;
}
.btn-go-back:before {
  color: #4b77be;
}
.btn-go-back:hover {
  opacity: 80%;
}
</style>
