'use strict';

module.exports = app => {
  const { INTEGER, STRING, DECIMAL, DATE } = app.Sequelize;
  return app.model.define('orders', {
    id: { type: INTEGER, primaryKey: true, autoIncrement: true },
    order_no: STRING,
    seat_id: INTEGER,
    description: STRING,
    amount: DECIMAL(19, 2),
    pay_status: INTEGER,
    status: INTEGER,
    create_time: DATE,
    update_time: DATE,
    del: INTEGER,
  });
};
