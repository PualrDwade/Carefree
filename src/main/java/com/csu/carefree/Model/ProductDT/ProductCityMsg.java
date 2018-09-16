package com.csu.carefree.Model.ProductDT;

import java.io.Serializable;

public class ProductCityMsg implements Serializable {
    private String id;
    private String city_id;
    private String product_price;
    private String product_id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public ProductCityMsg(String id, String city_id, String product_price, String product_id) {
        this.id = id;
        this.city_id = city_id;
        this.product_price = product_price;
        this.product_id = product_id;
    }

    public ProductCityMsg() {
    }
}
