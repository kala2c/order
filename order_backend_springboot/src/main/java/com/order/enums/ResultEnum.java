package com.order.enums;

public enum ResultEnum {
    Internal_Server_Error(500, "出错了，稍后再试"),
    UNKNOWN_ERROR(-1, "未知错误"),
    SUCCESS(200, "操作成功"),
    PARAM_ERROR(400, "参数不正确"),
    // Goods错误
    GOODS_NOT_FOUND(1001, "商品不存在"),

    // Category错误
    CATEGORY_NOT_FOUND(2001, "分类不存在"),

    // Order错误
    ORDER_NOT_FOUND(3001, "订单不存在"),
    ORDER_EMPTY(3002, "空订单"),
    ORDER_DETAIL_EMPTY(3003, "空订单"),
    ORDER_CREATE_FAIL(3005, "订单创建失败"),
    ORDER_DETAIL_SAVE_FAIL(3006, "订单内容保存失败"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
