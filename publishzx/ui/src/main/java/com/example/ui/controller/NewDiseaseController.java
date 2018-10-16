package com.example.ui.controller;

import com.example.ui.service.CaseService;
import com.example.ui.service.UserService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class NewDiseaseController {

    @Autowired
    CaseService caseService;

    //登录成功跳转
    //跳转时加掩码 来显示不同的大模块
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String tohome(HttpSession session, ModelMap modelMap) {
        if (session.getAttribute("type").equals("doc")) {
            modelMap.put("type", 1);
        } else modelMap.put("type", 0);

        return "home";
    }

    //跳转征象系统
    @RequestMapping(value = "/home_symptom", method = RequestMethod.GET)
    public String tosymptom(HttpSession session, ModelMap modelMap) {
        if (session.getAttribute("type").equals("doc")) {
            modelMap.put("type", 1);
        } else modelMap.put("type", 0);
        return "home_symptom";
    }

    //征象上传
    @RequestMapping(value = "/upload_symptom", method = RequestMethod.GET)
    public String uploadSymptom() {
        return "uploadsymptom";
    }

    //征象修改
    @RequestMapping(value = "/update_symptom", method = RequestMethod.GET)
    public String updateSymptom() {
        return "updatesymptom";
    }

    //通过id作为征象跳转指示
    @RequestMapping(value = "/second_sym", method = RequestMethod.GET)
    public String second_sym(@RequestParam String id, HttpSession session, ModelMap modelMap) {
        System.out.println("second+" + id);
        modelMap.put("id", id);
//        if (session.getAttribute("type").equals("doc")) {
//            modelMap.put("type", 1);
//        } else modelMap.put("type", 0);//给二级传系统名
        return "second_sym";
    }

    //显示征象信息
    @RequestMapping(value = "/symptom", method = RequestMethod.GET)
    public String symptom_sec(@RequestParam String id, @RequestParam String secondlevel, @RequestParam String symptom, HttpSession session, ModelMap modelMap) {
        System.out.println("second+" + id + "+" + secondlevel + "+" + symptom);

        modelMap.put("id", id);//给列表传系统名
        modelMap.put("secondlevel", secondlevel);//给列表传病种名
        modelMap.put("symptom", symptom);//
//        if (session.getAttribute("type").equals("doc")) {
//            modelMap.put("type", 1);
//        } else modelMap.put("type", 0);/// 给列表传疾病名
        return "symptom";
    }


    //显示病种
    // 八大系统下二级分类页面跳转  显示病种
    //通过id作为跳转指示
    @RequestMapping(value = "/second", method = RequestMethod.GET)
    public String second(@RequestParam String id, HttpSession session, ModelMap modelMap) {
        System.out.println("second+" + id);
        modelMap.put("id", id);
        if (session.getAttribute("type").equals("doc")) {
            modelMap.put("type", 1);
        } else modelMap.put("type", 0);//给二级传系统名
        return "second";
    }


    //显示疾病名
    //通过id 确定系统 通过{secondlevel}确定第二级病种  显示三级疾病名

    @RequestMapping(value = "/third", method = RequestMethod.GET)
    public String second(@RequestParam String id, @RequestParam String secondlevel, HttpSession session, ModelMap modelMap) {
        System.out.println("second+" + id + "+" + secondlevel);
        modelMap.put("id", id);//给三级传系统名
        modelMap.put("secondlevel", secondlevel);
        if (session.getAttribute("type").equals("doc")) {
            modelMap.put("type", 1);
        } else modelMap.put("type", 0);//给二//给三级传病种名

        return "third";
    }

    //显示病例列表
    @RequestMapping(value = "/cases", method = RequestMethod.GET)
    public String second(@RequestParam String id, @RequestParam String secondlevel, @RequestParam String disease, HttpSession session, ModelMap modelMap) {
        System.out.println("second+" + id + "+" + secondlevel + "+" + disease);

        modelMap.put("id", id);//给列表传系统名
        modelMap.put("secondlevel", secondlevel);//给列表传病种名
        modelMap.put("disease", disease);//
        if (session.getAttribute("type").equals("doc")) {
            modelMap.put("type", 1);
        } else modelMap.put("type", 0);/// 给列表传疾病名
        return "cases";
    }

    //显示具体病例
    @RequestMapping(value = "/thecase", method = RequestMethod.GET)
    public String thecase(@RequestParam String caseid, HttpSession session, ModelMap modelMap) {
        String thecase = caseService.getcaseinfo(caseid);
//        modelMap.put("id", id);//给病例传系统名
//        modelMap.put("secondlevel", secondlevel);//给病例传病种名
//        modelMap.put("disease", disease);//给病例传疾病名
        System.out.println(thecase + "thecase+++++++++++++++++++++++++++");
        JSONObject thecasea = JSONObject.fromObject(thecase);
        JSONArray data = thecasea.getJSONArray("CT");
        for (int i = 0; i < data.size(); i++) {
            String[] jsonimgs = new String[data.size()];
            JSONObject[] imgs = new JSONObject[data.size()];

            jsonimgs[i] = data.getString(i);
            imgs[i] = JSONObject.fromObject(jsonimgs[i]);
            modelMap.put("img" + i, imgs[i]);
            System.out.println("img                        " + imgs[i]);

        }
        modelMap.put("CTimg", data);

        modelMap.put("thecasea", thecasea);

        return "thecase";
    }


    @RequestMapping(value = "/cases", method = RequestMethod.POST)
    @ResponseBody
    public String getcases(String disease, String offset, String pagesize) {
        System.out.println("yanggang:disease" + disease);

        System.out.println("yanggang:offset" + offset);
        System.out.println("yanggang:pagesize" + pagesize);

        String result = caseService.getallcases(disease);
//        System.out.println("cases" + result);
        return result;
        //       return "aa";
    }


    //跳转我的上传病例页面
    @RequestMapping(value = "/myupload", method = RequestMethod.GET)
    public String tomyupload(HttpSession session, ModelMap modelMap) {
        if (session.getAttribute("type").equals("doc")) {
            modelMap.put("type", 1);
        } else modelMap.put("type", 0);/// 给列表
        return "myupload";
    }

    //返回我的上传病例
    @RequestMapping(value = "/myupload", method = RequestMethod.POST)
    @ResponseBody
    public String myupload(HttpSession session) {

        JSONObject newcase = new JSONObject();
        String email = (String) session.getAttribute("email");
        newcase.put("upload_usr", email);
        newcase.put("status", "1");
        newcase.put("offset", "0");
        newcase.put("length", "10");
        String result = caseService.getmyupload(newcase);
        return result;
    }

    //跳转我的保存病例页面
    @RequestMapping(value = "/mysave", method = RequestMethod.GET)
    public String tomysave(HttpSession session, ModelMap modelMap) {
        if (session.getAttribute("type").equals("doc")) {
            modelMap.put("type", 1);
        } else modelMap.put("type", 0);/// 给列表
        return "mysave";
    }

    //返回我的保存病例
    @RequestMapping(value = "/mysave", method = RequestMethod.POST)
    @ResponseBody
    public String mysave(HttpSession session) {

        JSONObject newcase = new JSONObject();
        String email = (String) session.getAttribute("email");
        newcase.put("upload_usr", email);
        newcase.put("status", "0");
        newcase.put("offset", "0");
        newcase.put("length", "10");
        String result = caseService.getmysave(newcase);
        System.out.println(result + "                                           result");
        return result;
    }

    //跳转我的审核病例页面
    @RequestMapping(value = "/mychecked", method = RequestMethod.GET)
    public String tomychecked(HttpSession session, ModelMap modelMap) {
        if (session.getAttribute("type").equals("doc")) {
            modelMap.put("type", 1);
        } else modelMap.put("type", 0);/// 给列表

        return "mychecked";
    }

    //返回我的审核病例
    @RequestMapping(value = "/mychecked", method = RequestMethod.POST)
    @ResponseBody
    public String mychecked(HttpSession session) {

        JSONObject newcase = new JSONObject();
        String email = (String) session.getAttribute("email");
        newcase.put("upload_usr", email);
        newcase.put("status", "2");
        newcase.put("offset", "0");
        newcase.put("length", "10");
        String result = caseService.getmychecked(newcase);
        return result;
    }

//    //跳转上传病例页面
//    @RequestMapping(value = "/uploadcase", method = RequestMethod.GET)
//    public String touploadcase() {
//        return "newupload";
//    }

    //跳转上传病例页面
    @RequestMapping(value = "/uploadcase", method = RequestMethod.GET)
    public String touploadcase(HttpSession session, ModelMap modelMap) {
        if (session.getAttribute("type").equals("doc")) {
            modelMap.put("type", 1);
        } else modelMap.put("type", 0);/// 给列表传疾病名
//    if(session.getAttribute("type").equals("doc"))
        return "newupload";
//    else{
//       return "timeout";
//    }
    }

    //上传病例数据
//    @RequestMapping(value = "/uploadcase", method = RequestMethod.POST)
//    @ResponseBody
//    public String uploadcase() {
//        return "success";
//    }

    //跳转审核页面
    //跳转审核页面
    @RequestMapping(value = "/tobedone", method = RequestMethod.GET)
    public String totobedone(HttpSession session, ModelMap modelMap) {
        if (session.getAttribute("type").equals("doc")) {
            modelMap.put("type", 1);
        } else modelMap.put("type", 0);/// 给列表传疾病名
        return "tobedone";
    }

    //审核返回病例
    @RequestMapping(value = "/tobedone", method = RequestMethod.POST)
    @ResponseBody
    public String tobedone() {
        String result = caseService.gettobedone();
        System.out.println("cases" + result);
        return result;
    }


    //显示具体审核病例
    @RequestMapping(value = "/thecheckcase", method = RequestMethod.GET)
    public String thecheckcase(@RequestParam String caseid, ModelMap modelMap) {
        String thecheckcase = caseService.getcaseinfo(caseid);
        System.out.println(thecheckcase + "thecheckcase+++++++++++++++++++++++++++");
        JSONObject thecheckcasea = JSONObject.fromObject(thecheckcase);
        modelMap.put("thecheckcasea", thecheckcasea);
        JSONArray data = thecheckcasea.getJSONArray("CT");
        for (int i = 0; i < data.size(); i++) {
            String[] jsonimgs = new String[data.size()];
            JSONObject[] imgs = new JSONObject[data.size()];

            jsonimgs[i] = data.getString(i);
            imgs[i] = JSONObject.fromObject(jsonimgs[i]);
            modelMap.put("img" + i, imgs[i]);
            System.out.println("img                        " + imgs[i]);

        }
        modelMap.put("CTimg", data);
        return "thecheckcase";
    }

    //审核通过
    @RequestMapping(value = "/changestatus1", method = RequestMethod.POST)
    @ResponseBody
    public String changestatus1(@RequestParam("caseid") String caseid, @RequestParam("note") String note) {
        System.out.println(caseid + "caseid--------------------------" + note);
        JSONObject back = caseService.setcasestatus1(caseid, note);
        String result = back.getString("result");
        return result;
    }

    //审核不通过
    @RequestMapping(value = "/changestatus2", method = RequestMethod.POST)
    @ResponseBody

    public String changestatus2(@RequestParam("caseid") String caseid, @RequestParam("note") String note) {
        System.out.println(caseid + "caseid--------------------------" + note);
        JSONObject back = caseService.setcasestatus2(caseid, note);
        String result = back.getString("result");
        System.out.println("result                        " + result);
        return result;
    }


    //上传病例数据
    @RequestMapping(value = "/uploadcase", method = RequestMethod.POST)
    public String uploadcase(HttpServletRequest request, HttpSession session, ModelMap modelMap) throws Exception {

        JSONObject newcase = new JSONObject();
        newcase.put("upload_time", "0");
        newcase.put("latest_update_time", "0");

        //上传者
        String email = (String) session.getAttribute("email");
        newcase.put("upload_usr", email);

        String status = request.getParameter("submit");
        if (status.equals("保存"))
            newcase.put("status", "0");
        else if (status.equals("提交审核"))
            newcase.put("status", "1");

        newcase.put("position", "tou");
        newcase.put("disease", "眶内肌锥内外病变");
        newcase.put("typical", "no");
//

        //年龄
        String age = request.getParameter("age");
        newcase.put("age", age);

        //sex
        String sex = request.getParameter("sex");
        newcase.put("sex", sex);

        //主诉
        String chief = request.getParameter("chief");
        newcase.put("chief", chief);

        //现病史
        String present_history = request.getParameter("present_history");
        newcase.put("present_history", present_history);

        //既往史、等等
        String past_history = request.getParameter("past_history");
        newcase.put("past_history", past_history);


        //CT_symptom
        String CT_symptom = request.getParameter("CT_symptom");
        newcase.put("CT_symptom", CT_symptom);

        //MRI_symptom
        String MRI_symptom = request.getParameter("MRI_symptom");
        newcase.put("MRI_symptom", MRI_symptom);

        String lab_exam = request.getParameter("lab_exam");
        newcase.put("lab_exam", lab_exam);

        //专科和其他检查
        String extra_exam = request.getParameter("extra_exam");
        newcase.put("extra_exam", extra_exam);

        //收藏数量
        newcase.put("collectionnumber", "0");

        //扩展阅读
        String expand_read = request.getParameter("expand_read");
        newcase.put("expand_read", expand_read);

        //影像描述
        String disc[] = request.getParameterValues("ds"); //同一个name对应多个值
        JSONArray discarray = new JSONArray();

        if (null != disc && disc.length > 0) {
            for (int i = 0; i < disc.length; i++) {
                discarray.add(i, disc[i]);
            }

            newcase.put("CT", discarray);
        } else {
            newcase.put("CT", "");


//        try {
//
//            String disc[] = request.getParameterValues("ds"); //同一个name对应多个值
//            for (int i = 0; i < disc.length; i++) {
//                JSONArray dsobj = JSONArray.fromObject(disc[i]);
//                newcase.accumulate("CT", dsobj);
//            }
//        }catch(Exception e){newcase.put("CT","");}

            newcase.put("CT_symptom", "hhe");
        }
        newcase.put("MRI", "hh");

        System.out.println(newcase);
        String result = caseService.uploadcase(newcase);
        System.out.println(result);
//        System.out.println(result);
//        JSONObject back = new JSONObject();
//        back.put("data","success");
        if (result.toString().equals("success")) {
            if (session.getAttribute("type").equals("doc")) {
                modelMap.put("type", 1);
            } else modelMap.put("type", 0);

            return "home";
        } else return "newupload";
//        return result.toString();
    }

    //上传病例图像
    @RequestMapping(value = "/uploadtheimg", method = RequestMethod.POST)
    @ResponseBody
    public String uploadtheimg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject imgs = new JSONObject();
        imgs.put("ok", "success");
        String imgfilepath = null;
        String fileName = null;
        String suffixName = null;
        String filepath = null;
        //CT
        JSONObject ctimgs = new JSONObject();
        List<MultipartFile> ctfiles = ((MultipartHttpServletRequest) request).getFiles("inputctimgs");
        if (ctfiles.size() != 0) {
            MultipartFile file = ctfiles.get(0);
            fileName = file.getOriginalFilename();
            System.out.println(fileName);

            suffixName = fileName.substring(fileName.lastIndexOf(".") + 1);
            filepath = "/home/xjtu556/upload_imgs/images/" + fileName;//自己设定的文件目录
            //fileName = fileName.substring(0, fileName.lastIndexOf("."));
//          StrfileNameingUtils.replace(fileName, ".", "_");
            imgfilepath = "/images/" + fileName;
            if (!file.isEmpty()) {
                try {
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
                    stream.write(file.getBytes());
                    stream.close();
                } catch (Exception e) {
                    return "Failed to Uploadct" + fileName + "=>" + e.getMessage();
                }
            } else {
                return "Failed to Uploadct " + fileName + "because the file was empty";
            }
            imgs.put("filepath", imgfilepath);
            imgs.put("suffixname", suffixName);
            imgs.put("filename", fileName);
        } else {

            imgs.put("CT", "hh");
        }


        //MRI
        List<MultipartFile> mrifiles = ((MultipartHttpServletRequest) request).getFiles("inputmriimgs");
        if (mrifiles.size() != 0) {
            MultipartFile file = mrifiles.get(0);
            fileName = file.getOriginalFilename();
            suffixName = fileName.substring(fileName.lastIndexOf("."));


            filepath = "/home/xjtu556/upload_imgs/images/" + fileName;//自己设定的文件目录
            imgfilepath = "/images/" + fileName;

            if (!file.isEmpty()) {
                try {
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
                    stream.write(file.getBytes());
                    stream.close();
                } catch (Exception e) {
                    return "Failed to UploadMRI" + fileName + "=>" + e.getMessage();
                }
            } else {
                return "Failed to UploadMRI " + fileName + "because the file was empty";
            }
            imgs.put("filepath", imgfilepath);
            imgs.put("suffixname", suffixName);
            imgs.put("filename", fileName);


        } else {
            imgs.put("MRI", "hh");

        }
        System.out.println(imgs);

        return imgs.toString();

    }


    //修改具体病例
    @RequestMapping(value = "/themodifycase", method = RequestMethod.GET)
    public String themodifycase(@RequestParam String caseid, HttpSession session, ModelMap modelMap) {
        String thecase = caseService.getcaseinfo(caseid);
//        modelMap.put("id", id);//给病例传系统名
//        modelMap.put("secondlevel", secondlevel);//给病例传病种名
//        modelMap.put("disease", disease);//给病例传疾病名
        System.out.println(thecase + "thecase+++++++++++++++++++++++++++");
        JSONObject thecasea = JSONObject.fromObject(thecase);
        JSONArray data = thecasea.getJSONArray("CT");
        for (int i = 0; i < data.size(); i++) {
            String[] jsonimgs = new String[data.size()];
            JSONObject[] imgs = new JSONObject[data.size()];

            jsonimgs[i] = data.getString(i);
            imgs[i] = JSONObject.fromObject(jsonimgs[i]);
            modelMap.put("img" + i, imgs[i]);
            System.out.println("img                        " + imgs[i]);

        }
        modelMap.put("CTimg", data);

        modelMap.put("thecase", thecasea);

        return "themodifycase";
    }

    //专家修改病例
    @RequestMapping(value = "/thecheckcasemodify")
    public String thecheckcasemodify(HttpSession session, ModelMap modelMap) {
        if (session.getAttribute("type").equals("doc")) {
            modelMap.put("type", 1);
        } else modelMap.put("type", 0);/// 给列表
        return "thecheckcasemodify";
    }

    //退修页面
    @RequestMapping(value = "/mymodify", method = RequestMethod.GET)
    public String tomymodify(HttpSession session, ModelMap modelMap) {
        if (session.getAttribute("type").equals("doc")) {
            modelMap.put("type", 1);
        } else modelMap.put("type", 0);/// 给列表
        return "mymodify";
    }

    //退修
    @RequestMapping(value = "/mymodify", method = RequestMethod.POST)
    @ResponseBody
    public String mymodify(HttpSession session) {

        JSONObject newcase = new JSONObject();
        String email = (String) session.getAttribute("email");
        newcase.put("upload_usr", email);
        newcase.put("status", "4");
        newcase.put("offset", "0");
        newcase.put("length", "10");
        System.out.println(newcase + "ooooooooooooooooooo");
        String result = caseService.getmysave(newcase);
        //System.out.println(result+" pppppppppppppppppppppppppppppresult");
        return result;
    }


    //用户退修
    @RequestMapping(value = "/changestatus3", method = RequestMethod.POST)
    @ResponseBody

    public String changestatus3(@RequestParam("caseid") String caseid, @RequestParam("note") String note) {
        System.out.println(caseid + "caseid--------------------------" + note);
        JSONObject back = caseService.setcasestatus3(caseid, note);
        String result = back.getString("result");
        System.out.println("result                        " + result);
        return result;
    }

    //专家自行修改
    @RequestMapping(value = "/thecheckcasemodify", method = RequestMethod.GET)
    public String tocheckcasemodify(HttpSession session, ModelMap modelMap) {
        if (session.getAttribute("type").equals("doc")) {
            modelMap.put("type", 1);
        } else modelMap.put("type", 0);/// 给列表传疾病名
        return "thecheckcasemodify";
    }

    //专家自行修改
    @RequestMapping(value = "/saveandmodify", method = RequestMethod.POST)
    @ResponseBody

    public String saveandmodify(@RequestParam("caseid") String caseid, @RequestParam("lab_exam") String lab_exam, @RequestParam("extra_exam") String extra_exam, @RequestParam("present_history") String present_history
            , @RequestParam("according") String according, @RequestParam("differential") String differential) {
        //System.out.println(caseid+"caseid--------------------------"+note);
        JSONObject back = caseService.saveandmodify(caseid, lab_exam, extra_exam, present_history, according, differential);
        String result = back.getString("result");
        System.out.println("result                        " + result);
        return result;
    }


    //搜索
    @RequestMapping(value = "/tosearch", method = RequestMethod.GET)
    public String tosearch(@RequestParam String key, ModelMap modelMap) {
        modelMap.put("key", key);
        return "search";
    }

    //搜索结果
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public String search(@RequestParam("key") String key) {
        String result = caseService.search(key);
        System.out.println(result);
        return result;
    }
}