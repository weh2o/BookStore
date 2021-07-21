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
public class Orders implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 訂單id
     */
        @TableId(value = "id", type = IdType.AUTO)
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
       * 訂單狀態
       */
      private Integer status = 0;
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
