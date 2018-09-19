package com.csu.carefree.Model.ProductDT;

//product选择的表单类
public class ProductForm {
    private String traverDays;
    private String supplierId;
    private String productType;


    @Override
    public String toString() {
        return traverDays + supplierId + productType;
    }

    public String getTraverDays() {
        return traverDays;
    }

    public void setTraverDays(String traverDays) {
        this.traverDays = traverDays;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public ProductForm() {
    }

    public ProductForm(String traverDays, String supplierId, String productType) {
        this.traverDays = traverDays;
        this.supplierId = supplierId;
        this.productType = productType;
    }

}
