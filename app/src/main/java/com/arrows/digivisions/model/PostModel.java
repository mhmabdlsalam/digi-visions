package com.arrows.digivisions.model;

public class PostModel {
    String title;
    String url ;


    public PostModel(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public PostModel() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }


}
