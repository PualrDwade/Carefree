package com.csu.carefree.Persistence;

import com.csu.carefree.Model.ProductDT.TicketMsg;

import java.util.List;

public interface TicketMsgMapper {
    List<TicketMsg> getTicketList();

    //门票查询筛选信息有：景点名字、城市名字
    List<TicketMsg> getTicketListByScenicName(String scenicName);

    List<TicketMsg> getTicketListByCityId(String cityId);
}
