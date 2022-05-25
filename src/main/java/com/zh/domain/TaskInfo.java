package com.zh.domain;

public class TaskInfo {
    private String taskname;
    private String msg;
    private String pubname;
    private String pubphone;
    private String path;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "TaskInfo{" +
                "taskname='" + taskname + '\'' +
                ", msg='" + msg + '\'' +
                ", pubname='" + pubname + '\'' +
                ", pubphone='" + pubphone + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
