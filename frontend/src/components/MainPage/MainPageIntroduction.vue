<template>
  <section class="background-wrap">
    <div>
      <div class="relative-block">
        <img
          src="../../assets/images/photographer.png"
          alt="photohrapher"
          class="background-img-photographer"
        />
        <img
          src="../../assets/images/customer.png"
          alt="customer"
          class="background-img-customer"
        />
        <p class="text-pg">빛나는 재능을 가진<br />당신도</p>
        <p class="text-ct">빛나는 순간을 남기고 싶은<br />당신도</p>
        <span class="text-closing">
          <p>당신만의</p>
          <p>
            <span class="pe-5">모델을</span>
            <span class="ps-5">작가를</span>
          </p>
          <p>만나보세요</p>
        </span>
      </div>
    </div>
  </section>
  <div class="empty-box"></div>
</template>

<script>
export default {
  name: 'MainPageIntroduction',
  data() {
    return {
      baseBgTop: 0,
      baseScrollY: 0,
    }
  },
  methods: {
    scrollEffect() {
      const bgSection = document.querySelector('.background-wrap')
      const textPg = document.querySelector('.text-pg')
      const textCt = document.querySelector('.text-ct')
      const textClosing = document.querySelector('.text-closing')
      const bgImgPhotographer = document.querySelector(
        '.background-img-photographer',
      )
      const bgImgCustomer = document.querySelector('.background-img-customer')

      const { top: bgTop, height: bgHeight } = bgSection.getBoundingClientRect()
      const { clientHeight } = document.documentElement
      const { scrollY } = window
      // scrollY = 0 인 시점이기 떄문에 clientHeight만 뺀다.
      const contentQuarter = (bgHeight - clientHeight) / 3
      // console.log(bgTop, bgHeight, scrollY, clientHeight)
      if (bgTop < 0 && scrollY + clientHeight < bgHeight) {
        if (parseInt(scrollY) <= contentQuarter) {
          const rate = (-1 * bgTop) / 100
          bgImgCustomer.style.filter = `blur(${rate}px)`
          bgImgPhotographer.style.filter = 'none'
          this.baseBgTop = bgTop
          this.baseScrollY = scrollY
          textPg.style.opacity = 1 - scrollY / 1000
          textCt.style.opacity = '0'
          textClosing.style.opacity = '0'
        } else if (
          contentQuarter < parseInt(scrollY) &&
          parseInt(scrollY) <= contentQuarter * 2
        ) {
          const rate = (-1 * bgTop + this.baseBgTop) / (100 * 2)
          bgImgPhotographer.style.filter = `blur(${rate * 1.7}px)`
          bgImgCustomer.style.filter = `none`
          textPg.style.opacity = '0'
          textCt.style.opacity = 1 - (scrollY - this.baseScrollY) / 1000
          textClosing.style.opacity = '0'
        } else if (
          contentQuarter < parseInt(scrollY) * 2 &&
          parseInt(scrollY) <= contentQuarter * 3
        ) {
          bgImgPhotographer.style.filter = 'none'
          textPg.style.opacity = '0'
          textCt.style.opacity = '0'
          textClosing.style.opacity = '1'
        }
      }
      if (bgTop >= 0) {
        bgImgCustomer.style.filter = 'none'
        bgImgPhotographer.style.filter = 'none'
      }
    },
  },
  mounted() {
    addEventListener('scroll', this.scrollEffect)
  },
}
</script>

<style scoped>
/* 스크롤을 내려도 멈춘 것처럼 보이는 효과 */
.background-wrap {
  height: 500vh;
  /* padding-bottom: 200px; */
}
.background-wrap div {
  overflow: hidden;
  /* width: 100vw 에서 가로 스크롤바를 없애기 위해 max-width 설정 (원인: 세로 스크롤바 너비 포함으로 인한 뷰표트 초과) */
  max-width: 100%;
  width: 100vw;
  height: 100vh;
  position: sticky;
  top: 0;
  left: 0;
}
.relative-block {
  position: relative;
}
.background-img-photographer {
  display: block;
  width: 100vw; /* vw? %? */
  height: 100vh;
  position: absolute;
  object-fit: cover;
  transition: opacity 3s;
  /* background-size: cover;
  background-image: url('../../assets/images/customer.png'),
    url('../../assets/images/photographer.png'); */
}
.background-img-customer {
  display: block;
  width: 100vw; /* vw? %? */
  height: 100vh;
  position: absolute;
  object-fit: cover;
  transition: opacity 3s;
}
.empty-box {
  width: 100%;
  height: 200px;
}
.text-pg {
  position: absolute;
  top: 20vh;
  left: 5vw;
  color: white;
  font-weight: bold;
  font-size: 2.3rem;
  opacity: 0;
}
.text-ct {
  position: absolute;
  top: 70vh;
  right: 5vw;
  color: white;
  font-weight: bold;
  font-size: 2.3rem;
  opacity: 0;
}
.text-closing {
  position: absolute;
  /* 가운데 정렬 */
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  text-align: center;
  color: white;
  font-weight: bold;
  font-size: 2.7rem;
  opacity: 0;
  /* 가로 화면이 줄어들었을 때 글자가 깨지지 않도록 */
  word-wrap: normal;
}
</style>
