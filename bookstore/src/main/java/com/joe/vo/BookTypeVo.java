package com.joe.vo;

import lombok.Data;

@Data
public class BookTypeVo {

    /**
     * 書本類型ID
     */
    private Integer bookTypeId;

    /**
     * 書本類型名稱
     */
    private String bookTypeName;

    /**
     * 圖書總數量
     */
    private Integer bookTotalCount;

    /**
     * 圖書總銷量
     */
    private Integer bookTotalSales;
}
