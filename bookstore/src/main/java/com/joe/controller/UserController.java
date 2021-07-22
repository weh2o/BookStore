package com.joe.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joe.entity.User;
import com.joe.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author joe
 * @since 2021-07-14
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 註冊
     * @param user
     * @return
     */
    @PostMapping("/register")
    public String register(User user,Model model){
        try {
            userService.save(user);
        } catch (Exception exception) {
            exception.printStackTrace();
            model.addAttribute("failMsg","帳號已存在!!");
            return "/user/register";
        }
        return "redirect:/login";
    }

    /**
     * 登入
     * @param userAccount
     * @param password
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String login(String userAccount, String password, Model model, HttpSession session){

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_account", userAccount);
        wrapper.eq("password", password);
        User user = userService.getOne(wrapper);
        if (user == null) {
            model.addAttribute("msg","帳號或密碼錯誤");
            return "/user/login";
        }
        session.setAttribute("user", user);
        return "redirect:/book/list/1";
    }

    /**
     * 登出
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/book/list/1";
    }

    /**
     * 在會員中心編輯個人資料
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/update")
    public String updateInfo(User user,HttpSession session){
        log.debug("姓名:{},信箱:{},電話:{},地址:{}",user.getUserName(),user.getEmail(),user.getPhone(),user.getAddress());
        User oldUser = (User) session.getAttribute("user");
        //修改
        oldUser.setUserName(user.getUserName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPhone(user.getPhone());
        oldUser.setAddress(user.getAddress());
        userService.updateById(oldUser);
        return "redirect:/userInfo";
    }

    /**
     * 修改密碼
     * @param oldPassword 原有密碼
     * @param newPassword 新密碼
     * @param newPasswordCheck 第二次輸入的新密碼
     * @param session
     * @param model
     * @return
     */
    @PostMapping("/updatePassword")
    public String updatePassword(String oldPassword,String newPassword,String newPasswordCheck,HttpSession session,Model model) {
        log.debug("舊密碼:{},新密碼1:{},新密碼2:{}",oldPassword,newPassword,newPasswordCheck);
        User user = (User) session.getAttribute("user");
        String password = user.getPassword();
        //對比密碼是否輸入正確
        if (!password.equalsIgnoreCase(oldPassword)){
            model.addAttribute("failMsg","密碼輸入錯誤 !");
            return "/user/updatePassword";
        }
        //對比兩次輸入的新密碼是否一致
        if (!newPassword.equalsIgnoreCase(newPasswordCheck)){
            model.addAttribute("failMsg","兩次輸入的新密碼不同 !");
            return "/user/updatePassword";
        }
        user.setPassword(newPassword);
        userService.updateById(user);
        model.addAttribute("successMsg","密碼修改成功");
        return "/user/updatePassword";
    }

    /**
     * 在購物車編輯個人資料
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/updateToOrders")
    @ResponseBody
    public User updateInfoToOrders(User user,HttpSession session){
        log.debug("姓名:{},電話:{},地址:{}",user.getUserName(),user.getPhone(),user.getAddress());
        User oldUser = (User) session.getAttribute("user");
        //修改
        oldUser.setUserName(user.getUserName());
        oldUser.setPhone(user.getPhone());
        oldUser.setAddress(user.getAddress());
        userService.updateById(oldUser);
        return oldUser;
    }
}

