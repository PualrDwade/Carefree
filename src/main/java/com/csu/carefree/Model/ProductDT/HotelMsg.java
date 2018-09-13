package com.csu.carefree.Model.ProductDT;

public class HotelMsg {
    private String id;
    private String name;
    private String type;
    private String score;
    private String hotel_price;
    private String hotel_content;
    private String city_id;
    private String scenic_id;
    private String img_url;
    private String supplier_id;
    private String hotel_link;
    private String sell_num;
    private String latest_time;

    public HotelMsg() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getHotel_price() {
        return hotel_price;
    }

    public void setHotel_price(String hotel_price) {
        this.hotel_price = hotel_price;
    }

    public String getHotel_content() {
        return hotel_content;
    }

    public void setHotel_content(String hotel_content) {
        this.hotel_content = hotel_content;
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

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getHotel_link() {
        return hotel_link;
    }

    public void setHotel_link(String hotel_link) {
        this.hotel_link = hotel_link;
    }

    public String getSell_num() {
        return sell_num;
    }

    public void setSell_num(String sell_num) {
        this.sell_num = sell_num;
    }

    public String getLatest_time() {
        return latest_time;
    }

    public void setLatest_time(String latest_time) {
        this.latest_time = latest_time;
    }

    public HotelMsg(String id, String name, String type, String score, String hotel_price, String hotel_content, String city_id, String scenic_id, String img_url, String supplier_id, String hotel_link, String sell_num, String latest_time) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.score = score;
        this.hotel_price = hotel_price;
        this.hotel_content = hotel_content;
        this.city_id = city_id;
        this.scenic_id = scenic_id;
        this.img_url = img_url;
        this.supplier_id = supplier_id;
        this.hotel_link = hotel_link;
        this.sell_num = sell_num;
        this.latest_time = latest_time;
    }
}