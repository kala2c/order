package com.order.controller.api;

import com.order.domain.Result;
import com.order.domain.converter.OrderForm2OrderDto;
import com.order.domain.dto.OrderDto;
import com.order.domain.validate.OrderForm;
import com.order.enums.ResultEnum;
import com.order.exception.AppException;
import com.order.service.impl.OrdersServiceImpl;
import com.order.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderApiController {

    @Autowired
    OrdersServiceImpl ordersService;

    /**
     * 创建订单
     * @param orderForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/post")
    public Result<Object> post(@Valid OrderForm orderForm,
                              BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            throw new AppException(ResultEnum.PARAM_ERROR.getCode(),
                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        OrderDto orderDto = OrderForm2OrderDto.convert(orderForm);
        if (orderDto.getOrderDetails().isEmpty()) {
            throw new AppException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        log.info("steptest{}", orderDto.toString());
        OrderDto result = ordersService.create(orderDto);
        Map<String, String> map = new HashMap<>();
        map.put("orderNo", result.getOrderNo());
        return ResultUtil.success(map);
    }
}
