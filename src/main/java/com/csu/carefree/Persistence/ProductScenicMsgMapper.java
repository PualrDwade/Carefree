package com.csu.carefree.Persistence;

import com.csu.carefree.Model.ProductDT.ProductScenicMsg;

import java.util.List;

public interface ProductScenicMsgMapper {
    //根据景点名字查找产品列表
    List<ProductScenicMsg> getProductIdsByScenicName(String scenicName);

    //根据产品ID查找景点名字列表
    List<ProductScenicMsg> getScenicNamesByProductId(String productId);
}
