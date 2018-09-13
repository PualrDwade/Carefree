package com.csu.carefree.Persistence;

import com.csu.carefree.Model.TraverAsk.UserAnswer;

import java.util.List;

/*
 *  用户问答模块接口
 *  用户答案
 */
public interface UserAnswerMapper {

    //通过用户问题ID获取问题答案
    List<UserAnswer> getUserAnswerByAsk(String userAskId);

    //用户回答问题
    void insertUserAnswer(UserAnswer userAnswer);
}
