package com.order.utils;

import com.order.enums.StatusEnum;

public class EnumUtil {
    public static <T extends StatusEnum> T getByStatus(Integer status, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (status.equals(each.getStatus())) {
                return each;
            }
        }
        return null;
    }
}