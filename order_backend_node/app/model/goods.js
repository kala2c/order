'use strict';

module.exports = app => {
  const { STRING, INTEGER, DECIMAL} = app.Sequelize;
  return app.model.define('goods', {
    id: { type: INTEGER, primaryKey: true, autoIncrement: true },
    name: STRING,
    description: STRING,
    icon: STRING,
    stock: INTEGER,
    price: DECIMAL,
    weight: INTEGER,
    category_id: INTEGER,
    status: INTEGER,
    del: INTEGER,
  });
};
