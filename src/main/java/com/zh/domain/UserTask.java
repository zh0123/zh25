package com.zh.domain;

public class UserTask {
    private Integer id;
    private String username;
    private String taskname;
    private String path;
    public String tag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "UserTask{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", taskname='" + taskname + '\'' +
                ", path='" + path + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
