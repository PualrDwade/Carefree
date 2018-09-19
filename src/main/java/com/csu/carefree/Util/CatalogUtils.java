package com.csu.carefree.Util;

import com.csu.carefree.Model.ProductDT.HotelMsg;
import com.csu.carefree.Model.ProductDT.ProductCityMsg;
import com.csu.carefree.Model.ProductDT.ProductMsg;
import com.csu.carefree.Service.CatalogService;
import java.util.List;

public class CatalogUtils {

    public void setDepartCityPrice(CatalogService catalogService, String destination,List<ProductMsg> productMsgList){
        if (destination != null) {
            for (int i = 0; i < productMsgList.size(); i++) {
                ProductCityMsg productCityMsg = catalogService.getDepartCityPrice(productMsgList.get(i).getId(), destination);
                if(productCityMsg != null) {
                    productMsgList.get(i).setCurrent_price(productCityMsg.getProduct_price());
                }
            }
        }
    }

    public void setHotelListByPrices(List<HotelMsg> hotelMsgList, String price) {
        if(price.equals("100")) {
            chooseHotelMsgFromListByPrice(hotelMsgList,0,100);
        }
        if(price.equals("250")) {
            chooseHotelMsgFromListByPrice(hotelMsgList,100,250);
        }
        if(price.equals("500")) {
            chooseHotelMsgFromListByPrice(hotelMsgList,250,500);
        }
        if(price.equals("1000")) {
            chooseHotelMsgFromListByPrice(hotelMsgList,500,1000);
        }
    }

    public void chooseHotelMsgFromListByPrice(List<HotelMsg> hotelMsgList,int a ,int b) {
        if (hotelMsgList != null) {
            for (int i = 0; i < hotelMsgList.size(); i++) {
                if(!(Integer.parseInt(hotelMsgList.get(i).getHotel_price())>a && Integer.parseInt(hotelMsgList.get(i).getHotel_price())<b))
                    hotelMsgList.get(i).setHotel_price("0");
            }
        }
    }
}
