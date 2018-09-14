package com.csu.carefree.Persistence;

import com.csu.carefree.Model.ProductDT.Product_CityMsg;

import java.util.List;

public interface ProductCityMsgMapper {
    List<Product_CityMsg> getProduct_CityList();

    //根据产品ID找出产品的所有出发城市以及相应的起价
    List<Product_CityMsg> getAllDepartCityListByProductId(String product_id);

    //根据产品ID以及出发城市名字查找相应的起价
    Product_CityMsg getADepartCity_Price(String Product_id, String cityName);
}
