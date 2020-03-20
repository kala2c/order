<template>
  <div class="card">
    <div class="left"><img :src="thumb" /></div>
    <div class="right">
      <div class="header">
        <span>{{title}}</span>
      </div>
      <div class="content">
        <span>{{desc}}</span>
      </div>
      <div class="footer">
        <span class="price">￥{{price}}</span>
        <span class="origin-price">￥{{originPrice}}</span>
        <slot name="footer"></slot>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    title: {
      type: String,
      default: ''
    },
    desc: {
      type: String,
      default: ''
    },
    thumb: {
      type: String,
      default: ''
    },
    price: {
      type: String,
      default: '0.00'
    },
    originPrice: {
      type: String,
      default: '0.00'
    }
  }
}
</script>

<style lang="scss" scoped>
  .card {
    display: flex;
    padding: 10px 15px;
    .left {
      width: 80px;
      height: 80px;
      img {
        width: 100%;
        height: 100%;
      }
    }
    .right {
      flex: 1;
      .header {
        font-weight: 600;
        font-size: 16px;
      }
      .content {
        height: 30px;
        color: #666;
        font-size: 12px
      }
      .footer {
        .price {
          font-size: 16px;
          color: rgb(247, 53, 59);
        }
        .origin-price {
          text-decoration: line-through;
          font-size: 13px;
          color: rgb(147, 153, 159);
        }
      }
    }
  }
</style>
