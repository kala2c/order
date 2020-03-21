'use strict';

const Controller = require('egg').Controller;

class HomeController extends Controller {
  async index() {
    const { ctx } = this;
    ctx.body = 'hi, egg';
  }
  async list() {
    const { ctx } = this;
    ctx.body = 'hi list';
  }
  async test() {
    const { ctx, app } = this;
    const rlt = await ctx.service.order.getList();
    ctx.body = rlt;
  }
}

module.exports = HomeController;
