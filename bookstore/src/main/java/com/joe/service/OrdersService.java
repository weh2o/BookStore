package com.joe.service;

import com.joe.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joe.entity.User;
import com.joe.vo.OrdersVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author joe
 * @since 2021-07-14
 */
public interface OrdersService extends IService<Orders> {


    /**
     * 生成訂單、對應的訂單商品詳情，扣庫存，修改銷售量
     * @param user
     * @param totalAmount
     */
    void checkout(User user, Double totalAmount);

    /**
     * 獲取用戶的所有訂單詳細狀態
     * @param userId
     * @return
     */
    List<OrdersVo> findAllUserOrdersVO(Integer userId);

    /**
     * 獲取全部訂單
     * @return
     */
    List<OrdersVo> findAllOrders();

    /**
     * 獲取一個訂單詳細狀態
     * @param ordersId
     * @return
     */
    OrdersVo findOneOrdersVO(Integer ordersId);

}
