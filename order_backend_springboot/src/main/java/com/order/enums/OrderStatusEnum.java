package com.order.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum implements StatusEnum {
    CANCEL(-1, "已取消"),
    NEW(0, "新订单"),
    ACCEPT(1, "已接单"),
    FINISHED(2, "已完成");

    private Integer status;
    private String statusText;

    OrderStatusEnum(Integer status, String statusText) {
        this.status = status;
        this.statusText = statusText;
    }

//    public static OrderStatusEnum getOrderStatusEnum(Integer status) {
//        for (OrderStatusEnum orderStatusEnum: OrderStatusEnum.values()) {
//            if (orderStatusEnum.getStatus().equals(status)) {
//                return orderStatusEnum;
//            }
//        }
//        return null;
//    }
}
