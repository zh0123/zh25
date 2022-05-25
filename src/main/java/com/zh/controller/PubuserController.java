package com.zh.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zh.domain.PubUser;
import com.zh.domain.Task;
import com.zh.domain.TaskInfo;
import com.zh.domain.User;
import com.zh.service.PicService;
import com.zh.service.PubuserService;
import com.zh.service.TaskService;
import com.zh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class PubuserController {
    @Autowired
    private PubuserService pubuserService;
    @Autowired
    private UserService userService;
    @Autowired
    private PicService picService;
    @Autowired
    private TaskService taskService;


    @RequestMapping("/save")
    public String save(PubUser pubUser, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(pubUser.toString());
        PubUser pubUser1=pubuserService.queryOne(pubUser.getUsername(),pubUser.getEmail());
        if (pubUser1!=null){
            return "index";
        }
        if (pubUser.getUser_type().equals("任务发布者")) {
            pubuserService.savePubuser(pubUser);
        }else {
            User user=new User();
            user.setUsername(pubUser.getUsername());
            user.setPassword(pubUser.getPassword());
            user.setEmail(pubUser.getEmail());
            userService.saveUser(user);
        }
//        response.sendRedirect(request.getContextPath()+"/account/findAll");
        return "success";
    }
    @RequestMapping("/login")
    public String login(PubUser pubUser, Model model, HttpSession session) throws IOException {
        System.out.println(pubUser.toString());
        if (pubUser.getUser_type().equals("任务发布者")) {
            PubUser pubUser1 = pubuserService.query(pubUser.getUsername(), pubUser.getPassword());
            if (pubUser1 == null) {
                model.addAttribute("msg","用户名或密码错误");

                return "index";
            }
            session.setAttribute("lognName",pubUser.getUsername());

        return "project-launch";
        }else {
            User user=userService.query(pubUser.getUsername(),pubUser.getPassword());
            if (user== null) {
                model.addAttribute("msg","用户名或密码错误");
                return "index";
            }
            session.setAttribute("lognName",pubUser.getUsername());
            List<Task> list=taskService.queryAll();
            model.addAttribute("data",list);

            return "portal";
        }
//        response.sendRedirect(request.getContextPath()+"/account/findAll");

    }
    @RequestMapping("/query")
    public void query(PubUser pubUser, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("用户名是====" + pubUser.getUsername() + "交色是：" + pubUser.getUser_type() + "email=" + pubUser.getEmail());
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        //获取out对象
        PrintWriter out = response.getWriter();
        //调用service层判断用户名是否存在，这里我们就简单的判断一下
        //实例化JSON的单个对象
        JSONObject jsonObject = new JSONObject();
        if (pubUser.getUser_type().equals("任务发布者")) {
            PubUser pubUser1 = pubuserService.queryOne(pubUser.getUsername(), pubUser.getEmail());
//        System.out.println(pubUser1.toString());
//        System.out.println(pubUser1.toString());
            if (pubUser1 == null) {
                jsonObject.put("msg", 0);
            } else if (pubUser1.getUsername().equals(pubUser.getUsername())) {
                jsonObject.put("msg", 1);
            } else if (pubUser1.getEmail() != null) {
                jsonObject.put("msg", 2);
            }

            //将JSON数据返回到前端
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(jsonObject);
            out.print(jsonArray);
            out.close();
        }else{
            User user = userService.queryOne(pubUser.getUsername(), pubUser.getEmail());
//        System.out.println(pubUser1.toString());
            if (user == null) {
                jsonObject.put("msg", 0);
            } else if (user.getUsername().equals(pubUser.getUsername())) {
                jsonObject.put("msg", 1);
            } else if (user.getEmail() != null) {
                jsonObject.put("msg", 2);
            }

            //将JSON数据返回到前端
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(jsonObject);
            out.print(jsonArray);
            out.close();
        }
    }
}
