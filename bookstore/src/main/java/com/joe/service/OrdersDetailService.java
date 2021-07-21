package com.joe.service;

import com.joe.entity.OrdersDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joe.vo.OrdersDetailVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author joe
 * @since 2021-07-14
 */
public interface OrdersDetailService extends IService<OrdersDetail> {

    /**
     * 根據訂單ID查詢訂單內商品的詳情
     * @param ordersId 訂單ID
     * @return
     */
    List<OrdersDetailVo> findOrderDetailVOById(Integer ordersId);
}
