package com.zh.controller;

import com.zh.config.OSSProperties;
import com.zh.domain.Video;
import com.zh.service.VideoService;
import com.zh.util.CrowdUtil;
import com.zh.util.ResultEntity;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class VideoController {
    @Autowired
    private VideoService videoService;
    @Autowired
    private OSSProperties ossProperties;
    @RequestMapping("pro")
    public String pro(){
        return "project-launch";
    }
    @RequestMapping("uploadv")
    public String upload(){
        return "project-video";
    }
    @RequestMapping("stream")
    public void getStreamData(HttpServletResponse response) {
//        System.out.println("path="+path);
//        File file=new File("C:\\Users\\79257\\Pictures\\1.mp4");
        File file=new File("http:\\zh210810.oss-cn-shenzhen.aliyuncs.com\\20220103\\ae8a7e9cbaf141d895d14edf7d7a9725.mp4");
        ServletOutputStream out=null;
        try {
            FileInputStream instream=new FileInputStream(file);
            byte[] b=new byte[1024];
            int length=0;
            BufferedInputStream buf=new BufferedInputStream(instream);
            out=response.getOutputStream();
            BufferedOutputStream bot=new BufferedOutputStream(out);
            while((length=buf.read(b))!=-1) {
                bot.write(b,0, b.length);
            }
        } catch (Exception  e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @RequestMapping("/uploadvideo")
    public String video(

            // 接收除了上传图片之外的其他普通数据
            String vpath,
            String pubname,
            // 接收上传的头图
            MultipartFile headerPicture,
            // 用来将收集了一部分数据的ProjectVO对象存入Session域
            HttpSession session,

            // 用来在当前操作失败后返回上一个表单页面时携带提示消息
            ModelMap modelMap, Model model
            ) throws IOException {

        // 一、完成头图上传
        // 1.获取当前headerPicture对象是否为空
        boolean headerPictureIsEmpty = headerPicture.isEmpty();

        if (headerPictureIsEmpty) {

            // 2.如果没有上传头图则返回到表单页面并显示错误消息
            modelMap.addAttribute("message", "上传为空");

            return "project-launch";

        }


        // 3.如果用户确实上传了有内容的文件，则执行上传
        ResultEntity<String> uploadHeaderPicResultEntity = CrowdUtil.uploadFileToOss(
                ossProperties.getEndPoint(),
                ossProperties.getAccessKeyId(),
                ossProperties.getAccessKeySecret(),
                headerPicture.getInputStream(),
                ossProperties.getBucketName(),
                ossProperties.getBucketDomain(),
                headerPicture.getOriginalFilename());

        String result = uploadHeaderPicResultEntity.getResult();

        // 4.判断头图是否上传成功
        if (ResultEntity.SUCCESS.equals(result)) {
            // 5.如果成功则从返回的数据中获取图片访问路径
            String headerPicturePath = uploadHeaderPicResultEntity.getData();
            Video video=videoService.query(headerPicturePath,pubname);
            if (video!=null){
                model.addAttribute("path",video.getVpath());
                modelMap.addAttribute("message", "上传成功");
                // 2.以完整的访问路径前往下一个收集回报信息的页面
                return "project-video";
            }
            videoService.insert(headerPicturePath,pubname);
            Video vi=videoService.query(headerPicturePath,pubname);
            model.addAttribute("path",vi.getVpath());
            System.out.println("path=="+vi.getVpath());
        } else {

            // 7.如果上传失败则返回到表单页面并显示错误消息
            modelMap.addAttribute("message", "上传失败");

            return "project-video";

        }



        modelMap.addAttribute("message", "上传成功");
        // 2.以完整的访问路径前往下一个收集回报信息的页面
        return "project-video";
    }

    @RequestMapping("/split")
    public String split(String path,Model model){
//        String videoFramesPath = "E:\\upload\\test\\";
        String downLoad= getClass().getResource(".").getFile().toString();
        String downLoadPath =downLoad+"\\split";
        File FramesPath=new File(downLoadPath);
        if (!FramesPath.exists()){
            FramesPath.mkdirs();
        }
        String videoFramesPath=downLoadPath+"\\";
        System.out.println("videoFramesPath=="+videoFramesPath);

        //最后获取到的视频的图片的路径
        String videPicture="";
//        String videoFileName = "http://zh210810.oss-cn-shenzhen.aliyuncs.com/20220103/d929b4b94841451682715d7b4622b1da.mp4";
        //Frame对象
        Frame frame = null;
        //标识
        int flag = 0;
        try {
            //获取视频文件
            FFmpegFrameGrabber fFmpegFrameGrabber = new FFmpegFrameGrabber(path);
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
        model.addAttribute("message","分割视频成功");
        return "project-video";

    }

}
