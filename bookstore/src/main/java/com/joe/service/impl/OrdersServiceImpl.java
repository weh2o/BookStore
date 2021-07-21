package com.joe.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joe.entity.*;
import com.joe.mapper.BookMapper;
import com.joe.mapper.CartMapper;
import com.joe.mapper.OrdersDetailMapper;
import com.joe.mapper.OrdersMapper;
import com.joe.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joe.utils.RandomOrderId;
import com.joe.vo.OrdersVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author joe
 * @since 2021-07-14
 */
@Service
@Transactional
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private OrdersDetailMapper ordersDetailMapper;

    @Autowired
    private BookMapper bookMapper;


    @Override
    public void checkout(User user, Double totalAmount) {

        //生成訂單
        Orders orders = new Orders();
        orders.setUserId(user.getId());
        orders.setUserName(user.getUserName());
        orders.setUserAddress(user.getAddress());
        orders.setCost(totalAmount);
        String orderNo = RandomOrderId.getOrderIdByTime(user.getId());
        orders.setOrdersNo(orderNo);
        ordersMapper.insert(orders);

        //生成對應的訂單商品
        //獲取該用戶所有購物車對象
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id", user.getId());
        List<Cart> cartList = cartMapper.selectList(wrapper);
        for (Cart cart : cartList) {
            OrdersDetail ordersDetail = new OrdersDetail();
            ordersDetail.setOrdersId(orders.getId());
            ordersDetail.setBookId(cart.getBookId());
            ordersDetail.setQuantity(cart.getQuantity());
            ordersDetail.setCost(cart.getCost());
            ordersDetailMapper.insert(ordersDetail);
            //修改庫存、銷量
            Book book = bookMapper.selectById(cart.getBookId());
            book.setStock(book.getStock() - cart.getQuantity());
            book.setSales(book.getSales() + cart.getQuantity());
            bookMapper.updateById(book);
        }
        //清空購物車
        cartMapper.delete(wrapper);

    }

    @Override
    public List<OrdersVo> findAllUserOrdersVO(Integer userId) {
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        List<Orders> orders = ordersMapper.selectList(wrapper);
        ArrayList<OrdersVo> ordersVOList = new ArrayList<>();
        //VO賦值
        for (Orders order : orders) {
            OrdersVo ordersVO = new OrdersVo();
            BeanUtils.copyProperties(order, ordersVO);
            Integer status = order.getStatus();
            //判斷狀態
            switch (status){
                case 0:
                    ordersVO.setStatusDetail("理貨中");
                    break;
                case 1:
                    ordersVO.setStatusDetail("運送中");
                    break;
                case 2:
                    ordersVO.setStatusDetail("訂單完成");
                    break;
            }
            ordersVOList.add(ordersVO);
        }
        return ordersVOList;
    }

    @Override
    public List<OrdersVo> findAllOrders() {
        List<Orders> orders = ordersMapper.selectList(null);
        ArrayList<OrdersVo> ordersVOList = new ArrayList<>();
        //VO賦值
        for (Orders order : orders) {
            OrdersVo ordersVO = new OrdersVo();
            BeanUtils.copyProperties(order, ordersVO);
            Integer status = order.getStatus();
            //判斷狀態
            switch (status){
                case 0:
                    ordersVO.setStatusDetail("理貨中");
                    break;
                case 1:
                    ordersVO.setStatusDetail("運送中");
                    break;
                case 2:
                    ordersVO.setStatusDetail("訂單完成");
                    break;
            }
            ordersVOList.add(ordersVO);
        }
        return ordersVOList;
    }

    @Override
    public OrdersVo findOneOrdersVO(Integer ordersId) {
        Orders orders = ordersMapper.selectById(ordersId);
        OrdersVo ordersVO = new OrdersVo();
        BeanUtils.copyProperties(orders, ordersVO);
        Integer status = orders.getStatus();
        //判斷狀態
        switch (status){
            case 0:
                ordersVO.setStatusDetail("理貨中");
                break;
            case 1:
                ordersVO.setStatusDetail("運送中");
                break;
            case 2:
                ordersVO.setStatusDetail("訂單完成");
                break;
        }
        return ordersVO;
    }
}
