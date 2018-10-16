package com.example.symptomservice2.service;


import net.sf.json.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "mongodb-service",url="202.117.15.224:8989")
public interface SymptomService {
    //添加征象数据
    @RequestMapping(value = "/add_symptom/", method = RequestMethod.POST, consumes = "application/json")
    JSONObject addsymptom(@RequestBody JSONObject object);

    //获取征象列表
    @RequestMapping(value = "/get_symptom/", method = RequestMethod.POST, consumes = "application/json")
    JSONObject getsymptom(@RequestBody JSONObject object);

    //更新征象数据
    @RequestMapping(value = "/update_symptom/", method = RequestMethod.POST, consumes = "application/json")
    JSONObject updatesymptom(@RequestBody JSONObject object);

    //获取某一征象的具体信息
    @RequestMapping(value = "/get_symptom_content/", method = RequestMethod.POST, consumes = "application/json")
    JSONObject get_symptom_content(@RequestBody JSONObject object);

}
