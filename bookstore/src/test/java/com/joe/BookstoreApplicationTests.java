package com.joe;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joe.entity.Book;
import com.joe.entity.BookType;
import com.joe.entity.Orders;
import com.joe.service.BookService;
import com.joe.service.OrdersService;
import com.joe.utils.RandomOrderId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BookstoreApplicationTests {

    @Autowired
    private OrdersService ordersService;
    @Autowired
    private BookService bookService;

    @Test
    void contextLoads() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("book_type_id",2 );
        Page<Book> page = new Page<>(1,4);
        Page bookPage = bookService.page(page, wrapper);
        bookPage.getRecords().forEach(System.out::println);

    }

}
