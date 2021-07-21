package com.joe.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joe.entity.Book;
import com.joe.entity.OrdersDetail;
import com.joe.mapper.BookMapper;
import com.joe.mapper.OrdersDetailMapper;
import com.joe.service.OrdersDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joe.vo.OrdersDetailVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class OrdersDetailServiceImpl extends ServiceImpl<OrdersDetailMapper, OrdersDetail> implements OrdersDetailService {

    @Autowired
    private OrdersDetailMapper ordersDetailMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<OrdersDetailVo> findOrderDetailVOById(Integer ordersId) {
        //根據訂單ID查找訂單商品
        QueryWrapper<OrdersDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("orders_id", ordersId);
        List<OrdersDetail> ordersDetailList = ordersDetailMapper.selectList(wrapper);
        ArrayList<OrdersDetailVo> VOList = new ArrayList<>();
        //VO賦值
        for (OrdersDetail ordersDetail : ordersDetailList) {
            OrdersDetailVo ordersDetailVO = new OrdersDetailVo();
            //訂單詳情複製
            BeanUtils.copyProperties(ordersDetail, ordersDetailVO);
            //圖書複製
            Book book = bookMapper.selectById(ordersDetail.getBookId());
            BeanUtils.copyProperties(book, ordersDetailVO);
            VOList.add(ordersDetailVO);
        }
        return VOList;
    }
}
