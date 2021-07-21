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
public class User implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 用戶ID
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 用戶帳號
     */
      private String userAccount;

      /**
     * 用戶名
     */
    private String userName;

      /**
     * 密碼
     */
      private String password;

      /**
     * 信箱
     */
      private String email;

      /**
     * 電話
     */
      private String phone;

      /**
     * 地址
     */
      private String address;

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
