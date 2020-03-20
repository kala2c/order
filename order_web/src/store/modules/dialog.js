
const state = {
  show: false,
  title: '提示',
  message: '警告'
}

const mutations = {
  setShow (state, status) {
    state.show = status
  },
  setTitle (state, title) {
    state.show = title
  },
  setMessage (state, message) {
    state.message = message
  }
}

const actions = {
  open ({ commit }, message) {
    console.log(111)
    commit('setShow', true)
    commit('setMessage', message)
  },
  close ({ commit }) {
    commit('setShow', false)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
