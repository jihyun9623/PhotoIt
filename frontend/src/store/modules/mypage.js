import http from "@/common/axios.js";

// mypage_info
const state = () => ({
  email: null,
  nickName: null,
  profilePhoto: null,
  isPhotoGrapher: null,
  location: null,
  introduce: null,
});

// mutations
const mutations = {
  SET_USER_INFO(state, payload) {
    state.email = payload.email;
    state.nickName = payload.nickName;
    state.profilePhoto = payload.profilePhoto;
    state.isPhotoGrapher = payload.isPhotoGrapher;
    state.location = payload.location;
    state.introduce = payload.introduce;
  },
};

// actions
const actions = {
  userInfo({ commit }) {
    http.get("/mypage/" + this.localStorage.nickName).then(({ res }) => {
      console.log("UserInfoData :");
      console.log(res);
      commit("SET_USER_INFO", {
        email: res.id,
        nickName: res.nickname,
        profilePhoto: res.profilephoto,
        isPhotoGrapher: res.pg,
        location: res.location,
        introduce: res.introduce,
      });
    });
  },
};

export default {
  namespaced: true,
  mypage_info: state,
  actions,
  mutations,
};
