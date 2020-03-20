package com.order.controller.admin;

import com.order.domain.dto.OrderDto;
import com.order.service.impl.OrdersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/order")
public class OrderController {

    @Autowired
    OrdersServiceImpl ordersService;

    @GetMapping("/history")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             Map<String, Object> map
    )
    {
        PageRequest pageRequest = PageRequest.of(page-1, 10);
        Page<OrderDto> orderDtoPage = ordersService.findFinishedList(pageRequest);
        map.put("orderDtoPage", orderDtoPage);
        return new ModelAndView("order/history", map);
    }

    @GetMapping("/manage")
    public String manage()
    {
        return "order/manage";
    }
}
