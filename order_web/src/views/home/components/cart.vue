<template>
  <div class="cart">
    <div class="fix-bar">
      <div class="icon-wrap" @click="openCart">
        <div class="icon">
          <div class="tag">{{totalCount}}</div>
          <van-icon name="shopping-cart" />
        </div>
      </div>
      <div class="content" @click="openCart">
        <span class="total">￥{{totalPrice | currency}}</span>
      </div>
      <div class="submit">
        <div
          v-if="cartGoodsList.length"
          class="submit-btn"
          @click="submit">提交订单</div>
        <div v-else class="disabled">未选择菜品</div>
      </div>
    </div>
    <div class="list-wrap" :class="{show: cartShow}">
      <div class="list-header">
        <span class="title">购物车</span>
        <span class="close" @click="closeCart">
          <van-icon name="arrow-down" />
        </span>
      </div>
      <div v-if="!cartGoodsList.length" class="empty">空空如也</div>
      <div v-else class="list-group">
        <div class="list-group-item"
          v-for="goods in cartGoodsList"
          :key="goods.id"
          >
          <span class="name">{{goods.name}}</span>
          <span class="price">￥{{(goods.price * goods.count) | currency}}</span>
          <Step
            @add="$store.dispatch('cart/push', goods)"
            @dec="$store.dispatch('cart/del', goods)"
            :count="goods.count"
          ></Step>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { Icon, Dialog } from 'vant'
import Step from './stepper'
import api from '@/api'
export default {
  components: {
    Step,
    VanIcon: Icon
  },
  data () {
    return {
      cartShow: false
    }
  },
  computed: {
    ...mapGetters(['cartGoodsList']),
    totalPrice () {
      let totalPrice = 0
      this.cartGoodsList.forEach(goods => {
        totalPrice += goods.price * goods.count
      })
      return totalPrice
    },
    totalCount () {
      let totalCount = 0
      this.cartGoodsList.forEach(goods => {
        totalCount += goods.count
      })
      return totalCount
    }
  },
  methods: {
    openCart () {
      this.cartShow = true
    },
    closeCart () {
      this.cartShow = false
    },
    submit () {
      if (this.cartShow) {
        Dialog.confirm({
          title: '提示',
          message: '确定提交吗'
        }).then(() => {
          const res = api.submitOrder()
          console.log(res)
        })
      } else {
        this.cartShow = true
      }
    }
  },
  filters: {
    currency (value) {
      if (!value) return 0
      return (value * 1).toFixed(2) + ''
    }
  }
}
</script>

<style lang="scss" scoped>
  .cart {
    position: absolute;
    bottom: 0;
    width: 100%;
    height: 50px;
    z-index: 2;
    .fix-bar {
      position: absolute;
      top: 0;
      z-index: 3;
      display: flex;
      width: 100%;
      height: 100%;
      line-height: 50px;
      color: #fff;
      font-weight: 600;
      background-color: rgb(66, 66, 66);
      .icon-wrap {
        display: flex;
        justify-content: center;
        width: 70px;
        .icon {
          position: relative;
          width: 55px;
          height: 55px;
          margin-top: -15px;
          border: 3px solid #666;
          border-radius: 50%;
          font-size: 25px;
          text-align: center;
          line-height: 55px;
          color: #eee;
          background-color: #1e9fff;
          .tag {
            position: absolute;
            top: -5px;
            right: 0;
            width: 20px;
            height: 20px;
            border-radius: 50%;
            line-height: 20px;
            text-align: center;
            color: #fff;
            font-size: 12px;
            background-color: rgb(240, 20, 20);
          }
        }
      }
      .content {
        flex: 1;
      }
      .submit {
        width: 100px;
        height: 100%;
        text-align: center;
        .submit-btn {
          color: #fff;
          background-color: #07c160;
        }
        .disabled {
          color: #bbb;
          background-color: #666;
        }
      }
    }
    .list-wrap {
      position: absolute;
      top: 0;
      z-index: 1;
      width: 100%;
      max-height: 445px;
      font-size: 16px;
      transition: all .6s ease;
      .list-header {
        box-sizing: border-box;
        padding: 0 15px;
        height: 45px;
        line-height: 45px;
        font-weight: 600;
        background-color: #f0f0f0;
        .close {
          float: right;
        }
      }
      .empty {
        padding: 20px 0;
        text-align: center;
      }
      .list-group {
        width: 100%;
        max-height: 400px;
        padding: 10px 15px;
        overflow: auto;
        background-color: #fff;
        .list-group-item {
          display: flex;
          align-items: center;
          height: 60px;
          border-bottom: 1px solid #f0f0f0;
          .name {
            flex: 1;
            display: inline-block;
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
          }
          .price {
            display: inline-block;
            width: 100px;
            font-weight: 700;
            color: rgb(240, 20, 20);
          }
        }
      }
    }
    .show {
      transform: translate3d(0, -100%, 0);
    }
  }
</style>
