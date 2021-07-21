package com.joe.vo;

import lombok.Data;

@Data
public class OrdersDetailVo {

    /**
     * 商品圖片
     */
    private String photo;

    /**
     * 書名
     */
    private String name;

    /**
     * 單價
     */
    private Double price;

    /**
     * 商品數量
     */
    private Integer quantity;

    /**
     * 商品總金額
     */
    private Double cost;

}
