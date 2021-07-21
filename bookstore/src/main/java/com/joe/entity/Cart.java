package com.joe.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
public class Cart implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 購物車id
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 用戶id
     */
      private Integer userId;

      /**
     * 商品id
     */
      private Integer bookId;

      /**
     * 商品數量
     */
      private Integer quantity;

      /**
     * 單價
     */
    private Double price;

      /**
     * 總價
     */
      private Double cost;

      /**
     * 創建時間
     */
        @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createTime;

      /**
     * 修改時間
     */
        @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updateTime;


}
