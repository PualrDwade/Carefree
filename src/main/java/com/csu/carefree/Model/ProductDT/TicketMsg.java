package com.csu.carefree.Model.ProductDT;

public class TicketMsg {
    private String id;
    private String scenic_id;
    private String name;
    private String ticket_content;
    private String ticket_num;
    private String ticket_price;
    private String ticket_link;
    private String supplier_id;

    @Override
    public String toString() {
        return "TicketMsg{" +
                "id='" + id + '\'' +
                ", scenic_id='" + scenic_id + '\'' +
                ", name='" + name + '\'' +
                ", ticket_content='" + ticket_content + '\'' +
                ", ticket_num='" + ticket_num + '\'' +
                ", ticket_price='" + ticket_price + '\'' +
                ", ticket_link='" + ticket_link + '\'' +
                ", supplier_id='" + supplier_id + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScenic_id() {
        return scenic_id;
    }

    public void setScenic_id(String scenic_id) {
        this.scenic_id = scenic_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTicket_content() {
        return ticket_content;
    }

    public void setTicket_content(String ticket_content) {
        this.ticket_content = ticket_content;
    }

    public String getTicket_num() {
        return ticket_num;
    }

    public void setTicket_num(String ticket_num) {
        this.ticket_num = ticket_num;
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

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public TicketMsg() {
    }

    public TicketMsg(String id, String scenic_id, String name,
                     String ticket_content, String ticket_num,
                     String ticket_price, String ticket_link,
                     String supplier_id) {
        this.id = id;
        this.scenic_id = scenic_id;
        this.name = name;
        this.ticket_content = ticket_content;
        this.ticket_num = ticket_num;
        this.ticket_price = ticket_price;
        this.ticket_link = ticket_link;
        this.supplier_id = supplier_id;
    }
}
