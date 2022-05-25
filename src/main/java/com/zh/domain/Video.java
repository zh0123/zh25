package com.zh.domain;

public class Video {
    private Integer id;
    private String vpath;
    private String pubname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVpath() {
        return vpath;
    }

    public void setVpath(String vpath) {
        this.vpath = vpath;
    }

    public String getPubname() {
        return pubname;
    }

    public void setPubname(String pubname) {
        this.pubname = pubname;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", vpath='" + vpath + '\'' +
                ", pubname='" + pubname + '\'' +
                '}';
    }
}
