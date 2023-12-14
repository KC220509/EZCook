package com.example.ezcook.model;

public class h_Notification_Model {
    int id;
    String user_id;
    String title;
    String content;
    int image;
    Long time;
    public h_Notification_Model() {}
    public h_Notification_Model(int id, String user_id, String title, String content, int image, Long time) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.image = image;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
