package com.csu.carefree.Model.TraverMsg;

public class ScenicMsg {
    private String id;
    private String name;
    private String city_name;
    private String img_url;
    private String address;
    private String basic_desc;
    private String link_url;
    private String popular_level;

    @Override
    public String toString() {
        return "ProductScenicMsg{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", city_id='" + city_name + '\'' +
                ", img_url='" + img_url + '\'' +
                ", address='" + address + '\'' +
                ", basic_desc='" + basic_desc + '\'' +
                ", link_url='" + link_url + '\'' +
                ", popular_level='" + popular_level + '\'' +
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

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBasic_desc() {
        return basic_desc;
    }

    public void setBasic_desc(String basic_desc) {
        this.basic_desc = basic_desc;
    }

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }

    public String getPopular_level() {
        return popular_level;
    }

    public void setPopular_level(String popular_level) {
        this.popular_level = popular_level;
    }

    public ScenicMsg() {
    }

    public ScenicMsg(String id, String name, String city_name, String img_url, String address, String basic_desc, String link_url, String popular_level) {
        this.id = id;
        this.name = name;
        this.city_name = city_name;
        this.img_url = img_url;
        this.address = address;
        this.basic_desc = basic_desc;
        this.link_url = link_url;
        this.popular_level = popular_level;
    }
}
