package com.order.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LayoutController {

    @GetMapping("/admin")
    public String index(Map<String, Object> map)
    {
        Map<String, Object> sidebar = new HashMap<>();
        Map<String, Object> order = new HashMap<>();
        order.put("活跃订单", "/admin/order/manage");
        order.put("历史订单", "/admin/order/history");
        Map<String, Object> goods = new HashMap<>();
        goods.put("菜品列表", "/admin/goods/list");
        goods.put("列表管理", "/admin/goods/manage");
        Map<String, Object> seat = new HashMap<>();
        seat.put("座位状态", "/admin/seat/console");
        seat.put("座位管理", "/admin/seat/manage");
        sidebar.put("订单管理", order);
        sidebar.put("菜品管理", goods);
        sidebar.put("座位管理", seat);

        map.put("sidebar", sidebar);


        return "layout/index";
    }

    @GetMapping("/admin/console")
    public String index()
    {
        return "console";
    }
}
