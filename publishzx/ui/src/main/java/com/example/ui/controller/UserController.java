package com.example.ui.controller;

import com.example.ui.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.ctc.wstx.util.StringUtil;
import com.example.ui.service.CaseService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

public class UserController {

    //调用user service
    @Autowired
    UserService userService;

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    @ResponseBody
    public String getUser(@RequestParam String name) {
        return userService.getUser(name);
    }



    //登录页面 or 登录密码错误页面
    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }
    @RequestMapping(value = "/gongneng")
    public String gongneng() {
        return "gongneng";
    }
    @RequestMapping(value = "/lianxi")
    public String lianxi() {
        return "lianxi";
    }
    @RequestMapping(value = "/thesym")
    public String kancase() {
        return "thesym";
    }

    //登录验证
    @RequestMapping(value = "/postData", method = RequestMethod.POST)
    @ResponseBody
    public String postData(String email, String pwd,HttpSession session) {
        System.out.println("-----------ui---" + email + " " + pwd);

        String result = userService.exist(email, pwd);
        System.out.println(result);
        if (result.equals("incorrect password")) {
            System.out.println("UI fail");
            return "fail";
        }
        if (result.equals("user_success")) {
            session.setAttribute("type","user");
            System.out.println("UI success");
            return "success";
        }

        if (result.equals("doc_success")) {
            session.setAttribute("type","doc");
            session.setAttribute("email",email);
            System.out.println("UI success");
            return "success";
        }
        if (result.equals("notexist")) {
            System.out.println("UI notexist");
            return "notexist";
        }
        if (result.equals("server error")) {
            System.out.println("UI notexist");
            return "server e500";
        }
        System.out.println("UI error");
        return "error";
    }

//    //登录成功跳转
//    @RequestMapping(value = "/home", method = RequestMethod.GET)
//    public String tohome() {
//
//        return "home";
//    }

    //用户名不存在跳转注册
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup() {
        return "signup";
    }

    //内部服务器错误
    @RequestMapping(value = "/http_500", method = RequestMethod.GET)
    public String http_500() {
        return "500";
    }


    //找回密码页面跳转
    @RequestMapping(value = "/alterpwd", method = RequestMethod.GET)
    public String toalterpwd() {
        return "alterpwd";
    }

    //验证旧密码
//通过session 获得email 通过页面获得旧密码 和登录验证一样 失败则提示旧密码失败
    @RequestMapping(value = "/testpwd", method = RequestMethod.POST)
    @ResponseBody
    public String testpwd(HttpSession session,HttpServletRequest request,String pwd) {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa+++"+pwd+"aaaaaaaaaaaaa");
        String email = session.getAttribute("email").toString();
        //String pwd = request.getParameter("oldpwd");
        String result = userService.exist(email,pwd);
        if (result.equals("user_success")||result.equals("doc_success")) {
            return "success";
        }
        else
            return "fail";
    }

    //修改密码
//通过session 获得email 通过页面获得旧密码 和登录验证一样 成功则修改密码
    @RequestMapping(value = "/alterpwd", method = RequestMethod.POST)
    @ResponseBody
    public String alterpwd(HttpSession session,HttpServletRequest request,String oldpwd,String newpwd) {
        String email = session.getAttribute("email").toString();
        //String pwd = request.getParameter("oldpwd");
        String result = userService.exist(email,oldpwd);
        if (result.equals("user_success")||result.equals("doc_success")) {
            return "success";
            //修改密码
            //String newpwd = request.getParameter("newpwd");

            //String alterresult =  userService.alterpwd(email,newpwd);
            //if(alterresult.equals("success"))
            //return "success";
            //else
            //return "fail";
        }
        else
            return "fail";
    }



    //普通用户注册
    @RequestMapping(value = "/usersignup", method = RequestMethod.POST)
    public String usersignup(String username, String userpwd, String useremail,HttpServletRequest request) {
        System.out.println("UI已发送注册信息"+username+userpwd+useremail);

        String name = request.getParameter("username");
        String pwd = request.getParameter("userpwd");
        String email = request.getParameter("useremail");

        String result = userService.usersignup(name, pwd, email);

        //String result = userService.usersignup(username, userpwd, useremail);
        if (result.equals("fail")) {//注册失败
            System.out.println("UI signup fail");
            return "404";
        }
        if (result.equals("success")) {//注册成功
            System.out.println("UI signup success");
            return "login";
        }
        System.out.println("UI signup error");
        return "500";
    }

    //医生注册
    @RequestMapping(value = "/docsignup", method = RequestMethod.POST)
    public String docsignup( HttpServletRequest request) {
        String docname = request.getParameter("docname");
        String docpwd = request.getParameter("docpwd");
        String docemail = request.getParameter("docemail");
        String dochospital = request.getParameter("dochospital");

        //                    String description = request.getParameter("inputctdescription" + i);

        System.out.println("UIdoc已发送注册信息"+docname+docpwd+docemail+dochospital);
        String filepath="path";
        //上传资格认证文件
        String fileName=null;
        String suffixName=null;
        String fileabsolutepath=null;
        List<MultipartFile> ctfiles = ((MultipartHttpServletRequest) request).getFiles("exampleInputFile");

//        MultipartFile file  = request.getPart("exampleInputFile");
//        MultipartFile file = request.getParrameter("exampleInputFile");
                    MultipartFile file = ctfiles.get(0);

                    fileName = file.getOriginalFilename();
                    suffixName = fileName.substring(fileName.lastIndexOf("."));

                    fileName = fileName.substring(0, fileName.lastIndexOf("."));
                    StringUtils.replace(fileName, ".", "_");
                    filepath = "/img/IMGS/" + fileName;
                    fileabsolutepath = "/home/xjtu556/IdeaProjects/publish/ui/src/main/resources/static/IMGS/" + fileName + suffixName;//自己设定的文件目录

                    System.out.println(fileabsolutepath);
                    if (!file.isEmpty()) {
                        try {
                            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(fileabsolutepath)));
                            stream.write(file.getBytes());
                            stream.close();
                        } catch (Exception e) {
                            return "Failed to Upload" + fileName + "=>" + e.getMessage();
                        }
                    } else {
                        return "Failed to Upload " + fileName + "because the file was empty";
                    }



        System.out.println("UIdoc filepath"+filepath);
        System.out.println("UIdoc fileabsolutepath"+fileabsolutepath);

        String result = userService.docsignup(docname, docpwd, docemail,dochospital,filepath);
        if (result.equals("fail")) {//注册失败
            System.out.println("UI signup fail");
            return "404";
        }
        if (result.equals("success")) {//注册成功
            System.out.println("UI signup success");
            return "login";
        }
        System.out.println("UI signup error");
        return "500";
    }

    //登出
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("id");
        session.removeAttribute("type");
        session.removeAttribute("email");
        return "login";
    }


}