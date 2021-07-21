package com.joe.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joe.entity.Book;
import com.joe.entity.Cart;
import com.joe.mapper.BookMapper;
import com.joe.mapper.CartMapper;
import com.joe.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joe.vo.CartVo;
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
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<CartVo> findAllCartVODetail(Integer userid) {
        ArrayList<CartVo> cartVoList = new ArrayList<>();
        //獲取用戶所有購物車對象
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id", userid);
        List<Cart> cartList = cartMapper.selectList(wrapper);
        //封裝
        for (Cart cart : cartList) {
            CartVo cartVo = new CartVo();
            //複製
            Book book = bookMapper.selectById(cart.getBookId());
            BeanUtils.copyProperties(book, cartVo);
            BeanUtils.copyProperties(cart, cartVo);
            cartVoList.add(cartVo);
        }
        return cartVoList;
    }
}
