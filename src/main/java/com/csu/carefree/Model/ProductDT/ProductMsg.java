package com.csu.carefree.Model.ProductDT;

public class ProductMsg {
    private String id;
    private String name;
    private String traver_days;
    private String start_city;
    private String product_price;
    private String getProduct_type;
    private String scenic_id;
    private String supplier;
    private String product_link;
    private String score;
    private String sell_num;

    public ProductMsg() {
    }

    @Override
    public String toString() {
        return "ProductMsg{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", traver_days='" + traver_days + '\'' +
                ", start_city='" + start_city + '\'' +
                ", product_price='" + product_price + '\'' +
                ", getProduct_type='" + getProduct_type + '\'' +
                ", scenic_id='" + scenic_id + '\'' +
                ", supplier='" + supplier + '\'' +
                ", product_link='" + product_link + '\'' +
                ", score='" + score + '\'' +
                ", sell_num='" + sell_num + '\'' +
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

    public String getTraver_days() {
        return traver_days;
    }

    public void setTraver_days(String traver_days) {
        this.traver_days = traver_days;
    }

    public String getStart_city() {
        return start_city;
    }

    public void setStart_city(String start_city) {
        this.start_city = start_city;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getGetProduct_type() {
        return getProduct_type;
    }

    public void setGetProduct_type(String getProduct_type) {
        this.getProduct_type = getProduct_type;
    }

    public String getScenic_id() {
        return scenic_id;
    }

    public void setScenic_id(String scenic_id) {
        this.scenic_id = scenic_id;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
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

    public ProductMsg(String id, String name, String traver_days,
                      String start_city, String product_price,
                      String getProduct_type, String scenic_id,
                      String supplier, String product_link,
                      String score, String sell_num) {
        this.id = id;
        this.name = name;
        this.traver_days = traver_days;
        this.start_city = start_city;
        this.product_price = product_price;
        this.getProduct_type = getProduct_type;
        this.scenic_id = scenic_id;
        this.supplier = supplier;
        this.product_link = product_link;
        this.score = score;
        this.sell_num = sell_num;
    }
}
