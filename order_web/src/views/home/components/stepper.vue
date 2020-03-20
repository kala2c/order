<template>
  <div class="step">
    <div class="wrap">
      <div class="dec" :class="{bind: !count}" @click="dec">-</div>
      <div class="count">{{count}}</div>
      <div class="add" @click="add">+</div>
    </div>
    <div class="clearfix"></div>
  </div>
</template>

<script>
export default {
  props: {
    count: {
      type: Number
      // default: 0
    }
  },
  methods: {
    dec () {
      if (this.count < 1) return false
      this.$emit('dec')
    },
    add () {
      this.$emit('add')
    }
  }
}
</script>

<style lang="scss" scoped>
.step {
  float: right;
  // display: inline-block;
  .clearfix {
    clear: both;
  }
}
.wrap {
  width: 60px;
  display: flex;
  .dec, .add {
    // box-sizing: border-box;
    width: 20px;
    height: 20px;
    text-align: center;
    line-height: 20px;
    border-radius: 50%;
    font-size: 18px;
    font-weight: bold;
    color: #fff;
    background-color: #1e9fff;
  }
  .dec.bind {
    background-color: #ccc;
  }
  .count {
    flex: 1;
    height: 20px;
    text-align: center;
    line-height: 20px;
  }
}
</style>
