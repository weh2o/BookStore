package com.joe.service;

import com.joe.entity.BookType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joe.vo.BookTypeVo;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author joe
 * @since 2021-07-14
 */
public interface BookTypeService extends IService<BookType> {
    /**
     * 獲取全部圖書類型的資料
     * @return
     */
    List<BookTypeVo> getAllBookTypeVo();

}
