package com.csu.carefree.Persistence;

import com.csu.carefree.Model.ProductDT.ProductMsg;

import java.util.List;

public interface ProductMapper {
    List<ProductMsg> getProductList();

    ProductMsg getProductById(String id);

    /*****************通过筛选条件获取产品*****************/
    //筛选条件：traver_days, product_type, supplier_id

    //行程天数
    List<ProductMsg> getProductListByTraver_days(String traver_days);

    //产品类型
    List<ProductMsg> getProductListByProduct_type(String product_type);

    //供应商
    List<ProductMsg> getProductListBySupplier_id(String supplier_id);

    //产品名称
    List<ProductMsg> getProductListByName(String product_name);

    //三个筛选条件都有
    List<ProductMsg> getProductListByThree(String traver_days, String product_type, String supplier_id);

    //*****************通过推荐条件来排序、推荐*****************/
    //注意：推荐条件是在筛选条件的基础上进行的，所以不能直接对数据库进行查找
    //推荐（排序）条件：score, sell_num, comments_num, product_grade
}
