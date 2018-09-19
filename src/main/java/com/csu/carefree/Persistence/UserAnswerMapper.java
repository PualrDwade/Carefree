package com.csu.carefree.Persistence;

import com.csu.carefree.Model.TraverAsk.UserAnswer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*
 *  用户问答模块接口
 *  用户答案
 */
public interface UserAnswerMapper {

    //通过用户问题ID获取问题答案
    List<UserAnswer> getUserAnswerByAsk(@Param("userAskId") String userAskId);

    //插入用户的回答问题
    void insertUserAnswer(UserAnswer userAnswer);

    //通过username得到用户回答列表
    List<UserAnswer> getUserAnswerListByName(String username);

    //获得用户所有回答
    List<UserAnswer> getUserAnswerList();
}
