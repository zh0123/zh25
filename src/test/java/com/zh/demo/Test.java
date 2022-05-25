//package com.zh.demo;
//
//import com.alibaba.fastjson.JSONArray;
//import com.zh.domain.Task;
//import com.zh.service.TaskService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//public class Test {
//    @Autowired
//    private TaskService taskService;
//    public static void main(String[] args) {
//
//        //新建JSONArray对象储存我们要导出的信息
//        JSONArray array = new JSONArray();
//        List<Task> list=taskService.queryAll();
//        for (Task task:list){
//            System.out.println(task.toString());
//        }
//    }
//}
