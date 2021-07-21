package com.joe.service;

import com.joe.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joe.vo.CartVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author joe
 * @since 2021-07-14
 */
public interface CartService extends IService<Cart> {
    /**
     * 獲取購物車內商品詳情
     * @param userid
     * @return
     */
    List<CartVo> findAllCartVODetail(Integer userid);
}
