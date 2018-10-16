package com.example.userservice.service;

import net.sf.json.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mongodb-service",url="202.117.15.224:8989")
public interface UserService {
//    @RequestMapping(value = "/postData", method = RequestMethod.POST)
//    String userLogin(@RequestParam("email") String email, @RequestParam("pwd") String pwd);


    @RequestMapping(value = "/log_verify/", method = RequestMethod.POST, consumes = "application/json")
    String userLogin(@RequestBody JSONObject object);

    @RequestMapping(value="/add_user/",method = RequestMethod.POST, consumes = "application/json")
    String usersignup(@RequestBody JSONObject object);

    @RequestMapping(value="/add_doctor/",method = RequestMethod.POST, consumes = "application/json")
    String docsignup(@RequestBody JSONObject object);

    //@RequestMapping(value = "/data", method = RequestMethod.POST)
    //List<User> getAllUser();
}