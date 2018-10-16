package com.example.ui.service;

import net.sf.json.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@FeignClient(value = "case-service")
public interface CaseService{

    @RequestMapping(value = "/uploadcase", method = RequestMethod.POST)
    String uploadcase( JSONObject object) ;


    @RequestMapping(value = "/cases", method = RequestMethod.POST)
    String getallcases( @RequestParam("disease") String disease);

    @RequestMapping(value = "/doccases", method = RequestMethod.POST)
    String getdoccases( @RequestParam("email") String email);

    @RequestMapping(value = "/tobedone", method = RequestMethod.POST)
    String gettobedone(  );

    @RequestMapping(value = "/changestatus1", method = RequestMethod.POST)
    JSONObject setcasestatus1(@RequestParam("caseid") String caseid,@RequestParam("note") String note);

    @RequestMapping(value = "/changestatus2", method = RequestMethod.POST)
    JSONObject setcasestatus2(@RequestParam("caseid") String caseid,@RequestParam("note") String note);

    @RequestMapping(value = "/thecase", method = RequestMethod.POST)
    String getcaseinfo(@RequestBody String caseid);

    @RequestMapping(value = "/myupload", method = RequestMethod.POST)
    String getmyupload( JSONObject object) ;

    @RequestMapping(value = "/mysave", method = RequestMethod.POST)
    String getmysave( JSONObject object) ;

    @RequestMapping(value = "/mychecked", method = RequestMethod.POST)
    String getmychecked( JSONObject object) ;


    @RequestMapping(value = "/changestatus3", method = RequestMethod.POST)
    JSONObject setcasestatus3(@RequestParam("caseid") String caseid,@RequestParam("note") String note);


    @RequestMapping(value = "/saveandmodify", method = RequestMethod.POST)
    JSONObject saveandmodify(@RequestParam("caseid") String caseid,@RequestParam("lab_exam") String lab_exam,@RequestParam("extra_exam") String extra_exam,@RequestParam("present_history") String present_history,@RequestParam("according") String according,@RequestParam("differential") String differential);

    @RequestMapping(value = "/mymodify", method = RequestMethod.POST)
    String getmymodify( JSONObject object);

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    String search(@RequestParam("key") String key);
}
