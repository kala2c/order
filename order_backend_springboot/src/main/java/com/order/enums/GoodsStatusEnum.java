package com.order.enums;

import lombok.Getter;

@Getter
public enum GoodsStatusEnum implements StatusEnum {
    UP(0, "上架"),
    DOWN(1, "下架");

    private Integer status;
    private String statusText;

    GoodsStatusEnum(Integer status, String statusText) {
        this.status = status;
        this.statusText = statusText;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

}
