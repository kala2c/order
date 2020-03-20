package com.order.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.order.enums.OrderPayStatusEnum;
import com.order.enums.OrderStatusEnum;
import com.order.enums.StatusEnum;
import com.order.utils.EnumUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private Integer id;

    private String orderNo;

    private Integer seatId;

    private String description;

    private BigDecimal amount;

    private Integer status = OrderStatusEnum.NEW.getStatus();

    private String statusText;

    private Integer payStatus = OrderPayStatusEnum.WAIT_PAY.getStatus();

    private String payStatusText;

    private Date createTime;

    private Date updateTime;

    private List<OrderDetailDto> orderDetails;

    @JsonIgnore
    public StatusEnum getStatusEnum() {
        return EnumUtil.getByStatus(status, OrderStatusEnum.class);
    }

    @JsonIgnore
    public StatusEnum getPayStatusEnum() {
        return EnumUtil.getByStatus(payStatus, OrderPayStatusEnum.class);
    }
}
