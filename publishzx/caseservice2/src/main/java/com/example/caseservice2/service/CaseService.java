package com.example.caseservice2.service;

import net.sf.json.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "mongodb-service",url="202.117.15。224:8989")
public interface CaseService {
    @RequestMapping(value = "/add_case/", method = RequestMethod.POST, consumes = "application/json")
    JSONObject addcase(@RequestBody JSONObject object);

    @RequestMapping(value = "/get_case/", method = RequestMethod.POST, consumes = "application/json")
    JSONObject getcase(@RequestBody JSONObject object);

    @RequestMapping(value = "/get_upload/", method = RequestMethod.POST, consumes = "application/json")
    JSONObject getupload(@RequestBody JSONObject object);


    @RequestMapping(value = "/set_status/", method = RequestMethod.POST, consumes = "application/json")
    JSONObject set_status(@RequestBody JSONObject object);

    @RequestMapping(value = "/get_case_content/", method = RequestMethod.POST, consumes = "application/json")
    JSONObject getcaseinfo(@RequestBody JSONObject object);

    @RequestMapping(value = "/get_disease/", method = RequestMethod.POST, consumes = "application/json")
    JSONObject getdisease(@RequestBody JSONObject object);

    @RequestMapping(value = "/update_case/", method = RequestMethod.POST, consumes = "application/json")
    JSONObject update_case(@RequestBody JSONObject object);

    @RequestMapping(value = "/get_symptom_content/", method = RequestMethod.POST, consumes = "application/json")
    JSONObject getsymptominfo(@RequestBody JSONObject object);

    @RequestMapping(value = "/publish/_search?pretty&from=0&size=10", method = RequestMethod.POST, consumes = "application/json")
    JSONObject search(@RequestBody JSONObject object);


}


