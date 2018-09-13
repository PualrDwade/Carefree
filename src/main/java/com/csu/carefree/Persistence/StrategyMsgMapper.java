package com.csu.carefree.Persistence;

import com.csu.carefree.Model.ProductDT.StrategyMsg;

import java.util.List;

public interface StrategyMsgMapper {
    List<StrategyMsg> getStrategyList();

    StrategyMsg getStrategyByScenic_name(String scenic_name);
}
