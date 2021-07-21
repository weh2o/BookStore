package com.joe.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joe.entity.Book;
import com.joe.entity.BookType;
import com.joe.service.BookService;
import com.joe.service.BookTypeService;
import com.joe.vo.BookVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@RequestMapping("/book")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookTypeService bookTypeService;
    //圖片存放路徑
    @Value("${photo.file.dir}")
    private String realpath;

    /**
     * 首頁展示所有數據
     * @param model
     * @return
     */
    @GetMapping("/list/{pageNum}")
    public String findAllBookToIndex(@PathVariable("pageNum") Integer pageNum,Model model){
        //類別
        List<BookType> typeList = bookTypeService.list();
        model.addAttribute("typeList",typeList);
        //圖書數據
        Page<Book> page = new Page<>(pageNum,4);
        Page bookPage = bookService.page(page, null);
        model.addAttribute("bookPage",bookPage);
        return "/index";
    }

    /**
     * 查詢分類的圖書(首頁)
     * @param id 圖書類別的id
     * @param model
     * @return
     */
    @GetMapping("/category/{id}")
    public String findCategoryBookIndex(
            @PathVariable("id") Integer id,
            Model model) {

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("book_type_id",id );
        Page<Book> page = new Page<>(1,4);
        Page bookPage = bookService.page(page, wrapper);
        model.addAttribute("bookPage",bookPage);

        //將分類選項傳給首頁
        List<BookType> typeList = bookTypeService.list();
        model.addAttribute("typeList",typeList);
        return "/index";
    }

    /**
     * 根據關鍵字查詢相關圖書
     * @param bookName 書名關鍵字
     * @param model
     * @return
     */
    @GetMapping("/search")
    public String searchBookName(String bookName,Model model){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.like("name", bookName);
        Page<Book> page = new Page<>(1,4);
        Page bookPage = bookService.page(page, wrapper);
        model.addAttribute("bookPage",bookPage);
        //將分類選項傳給首頁
        List<BookType> typeList = bookTypeService.list();
        model.addAttribute("typeList",typeList);
        return "/index";
    }

    /**
     * 後台展示所有數據
     * @param model
     * @return
     */
    @GetMapping("/back")
    public String findAllBookToBack(Model model){
        List<BookVo> bookVOList = bookService.findAllBookVo();
        model.addAttribute("bookList",bookVOList);
        return "/manager/back";
    }

    /**
     * 將圖書類型帶到添加頁面
     * @param model
     * @return
     */
    @GetMapping("/addlist")
    public String addList(Model model){
        List<BookType> bookTypes = bookTypeService.list();
        model.addAttribute("bookTypes",bookTypes);
        return "manager/addBook";
    }

    /**
     * 添加圖書
     * @param book
     * @param img
     * @return
     * @throws IOException
     */
    @PostMapping("/add")
    public String addBook(Book book, MultipartFile img) throws IOException {
        //日誌
        log.debug("書名:{},作者:{},類型:{},價格:{},庫存:{}",book.getName(),book.getAuthor(),book.getBookTypeId(),book.getPrice(),book.getStock());
        String originalFilename = img.getOriginalFilename();
        log.debug("圖片:{}",originalFilename);
        log.debug("存放路徑:{}",realpath);
        //處理圖片
        //修改文件名稱
        //後綴
        String fileNamePrefix = new SimpleDateFormat("yyyyMMddHHssSSS").format(new Date());
        //前綴
        String fileNameSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = fileNamePrefix + fileNameSuffix;
        //上傳文件
        img.transferTo(new File(realpath,newFileName));
        //處理圖書數據
        book.setPhoto(newFileName);
        bookService.save(book);
        return "redirect:/book/back";
    }
    /**
     * 刪除圖書
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Integer id){
        log.debug("前台的id:{}",id);
        //刪除資料庫中的頭像
        String photo = bookService.getById(id).getPhoto();
        if (photo != null) {
            File file = new File(realpath, photo);
            if (file.exists()) {
                file.delete();
            }
        }
        //刪除數據庫中的資料
        bookService.removeById(id);
        return "redirect:/book/back";
    }

    /**
     * 根據ID查詢圖書信息
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/detail/{id}")
    public String bookDetail(@PathVariable("id") Integer id,Model model) {
        log.debug("ID:{}",id);
        Book book = bookService.getById(id);
        model.addAttribute("book",book);
        //將圖書類型傳給前端
        List<BookType> typeList = bookTypeService.list();
        model.addAttribute("typeList",typeList);
        return "/manager/updateBook";
    }

    /**
     * 更改圖書的資料
     * @param book
     * @param img
     * @return
     * @throws IOException
     */
    @PostMapping("/update")
    public String updateBook(Book book,MultipartFile img) throws IOException {
        //日誌
        log.debug("更新的圖書: 書ID:{},書名:{},作者:{},類型:{},價格:{},庫存:{}",book.getId(),book.getName(),book.getAuthor(),book.getBookTypeId(),book.getPrice(),book.getStock());
        String originalFilename = img.getOriginalFilename();
        log.debug("新的圖片:{}",originalFilename);
        //判斷是否有上傳新圖片
        boolean newImg = !img.isEmpty();
        if (newImg) {
            //刪除原有的圖片
            String oldPhoto = bookService.getById(book.getId()).getPhoto();
            File oldFile = new File(realpath, oldPhoto);
            if (oldFile.exists()) {
                oldFile.delete();
            }
            //處理新上傳的圖片
            String fileNamePrefix = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
            String fileNameSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = fileNamePrefix + fileNameSuffix;
            img.transferTo(new File(realpath,newFileName));
            book.setPhoto(newFileName);
        }
        //更新其他圖書資料
        //保留銷量
        Book oldBook = bookService.getById(book.getId());
        book.setSales(oldBook.getSales());
        bookService.updateById(book);

        return "redirect:/book/back";
    }

    /**
     * 根據圖書類型查詢圖書(後台)
     * @param id 圖書類型ID
     * @param model
     * @return
     */
    @GetMapping("/category/{id}/backstage")
    public String findCategoryBookBackstage(
            @PathVariable("id") Integer id,
            Model model) {
        List<BookVo> bookList = bookService.findBookVoByBookTypeId(id);
        model.addAttribute("bookList",bookList);

        return "/manager/back";
    }
}

