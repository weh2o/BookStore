package com.joe.vo;

import lombok.Data;

@Data
public class BookVo {
    private Integer id;
    /**
     * 書名
     */
    private String name;

    /**
     * 作者
     */
    private String author;

    /**
     * 價格
     */
    private Double price;

    /**
     * 銷量
     */
    private Integer sales;

    /**
     * 庫存
     */
    private Integer stock;

    /**
     * 圖片
     */
    private String photo;

    /**
     * 圖書具體類型
     */
    private String bookType;

}
