package com.csu.carefree.Persistence;

import com.csu.carefree.Model.ProductDT.Product_ScenicMsg;

import java.util.List;

public interface ProductScenicMsgMapper {
    //根据景点名字查找产品列表
    List<Product_ScenicMsg> getProductIdsByScenicName(String scenicName);

    //根据产品ID查找景点名字列表
    List<Product_ScenicMsg> getScenicNamesByProductId(String ProductId);
}
