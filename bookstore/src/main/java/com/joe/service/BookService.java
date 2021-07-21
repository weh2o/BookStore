package com.joe.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.joe.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joe.vo.BookVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author joe
 * @since 2021-07-14
 */
public interface BookService extends IService<Book> {
    /**
     * 查詢所有圖書的詳細資料
     * @return
     */
    List<BookVo> findAllBookVo();

    /**
     * 根據圖書類型ID獲得圖書Vo
     * @param bookTypeId
     * @return
     */
    List<BookVo> findBookVoByBookTypeId(Integer bookTypeId);



}
