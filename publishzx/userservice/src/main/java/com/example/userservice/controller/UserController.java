package com.example.userservice.controller;

import com.example.userservice.service.UserService;
import net.sf.json.JSON;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class UserController{

    //用户登录
    @Autowired
    UserService userService;
    @RequestMapping(value = "/postData", method = RequestMethod.POST)
    public String userLogin(@RequestParam String email,@RequestParam String pwd) {
        String e=email;
        String p=pwd;
        System.out.println(e+" "+p);
        net.sf.json.JSONObject object=new net.sf.json.JSONObject();
        object.put("email",e);
        object.put("pwd",p);
       System.out.println(object);

       String result=userService.userLogin(object);
        if (result.equals("success")){
            System.out.println("UI login success");
        }
        else  if (result.equals("no such user")){
            System.out.println("UI login fail");
        }
        else
            System.out.println("UI login notexist");
        System.out.println(result);
        return result;
    }

    //普通用户注册
    @RequestMapping(value = "/usersignup", method = RequestMethod.POST)
    @ResponseBody
    public String usersignup(@RequestParam("username")String username,
                             @RequestParam("userpwd") String userpwd,@RequestParam("useremail") String useremail) {
        String e=useremail;
        String p=userpwd;
        String n=username;
        System.out.println(e+" "+p+" "+n);
        net.sf.json.JSONObject object=new net.sf.json.JSONObject();
        object.put("email",e);
        object.put("pwd",p);
        object.put("name",n);
        object.put("latest_login"," ");
        object.put("latest_log_ip"," ");
        object.put("create_time"," ");
        System.out.println(object);

        String result=userService.usersignup(object);
        System.out.println("singup"+username+userpwd+useremail);
        if(result.equals("fail")) {//注册失败
            System.out.println("UI signup fail");
            return "fail";
        }
        if(result.equals("success")){//注册成功
            System.out.println("UI signup success");
            return "success";
        }
        System.out.println("UI signup error");
        return "error";
    }

    @RequestMapping(value = "/docsignup", method = RequestMethod.POST)
    @ResponseBody
    public String docsignup(@RequestParam("docname")String docname,
                             @RequestParam("docpwd") String docpwd,@RequestParam("docemail") String docemail,
                             @RequestParam("dochospital") String dochospital,
                             @RequestParam("filepath") String filepath) {
        String e=docemail;
        String p=docpwd;
        String n=docname;
        String h=dochospital;
        String f=filepath;
        System.out.println(e+" "+p+" "+n+" "+h+" "+f);
        net.sf.json.JSONObject object=new net.sf.json.JSONObject();
        object.put("email",e);
        object.put("pwd",p);
        object.put("name",n);
        object.put("hospital",h);
        object.put("filepath",f);
        object.put("latest_login"," ");
        object.put("latest_log_ip"," ");
        object.put("create_time"," ");
        System.out.println(object);

        String result=userService.docsignup(object);
        //System.out.println("singup"+username+userpwd+useremail);
        if(result.equals("fail")) {//注册失败
            System.out.println("UI signup fail");
            return "fail";
        }
        if(result.equals("success")){//注册成功
            System.out.println("UI signup success");
            return "success";
        }
        System.out.println("UI signup error");
        return "error";
    }


}