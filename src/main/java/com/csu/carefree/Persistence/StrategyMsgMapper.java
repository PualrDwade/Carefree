package com.csu.carefree.Persistence;

import com.csu.carefree.Model.ProductDT.StrategyMsg;

import java.util.List;

public interface StrategyMsgMapper {

    // 得到攻略信息
    List<StrategyMsg> getStrategyList();


    // 根据景点得到
    StrategyMsg getStrategyByScenicName(String scenicName);
}
