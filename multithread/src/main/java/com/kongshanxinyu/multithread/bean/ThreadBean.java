package com.kongshanxinyu.multithread.bean;

/**
 * Created by Steve on 16/10/16.
 * E-mail: singleframe@aliyun.com
 */
public class ThreadBean {

    private int id;
    private String url;
    private int lo_start;
    private int lo_stop;
    private int len_downloaded;

    public ThreadBean(){}

    public ThreadBean(int id, String url, int lo_start, int lo_stop, int len_downloaded) {
        this.id = id;
        this.url = url;
        this.lo_start = lo_start;
        this.lo_stop = lo_stop;
        this.len_downloaded = len_downloaded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLo_start() {
        return lo_start;
    }

    public void setLo_start(int lo_start) {
        this.lo_start = lo_start;
    }

    public int getLo_stop() {
        return lo_stop;
    }

    public void setLo_stop(int lo_stop) {
        this.lo_stop = lo_stop;
    }

    public int getLen_downloaded() {
        return len_downloaded;
    }

    public void setLen_downloaded(int len_downloaded) {
        this.len_downloaded = len_downloaded;
    }
}
