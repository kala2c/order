package com.order.domain.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.order.domain.dto.OrderDetailDto;
import com.order.domain.dto.OrderDto;
import com.order.domain.validate.OrderForm;
import com.order.enums.ResultEnum;
import com.order.exception.AppException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDto {

    public static OrderDto convert(OrderForm orderForm)
    {
        Gson gson = new Gson();
        OrderDto orderDto = new OrderDto();
        orderDto.setSeatId(orderForm.getSeatId());
        orderDto.setDescription(orderForm.getDescription());

        List<OrderDetailDto> orderDetailDtoList = new ArrayList<>();
        try {
            orderDetailDtoList = gson.fromJson(orderForm.getItem(),
                new TypeToken<List<OrderDetailDto>>(){
                }.getType());
        } catch (Exception e) {
            log.error("item-json转换出错");
            throw new AppException(ResultEnum.UNKNOWN_ERROR);
        }

        orderDto.setOrderDetails(orderDetailDtoList);

        return orderDto;
    }
}