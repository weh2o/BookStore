package com.joe.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author joe
 * @since 2021-07-14
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class OrdersDetail implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 訂單詳情id
     */
        @TableId(value = "id", type = IdType.AUTO)
        private Integer id;

      /**
     * 訂單id
     */
      private Integer ordersId;

      /**
     * 商品id
     */
      private Integer bookId;

      /**
     * 商品數量
     */
      private Integer quantity;

      /**
     * 商品總金額
     */
      private Double cost;


}
