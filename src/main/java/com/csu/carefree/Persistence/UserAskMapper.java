package com.csu.carefree.Persistence;

import com.csu.carefree.Model.TraverAsk.UserAsk;

import java.util.List;

/*
 *  用户问答模块接口
 *  用户提问
 */
public interface UserAskMapper {

    //获取数据库所有问题
    List<UserAsk> getUserAskList();

    //搜索关键词获取问题信息
    List<UserAsk> searchUserAskList(String keyword);

    //通过用户问题ID获取问题信息
    UserAsk getUserAskById(String userAskId);

    //插入用户提出的问题
    void insertUserAsk(UserAsk userAsk);

    //通过username得到用户问题列表
    List<UserAsk> getUserAskListbyName(String username);

    //通过时间进行排序获得所有的List
    List<UserAsk> getUserAskListByTime();
}
