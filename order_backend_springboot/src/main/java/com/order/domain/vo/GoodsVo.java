package com.order.domain.vo;

import lombok.Data;

@Data
public class GoodsVo {
    private Integer id;
    private String name;
    private String description;
    private String icon;
    private Double price;
}
