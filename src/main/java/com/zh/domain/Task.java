package com.zh.domain;

public class Task {
    private Integer id;
    private String taskname;
    private String msg;
    private String pubname;
    private String pubphone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskname='" + taskname + '\'' +
                ", msg='" + msg + '\'' +
                ", pubname='" + pubname + '\'' +
                ", pubphone='" + pubphone + '\'' +
                '}';
    }
}
