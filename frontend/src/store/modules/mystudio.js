import httpNoJWT from '@/assets/js/axiosNotJWT.js'

// state
const state = () => ({
  nickname: null,
  pg_profile: null,
  best3: null,
  photo_all: null,
  photo_2n_0: [],
  photo_2n_1: [],
})

// actions
const actions = {
  pgProfile: function ({ commit }, nickname) {
    httpNoJWT
      .get('/studio/pgprofile/' + nickname)
      .then((res) => {
        console.log(res.data)
        commit('PG_PROFILE', res.data)
      })
      .catch((err) => {
        console.log(err)
      })
  },
  best3: function ({ commit }, nickname) {
    console.log('best3 actions')
    httpNoJWT
      .get('/studio/bestphotos/' + nickname)
      .then((res) => {
        commit('BEST3', res.data.origin)
      })
      .catch((err) => {
        console.log(err)
      })
  },
  photoAll: function ({ commit }, nickname) {
    console.log('photoAll actions')
    httpNoJWT
      .get('/studio/pgphoto/' + nickname)
      .then((res) => {
        commit('PHOTO_ALL', res.data.origin)
      })
      .catch((err) => {
        console.log(err)
      })
  },
  // pgSearch: function ({ commit }, info) {

  // }
}

const mutations = {
  PG_PROFILE: function (state, data) {
    state.pg_profile = data
  },
  BEST3: function (state, data) {
    state.best3 = data
    console.log(state.best3)
  },
  PHOTO_ALL: function (state, data) {
    state.photo_all = data
    for (var step_0 = 0; step_0 < data.length; step_0 = step_0 + 6) {
      // left push
      state.photo_2n_0.push({
        id: parseInt(step_0 / 6),
        data: [data[step_0], data[step_0 + 2], data[step_0 + 4]],
      })
    }
    for (var step_1 = 1; step_1 < data.length; step_1 = step_1 + 6) {
      // right push
      state.photo_2n_1.push({
        id: parseInt(step_1 / 6),
        data: [data[step_1], data[step_1 + 2], data[step_1 + 4]],
      })
    }
  },
}

export default {
  namespaced: true,
  state,
  actions,
  mutations,
}
