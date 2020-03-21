'use strict';

const Service = require('egg').Service;

class OrderService extends Service {
  async getList() {
    const { ctx } = this;
    const rlt = await ctx.model.OrderMaster.findAll({
      include: [{
        model: ctx.model.OrderDetail,
        as: 'detail',
      }],
    });
    return rlt;
  }
}

module.exports = OrderService;
