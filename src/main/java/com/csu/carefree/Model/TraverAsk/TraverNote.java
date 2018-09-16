package com.csu.carefree.Model.TraverAsk;

import java.io.Serializable;

public class TraverNote implements Serializable {
    private String id;
    private String title;
    private String note_content;
    private String star_num;
    private String notify_status;
    private String add_time;
    private String img_url;
    private String city_id;
    private String user_id;
    @Override
    public String toString() {
        return "TraverNote{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", user_id='" + user_id + '\'' +
                ", note_content='" + note_content + '\'' +
                ", star_num='" + star_num + '\'' +
                ", notify_status='" + notify_status + '\'' +
                ", add_time='" + add_time + '\'' +
                ", img_url='" + img_url + '\'' +
                ", scenic_id='" + city_id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNote_content() {
        return note_content;
    }

    public void setNote_content(String note_content) {
        this.note_content = note_content;
    }

    public String getStar_num() {
        return star_num;
    }

    public void setStar_num(String star_num) {
        this.star_num = star_num;
    }

    public String getNotify_status() {
        return notify_status;
    }

    public void setNotify_status(String notify_status) {
        this.notify_status = notify_status;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String scenic_id) {
        this.city_id = scenic_id;
    }

    public TraverNote() {
    }

    public TraverNote(String id, String title, String user_id,
                      String note_content, String star_num,
                      String notify_status, String add_time,
                      String img_url, String city_id) {
        this.id = id;
        this.title = title;
        this.user_id = user_id;
        this.note_content = note_content;
        this.star_num = star_num;
        this.notify_status = notify_status;
        this.add_time = add_time;
        this.img_url = img_url;
        this.city_id = city_id;
    }
}
