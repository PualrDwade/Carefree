package com.csu.carefree.Persistence;

import com.csu.carefree.Model.ProductDT.ProductCityMsg;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ProductCityMsgMapper {
    List<ProductCityMsg> getProductCityList();

    //根据产品ID找出产品的所有出发城市以及相应的起价
    List<ProductCityMsg> getAllDepartCityListByProductId(String productId);

    //根据产品ID以及出发城市名字查找相应的起价
    ProductCityMsg getDepartCityPriceByProductId(@Param("productId") String productId, @Param("cityName") String cityName);
}
