package com.joe.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class CartVo {

    private Integer id;

    //用戶id
    private Integer userId;

    //商品id
    private Integer bookId;

    //商品數量
    private Integer quantity;

    //總價
    private Double cost;

    //單價
    private Double price;

    //商品圖片
    private String photo;

    //書名
    private String name;

    //商品庫存
    private Integer stock;
}
