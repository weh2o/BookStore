package com.joe.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joe.entity.Book;
import com.joe.entity.BookType;
import com.joe.mapper.BookMapper;
import com.joe.mapper.BookTypeMapper;
import com.joe.service.BookTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joe.vo.BookTypeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
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
public class BookTypeServiceImpl extends ServiceImpl<BookTypeMapper, BookType> implements BookTypeService {

    @Autowired
    private BookTypeMapper bookTypeMapper;

    @Autowired
    private BookMapper bookMapper;
    @Override
    public List<BookTypeVo> getAllBookTypeVo() {
        ArrayList<BookTypeVo> voList = new ArrayList<>();
        List<BookType> bookTypeList = bookTypeMapper.selectList(null);
        //Vo賦值
        for (BookType bookType : bookTypeList) {
            BookTypeVo vo = new BookTypeVo();
            BeanUtils.copyProperties(bookType, vo);
            //查詢該類型的所有圖書
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("book_type_id", bookType.getBookTypeId());
            List<Book> bookList = bookMapper.selectList(wrapper);
            //圖書總數
            vo.setBookTotalCount(bookList.size());
            //圖書總銷量
            Integer totalSales = 0;
            for (Book book : bookList) {
                Integer sales = book.getSales();
                totalSales += sales;
            }
            vo.setBookTotalSales(totalSales);
            voList.add(vo);
        }
        return voList;
    }
}
