package com.example.ui.controller;

import com.example.ui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {


    //调用user service
    @Autowired
    UserService userService;

    //index示意页面
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        modelMap.put("msg", "SpringBoot Ajax 示例");
        return "index";
    }

    //index 请求数据 返回所有用户
    @RequestMapping(value = "/data", method = RequestMethod.POST)
    @ResponseBody
    public String home() {
        return userService.getAllUser();
    }

    //出错跳转
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error(ModelMap modelMap) {
        modelMap.put("msg", "哎呀又出错了");
        return "404";
    }


}