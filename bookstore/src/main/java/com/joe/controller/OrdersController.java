package com.joe.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joe.entity.Orders;
import com.joe.entity.User;
import com.joe.service.CartService;
import com.joe.service.OrdersService;
import com.joe.vo.OrdersVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
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
@Slf4j
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrdersService ordersService;

    /**
     * 跳轉到確認訂單頁面
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/check")
    public String checkCart(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("cartList",cartService.findAllCartVODetail(user.getId()));
        return "/cart/settlement";
    }

    /**
     * 結帳
     * @param totalAmount
     * @param session
     * @return
     */
    @GetMapping("/checkout/{totalAmount}")
    public String checkout(@PathVariable("totalAmount")Double totalAmount,  HttpSession session,Model model) {
        log.debug("總金額:{}",totalAmount);
        User user = (User) session.getAttribute("user");
        ordersService.checkout(user, totalAmount);
        return "redirect:/orders/complete";
    }

    /**
     * 結帳後跳轉到購買完成頁面
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/complete")
    public String complete(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        //查詢該用戶所有訂單
        QueryWrapper<Orders> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", user.getId());
        List<Orders> ordersList = ordersService.list(wrapper);
        //尺寸 = 最後一個
        int size = ordersList.size();
        Orders orders = ordersList.get(size-1); //索引從0開始
        model.addAttribute("orders",orders);
        return "/cart/complete";
    }

    /**
     * 獲取用戶全部的訂單
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/allUserOrders")
    public String UserOrders(HttpSession session,Model model) {
        User user = (User) session.getAttribute("user");
        List<OrdersVo> ordersList = ordersService.findAllUserOrdersVO(user.getId());
        model.addAttribute("ordersList",ordersList);
        return "/orders/userOrders";
    }

    /**
     * 獲取全部的訂單
     * @param model
     * @return
     */
    @GetMapping("/allOrders")
    public String allOrders(Model model) {
        List<OrdersVo> ordersList = ordersService.findAllOrders();
        model.addAttribute("ordersList",ordersList);
        return "/orders/managerOrders";
    }

    /**
     * 更改訂單狀態
     * @param ordersId
     * @param status
     * @return
     */
    @PostMapping("/updateStatus/{ordersId}")
    @ResponseBody
    public OrdersVo updateStatus(
            @PathVariable("ordersId")Integer ordersId,
            Integer status){
        log.debug("訂單{},狀態{}",ordersId,status);
        //修改數據庫中的訂單的狀態
        Orders orders = ordersService.getById(ordersId);
        orders.setStatus(status);
        ordersService.updateById(orders);
        //獲取修改狀態後的訂單VO
        OrdersVo ordersVO = ordersService.findOneOrdersVO(ordersId);
        return ordersVO;
    }

}

