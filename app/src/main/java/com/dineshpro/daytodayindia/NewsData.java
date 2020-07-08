package com.dineshpro.daytodayindia;

public class NewsData {
    String id,title,description,date,time,imgurl;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getImgurl() {
        return imgurl;
    }

    public NewsData(String id, String title, String description, String date, String time, String imgurl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.imgurl = imgurl;
    }
}
