package com.joe.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joe.entity.Book;
import com.joe.entity.BookType;
import com.joe.mapper.BookMapper;
import com.joe.mapper.BookTypeMapper;
import com.joe.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joe.vo.BookVo;
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
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    BookTypeMapper bookTypeMapper;

    @Override
    public List<BookVo> findAllBookVo() {
        List<BookVo> bookVOList = new ArrayList<>();
        List<Book> bookList = bookMapper.selectList(null);
        //封裝
        for (Book book : bookList) {
            BookVo bookVO = new BookVo();
            BeanUtils.copyProperties(book,bookVO);
            //根據圖書的類型id查找 圖書類型對象
            BookType bookType = bookTypeMapper.selectById(book.getBookTypeId());
            bookVO.setBookType(bookType.getBookTypeName());
            bookVOList.add(bookVO);
        }
        return bookVOList;
    }

    @Override
    public List<BookVo> findBookVoByBookTypeId(Integer bookTypeId) {
        List<BookVo> bookVOList = new ArrayList<>();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("book_type_id",bookTypeId);
        List<Book> bookList = bookMapper.selectList(wrapper);
        //封裝
        for (Book book : bookList) {
            BookVo bookVO = new BookVo();
            BeanUtils.copyProperties(book,bookVO);
            //根據圖書的類型id查找 圖書類型對象
            BookType bookType = bookTypeMapper.selectById(book.getBookTypeId());
            bookVO.setBookType(bookType.getBookTypeName());
            bookVOList.add(bookVO);
        }
        return bookVOList;
    }


}
