package com.csu.carefree.Persistence;

import com.csu.carefree.Model.TraverMsg.CityMsg;
import com.csu.carefree.Model.TraverMsg.ProvinceMsg;
import com.csu.carefree.Model.TraverMsg.ScenicMsg;

import java.util.List;

public interface CityMsgMapper {

    //通过省份ID获取省份信息
    ProvinceMsg getProvinceMsgById(String provinceId);

    //通过城市ID获取城市信息
    CityMsg getCityMsgById(String cityId);

    //通过城市名称获取城市信息
    List<CityMsg> searchCityMsgByName(String cityName);

    //通过城市ID获取城市所有景点信息
    List<ScenicMsg> getScenicMsgListById(String cityId);
}
