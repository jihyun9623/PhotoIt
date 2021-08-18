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
      </div>
    </div>
  </div>
</template>

<script>
import http from '@/assets/js/axios.js'
import axios from 'axios'

export default {
  name: 'UploadModal',
  data() {
    return {
      attachFile: false,
      fileList: [],
    }
  },
  methods: {
    initUI() {
      this.attachFile = false
      this.fileList = []
      document.querySelector('#inputFileUploadInsert').value = ''
    },
    changeFile(fileEvent) {
      if (fileEvent.target.files && fileEvent.target.files.length > 0) {
        for (var i = 0; i < fileEvent.target.files.length; i++) {
          const file = fileEvent.target.files[i]
          this.fileList.push(URL.createObjectURL(file))
        }
      }
    },
    uploadFile({ commit }) {
      // file upload
      let data = new FormData()
      let attachFiles = document.querySelector('#inputFileUploadInsert')
      console.log('upload pending files')
      console.log(attachFiles)

      let cnt = attachFiles.files.length
      for (var i = 0; i < cnt; i++) {
        data.append('file', attachFiles.files[i])
      }

      const jwt = localStorage.getItem('jwt')
      const config = {
        'Content-Type': 'multipart/form-data',
        Authorization: jwt,
      }
      return axios({
        method: 'post',
        url: http.serverURL + '/studioedit/photo',
        headers: config,
        data: data,
      })
        .then((res) => {
          console.log(res)
          commit('SET_RETURN', {
            return: true,
          })
        })
        .catch((err) => {
          console.log(err)
          commit('SET_RETURN', {
            return: false,
          })
        })
    },
    closeModal() {
      this.$emit('call-parent-close') // no parameter
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
