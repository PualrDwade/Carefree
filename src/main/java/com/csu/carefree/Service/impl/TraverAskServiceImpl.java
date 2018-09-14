package com.csu.carefree.Service.impl;

import com.csu.carefree.Model.TraverAsk.UserAnswer;
import com.csu.carefree.Model.TraverAsk.UserAsk;
import com.csu.carefree.Persistence.UserAnswerMapper;
import com.csu.carefree.Persistence.UserAskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 *   Great by WLX
 */
@Service
public class TraverAskServiceImpl {

    @Autowired
    private UserAskMapper userAskMapper;

    @Autowired
    private UserAnswerMapper userAnswerMapper;

    @Override
    public List<UserAsk> getUserAskList(){    //获取数据库所有问题
        return userAskMapper.getUserAskList();
    }

    @Override
    public List<UserAsk> searchUserAskList(String keyword){    //搜索关键词获取问题信息
        return userAskMapper.searchUserAskList(keyword);
    }

    @Override
    public UserAsk getUserAskById(String userAskId){    //通过用户问题ID获取问题信息
        return userAskMapper.getUserAskById(userAskId);
    }

    @Override
    public void insertUserAsk(UserAsk userAsk){    //用户提出提出问题
        userAskMapper.insertUserAsk(userAsk);
    }

    @Override
    public List<UserAnswer> getUserAnswerByAsk(String userAskId){    //通过用户问题ID获取问题答案
        return userAnswerMapper.getUserAnswerByAsk(userAskId);
    }

    @Override
    public void insertUserAnswer(UserAnswer userAnswer){    //用户回答问题
        userAnswerMapper.insertUserAnswer(userAnswer);
    }
}
