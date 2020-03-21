'use strict';

const Service = require('egg').Service;

class GoodsService extends Service {
  async getList() {
    const { ctx, app } = this;
    const Op = app.Sequelize.Op;
    const goods = await ctx.model.Goods.findAll();

    const temp = {};
    goods.forEach(item => {
      if (temp[item.categoryId] !== undefined) {
        temp[item.categoryId].push(item);
      } else {
        temp[item.categoryId] = [ item ];
      }
    });
    const category = await ctx.model.Category.findAll({
      where: {
        id: {
          [Op.in]: Object.keys(temp),
        },
      },
      raw: true,
    });
    category.forEach(item => {
      item.goods = temp[item.id];
    });
    return category;
  }
}

module.exports = GoodsService;
