package com.zh.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.apache.commons.lang.StringEscapeUtils;
import org.json.JSONException;
import org.json.JSONObject;
import com.zh.config.OSSProperties;
import com.zh.domain.Picture;
import com.zh.domain.Project;
import com.zh.domain.Task;
import com.zh.domain.UserTask;
import com.zh.service.PicService;
import com.zh.service.TaskService;
import com.zh.util.CrowdUtil;
import com.zh.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.json.JSONArray;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PicController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private PicService picService;
    @Autowired
    private OSSProperties ossProperties;
    @RequestMapping("/show")
    public String show(Integer tid, Model model, String username, String taskname) {
        List<Picture> list = picService.queryAll(tid);
        System.out.println("tid=" + tid + "username=" + username + taskname);
        List<UserTask> l = taskService.find(username, taskname);
        //往usertask追加数据
        for (Picture picture : list) {
            UserTask u = taskService.findTag(picture.getPath());
            if (u == null) {
                taskService.insertTask(username, taskname, picture.getPath());
            }
        }
        List<UserTask> list1 = taskService.find(username, taskname);
//        PageInfo<UserTask> pageInfo=  taskService.find(username,taskname);
        model.addAttribute("taskList", list1);
        model.addAttribute("taskname", taskname);
//        model.addAttribute("pageInfo",pageInfo);
        System.out.println(list);
        return "portal22";
    }
    @RequestMapping("/updateTag")
    public String update(String path, String tag, String username, String taskname, Model model) {
        System.out.println("path=" + path + "  tag=" + tag);
        taskService.updateTag(tag, path);
        List<UserTask> list1 = taskService.find(username, taskname);
        model.addAttribute("taskList", list1);
        model.addAttribute("taskname", taskname);
        return "portal22";
    }
    @RequestMapping("/download")
    public String download(String username, String taskname, Model model) throws IOException, JSONException {
        List<UserTask> list = taskService.find(username, taskname);
//        String path1="E:\\upload\\test\\";
        String path1 = "C:\\" + taskname + "\\";
//        String path1=getClass().getResource(".").getFile().toString()+"\\img\\";;
        for (UserTask task : list) {
            String path = task.getPath();
            String[] s = path.split("/");
            String f = s[s.length - 1];
            String filename = f.substring(0, f.lastIndexOf("."));
            String fullPath = path1 + filename + ".json";
            File file1 = new File(path1);
            {
                if (!file1.exists()) {
                    file1.mkdirs();
                }
            }
            File file = new File(fullPath);
            if (!file.exists()) {
                file.createNewFile();
            }
//            for (int i=0;i<6;i++) {
//                if (i==0) {
            JSONObject jsonObj = new JSONObject();
            JSONArray array = new JSONArray();
            Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            String tag = task.getTag();
            if (task.getTag() == null) {
                tag = "未标注";
            }
            Gson gson = new Gson();
            String download = task.getPath();
            jsonObj.put("info", "info");
            jsonObj.put("licenses", "licenses");
            jsonObj.put("images", "images");
            jsonObj.put("annotations", "annotations");
            jsonObj.put("categories", "categories");
            array.put(jsonObj);
            String tmp = StringEscapeUtils.unescapeJavaScript(String.valueOf(array));
            write.write(tmp);
            write.write("\n");
            JSONObject jsonObj1 = new JSONObject();
            JSONArray array1 = new JSONArray();
            write.write("info:");
            jsonObj1.put("description", task.getUsername() + "创建的数据集");
            jsonObj1.put("url", download);
            jsonObj1.put("version", "1.0");
            jsonObj1.put("year", "2021");
            jsonObj1.put("contribuitor", task.getUsername());
            jsonObj1.put("contribuitor", task.getUsername());
            array1.put(jsonObj1);
            String tmp1 = StringEscapeUtils.unescapeJavaScript(String.valueOf(array1));
            write.write(tmp1);
            write.write(",");
            write.write("\n");
            JSONObject jsonObj2 = new JSONObject();
            JSONArray array2 = new JSONArray();
            write.write("license:");
            jsonObj2.put("url", download);
            jsonObj2.put("id", task.getId());
            jsonObj2.put("name", filename);
            array2.put(jsonObj2);
            String tmp2 = StringEscapeUtils.unescapeJavaScript(String.valueOf(array2));
            write.write(tmp2);
            write.write(",");
            write.write("\n");
            JSONObject jsonObj3 = new JSONObject();
            JSONArray array3 = new JSONArray();
            write.write("images:");
            jsonObj3.put("license", "4");
            jsonObj3.put("file_name", filename + ".jpg");
            jsonObj3.put("width", "600");
            jsonObj3.put("height", "600");
            jsonObj3.put("date_captured", "2021");
            jsonObj3.put("url", download);
            jsonObj3.put("flickr_url", download);
            jsonObj3.put("id", task.getId());
            array3.put(jsonObj3);
            String tmp3 = StringEscapeUtils.unescapeJavaScript(String.valueOf(array3));
            write.write(tmp3);
            write.write(",");
            write.write("\n");
            JSONObject jsonObj4 = new JSONObject();
            JSONArray array4 = new JSONArray();
            write.write("annotations:");
            jsonObj4.put("license", "4");
            jsonObj4.put("area", "1045.58655");
            jsonObj4.put("iscrowd", "0");
            jsonObj4.put("image_Id", "000");
            jsonObj4.put("bbox", "[2,22,45,65,652,52,3,5,1,5]");
            jsonObj4.put("category_id", "4");
            jsonObj4.put("id", task.getId());
            array4.put(jsonObj4);
            String tmp4 = StringEscapeUtils.unescapeJavaScript(String.valueOf(array4));
            write.write(tmp4);
            write.write(",");
            write.write("\n");
            JSONObject jsonObj5 = new JSONObject();
            JSONArray array5 = new JSONArray();
            write.write("categores:");
            jsonObj5.put("1", "花");
            jsonObj5.put("2", "鸟");
            jsonObj5.put("3", "鱼");
            jsonObj5.put("4", "虫");
            array5.put(jsonObj5);
            String tmp5 = StringEscapeUtils.unescapeJavaScript(String.valueOf(array5));
            write.write(tmp5);
            write.flush();
            write.close();
        }
        model.addAttribute("taskList", list);
        model.addAttribute("taskname", taskname);
        return "portal22";
    }
    @RequestMapping("/upload")
    public String upload(Project project,// 接收上传的详情图片
                         List<MultipartFile> detailPictureList,
                         // 用来将收集了一部分数据的ProjectVO对象存入Session域
                         HttpSession session,
                         // 用来在当前操作失败后返回上一个表单页面时携带提示消息
                         ModelMap modelMap) throws IOException {
        Task task = new Task();
        task.setMsg(project.getMsg());
        task.setTaskname(project.getTaskname());
        task.setPubname(project.getPubname());
        task.setPubphone(project.getPubphone());
        Task task1 = taskService.query(project.getTaskname());
        if (task1 == null) {
            taskService.saveTask(task);
        }
        task1 = taskService.query(project.getTaskname());
        // 二、上传详情图片
        // 1.创建一个用来存放详情图片路径的集合
        List<String> picturePathList = new ArrayList<String>();
        // 2.检查detailPictureList是否有效
        if (detailPictureList == null || detailPictureList.size() == 0) {
            modelMap.addAttribute("message", "上传图片为空");
            return "project-launch";
        }
          //3.遍历detailPictureList集合
        for (MultipartFile detailPicture : detailPictureList) {
            // 4.当前detailPicture是否为空
            if (detailPicture.isEmpty()) {
                // 5.检测到详情图片中单个文件为空也是回去显示错误消息
                modelMap.addAttribute("message", "上传图片为空");
                return "project-launch";
            }
            //6.执行上传
            ResultEntity<String> detailUploadResultEntity = CrowdUtil.uploadFileToOss(
                    ossProperties.getEndPoint(),
                    ossProperties.getAccessKeyId(),
                    ossProperties.getAccessKeySecret(),
                    detailPicture.getInputStream(),
                    ossProperties.getBucketName(),
                    ossProperties.getBucketDomain(),
                    detailPicture.getOriginalFilename());
            //7.检查上传结果
            String detailUploadResult = detailUploadResultEntity.getResult();
            if (ResultEntity.SUCCESS.equals(detailUploadResult)) {
                String detailPicturePath = detailUploadResultEntity.getData();
                // 8.收集刚刚上传的图片的访问路径
                picturePathList.add(detailPicturePath);
            } else {
                // 9.如果上传失败则返回到表单页面并显示错误消息
                modelMap.addAttribute("message", "上传文件失败");
                return "project-launch";
            }
        }
        picService.insertPathList(task1.getId(), picturePathList);
        modelMap.addAttribute("message", "上传文件成功");
        return "project-launch";

    }


}
