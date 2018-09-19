package com.csu.carefree.Service;


import com.csu.carefree.Model.TraverAsk.UserAnswer;
import com.csu.carefree.Model.TraverAsk.UserAsk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TraverAskService {

    //获取数据库所有问题
    List<UserAsk> getUserAskList();

    //搜索关键词获取问题信息
    List<UserAsk> searchUserAskList(String keyword);

    //通过用户问题ID获取问题信息
    UserAsk getUserAskById(String userAskId);

    //用户提出提出问题
    void insertUserAsk(UserAsk userAsk);

    //通过用户问题ID获取问题答案
    List<UserAnswer> getUserAnswerByAsk(String userAskId);

    //用户回答问题
    void insertUserAnswer(UserAnswer userAnswer);

    //通过username得到用户回答列表
    List<UserAnswer> getUserAnswerListByName(String username);

    //通过时间进行排序获得所有的List
    List<UserAsk> getUserAskListByTime();

    //获得热门游记
    List<UserAsk> getHotUserAskListByTime(int askListNum);

    //获得用户所有回答
    List<UserAnswer> getUserAnswerList();
}
