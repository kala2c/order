'use strict';

module.exports = app => {
  const { INTEGER, STRING } = app.Sequelize;
  return app.model.define('order_detail', {
    id: { type: INTEGER, primaryKey: true, autoIncrement: true },
    orderNo: STRING,
    goodsId: INTEGER,
    count: INTEGER,
    status: INTEGER,
  }, {
    freezeTableName: true,
  });
};
