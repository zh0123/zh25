package com.zh.demo;


import com.zh.domain.Task;
import com.zh.domain.User;
import com.zh.domain.UserTask;
import com.zh.service.TaskService;
import com.zh.service.UserService;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    @Test
    public void contextLoads() {
        //新建JSONArray对象储存我们要导出的信息
        JSONArray array = new JSONArray();
        List<Task> list=taskService.queryAll();
        for (Task task:list){
            System.out.println(task.toString());
        }
    }
    @Test
    public void testPic(){
        String videoFramesPath = "E:\\upload\\test\\";
        //最后获取到的视频的图片的路径
        String videPicture="";
        String videoFileName = "http://zh210810.oss-cn-shenzhen.aliyuncs.com/20220103/d929b4b94841451682715d7b4622b1da.mp4";
        //Frame对象
        Frame frame = null;
        //标识
        int flag = 0;
        try {
            //获取视频文件
            FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(videoFileName);
            fFmpegFrameGrabber.start();

            //获取视频总帧数
            int ftp = fFmpegFrameGrabber.getLengthInFrames();
            System.out.println("时长 " + ftp / fFmpegFrameGrabber.getFrameRate() / 60);
            System.out.println("ftp==="+ftp);

            while (flag <= ftp) {
                frame = fFmpegFrameGrabber.grabImage();
                //对视频的第五帧进行处理
                if (frame != null) {
                    //文件绝对路径+名字
                    String fileName = videoFramesPath + UUID.randomUUID().toString()+"_" + String.valueOf(flag) + ".jpg";
                    //文件储存对象
                    File outPut = new File(fileName);
                    ImageIO.write( new Java2DFrameConverter().getBufferedImage(frame), "jpg", outPut);
                    //视频第五帧图的路径
                    String savedUrl = videoFramesPath + outPut.getName();
                    videPicture=savedUrl;
//                    break;
                }
                flag++;
            }
            fFmpegFrameGrabber.stop();
            fFmpegFrameGrabber.close();
        } catch (Exception E) {
            E.printStackTrace();
        }
    }




    @Test
    public void test() throws JSONException, IOException {
        //新建JSONArray对象储存我们要导出的信息

        List<UserTask> list=taskService.find("张三","任务二");

        String path1=getClass().getResource(".").getFile().toString();
//        String fullPath = path + File.separator + fileName + ".json";
//        String path1="E:\\upload\\test\\";
        for (UserTask task:list) {
            JSONObject jsonObj = new JSONObject();
            String path=task.getPath();
            String[] s = path.split("/");
            String f=s[s.length - 1];
            String filename = f.substring(0,f.lastIndexOf("."));
            String fullPath = path1  + filename + ".json";
            File file = new File(fullPath);
            if (!file.exists()){
                file.createNewFile();
            }
            JSONArray array = new JSONArray();
            Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            String tag=task.getTag();
            if (task.getTag()==null){
                tag="未标注";
            }
            jsonObj.put("tag",tag);
//            array.put(jsonObj);
//            write.write(String.valueOf(array));
            jsonObj.put("path", f);
            array.put(jsonObj);
            write.write(String.valueOf(array));
            write.flush();
            write.close();

        }



    }
    @Test
    public void test1(){
        String path="http://zh210810.oss-cn-shenzhen.aliyuncs.com/20220101/6e3befa64e034a4c85c7fe832078ca65.jpg";
        String[] s = path.split("/");
        System.out.println(s[s.length - 1]);
        String f=s[s.length - 1];
        String test = f.substring(0,f.lastIndexOf("."));
        System.out.println(test);
        String path1=getClass().getResource(".").getFile().toString();
        System.out.println("path1==="+path1);
    }


}
