package com.csu.carefree.Persistence;

import com.csu.carefree.Model.TraverMsg.CityMsg;
import com.csu.carefree.Model.TraverMsg.ScenicMsg;

import java.util.List;

public interface ScenicMsgMapper {

    //通过景点ID获取景点信息
    ScenicMsg getScenicMsgById(String scenicId);

    //通过景点名称获取景点信息
    List<ScenicMsg> getScenicMsgListByName(String name);

    //通过城市名称获取景点列表
    List<ScenicMsg> getScenicMsgListByCityName(String cityName);
}
