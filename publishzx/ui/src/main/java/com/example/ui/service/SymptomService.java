package com.example.ui.service;


import net.sf.json.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "symptom-service")
public interface SymptomService {
    //上传征象数据
    @RequestMapping(value = "/uploadsymptom", method = RequestMethod.POST)
    String uploadsymptom( JSONObject object) ;

    //获取征象列表
    @RequestMapping(value = "/getsymptom", method = RequestMethod.POST)
    String getsymptom( JSONObject object) ;

    //获取某一征象的具体信息
    @RequestMapping(value = "/getcontent", method = RequestMethod.POST)
    String getcontent( JSONObject object) ;

    //更新征象数据
    @RequestMapping(value = "/updatesymptom", method = RequestMethod.POST)
    String updatesymptom( JSONObject object) ;
}
