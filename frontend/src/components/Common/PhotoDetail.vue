<template>
  <div
    class="modal fade"
    :id="`detailModal-${detailPhotoId}`"
    tabindex="-1"
    aria-labelledby="detailModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-body d-flex justify-content-center">
          <img :src="detailPhoto" alt="detail" class="modal-img" />
        </div>
        <div class="modal-footer d-flex justify-content-start pt-0">
          <div class="ps-2 pb-2" style="color: white">
            <p class="mb-0">
              <!-- 작가 프로필 사진 및 찜 아이콘 -->
              <span class="pe-3 profile-box">
                <span v-if="detailProfile">
                  <img
                    class="pg-profile"
                    :src="detailProfile"
                    alt="photographer profile"
                  />
                  <i
                    v-if="detailIsFavorite && isLogin"
                    class="fas fa-heart favorit-icon"
                    @click="deleteFavorite"
                  ></i>
                  <i
                    v-else-if="!detailIsFavorite && isLogin"
                    class="far fa-heart favorit-icon"
                    @click="addFavorite"
                  ></i>
                </span>
                <!-- 기본 프로필 사진 및 찜 아이콘 -->
                <span v-else>
                  <img
                    class="pg-profile"
                    src="../../assets/images/profile_default.png"
                    alt="default profile"
                  />
                  <i
                    v-if="detailIsFavorite && isLogin"
                    class="fas fa-heart favorit-icon"
                    @click="deleteFavorite"
                  ></i>
                  <i
                    v-else-if="!detailIsFavorite && isLogin"
                    class="far fa-heart favorit-icon"
                    @click="addFavorite"
                  ></i>
                </span>
              </span>
              <!-- 사진 작가 닉네임 -->
              <span class="me-5 fw-bold">{{ detailPgNickname }}</span>
              <!-- 사진 태그 리스트 -->
              <span v-for="(tag, idx) in detailTagList" :key="idx" class="me-2">
                #{{ tag }}
              </span>
            </p>
          </div>
          <!-- 작가의 다른 사진 -->
          <div class="container-fluid px-0">
            <div class="row">
              <div
                class="col-3"
                v-for="(item, idx) in detailOtherPhotos"
                :key="idx"
              >
                <div class="card">
                  <img
                    class="img-fluid card-img"
                    alt="other photo"
                    :src="item.thumbnail"
                    :id="`id-${item.photoId}`"
                    @click="
                      photoDetail(
                        detailPgNickname,
                        item.thumbnail,
                        item.photoId,
                      )
                    "
                  />
                  <PhotoDetail v-if="modalToggle" />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'PhotoDetail',
  data() {
    return {
      modalToggle: false,
    }
  },
  methods: {
    async photoDetail(itemNickname, itemThumbnail, itemDetailId) {
      this.modalToggle = false
      if (this.modalToggle === false) {
        let id = ''
        this.$store.dispatch('login/isLoginCheck')
        if (this.$store.state.login.isLogin) {
          id = localStorage.getItem('id')
        }
        const nickName = itemNickname
        const thumbnail = itemThumbnail
        await this.$store.dispatch('mainpage/getDetailPhotos', {
          id: id,
          nickName: nickName,
          thumbnail: thumbnail,
        })
        // axios 호출이 끝난 다음에야 DOM을 붙인다.
        // console.log(this.$store.state.mainpage.detailPhotoId, itemDetailId)
        const tagItem = document.querySelector(`#id-${itemDetailId}`)
        tagItem.setAttribute('data-bs-toggle', 'modal')
        tagItem.setAttribute('data-bs-target', `#detailModal-${itemDetailId}`)
        this.modalToggle = true
      }
    },
    addFavorite() {
      this.$store.dispatch('mainpage/addFavorite', {
        pgNick: this.detailPgNickname,
        userNick: this.$store.state.mainpage.nickname,
      })
    },
    deleteFavorite() {
      this.$store.dispatch('mainpage/deleteFavorite', {
        pgNick: this.detailPgNickname,
        userNick: this.$store.state.mainpage.nickname,
      })
    },
  },
  computed: {
    detailPhoto() {
      return this.$store.state.mainpage.detailPhoto
    },
    detailPhotoId() {
      return this.$store.state.mainpage.detailPhotoId
    },
    detailOtherPhotos() {
      return this.$store.state.mainpage.detailOtherPhotos
    },
    detailPgNickname() {
      return this.$store.state.mainpage.detailPgNickname
    },
    detailTagList() {
      return this.$store.state.mainpage.detailTagList
    },
    detailProfile() {
      return this.$store.state.mainpage.detailProfile
    },
    detailIsFavorite() {
      return this.$store.state.mainpage.detailIsFavorite
    },
    isLogin() {
      return this.$store.state.login.isLogin
    },
  },
}
</script>

<style scoped>
.modal-img {
  max-width: 50vw;
  max-height: 50vh;
  object-fit: contain;
}
.modal-dialog {
  max-height: 50vh;
  max-width: 55vw;
}
/* .modal-body {
  margin: 0.25rem;
  padding: 0;
} */
.modal-content {
  background-color: black;
}
.modal-footer {
  border-style: none;
}
.card-img {
  width: 100%;
  height: 10vh;
  object-fit: cover;
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
.pg-profile {
  width: 5vh;
  height: 5vh;
  border-radius: 50%;
  overflow: hidden;
}
.profile-box {
  position: relative;
}
.favorit-icon {
  position: absolute;
  top: 100%;
  right: 20%;
  color: red;
  cursor: pointer;
}
</style>
