package com.csu.carefree.Model.ProductDT;

public class TicketMsg {
    private String id;
    private String scenic_name;
    private String ticket_content;
    private String scenic_address;
    private String ticket_price;
    private String ticket_link;
    private String score;
    private String img_url;
    private String city_id;
    private String supplier_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScenic_name() {
        return scenic_name;
    }

    public void setScenic_name(String scenic_name) {
        this.scenic_name = scenic_name;
    }

    public String getTicket_content() {
        return ticket_content;
    }

    public void setTicket_content(String ticket_content) {
        this.ticket_content = ticket_content;
    }

    public String getScenic_address() {
        return scenic_address;
    }

    public void setScenic_address(String scenic_address) {
        this.scenic_address = scenic_address;
    }

    public String getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(String ticket_price) {
        this.ticket_price = ticket_price;
    }

    public String getTicket_link() {
        return ticket_link;
    }

    public void setTicket_link(String ticket_link) {
        this.ticket_link = ticket_link;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
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

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public TicketMsg(String id, String scenic_name, String ticket_content, String scenic_address, String ticket_price, String ticket_link, String score, String img_url, String city_id, String supplier_id) {
        this.id = id;
        this.scenic_name = scenic_name;
        this.ticket_content = ticket_content;
        this.scenic_address = scenic_address;
        this.ticket_price = ticket_price;
        this.ticket_link = ticket_link;
        this.score = score;
        this.img_url = img_url;
        this.city_id = city_id;
        this.supplier_id = supplier_id;
    }

    public TicketMsg() {
    }
}