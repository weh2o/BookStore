package com.joe.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandomOrderId {
    /**
     * 生成隨機的訂單
     * @return
     */
    public static String getOrderIdByTime(Integer id) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        String result = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            result += random.nextInt(10);
        }
        String OrderId = newDate + result + id;

        return OrderId;
    }

}
