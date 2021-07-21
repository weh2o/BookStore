package com.joe.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joe.entity.Book;
import com.joe.entity.BookType;
import com.joe.service.BookService;
import com.joe.service.BookTypeService;
import com.joe.vo.BookTypeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/bookType")
@Slf4j
public class BookTypeController {

    @Autowired
    private BookTypeService bookTypeService;

    @Autowired
    private BookService bookService;

    /**
     * 展示所有圖書類型資料
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String typeList(Model model) {
        List<BookTypeVo> bookTypeVo = bookTypeService.getAllBookTypeVo();
        model.addAttribute("typeList",bookTypeVo);
        return "/manager/managerBookType";
    }

    /**
     * 新增圖書類型
     * @param bookType 圖書類型名稱
     * @return
     */
    @PostMapping("/add")
    public String addBookType(String bookType,Model model){
        log.debug("類型:{}",bookType);
        //判斷類型名稱是否重複
        QueryWrapper<BookType> wrapper = new QueryWrapper<>();
        wrapper.eq("book_type_name", bookType);
        BookType oldBookType = bookTypeService.getOne(wrapper);
        if (oldBookType != null) {
            model.addAttribute("failMsg","圖書類型 " + bookType +" 已經存在 !!");
            List<BookTypeVo> bookTypeVo = bookTypeService.getAllBookTypeVo();
            model.addAttribute("typeList",bookTypeVo);
            return "/manager/managerBookType";
        }
        //不存在，添加圖書類型
        BookType type = new BookType();
        type.setBookTypeName(bookType);
        bookTypeService.save(type);
        return "redirect:/bookType/list";
    }


    /**
     * 修改圖書類型資料
     * @param bookTypeId 被修改的圖書類型ID
     * @param newBookTypeName 新的圖書類型名稱
     * @param model 用於傳遞錯誤信息
     * @return
     */
    @PostMapping("/update")
    public String updateBookType(Integer bookTypeId,String newBookTypeName,Model model){
        log.debug("被修改:{},類型:{}",bookTypeId,newBookTypeName);

        //判斷類型名稱是否重複
        QueryWrapper<BookType> wrapper = new QueryWrapper<>();
        wrapper.eq("book_type_name", newBookTypeName);
        BookType oldBookType = bookTypeService.getOne(wrapper);
        if (oldBookType != null) {
            model.addAttribute("failMsg", "圖書類型 " + newBookTypeName + " 已經存在 !!");
            List<BookTypeVo> bookTypeVo = bookTypeService.getAllBookTypeVo();
            model.addAttribute("typeList", bookTypeVo);
            return "/manager/managerBookType";
        }
        //修改圖書類型資料
        BookType type = bookTypeService.getById(bookTypeId);
        type.setBookTypeName(newBookTypeName);
        bookTypeService.updateById(type);
        return "redirect:/bookType/list";
    }

    /**
     * 刪除圖書類型
     * @param id 圖書類型ID
     * @return
     */
    @GetMapping("/delete/{id}")
    public String deleteBookType(@PathVariable("id") Integer id) {
        //獲取該類型的所有圖書
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.eq("book_type_id", id);
        List<Book> bookList = bookService.list(wrapper);
        //將原有的圖書全部改為 "未分類"
        for (Book book : bookList) {
            book.setBookTypeId(1);
            bookService.updateById(book);
        }
        bookTypeService.removeById(id);
        return "redirect:/bookType/list";
    }
}

