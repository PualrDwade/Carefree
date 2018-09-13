package com.csu.carefree.Model.ProductDT;

public class StrategyMsg {
    private String id;
    private String title;
    private String link_url;
    private String simple_content;
    private String supplier_id;
    private String img_url;
    private String scenic_id;

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

    public String getScenic_id() {
        return scenic_id;
    }

    public void setScenic_id(String scenic_id) {
        this.scenic_id = scenic_id;
    }

    public StrategyMsg() {
    }

    public StrategyMsg(String id, String title, String link_url, String simple_content, String supplier_id, String img_url, String scenic_id) {
        this.id = id;
        this.title = title;
        this.link_url = link_url;
        this.simple_content = simple_content;
        this.supplier_id = supplier_id;
        this.img_url = img_url;
        this.scenic_id = scenic_id;
    }
}
