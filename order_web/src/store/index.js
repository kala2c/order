import Vue from 'vue'
import Vuex from 'vuex'
import dialog from './modules/dialog'
import cart from './modules/cart'
import loading from './modules/loading'
Vue.use(Vuex)

const getters = {
  dialogShow: state => state.dialog.show,
  dialogTitle: state => state.dialog.title,
  dialogMessage: state => state.dialog.message,
  cartGoodsList: state => state.cart.goodsList,
  loading: state => state.loading.show
}

export default new Vuex.Store({
  getters,
  modules: {
    dialog,
    cart,
    loading
  }
})
