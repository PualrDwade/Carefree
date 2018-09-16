package com.csu.carefree.Model.TraverMsg;

import java.io.Serializable;

public class TraverMsg implements Serializable {
    private String id;
    private String name;
    private String user_id;
    private String start_city;
    private String end_city;
    private String traverdays;
    private String adult_num;
    private String child_num;
    private String traver_type;

    @Override
    public String toString() {
        return "TraverMsg{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", user_id='" + user_id + '\'' +
                ", start_city='" + start_city + '\'' +
                ", end_city='" + end_city + '\'' +
                ", traverdays='" + traverdays + '\'' +
                ", adult_num='" + adult_num + '\'' +
                ", child_num='" + child_num + '\'' +
                ", traver_type='" + traver_type + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStart_city() {
        return start_city;
    }

    public void setStart_city(String start_city) {
        this.start_city = start_city;
    }

    public String getEnd_city() {
        return end_city;
    }

    public void setEnd_city(String end_city) {
        this.end_city = end_city;
    }

    public String getTraverdays() {
        return traverdays;
    }

    public void setTraverdays(String traverdays) {
        this.traverdays = traverdays;
    }

    public String getAdult_num() {
        return adult_num;
    }

    public void setAdult_num(String adult_num) {
        this.adult_num = adult_num;
    }

    public String getChild_num() {
        return child_num;
    }

    public void setChild_num(String child_num) {
        this.child_num = child_num;
    }

    public String getTraver_type() {
        return traver_type;
    }

    public void setTraver_type(String traver_type) {
        this.traver_type = traver_type;
    }

    public TraverMsg() {
    }

    public TraverMsg(String id, String name, String user_id,
                     String start_city, String end_city,
                     String traverdays, String adult_num,
                     String child_num, String traver_type) {
        this.id = id;
        this.name = name;
        this.user_id = user_id;
        this.start_city = start_city;
        this.end_city = end_city;
        this.traverdays = traverdays;
        this.adult_num = adult_num;
        this.child_num = child_num;
        this.traver_type = traver_type;
    }
}
