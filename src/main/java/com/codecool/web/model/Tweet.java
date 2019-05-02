package com.codecool.web.model;

import java.util.Date;

public class Tweet {
    private int id;
    private String poster;
    private String content;
    private Date timeStamp;

    public Tweet(int id, String poster, String content, Date timeStamp) {
        this.id = id;
        this.poster = poster;
        this.content = content;
        this.timeStamp = timeStamp;
    }

    public Tweet(int id, String poster, String content) {
        this.id = id;
        this.poster = poster;
        this.content = content;
        this.timeStamp = new Date(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public String getPoster() {
        return poster;
    }

    public String getContent() {
        return content;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }
}
