package com.csu.carefree.Persistence;

import com.csu.carefree.Model.TraverMsg.CityMsg;
import com.csu.carefree.Model.TraverMsg.ProvinceMsg;

import java.util.List;

public interface ProvinceMsgMapper {

    //根据省份ID获取城市信息
    ProvinceMsg getProvinceMsgById(String provinceId);

    //根据省份名称名称获取城市信息
    List<ProvinceMsg> searchProvinceMsgByName(String provinceName);

    //根据省份ID获取省份所有城市信息
    List<CityMsg> getCityListById(String provinceId);

}
