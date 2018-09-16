package com.csu.carefree.Model.ProductDT;

import java.io.Serializable;

public class Supplier implements Serializable {
    private String id;
    private String name;
    private String link_url;
    private String cooperation_type;

    @Override
    public String toString() {
        return "Supplier{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", link_url='" + link_url + '\'' +
                ", cooperation_type='" + cooperation_type + '\'' +
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

    public String getLink_url() {
        return link_url;
    }

    public void setLink_url(String link_url) {
        this.link_url = link_url;
    }

    public String getCooperation_type() {
        return cooperation_type;
    }

    public void setCooperation_type(String cooperation_type) {
        this.cooperation_type = cooperation_type;
    }

    public Supplier() {
    }

    public Supplier(String id, String name, String link_url, String cooperation_type) {
        this.id = id;
        this.name = name;
        this.link_url = link_url;
        this.cooperation_type = cooperation_type;
    }
}
