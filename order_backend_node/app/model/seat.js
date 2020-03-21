'use strict';

module.exports = app => {
  const { INTEGER, STRING } = app.Sequelize;
  return app.model.define('seat', {
    id: { type: INTEGER, primaryKey: true, autoIncrement: true },
    number: INTEGER,
    name: INTEGER,
    latlng: STRING,
    description: STRING,
    status: INTEGER,
    del: INTEGER,
  });
};
