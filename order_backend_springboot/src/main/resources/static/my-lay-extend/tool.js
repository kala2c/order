layui.define(['layer', 'jquery'], function(exports) {
  var layer = layui.layer;
  var $ = layui.jquery
  var tool = {
    refresh: function() {
      window.location.reload();
    }
  }

  // $('.layout-nav-item-refresh').click(function () {
  //   tool.refresh();
  // });
  $('.layout-nav-item-fold').click(function() {
    // 折叠收缩
    console.log('fold')
  })
  $('.layout-nav-item-full-screen').click(function() {
    console.log('fill-screen');
  })
  exports('tool', tool);
});