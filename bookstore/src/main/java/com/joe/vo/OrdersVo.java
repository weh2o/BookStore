package com.joe.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrdersVo {

    private Integer id;

    /**
     * 訂單號
     */
    private String ordersNo;

    /**
     * 用戶id
     */
    private Integer userId;

    /**
     * 用戶名
     */
    private String userName;

    /**
     * 用戶地址
     */
    private String userAddress;

    /**
     * 訂單總金額
     */
    private Double cost;


    /**
     * 訂單狀態編號
     */
    private Integer status;

    /**
     * 訂單狀態
     */
    private String statusDetail;
    /**
     * 創建時間
     */
    private LocalDateTime createTime;
}
