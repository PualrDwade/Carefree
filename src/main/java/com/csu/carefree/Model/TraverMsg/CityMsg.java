package com.csu.carefree.Model.TraverMsg;

public class CityMsg {
    private String id;
    private String name;
    private String img_url;
    private String province_name;

    @Override
    public String toString() {
        return "ProductCityMsg{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", img_url='" + img_url + '\'' +
                ", province_name='" + province_name + '\'' +
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

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province) {
        this.province_name = province_name;
    }

    public CityMsg() {
    }

    public CityMsg(String id, String name, String img_url, String province_name) {
        this.id = id;
        this.name = name;
        this.img_url = img_url;
        this.province_name = province_name;
    }
}
