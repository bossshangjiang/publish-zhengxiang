package com.example.symptomservice2.controller;


import com.example.symptomservice2.service.SymptomService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class symptomController {
    @Autowired
    SymptomService symptomService;

    //上传征象数据
    @RequestMapping(value = "/uploadsymptom", method = RequestMethod.POST)
    String uploadsymptom(@RequestBody JSONObject object){
        System.out.println(object);
        JSONObject object2=symptomService.addsymptom(object);
        String result= object2.getString("result");
        return result;
    }

    //获取征象列表
    @RequestMapping(value = "/getsymptom", method = RequestMethod.POST)
    String getsymptom(@RequestBody JSONObject object){
        System.out.println(object);
        JSONObject object2=symptomService.getsymptom(object);
        String result= object2.getString("result");
        return result;
    }

    //获取某一征象的具体信息
    @RequestMapping(value = "/getcontent", method = RequestMethod.POST)
    String getsympcontent(@RequestBody JSONObject object){
        System.out.println(object);
        JSONObject object2=symptomService.get_symptom_content(object);
        String result= object2.getString("result");
        return result;
    }

    //更新征象数据
    @RequestMapping(value = "/updatesymptom", method = RequestMethod.POST)
    String updatesymptom(@RequestBody JSONObject object){
        System.out.println(object);
        JSONObject object2=symptomService.updatesymptom(object);
        String result= object2.getString("result");
        return result;
    }
}
