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
public class Book implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * ID
     */
        @TableId(value = "id", type = IdType.AUTO)
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
      private Integer sales = 0;

      /**
     * 庫存
     */
      private Integer stock;

      /**
     * 圖片
     */
      private String photo;

      /**
     * 類型
     */
      private Integer bookTypeId;


  public void setSales(Integer sales) {

    if (sales != null && !"".equals(sales)) {
      this.sales = sales;
    }
  }

  public Book(Integer id, String name, String author, Double price, Integer sales, Integer stock, String photo, Integer bookTypeId) {
    this.id = id;
    this.name = name;
    this.author = author;
    this.price = price;
    this.stock = stock;
    this.photo = photo;
    this.bookTypeId = bookTypeId;
    //給予銷售默認值
    if (sales != null && !"".equals(sales)) {
      this.sales = sales;
    }

  }
}
