package com.example.ui.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "user-service")
public interface UserService{

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String getUser(@RequestParam(value = "name") String name);


    @RequestMapping(value = "/data",method = RequestMethod.POST)
    @ResponseBody
    String getAllUser();

    @RequestMapping(value="/postData",method = RequestMethod.POST)
    String exist(@RequestParam("email") String email,@RequestParam("pwd") String pwd);

    @RequestMapping(value="/usersignup",method = RequestMethod.POST)
    String usersignup(@RequestParam("username") String username,@RequestParam("userpwd") String userpwd,@RequestParam("useremail") String useremail);

    @RequestMapping(value = "/docsignup", method = RequestMethod.POST)
    String docsignup(@RequestParam("docname") String docname,@RequestParam("docpwd") String docpwd,@RequestParam("docemail") String docemail,@RequestParam("dochospital") String dochospital,@RequestParam("filepath") String filepath);

   // @RequestParam("filepath") String filepath
}