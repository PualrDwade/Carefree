package com.csu.carefree.Model.ProductDT;

public class ProductMsg {
    private String id;
    private String name;
    private String traver_days;
    private String product_type;
    private String supplier_id;
    private String product_link;
    private String score;
    private String sell_num;
    private String img_url;
    private String comments_num;
    private String product_grade;

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

    public String getTraver_days() {
        return traver_days;
    }

    public void setTraver_days(String traver_days) {
        this.traver_days = traver_days;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getProduct_link() {
        return product_link;
    }

    public void setProduct_link(String product_link) {
        this.product_link = product_link;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSell_num() {
        return sell_num;
    }

    public void setSell_num(String sell_num) {
        this.sell_num = sell_num;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getComments_num() {
        return comments_num;
    }

    public void setComments_num(String comments_num) {
        this.comments_num = comments_num;
    }

    public String getProduct_grade() {
        return product_grade;
    }

    public void setProduct_grade(String product_grade) {
        this.product_grade = product_grade;
    }

    public ProductMsg() {
    }

    public ProductMsg(String id, String name, String traver_days, String product_type, String supplier_id, String product_link, String score, String sell_num, String img_url, String comments_num, String product_grade) {
        this.id = id;
        this.name = name;
        this.traver_days = traver_days;
        this.product_type = product_type;
        this.supplier_id = supplier_id;
        this.product_link = product_link;
        this.score = score;
        this.sell_num = sell_num;
        this.img_url = img_url;
        this.comments_num = comments_num;
        this.product_grade = product_grade;
    }
}
