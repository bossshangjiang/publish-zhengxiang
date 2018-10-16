package com.example.caseservice2.controller;

import com.example.caseservice2.service.CaseService;
import com.example.caseservice2.service.CaseService2;
import net.sf.json.JSONObject;
import net.sf.json.JSON;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class CaseController {
    @Autowired
    CaseService caseService;
    @Autowired
    CaseService2 caseService2;

    @RequestMapping(value = "/uploadcase", method = RequestMethod.POST)
    String uploadcase(@RequestBody JSONObject object){
        System.out.println(object);
        JSONObject object2=caseService.addcase(object);
        String result= object2.getString("result");
        return result;
    }


    //获取我上传的病例
    @RequestMapping(value = "/myupload", method = RequestMethod.POST)
    JSONObject getmyupload(@RequestBody JSONObject object) {
        System.out.println(object+"999999999999999999999999999");
        return caseService.getupload(object);}

    //获取我保存的病例
    @RequestMapping(value = "/mysave", method = RequestMethod.POST)
    JSONObject getmysave(@RequestBody JSONObject object) {
        System.out.println(caseService.getupload(object));
        return caseService.getupload(object);}

    //获取我已被审核的病例
    @RequestMapping(value = "/mychecked", method = RequestMethod.POST)
    JSONObject getmychecked(@RequestBody JSONObject object) {return caseService.getupload(object);}

    //退修
    @RequestMapping(value = "/mymodify", method = RequestMethod.POST)
    JSONObject getmymodify(@RequestBody JSONObject object) {
        System.out.println(object);
        System.out.println(caseService.getupload(object));
        return caseService.getupload(object);}

    //获取病例信息
    @RequestMapping(value = "/thecase", method = RequestMethod.POST)
    JSONObject getcaseinfo(@RequestBody String caseid){
        System.out.println(caseid+"caseid+++++++++++++++++++++++++++++=");

        JSONObject object=new JSONObject();
        object.put("objectId",caseid);
        System.out.println(object);
        System.out.println(caseService.getcaseinfo(object));
        return caseService.getcaseinfo(object);
    }


    //获取修改病例信息
    @RequestMapping(value = "/themodifycase", method = RequestMethod.POST)
    JSONObject getmodifycaseinfo(@RequestBody String caseid){
        System.out.println(caseid+"caseid+++++++++++++++++++++++++++++=");

        JSONObject object=new JSONObject();
        object.put("objectId",caseid);
        System.out.println(object);
        System.out.println(caseService.getcaseinfo(object));
        return caseService.getcaseinfo(object);
    }

    @RequestMapping(value = "/cases", method = RequestMethod.POST)
    JSONObject cases(@RequestParam("disease") String disease){
        JSONObject object=new JSONObject();
        object.put("disease",disease);
        object.put("order","asc");
        object.put("order_item","collectionnumber");
        object.put("offset","0");
        object.put("length","10");
        object.put("status","2");
        //System.out.println(object);
        System.out.println(caseService.getdisease(object));

        return caseService.getdisease(object);
    }

    @RequestMapping(value = "/tobedone", method = RequestMethod.POST)
    JSONObject gettobedone(  ){
        JSONObject object=new JSONObject();
        object.put("index","status");
        object.put("order","asc");
        object.put("order_item","collectionnumber");
        object.put("offset","0");
        object.put("length","10");
        object.put("index_val","1");
        System.out.println(object);
        System.out.println(caseService.getcase(object));

        return caseService.getcase(object);
    }

    //改为审核通过
    @RequestMapping(value = "/changestatus1", method = RequestMethod.POST)
    JSONObject setcasestatus1(@RequestParam("caseid") String caseid,@RequestParam("note") String note){
        System.out.println(caseid+"caseid+++++++++++++++++++++++++++++=");

        JSONObject object=new JSONObject();
        object.put("objectId",caseid);
        object.put("status","2");
        object.put("note",note);
        System.out.println(object);
        System.out.println(caseService.set_status(object));

        return caseService.set_status(object);
    }

    //改为审核不通过
    @RequestMapping(value = "/changestatus2", method = RequestMethod.POST)
    JSONObject setcasestatus2(@RequestParam("caseid") String caseid,@RequestParam("note") String note){
        System.out.println(caseid+"caseid+++++++++++++++++++++++++++++=");

        JSONObject object=new JSONObject();
        object.put("objectId",caseid);
        object.put("status","3");
        object.put("note",note);
        System.out.println(object);
        System.out.println(caseService.set_status(object));

        return caseService.set_status(object);
    }

    //退修
    @RequestMapping(value = "/changestatus3", method = RequestMethod.POST)
    JSONObject setcasestatus3(@RequestParam("caseid") String caseid,@RequestParam("note") String note){
        System.out.println(caseid+"caseid+++++++++++++++++++++++++++++=");

        JSONObject object=new JSONObject();
        object.put("objectId",caseid);
        object.put("status","4");
        object.put("note",note);
        System.out.println(object);
        System.out.println(caseService.set_status(object));

        return caseService.set_status(object);
    }

    //专家自行修改
    @RequestMapping(value = "/saveandmodify", method = RequestMethod.POST)
    JSONObject saveandmodify(@RequestParam("caseid") String caseid,@RequestParam("lab_exam") String lab_exam,@RequestParam("extra_exam") String extra_exam,@RequestParam("present_history") String present_history
            ,@RequestParam("according") String according,@RequestParam("differential") String differential){
        System.out.println(caseid+"caseid+++++++++++++++++++++++++++++=");

        JSONObject object=new JSONObject();
        object.put("objectId",caseid);
        object.put("lab_exam",lab_exam);
        object.put("extra_exam",extra_exam);
        object.put("present_history",present_history);
        object.put("update_time","2017-10-16");
        //object.put("according",according);
        //object.put("differential",differential);
        System.out.println(object);
        System.out.println(caseService.update_case(object));

        return caseService.update_case(object);
    }

    //查看征象详情
    @RequestMapping(value = "/thesymptom", method = RequestMethod.POST)
    JSONObject getsymptominfo(@RequestBody String symptomid){
        System.out.println(symptomid+"symptomid+++++++++++++++++++++++++++++=");

        JSONObject object=new JSONObject();
        object.put("objectId",symptomid);
        System.out.println(object);
        System.out.println(caseService.getsymptominfo(object));
        return caseService.getsymptominfo(object);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    JSONObject search( @RequestParam("key") String key){
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("fields");
        arrayList.add("extra_exam");
        arrayList.add("discription");
        arrayList.add("typical");
        arrayList.add("present_history");
        arrayList.add("MRI_symptom");
        arrayList.add("CT_symptom");
        arrayList.add("position");
        arrayList.add("chief");
        arrayList.add("sex");
        arrayList.add("CT-symptom");
        arrayList.add("lab_exam");
        JSONObject multi_matchobject=new JSONObject();
        multi_matchobject.put("query",key);
        multi_matchobject.put("fields",JSONArray.fromObject(arrayList));
        multi_matchobject.put("tie_breaker","0.3");
        JSONObject object1=new JSONObject();
        object1.put("multi_match",multi_matchobject);
        JSONObject object=new JSONObject();
        object.put("query",object1);
        object.put("from","0");
        object.put("size","10");
//        System.out.println(caseService.search(object));
        System.out.println(object);
        return caseService2.search(object);
    }



}
