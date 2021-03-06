package com.csu.carefree.Persistence;

import com.csu.carefree.Model.ProductDT.HotelMsg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotelMsgMapper {

    //获取所有酒店信息
    List<HotelMsg> getHotelMsgList();

    //通过酒店ID获取酒店具体信息
    HotelMsg getHotelMsg(String id);

    //通过关键字搜索酒店
    List<HotelMsg> searchHotelMsgList(String keyword);

    //根据目的地获得酒店信息
    List<HotelMsg> getHotelListByDestination(@Param("destination") String destination);

    //目的地和供应商
    List<HotelMsg> getHotelListByDestinationAndStore(@Param("destination") String destination, @Param("suppliper_id") String supplierId);
}
