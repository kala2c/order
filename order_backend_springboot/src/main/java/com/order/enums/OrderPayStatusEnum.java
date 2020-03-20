package com.order.enums;

import lombok.Getter;

@Getter
public enum OrderPayStatusEnum implements StatusEnum {
    WAIT_PAY(0, "未支付"),
    PAYED(1, "已支付");

    private Integer status;
    private String statusText;

    OrderPayStatusEnum(Integer status, String statusText) {
        this.status = status;
        this.statusText = statusText;
    }
}
