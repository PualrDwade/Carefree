package com.csu.carefree.Model.TraverMsg;

public class CityMsg {
    private String id;
    private String name;
    private String city_content;
    private String img_url;
    private String province;

    @Override
    public String toString() {
        return "CityMsg{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", city_content='" + city_content + '\'' +
                ", img_url='" + img_url + '\'' +
                ", province='" + province + '\'' +
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

    public String getCity_content() {
        return city_content;
    }

    public void setCity_content(String city_content) {
        this.city_content = city_content;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public CityMsg() {
    }

    public CityMsg(String id, String name, String city_content, String img_url, String province) {
        this.id = id;
        this.name = name;
        this.city_content = city_content;
        this.img_url = img_url;
        this.province = province;
    }
}
