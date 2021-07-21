package com.joe.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joe.entity.Book;
import com.joe.entity.Cart;
import com.joe.entity.User;
import com.joe.service.BookService;
import com.joe.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.sql.Wrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author joe
 * @since 2021-07-14
 */
@Controller
@RequestMapping("/cart")
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private BookService bookService;

    /**
     * 添加購物車
     * @param BookId 圖書ID
     * @param session
     * @return
     */
    @GetMapping("/add/{BookId}")
    @ResponseBody
    public Map<String,Object> addCart(@PathVariable("BookId") Integer BookId, HttpSession session){
        log.debug("添加的id:{}",BookId);
        //查看是否此商品已存在購物車內
        QueryWrapper wrapper = new QueryWrapper();
        User user = (User) session.getAttribute("user");
        wrapper.eq("user_id",user.getId());
        wrapper.eq("book_id",BookId );
        Cart oldCart = cartService.getOne(wrapper);

        Book book = bookService.getById(BookId);
        //不存在: 添加到購物車
        if (oldCart == null) {
            Cart cart = new Cart();
            cart.setUserId(user.getId());
            cart.setBookId(BookId);
            cart.setQuantity(1);
            cart.setPrice(book.getPrice());
            cart.setCost(book.getPrice());
            cartService.save(cart);
        }else {
            //已存在: 修改數量、總價
            oldCart.setQuantity(oldCart.getQuantity() + 1);
            oldCart.setCost(oldCart.getPrice() * oldCart.getQuantity());
            cartService.updateById(oldCart);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "將 " + book.getName() + " 添加到購物車");

        return map;
    }

    /**
     * 展示購物車內所有圖書
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/list")
    public String list(Model model,HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("cartList",cartService.findAllCartVODetail(user.getId()));
        return "/cart/cart";
    }

    /**
     * 從購物車中刪除一個圖書
     * @param cartId 購物車對象id
     * @return
     */
    @GetMapping("/delete/{cartId}")
    public String deleteCartOne(@PathVariable("cartId") Integer cartId){
        log.debug("要刪除的ID:{}",cartId);
        cartService.removeById(cartId);
        return "redirect:/cart/list";
    }

    /**
     * 清空購物車
     * @param session
     * @return
     */
    @GetMapping("/deleteAll")
    public String deleteCartAll(HttpSession session) {
        QueryWrapper wrapper = new QueryWrapper();
        User user = (User) session.getAttribute("user");
        wrapper.eq("user_id", user.getId());
        cartService.remove(wrapper);
        return "redirect:/cart/list";
    }

    /**
     * 修改購物車內圖書數量
     * @param cartId
     * @param count
     * @return
     */
    @PostMapping("/updateCount/{cartId}/{count}")
    @ResponseBody
    public String updateCount(
            @PathVariable("cartId") Integer cartId,
            @PathVariable("count") Integer count) {
        Cart cart = cartService.getById(cartId);
        cart.setQuantity(count);
        cart.setCost(cart.getPrice() * cart.getQuantity());
        if (cartService.updateById(cart)) {
            return "success";
        }
        return "fail";
    }
}

