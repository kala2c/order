layui.define(['layer', 'jquery'], function(exports) {
  var layer = layui.layer;
  var $ = layui.jquery;
  // var config = layui.config;
  // var defaultHref = defaultPageHref;
  var defaultHref = '/admin/console';

  // 列表和页面wrap元素
  var tabGroup = $('.layout-tabs-group');
  var pageWrap = $('.layout-page-wrap');
  // 生成tab item 的html元素
  var tabItemTpl = function(page) {
    var tabItem = $('<li></li>');
    tabItem.attr('data-href', page.href);
    tabItem.addClass('layout-tabs-item');
    tabItem.html('<span>'+page.title+'</span>\
                  <span class="page-close-btn">&times;</span>');
    return tabItem;
  }
  // 生成iframe 即页面的html元素
  var pageItemTpl = function(page) {
    var pageItem = $('<iframe></iframe>');
    pageItem.attr('data-href', page.href);
    pageItem.attr('src', page.href);
    pageItem.attr('frameborder', 0)
    pageItem.addClass('page-item');
    return pageItem;
  }

  // 生成默认首页
  var defaultPageItem = pageItemTpl({href: defaultHref});
  pageWrap.append(defaultPageItem);
  // 链接 -> action页面的数据映射
  var pageMap = {
    // 数据存放
    data: { },
    // 当前展示页面的指针
    href: defaultHref,
    // 记录上一个页面指针
    oldHref: null,
    // 添加一个页面
    push: function(title, href, actionItem) {
      var page = {
        title: title,
        href: href,
        actionItem: actionItem,
        tabItem: null,
        pageItem: null
      }
      if (this.data[href]) {
        $(this.data[href].tabItem).click();
      } else {
        // 执行html添加
        this.data[href] = page;
        patchPush(page)
      }
    },
    // 移除一个页面
    remove: function(href) {
      this.data[href] = null;
    }
  };
  pageMap.data[defaultHref] = {
    title: null,
    href: 'welcome',
    actionItem: null,
    tabItem: $('.layout-tabs-home'),
    pageItem: defaultPageItem
  }
  // 添加页面和选项卡的函数
  var patchPush = function(page) {
    // 添加元素到页面
    var tabItem = tabItemTpl(page);
    var pageItem = pageItemTpl(page);
    tabGroup.append(tabItem);
    pageWrap.append(pageItem);
    // 添加元素到page.data
    page.tabItem = tabItem;
    pageMap.data[page.href].tabItem = tabItem;
    page.pageItem = pageItem;
    pageMap.data[page.href].pageItem = pageItem;
    // 给元素绑定点击事件
    $(tabItem).click(function(event) {
      var $target = $(event.target);
      var href = $(this).attr('data-href');
      // 删除页面
      if ($target.attr('class') == 'page-close-btn') {
        $(pageMap.data[href].pageItem).remove();
        $(pageMap.data[href].tabItem).remove();
        // 还有上一页的话点击上一页
        var templ = tabGroup.children().length;
        if (templ > 0) {
          tabGroup.children()[templ-1].click();
        } else {
          // 没有就点击主页
          $(pageMap.data[defaultHref].tabItem).click();
          $(pageMap.data[href].actionItem).parent().removeClass('layui-this');
        }
        pageMap.remove(href)
      // 切换页面
      } else {
        pageMap.oldHref = pageMap.href
        if (pageMap.data[pageMap.href]) {
          $(pageMap.data[pageMap.href].tabItem).removeClass('active');
          $(pageMap.data[pageMap.href].pageItem).css('display', 'none');
        }
        pageMap.href = href;
        $(this).addClass('active');
        $(pageMap.data[href].pageItem).css('display', 'block');
        // 点击一下侧栏对应的按钮
        pageMap.data[href].actionItem.click();
      }      
    })
    // 添加后点击一下切换进来
    $(tabItem).click();
  }
  var layout = {
    screen: function() {
      var w = window.innerWidth;
      return w;
    },
    pageRefresh: function() {
      pageMap.data[pageMap.href].pageItem[0].contentWindow.location.reload();
    }
  }
  //设定顶栏按钮作用
  // 刷新
  $('.layout-nav-item-refresh').click(function () {
    // console.log(111);
    if (pageMap.data[pageMap.href]) {
      layout.pageRefresh();
    }
  });
  // 侧栏收缩、展开
  $('.layout-nav-item-fold').click(function() {
    console.log('fold')
  });
  // 全屏 恢复
  $('.layout-nav-item-full-screen').click(function() {
    console.log('fill-screen');
  });
  // 关闭全部页面
  $('.layout-tabs-close').click(function() {
    layer.confirm('关闭全部页面', {icon: 3, title:'提示'}, function(index){      
      $(pageMap.data[pageMap.href].actionItem).parent().removeClass('layui-this');
      tabGroup.children().each(function() {
        var href = $(this).attr('data-href');
        $(pageMap.data[href].tabItem).remove();
        $(pageMap.data[href].pageItem).remove();
      })
      $('.layout-tabs-home').click();
      layer.close(index);
    });
  });
  // 点击主页
  $('.layout-tabs-home').click(function() {
    if ($(this).attr('class').indexOf('active') > -1) return;
    pageMap.oldHref = pageMap.href
    if (pageMap.data[pageMap.href]) {
      $(pageMap.data[pageMap.href].actionItem).parent().removeClass('layui-this');
      $(pageMap.data[pageMap.href].tabItem).removeClass('active');
      $(pageMap.data[pageMap.href].pageItem).css('display', 'none');
    }
    pageMap.href = defaultHref;
    $(this).addClass('active');
    $(pageMap.data[defaultHref].pageItem).css('display', 'block');
  });

  //点击链接后生成iframe
  
  $('.layout-action').on('click', function() {
    var href = $(this).attr('data-href');
    var title = $(this).text();
    if (href == pageMap.href) {
      return;
    }
    pageMap.push(title, href, this);
  })
  exports('layout', layout);
});