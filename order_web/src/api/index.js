import axios from 'axios'
// import store from '../store'
import { Dialog } from 'vant'
import { getToken } from '@/utils/token'

const baseURL = 'http://localhost:7001'
export const request = async function (q, url, method, useToken = true) {
  const query = q || {}
  let rlt = null

  const config = {
    baseURL,
    url,
    data: query.data || {},
    params: query.params || {},
    method: method || 'get'
  }

  if (useToken) {
    config.header = { Authorization: 'bearer ' + getToken() }
  }

  await axios(config).then((res) => {
    rlt = res
  }).catch((res) => {
    const message = res.data ? res.data.message || '服务器忙 稍后再试' : '服务器忙 稍后再试'
    Dialog.alert({
      title: '提示',
      message
    })
    // store.dispatch('dialog/open', message)
  }).finally(() => {

  })
  return rlt
}

const getGoodsList = query => request(query, '/api/goods/list')
const submitOrder = query => request(query, 'api/order/post')

export default {
  getGoodsList,
  submitOrder
}
