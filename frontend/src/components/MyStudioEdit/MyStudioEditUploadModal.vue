<!-- 아직 작성하지 않은 코드입니다. -->
<template>
  <div
    class="modal fade"
    id="UploadModal"
    tabindex="-1"
    aria-labelledby="UploadModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">사진 올리기</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @click="closeModal"
          ></button>
        </div>
        <div class="modal-body">
          <div class="form-check mb-3">
            <input
              v-model="attachFile"
              class="form-check-input"
              type="checkbox"
              value=""
              id="chkFileUploadInsert"
            />
            <label class="form-check-label" for="chkFileUploadInsert"
              >파일 추가</label
            >
          </div>
          <div class="mb-3" v-show="attachFile" id="imgFileUploadInsertWrapper">
            <input
              @change="changeFile"
              type="file"
              id="inputFileUploadInsert"
              multiple
            />
            <div id="imgFileUploadInsertThumbnail" class="thumbnail-wrapper">
              <!-- vue way img 를 만들어서 append 하지 않고, v-for 로 처리 -->
              <img
                v-for="(file, index) in fileList"
                v-bind:src="file"
                v-bind:key="index"
              />
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button
            @click="uploadFile"
            data-bs-dismiss="modal"
            class="btn btn-sm btn-primary btn-outline"
            type="button"
          >
            업로드
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import http from '@/assets/js/axios.js'
import axios from 'axios'

export default {
  name: 'UploadModal',
  data() {
    return {
      attachFile: false,
      files: '',
      fileList: [],
      tag: ['우정', '웨딩'],
    }
  },
  methods: {
    initUI() {
      this.attachFile = false
      this.fileList = []
      document.querySelector('#inputFileUploadInsert').value = ''
    },
    changeFile(fileEvent) {
      this.files = fileEvent.target
      if (fileEvent.target.files && fileEvent.target.files.length > 0) {
        for (var i = 0; i < fileEvent.target.files.length; i++) {
          const file = fileEvent.target.files[i]
          this.fileList.push(URL.createObjectURL(file))
        }
      }
    },
    uploadFile() {
      // file upload
      // let dataArray = new Array()
      let tags = this.tag
      let data = new FormData()
      data.append('data', tags)
      // data.append('file', dataArray)

      let cnt = this.files.files.length
      for (var i = 0; i < cnt; i++) {
        data.append('file', this.files.files[i])
        // dataArray.push(this.files.files[i])
        // data.append('file', this.files.files[i])
      }
      // let attachFiles = document.querySelector('#inputFileUploadInsert')
      console.log('upload pending files')
      // console.log(dataArray)
      console.log(data)

      // let cnt = this.files.files.length
      // for (var i = 0; i < cnt; i++) {
      //   data.append('file', this.files.files[i])
      // }

      const jwt = localStorage.getItem('jwt')
      const config = {
        Authorization: jwt,
      }
      return axios({
        method: 'post',
        url: 'http://localhost:8080/studioedit/photo',
        headers: config,
        data: data,
      })
        .then((res) => {
          console.log(res)
          this.toastSuccess('업로드하였습니다.')
          this.closeModal()
        })
        .catch((err) => {
          console.log(err)
          this.toastDanger('업로드하는데 실패하였습니다.')
          this.closeModal()
        })
    },
    closeModal() {
      this.$emit('call-parent-close') // no parameter
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
  },
  mounted() {
    let $this = this
    this.$el.addEventListener('show.bs.modal', function () {
      $this.initUI()
    })
  },
}
</script>

<style>
.thumbnail-wrapper {
  margin-top: 5px;
}

.thumbnail-wrapper img {
  width: 100px !important;
  margin-right: 5px;
  max-width: 100%;
}
</style>
