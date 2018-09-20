package com.csu.carefree.Model.TraverAsk;

import java.io.Serializable;

public class UserAsk implements Serializable, Comparable<UserAsk> {
    private String id;
    private String title;
    private String ask_content;
    private int star_num;
    private String add_time;
    private String city_id;
    private String user_id;
    private int answer_num = 0;


    public int getAnswer_num() {
        return answer_num;
    }

    public void setAnswer_num(int answer_num) {
        this.answer_num = answer_num;
    }


    @Override
    public String toString() {
        return "UserAsk{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", ask_content='" + ask_content + '\'' +
                ", star_num='" + star_num + '\'' +
                ", user_id='" + user_id + '\'' +
                ", add_time='" + add_time + '\'' +
                ", city_id='" + city_id + '\'' +
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

    public String getAsk_content() {
        return ask_content;
    }

    public void setAsk_content(String ask_content) {
        this.ask_content = ask_content;
    }

    public int getStar_num() {
        return star_num;
    }

    public void setStar_num(String star_num) {
        this.star_num = Integer.parseInt(star_num);
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String scenic_id) {
        this.city_id = city_id;
    }

    public UserAsk() {
    }

    public UserAsk(String id,
                   String title,
                   String ask_content,
                   String star_num,
                   String add_time,
                   String user_id,
                   String city_id) {
        this.id = id;
        this.title = title;
        this.ask_content = ask_content;
        this.star_num = Integer.parseInt(star_num);
        this.user_id = user_id;
        this.add_time = add_time;
        this.city_id = city_id;
    }

    @Override
    public int compareTo(UserAsk o) {
        if (this.getAnswer_num() > o.getAnswer_num()) {
            return 1;
        } else {
            return -1;
        }
    }
}
