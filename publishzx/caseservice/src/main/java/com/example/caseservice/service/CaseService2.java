package com.example.caseservice.service;

import net.sf.json.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "mongodb-service",url="202.117.15.224:9200")
public interface CaseService2 {

    @RequestMapping(value = "/publish/_search/", method = RequestMethod.GET, consumes = "application/json")
    JSONObject search(@RequestBody JSONObject object);

}