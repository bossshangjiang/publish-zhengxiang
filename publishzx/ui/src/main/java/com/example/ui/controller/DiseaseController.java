//package com.example.ui.controller;
//
//import com.ctc.wstx.util.StringUtil;
//import com.example.ui.service.CaseService;
//import net.sf.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//import org.thymeleaf.util.StringUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//public class DiseaseController {
//
//    //登录成功跳转
//    //跳转时加掩码 来显示不同的大模块
//    @RequestMapping(value = "/home", method = RequestMethod.GET)
//    public String tohome() {
//        return "home";
//    }
//
//
//    //显示病种
//    // 八大系统下二级分类页面跳转  显示病种
//    //通过id作为跳转指示
//    @RequestMapping(value = "/second", method = RequestMethod.GET)
//    public String second(@RequestParam String id, ModelMap modelMap) {
//        System.out.println("second+" + id);
//        modelMap.put("id", id);//给二级传系统名
//        return "second";
//    }
//
//
//    //显示疾病名
//    //通过id 确定系统 通过{secondlevel}确定第二级病种  显示三级疾病名
//
//    @RequestMapping(value = "/third", method = RequestMethod.GET)
//    public String second(@RequestParam String id, @RequestParam String secondlevel, ModelMap modelMap) {
//        System.out.println("second+" + id + "+" + secondlevel);
//        modelMap.put("id", id);//给三级传系统名
//        modelMap.put("secondlevel", secondlevel);//给三级传病种名
//
//        return "third";
//    }
//
//    //跳转病例列表页面
//    @RequestMapping(value = "/cases", method = RequestMethod.GET)
//    public String second(@RequestParam String id, @RequestParam String secondlevel, @RequestParam String disease, ModelMap modelMap) {
//        System.out.println("second+" + id + "+" + secondlevel + "+" + disease);
//
//        modelMap.put("id", id);//给列表传系统名
//        modelMap.put("secondlevel", secondlevel);//给列表传病种名
//        modelMap.put("disease", disease);//给列表传疾病名
//        return "cases";
//    }
//
//    //根据上传者查询
//    @RequestMapping(value = "/doccases", method = RequestMethod.GET)
//    public String second(HttpSession session,ModelMap modelMap) {
//      //  System.out.println("second+" + id + "+" + secondlevel + "+" + disease);
//
//        String email=(String)session.getAttribute("email");
//        modelMap.put("email", email);//给列表传系统""名
//        return "doccases";
//    }
//
//    //显示具体病例
//    @RequestMapping(value = "/thecase", method = RequestMethod.GET)
//    public String thecase(@RequestParam String id, @RequestParam String secondlevel, @RequestParam String disease, ModelMap modelMap) {
//        System.out.println("thecase+" + id + "+" + secondlevel + "+" + disease);
//
//        modelMap.put("id", id);//给病例传系统名
//        modelMap.put("secondlevel", secondlevel);//给病例传病种名
//        modelMap.put("disease", disease);//给病例传疾病名
//        return "thecase";
//    }
//
//
//    //尝试解析远程json
//    //搞定
//    @Autowired
//    CaseService caseService;
//
//    @RequestMapping(value = "/cases", method = RequestMethod.POST)
//    @ResponseBody
//    public String getcases( String disease) {
//        System.out.println("disease" + disease);
//        String result = caseService.getallcases(disease);
//        System.out.println("cases" + result);
//        return result;
//    }
//
//    //跳转病例列表页面
//    @RequestMapping(value = "/tobedone", method = RequestMethod.GET)
//    public String totobedone() {
//
//        return "tobedone";
//    }
//
//
//    //审核返回病例
//    @RequestMapping(value = "/tobedone", method = RequestMethod.POST)
//    @ResponseBody
//    public String tobedone(  ) {
//
//        String result = caseService.gettobedone();
//        System.out.println("cases" + result);
//        return result;
//    }
//
//
//    //跳转上传病例页面
//    @RequestMapping(value = "/uploadcase", method = RequestMethod.GET)
//    public String touploadcase(HttpSession session) {
//    if(session.getAttribute("type").equals("doc"))
//        return "uploadcase";
//    else{
//       return "uploadfail";
//    }
//    }
//
//    //上传病例数据
////    @RequestMapping(value = "/uploadcase", method = RequestMethod.POST)
////    @ResponseBody
////    public String uploadcase() {
////        return "success";
////    }
//
//
//
//    //上传病例数据
//    @RequestMapping(value = "/uploadcase", method = RequestMethod.POST)
//    @ResponseBody
//    public String uploadcase(HttpServletRequest request,HttpSession session) {
//
//
//
//            JSONObject newcase = new JSONObject();
//
//            newcase.put("upload_time", "0");
//            newcase.put("latest_update_time", "0");
//            newcase.put("upload_usr", "xjtu556@qq.com");
//
//
//            //年龄
//            String age = request.getParameter("age");
//            newcase.put("age", age);
//
//            //sex
//            String sex = request.getParameter("sex");
//            newcase.put("sex", sex);
//
//
//        newcase.put("status", "1");
//
//            //主诉
//            String chief = request.getParameter("chief");
//            newcase.put("chief", chief);
//
//            //现病史
//            String present_history = request.getParameter("present_history");
//            newcase.put("present_history", present_history);
//
//            //既往史、等等
//            String past_history = request.getParameter("past_history");
//            newcase.put("past_history", past_history);
//
//            //上传者
//       // String email = (String)session.getAttribute("email");
//       // newcase.put("email",email );
//
//            //files
//            String fileName = null;
//            String suffixName = null;
//            String filepath = null;
//            String fileabsolutepath = null;
//            //CT
//            JSONObject ctimgs = new JSONObject();
//            List<MultipartFile> ctfiles = ((MultipartHttpServletRequest) request).getFiles("inputctimgs");
//            if (ctfiles.size() != 0) {
//                for (int i = 0; i < ctfiles.size(); i++) {
//                    String description = request.getParameter("inputctdescription" + i);
//                    MultipartFile file = ctfiles.get(i);
//
//                    fileName = file.getOriginalFilename();
//                    suffixName = fileName.substring(fileName.lastIndexOf("."));
//
//                    fileName = fileName.substring(0, fileName.lastIndexOf("."));
//                    StringUtils.replace(fileName, ".", "_");
//                    filepath = "/img/IMGS/" + fileName;
//                    fileabsolutepath = "/home/xjtu556/IdeaProjects/publish/ui/src/main/resources/static/IMGS/" + fileName + suffixName;//自己设定的文件目录
//
//                    System.out.println(fileabsolutepath);
//                    if (!file.isEmpty()) {
//                        try {
//                            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(fileabsolutepath)));
//                            stream.write(file.getBytes());
//                            stream.close();
//                        } catch (Exception e) {
//                            return "Failed to Upload" + fileName + "=>" + e.getMessage();
//                        }
//                    } else {
//                        return "Failed to Upload " + fileName + "because the file was empty";
//                    }
//
//                    JSONObject imgspath = new JSONObject();
//                    imgspath.put("type", suffixName);
//                    imgspath.put("description", description);
//
//
//                    ctimgs.put(filepath, imgspath);
//
//
//                }
//                newcase.put("CT", ctimgs);
//
//                //CT_symptom
//                String CT_symptom = request.getParameter("CT_symptom");
//                newcase.put("CT_symptom", CT_symptom);
//            } else {
//
//                newcase.put("CT", "hh");
//                newcase.put("CT_symptom", "hhe");
//            }
//
//            //MRI
//            JSONObject mriimgs = new JSONObject();
//            List<MultipartFile> mrifiles = ((MultipartHttpServletRequest) request).getFiles("inputmriimgs");
//            if (mrifiles.size() <= 0) {
//                for (int i = 0; i < mrifiles.size(); i++) {
//                    String description = request.getParameter("inputmridescription" + i);
//                    MultipartFile file = mrifiles.get(i);
//                    fileName = file.getOriginalFilename();
////                suffixName = fileName.substring(fileName.lastIndexOf("."));
//                    filepath = "/Users/cc/Documents/projects/upload/" + fileName;//自己设定的文件目录
//                    if (!file.isEmpty()) {
//                        try {
//                            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
//                            stream.write(file.getBytes());
//                            stream.close();
//                            System.out.println("MRI upload");
//                        } catch (Exception e) {
//                            return "Failed to Upload" + fileName + "=>" + e.getMessage();
//                        }
//                    } else {
//                        return "Failed to Upload " + fileName + "because the file was empty";
//                    }
//                    mriimgs.put(filepath, description);
//
//                }
//                newcase.put("MRI", mriimgs);
//                //MRI_symptom
//                String MRI_symptom = request.getParameter("MRI_symptom");
//                newcase.put("MRI_symptom", MRI_symptom);
//            } else {
//                newcase.put("MRI", "hh");
//                newcase.put("MRI_symptom", "hhe");
//
//            }
//            //实验室检查
//            String lab_exam = request.getParameter("lab_exam");
//            newcase.put("lab_exam", lab_exam);
//
//            //专科和其他检查
//            String extra_exam = request.getParameter("extra_exam");
//            newcase.put("extra_exam", extra_exam);
//
//            //收藏数量
//            newcase.put("collectionnumber", "0");
//
//            //扩展阅读
//            String expand_read = request.getParameter("expand_read");
//            newcase.put("expand_read", expand_read);
//
//            newcase.put("position", "tou");
//            newcase.put("disease", "眶内肌锥内外病变");
//            newcase.put("typical", "no");
//
//
//            System.out.println(newcase);
//            String result = caseService.uploadcase(newcase);
//            System.out.println(result);
//            return "Upload Success!";
//        }
//
//
//
//}