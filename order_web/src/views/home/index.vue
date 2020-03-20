<template>
  <div class="home">
    <van-nav-bar
      title="菜单列表"
      left-text="返回"
      right-text="按钮"
      left-arrow
    />
    <!-- @click-left="$router.go(-1)" -->
    <div class="content">
      <sidebar v-model="activeIndex">
        <sidebar-item
          v-for="category in categoryList"
          :key="category.id"
          :title="category.name" />
      </sidebar>
      <div class="card-group">
        <card
          v-for="goods in goodsList"
          :key="goods.id"
          :tag="'热销'"
          :price="goods.price | currency"
          :originPrice="10 | currency"
          :desc="goods.description"
          :title="goods.name"
          :thumb="goods.icon"
        >
        <template slot="footer">
          <Step @add="addGoods(goods)" @dec="delGoods(goods)" :count="count(goods)"></Step>
        </template>
        </card>
      </div>
    </div>
    <div class="footer">
      <cart></cart>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { NavBar, Sidebar, SidebarItem } from 'vant'
import Step from './components/stepper'
import Card from './components/card'
import Cart from './components/cart'
import store from '@/store'
import api from '@/api'
export default {
  components: {
    VanNavBar: NavBar,
    Sidebar,
    SidebarItem,
    Card,
    Step,
    Cart
  },
  data () {
    return {
      categoryList: [],
      activeIndex: 0,
      goodsList: []
    }
  },
  computed: {
    ...mapGetters(['cartGoodsList']),
    count () {
      return function (goods) {
        let count = 0
        this.cartGoodsList.forEach((item) => {
          if (item.id === goods.id) {
            count = item.count
          }
        })
        return count
      }
    }
  },
  watch: {
    activeIndex () {
      this.goodsList = this.categoryList[this.activeIndex].goods
    }
  },
  filters: {
    currency (value) {
      if (!value) return 0
      return (value * 1).toFixed(2) + ''
    }
  },
  methods: {
    openDialog () {
      store.dispatch('dialog/open', '测试')
    },
    addGoods (goods) {
      store.dispatch('cart/push', goods)
    },
    delGoods (goods) {
      store.dispatch('cart/del', goods)
    },
    async initData () {
      const res = await api.getGoodsList()
      if (!res) return
      this.categoryList = res.data.data
      // console.log(this.categoryList)
      this.goodsList = this.categoryList[this.activeIndex].goods
    }
  },
  async created () {
    this.initData()
  },
  mounted () {
    // console.log(this.categoryList)
  }
}
</script>

<style lang="scss" scoped>
  .home {
    position: relative;
    height: 100%;
    overflow: hidden;
  }
  .content {
    display: flex;
    .card-group {
      flex: 1;
    }
  }
  .step {
    display: inline-block;
  }
  .footer {
    position: absolute;
    bottom: 0;
    width: 100%;
    height: 50px;
  }
</style>
