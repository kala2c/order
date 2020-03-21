
module.exports = options => {
  return async function(ctx, next) {
    await next();
    const { request } = ctx;
    ctx.set('Access-Control-Allow-Origin', '*');
    ctx.set('Access-Control-Allow-Credentials', 'true');
    ctx.set('Access-Control-Allow-Methods', 'POST, GET, PATCH, DELETE, PUT');
    ctx.set('Access-Control-Max-Age', '3600');
    ctx.set('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept');
    if (request.method === 'OPTIONS') {
      ctx.status = 200;
    }
  };
};
