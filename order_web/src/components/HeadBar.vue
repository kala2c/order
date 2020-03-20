<template>
  <van-nav-bar
    :title="title"
    left-text="返回"
    right-text="按钮"
    left-arrow
    @click-left="onClickLeft"
    @click-right="onClickRight"
  />
</template>

<script>
import { NavBar } from 'vant'
export default {
  components: {
    VanNavBar: NavBar
  },
  props: {
    title: {
      type: String,
      default: '点餐'
    }
  }
}
</script>

<style lang="scss" scoped>
.head-bar {
  text-align: center
}
</style>
