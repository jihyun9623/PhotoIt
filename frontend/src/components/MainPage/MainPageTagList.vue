<template>
  <section>
    <div class="container">
      <!-- 태그 -->
      <div class="tag-name d-flex justify-content-start fw-bold fs-4 mb-1">
        <!-- view router: 검색 결과 페이지로 이동 -->
        <router-link
          :to="{ path: `/search/${tagName}/all/tag` }"
          class="tag-name"
          @click="goSearch"
        >
          # {{ tagName }} <i class="fas fa-caret-down"></i>
        </router-link>
      </div>
      <div class="row">
        <!-- 캐러셀 -->
        <div
          :id="`tagListCarousel-${tagName}`"
          class="carousel slide"
          data-bs-interval="false"
        >
          <div class="carousel-inner">
            <!-- 카드 -->
            <div class="carousel-item active">
              <div class="row">
                <MainPageTagItem
                  v-for="(item, idx) in defaultPackage"
                  :item="item"
                  :key="idx"
                  :id="`id-${item.photoId}`"
                  class="tag-item"
                  @click="
                    photoDetail(item.nickName, item.thumbnail, item.photoId)
                  "
                />
                <!-- 모달 -->
                <PhotoDetail />
              </div>
            </div>
            <div
              class="carousel-item"
              v-for="(otherPackage, idx) in otherPackages"
              :key="idx"
            >
              <div class="row">
                <MainPageTagItem
                  v-for="(item, idx) in otherPackage"
                  :key="idx"
                  :id="`id-${item.photoId}`"
                  :item="item"
                  class="tag-item"
                  @click="
                    photoDetail(item.nickName, item.thumbnail, item.photoId)
                  "
                />
                <!-- 모달 -->
                <PhotoDetail />
              </div>
            </div>
          </div>
          <!-- 버튼 -->
          <button
            class="carousel-control-prev"
            type="button"
            :data-bs-target="`#tagListCarousel-${tagName}`"
            data-bs-slide="prev"
            style="background-color: #c4c4c4; width: auto"
          >
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
          </button>
          <button
            class="carousel-control-next"
            type="button"
            :data-bs-target="`#tagListCarousel-${tagName}`"
            data-bs-slide="next"
            style="background-color: #c4c4c4; width: auto"
          >
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
          </button>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import MainPageTagItem from '@/components/MainPage/MainPageTagItem'
import PhotoDetail from '@/components/Common/PhotoDetail'

export default {
  name: 'MainPageTagList',
  components: {
    MainPageTagItem,
    PhotoDetail,
  },
  props: {
    tagSet: {
      type: Object,
    },
  },
  data() {
    return {
      tagName: this.tagSet.tag,
      allPackage: this.tagSet.thumbNickNameList,
      defaultPackage: null,
      otherPackages: [],
      carouselRotation: null,
      modalToggle: false,
    }
  },
  methods: {
    defaltSlice() {
      this.defaultPackage = this.allPackage.slice(0, 4)
    },
    makePackage() {
      // 캐러셀 하나당 카드 4개씩
      this.carouselRotation = Math.ceil(this.allPackage.length / 4) - 1
      for (let start = 4; start < this.allPackage.length; start += 4) {
        this.otherPackages.push(this.allPackage.slice(start, start + 4))
      }
    },
    goSearch() {
      this.$store.dispatch('search/tagSearch', {
        keyword: this.tagName,
        region: 'all',
      })
    },
    async photoDetail(itemNickname, itemThumbnail, itemDetailId) {
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
        tagItem.click()
      } else {
        this.modalToggle = false
      }
    },
  },
  mounted() {
    this.defaltSlice()
    this.makePackage()
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
.tag-name {
  text-decoration: none;
  color: black;
}
</style>
