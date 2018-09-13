package com.csu.carefree.Model.TraverMsg;

public class ProvinceMsg {
    private String id;
    private String name;
    private String province_content;
    private String img_url;


    @Override
    public String toString() {
        return "ProvinceMsg{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", province_content='" + province_content + '\'' +
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

    public String getProvince_content() {
        return province_content;
    }

    public void setProvince_content(String province_content) {
        this.province_content = province_content;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public ProvinceMsg() {
    }

    public ProvinceMsg(String id, String name, String province_content, String img_url) {
        this.id = id;
        this.name = name;
        this.province_content = province_content;
        this.img_url = img_url;
    }
}
