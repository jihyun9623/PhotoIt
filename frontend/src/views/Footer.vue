<template>
  <footer id="footer">
    <div class="footer-top">
      <div class="container">
        <h3 class="center fontL">Photo it.</h3>
        <hr style="width: 2vm" />
        <div class="row d-flex justify-content-between footerMargin mt-4">
          <div class="col-lg-3 col-md-6 fontCafe">
            <div class="footer-info">
              <!-- <p>
                서울시 강남구 <br />
                테헤란로 멀티스퀘어 <br /><br />
                <strong>Phone:</strong> 1544-9001<br />
                <strong>Email:</strong> admin@ssafy.com<br />
              </p> -->
              <img
                src="@/assets/images/Logo_ver2_white.png"
                alt="로고"
                class="logo center logoSize"
              />
            </div>
          </div>

          <div class="col-lg-5 col-md-6 footer-newsletter">
            <h4>한규와 뚝딱이들 (QHD 4K)</h4>
            <hr />
            <h5>신한규 &nbsp;김상재&nbsp; 김지현</h5>
            <h5>박세령 &nbsp;이은총&nbsp; 이희정</h5>
          </div>
        </div>
      </div>
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
