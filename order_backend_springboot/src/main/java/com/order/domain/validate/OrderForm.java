package com.order.domain.validate;

import lombok.Data;
import org.apache.logging.log4j.message.Message;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class OrderForm {

    private String description;

    @NotEmpty(message = "购物车不能为空")
    private String item;

    @NotNull(message = "座位号必须指定")
    private Integer seatId;
}
