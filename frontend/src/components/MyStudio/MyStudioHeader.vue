<template>
  <div class="headerwrapper">
    <div class="headercontainer row">
      <div class="headerelement col-4">
        <img src="@/assets/images/profile_default.png" class="pg-profile" />
        <div>
          <p class="pg-name">{{ this.$store.state.mystudio.nickname }}</p>
          <div class="pg-mentarea">
            <p class="col-1 pg-mentquote">"</p>
            <p class="col-10 pg-ment">
              {{ this.$store.state.mystudio.pg_profile.introduce }}
            </p>
            <p class="col-1 pg-mentquote">"</p>
          </div>
        </div>
      </div>
      <div class="headerelement col-2">
        <MyStudioHeaderCalender class="col-4" />
        <MyStudioHeaderChat class="col-4" />
        <MyStudioHeaderFavorite class="col-4" />
      </div>
      <div class="headerelement col-3">
        <MyStudioHeaderSearchBar />
      </div>
      <div class="headerelement col-3">
        <div class="col-4"></div>
        <div class="col-4">edit</div>
        <div class="col-4">profile</div>
      </div>
    </div>
  </div>
</template>

<script>
import MyStudioHeaderCalender from '@/components/MyStudio/MyStudioHeaderCalendar.vue'
import MyStudioHeaderChat from '@/components/MyStudio/MyStudioHeaderChat.vue'
import MyStudioHeaderFavorite from '@/components/MyStudio/MyStudioHeaderFavorite.vue'
import MyStudioHeaderSearchBar from '@/components/MyStudio/MyStudioHeaderSearchBar.vue'

export default {
  name: 'MyStudioHeader',
  components: {
    MyStudioHeaderCalender,
    MyStudioHeaderChat,
    MyStudioHeaderFavorite,
    MyStudioHeaderSearchBar,
  },
}
</script>

<style>
.headerwrapper {
  width: 100%;
  height: 72px;
  padding-left: 80px;
  padding-right: 80px;
  background: white;
  text-align: center;
}

.headercontainer {
  width: 100%;
  max-width: 1200px;
  display: inline-flex;
  flex-direction: row;
}

.headerelement {
  line-height: 100%;
  display: flex;
  justify-content: flex-right;
  align-items: center;
}

.pg-profile {
  width: 45px;
  height: 45px;
  object-fit: cover;
  cursor: pointer;
  clip-path: circle(22px at center);
  margin-right: 13px;
}

.pg-mentarea {
  width: auto;
  display: inline-flex;
  display: flex;
  align-items: center;
  height: 32px;
  margin-bottom: 0;
}

.pg-name {
  height: 40px;
  width: auto;
  display: flex;
  align-items: center;
  margin-bottom: 0;
  font-weight: bold;
  font-size: 20px;
}

.pg-mentquote {
  display: inline-block;
  font-weight: bold;
  margin-bottom: 0;
  font-size: 35px;
  padding: 0;
  text-align: center;
}

.pg-ment {
  height: 100%;
  margin-bottom: 0;
  display: flex;
  align-items: center;
  font-size: 16px;
}
</style>
