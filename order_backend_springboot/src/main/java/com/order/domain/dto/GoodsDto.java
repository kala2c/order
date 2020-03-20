package com.order.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.order.domain.entity.Category;
import com.order.enums.GoodsStatusEnum;
import com.order.enums.OrderStatusEnum;
import com.order.enums.StatusEnum;
import com.order.utils.EnumUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class GoodsDto {

    private Integer id;

    private Integer categoryId;

    private String name;

    private String description;

    private String icon = "http://static.c2wei.cn/logo.png";

    private Integer stock = 99;

    private BigDecimal price;

    private Integer weight = 0;

    private Integer status = GoodsStatusEnum.UP.getStatus();

    private Date createTime;

    private Date updateTime;

    private CategoryDto category;

    @JsonIgnore
    public StatusEnum getStatusEnum() {
        return EnumUtil.getByStatus(status, GoodsStatusEnum.class);
    }
}
