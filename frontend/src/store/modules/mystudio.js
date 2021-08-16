import axios from 'axios'

const SERVER_URL = 'http://localhost:8080/studio'

// state
const state = () => ({
  photo_list: [
    {
      id: 1,
      photo_path:
        'https://images.unsplash.com/photo-1496749843252-699a989877a1?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=fe5da9650707e5a93c8c3cf164c2e74b&auto=format&fit=crop&w=1375&q=80',
    },
    {
      id: 2,
      photo_path:
        'https://images.unsplash.com/photo-1492970471430-bc6bd7eb2b13?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=9893bc89e46e2b77a5d8c091fbba04e9&auto=format&fit=crop&w=1355&q=80',
    },
    {
      id: 3,
      photo_path:
        'https://images.unsplash.com/photo-1501707305551-9b2adda5e527?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=0cf5887247e17503ce4e542d00d86b9d&auto=format&fit=crop&w=1335&q=80',
    },
    {
      id: 4,
      photo_path:
        'https://images.unsplash.com/photo-1513555329264-541740eca390?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=356f530d44af9c3c2690fd464baa655c&auto=format&fit=crop&w=1350&q=800',
    },
    {
      id: 5,
      photo_path:
        'https://images.unsplash.com/photo-1510022079733-8b58aca7c4a9?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=6da2c9e985e56b6a43209d7b1d46c8ca&auto=format&fit=crop&w=701&q=80',
    },
    {
      id: 6,
      photo_path:
        'https://images.unsplash.com/photo-1521089815383-cf2b2cf7f0f0?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=524500bf8ae21941d5af74e374d43dec&auto=format&fit=crop&w=701&q=80',
    },
  ],
  nickname: null,
  pg_profile: null,
  best3: null,
  photo_all: null,
})

// actions
const actions = {
  pgProfile: function ({ commit }, nickname) {
    axios({
      method: 'get',
      url: `${SERVER_URL}/pgprofile/${nickname}`,
    })
      .then((res) => {
        commit('PG_PROFILE', res.data)
      })
      .catch((err) => {
        console.log(err)
      })
  },
  best3: function ({ commit }, nickname) {
    console.log('best3 actions')
    axios({
      method: 'get',
      url: `${SERVER_URL}/bestphotos/${nickname}`,
    })
      .then((res) => {
        console.log(res)
        commit('BEST3', res.data)
      })
      .catch((err) => {
        console.log(err)
      })
  },
  photoAll: function ({ commit }, nickname) {
    console.log('photoAll actions')
    axios({
      method: 'get',
      url: `${SERVER_URL}/pgphoto/${nickname}`,
    })
      .then((res) => {
        console.log(res.data.origin)
        commit('PHOTO_ALL', res.data.origin)
      })
      .catch((err) => {
        console.log(err)
      })
  },
}

const mutations = {
  PG_PROFILE: function (state, data) {
    state.pg_profile = data
    console.log(state.pg_profile.introduce)
    console.log(state.pg_profile.location)
  },
  BEST3: function (state, data) {
    state.best3 = data
  },
  PHOTO_ALL: function (state, data) {
    state.photo_all = data
  },
}

export default {
  namespaced: true,
  state,
  actions,
  mutations,
}
