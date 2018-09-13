package com.csu.carefree.Model.ProductDT;

public class Product_ScenicMsg {
    private String id;
    private String scenic_name;
    private String product_id;

    public Product_ScenicMsg(String id, String scenic_name, String product_id) {
        this.id = id;
        this.scenic_name = scenic_name;
        this.product_id = product_id;
    }

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

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public Product_ScenicMsg() {
    }
}
