<template>
  <footer id="footer">
    <div class="footer-top">
      <div class="container">
        <h3 class="center" style="font-size: xx-large">Photo it.</h3>
        <hr style="width: 1vm" />
        <div class="row d-flex justify-content-between footerMargin mt-4">
          <div class="col-lg-5 col-md-6 fontCafe">
            <div class="footer-info center">
              <img
                src="@/assets/images/Logo_ver2_white.png"
                alt="로고"
                class="logo logoSize center"
                style="width: 10vw; height: auto"
              />
            </div>
            <div class="fontCafe center mt-3">사진을 잇다, Photo-it</div>
          </div>

          <div class="col-lg-5 col-md-6 footer-newsletter">
            <h4 class="fontCafe center">한규와 뚝딱이들 (QHD 4K)</h4>
            <hr />
            <h5 class="fontCafe center">신한규 &nbsp;김상재&nbsp; 김지현</h5>
            <h5 class="fontCafe center">박세령 &nbsp;이은총&nbsp; 이희정</h5>
          </div>
        </div>
      </div>
      <p class="mt-5 mb-3 text-muted center fontS">&copy; PHOTO-IT 2021</p>
    </div>
  </footer>
</template>

<script>
export default {
  name: 'Footer',
}
</script>

<style scoped>
a {
  text-decoration: none;
}

#footer {
  background: #151515;
  padding: 60px 0 30px 0;
  color: #fff;
  font-size: 18px;
  text-align: left;
  display: block;
}

li {
  padding: 10px 0;
}
</style>
