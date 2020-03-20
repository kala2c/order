package com.order.utils;


import java.util.Random;

public class KeyUtil {

    public static String createUniqueKey()
    {
        Random random = new Random();
        Integer number = random.nextInt(9000) + 1000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
