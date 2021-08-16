<template>
  <footer id="footer">
    <div class="footer-top">
      <div class="container">
        <div class="row">
          <div class="col-lg-3 col-md-6">
            <div class="footer-info">
              <h3>Photo it.</h3>
              <p>
                서울시 강남구 <br />
                테헤란로 멀티스퀘어 <br /><br />
                <strong>Phone:</strong> 1544-9001<br />
                <strong>Email:</strong> admin@ssafy.com<br />
              </p>
              <div class="social-links mt-3">
                <a href="#" class="twitter"><i class="bx bxl-twitter"></i></a>
                <a href="#" class="facebook"><i class="bx bxl-facebook"></i></a>
                <a href="#" class="instagram"
                  ><i class="bx bxl-instagram"></i
                ></a>
                <a href="#" class="google-plus"><i class="bx bxl-skype"></i></a>
                <a href="#" class="linkedin"><i class="bx bxl-linkedin"></i></a>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 footer-links">
            <h4>Quick Menu</h4>
            <ul>
              <li>
                <a href="#">Main Page</a>
              </li>
              <li>
                <router-link :to="{ path: '/login' }">로그인</router-link>
              </li>
              <li>
                <router-link :to="{ path: '/signup' }">회원가입</router-link>
              </li>
              <li>
                <router-link :to="{ path: '/mypage' }">마이페이지</router-link>
              </li>
            </ul>
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
