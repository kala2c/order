'use strict';

module.exports = app => {
  const { INTEGER, STRING, DECIMAL } = app.Sequelize;
  const Orders = app.model.define('order_master', {
    id: { type: INTEGER, primaryKey: true, autoIncrement: true },
    orderNo: STRING,
    seatId: INTEGER,
    description: STRING,
    amount: DECIMAL(19, 2),
    payStatus: INTEGER,
    status: INTEGER,
    del: INTEGER,
  }, {
    freezeTableName: true,
  });

  Orders.associate = () => {
    Orders.hasMany(app.model.OrderDetail, {
      constraints: false,
      as: 'detail',
      foreignKey: 'orderNo',
      sourceKey: 'orderNo',
    });
  };

  return Orders;
};
