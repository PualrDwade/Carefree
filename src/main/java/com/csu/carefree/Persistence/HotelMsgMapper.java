package com.csu.carefree.Persistence;

import com.csu.carefree.Model.ProductDT.HotelMsg;

import java.util.List;

public interface HotelMsgMapper {

    List<HotelMsg> getHotelMsgList();

    HotelMsg getHotelMsg(String id);

    List<HotelMsg> searchHotelMsgList(String keyword);
}
