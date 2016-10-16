package com.kongshanxinyu.multithread.bean;

import java.io.File;

/**
 * Created by Steve on 16/10/16.
 * E-mail: singleframe@aliyun.com
 */
public class FileBean {

    private int id;
    private String name;
    private String url;
    private int len;
    private int len_downloaded;

    public FileBean(){}

    public FileBean(int id, String name, String url, int len, int len_downloaded) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.len = len;
        this.len_downloaded = len_downloaded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public int getLen_downloaded() {
        return len_downloaded;
    }

    public void setLen_downloaded(int len_downloaded) {
        this.len_downloaded = len_downloaded;
    }
}
