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

    @Overridehttps://github.com/PualrDwade/Carefree/pull/18/conflict?name=src%252Fmain%252Fjava%252Fcom%252Fcsu%252Fcarefree%252FService%252Fimpl%252FTraverAskServiceImpl.java&ancestor_oid=bde993bc05357d8695d3d56035f0ee8054e1c884&base_oid=032e7618f74af15bd9ec30528c504a63602686d5&head_oid=519138beffc07762578114d82923545ff1e31def
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

    @Override
    public List<UserAnswer> getUserAnswerListByName(String username) {
        return userAnswerMapper.getUserAnswerListByName(username);
    }
}
