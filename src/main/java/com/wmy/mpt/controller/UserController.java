package com.wmy.mpt.controller;

import com.wmy.mpt.model.User;
import com.wmy.mpt.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping("getUser")
    @ResponseBody
    public User userList(String id){
        User user = userService.selectById(id);
        return user;
    }

    @RequestMapping("/findall")
    @ResponseBody
    public List<User> allUser(){
        return userService.selectList(null);
    }
}
