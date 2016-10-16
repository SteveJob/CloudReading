package com.kongshanxinyu.cloudreading.bean;

import android.graphics.Bitmap;

/**
 * Created by Steve on 16/10/8.
 * E-mail: singleframe@aliyun.com
 */
public class Music {

    // 歌曲类型 本地/网络
    private Type type;
    // [本地歌曲]歌曲id
    private long id;
    // 音乐标题
    private String title;
    // 艺术家
    private String artist;
    // 专辑
    private String album;
    // 持续时间
    private long duration;
    // 音乐路径
    private String uri;


    // [本地歌曲]专辑封面路径
    private String coverUri;
    // [网络歌曲]专辑封面bitmap
    private Bitmap cover;


    // 文件名
    private String fileName;
    // 文件大小
    private long fileSize;
    // 发行日期
    private int year;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getCoverUri() {
        return coverUri;
    }

    public void setCoverUri(String coverUri) {
        this.coverUri = coverUri;
    }

    public Bitmap getCover() {
        return cover;
    }

    public void setCover(Bitmap cover) {
        this.cover = cover;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Music() {
    }

    public Music(Type type, long id, String title, String artist, String album, long duration, String uri, String coverUri, Bitmap cover, String fileName, long fileSize, int year) {
        this.type = type;
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
        this.uri = uri;
        this.coverUri = coverUri;
        this.cover = cover;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.year = year;
    }

    public enum Type{
        LOCAL,NETWORK
    }

}
