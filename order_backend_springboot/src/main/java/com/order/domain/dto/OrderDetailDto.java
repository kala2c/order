package com.order.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDetailDto {
//    必需的值
    private Integer goodsId;
    private Integer count;
//    向控制器传输的值
    private Integer name;
    private BigDecimal price;
    private String icon;
}
