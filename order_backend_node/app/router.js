'use strict';

/**
 * @param {Egg.Application} app - egg application
 */
module.exports = app => {
  const { router, controller } = app;
  router.get('/', controller.home.index);
  router.get('/test', controller.home.test);

  router.get('/api/goods/list', controller.goods.list);
  router.get('/api/goods/test', controller.goods.test);
};
