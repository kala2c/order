'use strict';

const Controller = require('egg').Controller;

class GoodsController extends Controller {
  async list() {
    const { ctx } = this;
    // const data = [{
    //   id: 1,
    //   name: '小炒',
    //   goods: [{
    //     id: 1,
    //     price: 3.88,
    //     description: '好东西吃',
    //     name: '土豆饼',
    //     icon: 'https://img.yzcdn.cn/vant/ipad.jpeg',
    //   }],
    // }];
    const data = await ctx.service.goods.getList();
    ctx.body = {
      code: 1000,
      message: 'OK',
      data,
    };
  }
  async test() {
    const { ctx } = this;
    const rlt = await ctx.model.Goods.findAll();
    ctx.body = rlt;
  }
}

module.exports = GoodsController;
