package com.joe.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class BookType implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 書本類型ID
     */
        @TableId(value = "book_type_id", type = IdType.AUTO)
      private Integer bookTypeId;

      /**
     * 書本類型名稱
     */
      private String bookTypeName;


}
