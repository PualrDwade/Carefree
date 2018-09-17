package com.csu.carefree.Service.impl;

import com.csu.carefree.Model.TraverAsk.UserAnswer;
import com.csu.carefree.Model.TraverAsk.UserAsk;
import com.csu.carefree.Persistence.UserAnswerMapper;
import com.csu.carefree.Persistence.UserAskMapper;
import com.csu.carefree.Service.TraverAskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 *   Great by WLX
 */
@Service
public class TraverAskServiceImpl implements TraverAskService {

    @Autowired
    private UserAskMapper userAskMapper;

    @Autowired
    private UserAnswerMapper userAnswerMapper;


    @Override
    public List<UserAsk> getUserAskList() {
        return userAskMapper.getUserAskList();
    }

    @Override
    public List<UserAsk> searchUserAskList(String keyword) {
        return userAskMapper.searchUserAskList("%"+keyword+"%");
    }

    @Override
    public UserAsk getUserAskById(String userAskId) {
        return userAskMapper.getUserAskById(userAskId);
    }

    @Override
    public void insertUserAsk(UserAsk userAsk) {
        userAskMapper.insertUserAsk(userAsk);
    }

    @Override
    public List<UserAnswer> getUserAnswerByAsk(String userAskId) {
        return userAnswerMapper.getUserAnswerByAsk(userAskId);
    }

    @Override
    public void insertUserAnswer(UserAnswer userAnswer) {
        userAnswerMapper.insertUserAnswer(userAnswer);
    }

    @Override
    public List<UserAnswer> getUserAnswerListByName(String username){
        return userAnswerMapper.getUserAnswerListByName(username);
    }
}
