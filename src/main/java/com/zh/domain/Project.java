package com.zh.domain;

import java.util.List;

public class Project {
    private String taskname;
    private String msg;
    private String pubname;
    private String pubphone;
    // 详情图片的路径
    private List<String> pictureList;

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPubname() {
        return pubname;
    }

    public void setPubname(String pubname) {
        this.pubname = pubname;
    }

    public String getPubphone() {
        return pubphone;
    }

    public void setPubphone(String pubphone) {
        this.pubphone = pubphone;
    }

    public List<String> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }

    @Override
    public String toString() {
        return "Project{" +
                "taskname='" + taskname + '\'' +
                ", msg='" + msg + '\'' +
                ", pubname='" + pubname + '\'' +
                ", pubphone='" + pubphone + '\'' +
                ", pictureList=" + pictureList +
                '}';
    }
}
