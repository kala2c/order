'use strict';

module.exports = app => {
  const { INTEGER, STRING } = app.Sequelize;
  return app.model.define('category', {
    id: { type: INTEGER, primaryKey: true, autoIncrement: true },
    name: STRING,
    description: STRING,
    weight: INTEGER,
    status: INTEGER,
    del: INTEGER,
  }, {
    freezeTableName: true,
  });
};
