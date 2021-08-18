<template>
  <!-- <MyStudioHeader /> -->
  <h1>배고프다..</h1>
  <!-- bestlist와 photolist를 활용하여 사진 표시, 및 클릭시 modal표시 -->
  <div class="container">
    bestlist
    <div class="best-container row">
      <div
        v-for="(item, idx) in bestid"
        :key="idx"
        :id="bestid[idx]"
        :item="item"
        class="tag-item col-md-4 d-flex align-self-center"
        @click="bestClick(bestid[idx])"
      >
        <img class="img-fluid card-img" :src="item" />
      </div>
    </div>

    <button
      class="btn btn-sm btn-warning float-start"
      data-toggle="modal"
      data-target="#UploadModal"
      @click="showModal()"
    >
      사진 업로드
    </button>

    photolist
    <div class="photo-container row flex-wrap">
      <div
        v-for="(item, idx) in photoid"
        :key="idx"
        :id="photoid[idx]"
        :item="item"
        class="tag-item col-md-2 d-flex align-self-center"
        @click="photoClick(photoid[idx])"
      >
        <img class="img-fluid card-img" :src="item" />
      </div>
    </div>
  </div>

  <UploadModal v-on:call-parent-close="closeModal"></UploadModal>
</template>

<script>
import http from '@/assets/js/axios.js'
import UploadModal from '@/components/MyStudioEdit/MyStudioEditUploadModal.vue'

export default {
  name: 'MyStudioEdit',
  components: {
    UploadModal,
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
  },
  methods: {
    // Edit Best Mode 표시
    showModal() {
      this.modal = true
    },
    closeModal() {
      this.modal = false
      window.location.reload()
    },
    bestClick(id) {
      console.log(id)
    },
    photoClick(id) {
      console.log(id)
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
.tag-item:hover {
  cursor: pointer;
  transform: scale(1.1);
  opacity: 0.7;
  overflow: hidden;
}
.best-container {
  border: 5px solid darkblue;
  height: 400px;
  width: 100%;
}
.photo-container {
  border: 5px solid darkblue;
  height: 100%;
  width: 100%;
}
</style>
