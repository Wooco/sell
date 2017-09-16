package com.imooc.utils;

import java.util.Random;

/**
 * Created by wujianchuan
 * 2017/9/16 15:16
 */
public class KeyUtil {

    public static synchronized String getUniqueKey() {

        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
