package com.joe.controller;


import com.joe.service.OrdersDetailService;
import com.joe.service.OrdersService;
import com.joe.vo.OrdersDetailVo;
import com.joe.vo.OrdersVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author joe
 * @since 2021-07-14
 */
@Controller
@RequestMapping("/ordersDetail")
public class OrdersDetailController {

    @Autowired
    private OrdersDetailService ordersDetailService;

    @Autowired
    private OrdersService ordersService;

    /**
     * 獲得訂單內的商品詳情(用戶)
     * @param ordersId
     * @param model
     * @return
     */
    @GetMapping("/{ordersId}")
    public String allOrdersDetailById(@PathVariable("ordersId") Integer ordersId, Model model) {
        List<OrdersDetailVo> ordersDetailVOList = ordersDetailService.findOrderDetailVOById(ordersId);
        model.addAttribute("ordersDetailList",ordersDetailVOList);
        return "/orders/userOrdersDetail";
    }

    /**
     * 獲得訂單內的商品詳情(管理員)
     * @param ordersId
     * @param model
     * @return
     */
    @GetMapping("/manager/{ordersId}")
    public String managerAllOrdersDetailById(@PathVariable("ordersId") Integer ordersId, Model model) {
        List<OrdersDetailVo> ordersDetailVOList = ordersDetailService.findOrderDetailVOById(ordersId);
        model.addAttribute("ordersDetailList",ordersDetailVOList);
        OrdersVo ordersVO = ordersService.findOneOrdersVO(ordersId);
        model.addAttribute("orders",ordersVO);
        return "/orders/managerOrdersDetail";
    }

}

