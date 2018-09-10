package com.csu.carefree.Model.ProductDT;

public class StrategyMsg {
    private String id;
    private String titile;
    private String link_url;
    private String simple_content;
    private String supplier_id;
    private String img_url;
    private String city_id;
    private String scenic_id;

    @Override
    public String toString() {
        return "StrategyMsg{" +
                "id='" + id + '\'' +
                ", titile='" + titile + '\'' +
                ", link_url='" + link_url + '\'' +
                ", simple_content='" + simple_content + '\'' +
                ", supplier_id='" + supplier_id + '\'' +
                ", img_url='" + img_url + '\'' +
                ", city_id='" + city_id + '\'' +
                ", scenic_id='" + scenic_id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }

    public String getSimple_content() {
        return simple_content;
    }

    public void setSimple_content(String simple_content) {
        this.simple_content = simple_content;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
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

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getScenic_id() {
        return scenic_id;
    }

    public void setScenic_id(String scenic_id) {
        this.scenic_id = scenic_id;
    }

    public StrategyMsg() {
    }

    public StrategyMsg(String id, String titile, String link_url,
                       String simple_content, String supplier_id,
                       String img_url, String city_id, String scenic_id) {
        this.id = id;
        this.titile = titile;
        this.link_url = link_url;
        this.simple_content = simple_content;
        this.supplier_id = supplier_id;
        this.img_url = img_url;
        this.city_id = city_id;
        this.scenic_id = scenic_id;
    }
}
