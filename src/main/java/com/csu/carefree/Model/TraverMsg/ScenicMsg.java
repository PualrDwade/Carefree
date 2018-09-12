package com.csu.carefree.Model.TraverMsg;

public class ScenicMsg {
    private String id;
    private String name;
    private String city_id;
    private String scenic_content;
    private String img_url;

    @Override
    public String toString() {
        return "ScenicMsg{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", city_id='" + city_id + '\'' +
                ", scenic_content='" + scenic_content + '\'' +
                ", img_url='" + img_url + '\'' +
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

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getScenic_content() {
        return scenic_content;
    }

    public void setScenic_content(String scenic_content) {
        this.scenic_content = scenic_content;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public ScenicMsg() {
    }

    public ScenicMsg(String id, String name, String city_id, String scenic_content, String img_url) {
        this.id = id;
        this.name = name;
        this.city_id = city_id;
        this.scenic_content = scenic_content;
        this.img_url = img_url;
    }
}
