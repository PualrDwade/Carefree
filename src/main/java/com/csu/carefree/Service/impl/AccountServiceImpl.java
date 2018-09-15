package com.csu.carefree.Service.impl;

import com.csu.carefree.Model.Account.EmailVerifyRecord;
import com.csu.carefree.Model.Account.Sigon;
import com.csu.carefree.Model.Account.UserProfile;
import com.csu.carefree.Model.TraverAsk.TraverNote;
import com.csu.carefree.Model.TraverAsk.UserAnswer;
import com.csu.carefree.Model.TraverAsk.UserAsk;
import com.csu.carefree.Persistence.*;
import com.csu.carefree.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    @Autowired
    SigonMapper sigonMapper;

    @Autowired
    UserProfileMapper userProfileMapper;

    @Autowired
    EmailVerifyRecordMapper emailVerifyRecordMapper;

    @Autowired
    TraverNoteMapper traverNoteMapper;

    @Autowired
    UserAskMapper userAskMapper;

    @Autowired
    UserAnswerMapper userAnswerMapper;

    /**
     * 登陆注册
     **/
    @Override
    public Sigon getSigon(String username) {
        return sigonMapper.getSigon(username);
    }

    @Override
    public boolean setSigon(String username, String password) {
        return sigonMapper.setSigon(username, password);
    }

    @Override
    public boolean updateSigon(String username, String password) {
        return sigonMapper.updateSigon(username, password);
    }


    /**
     * 个人信息
     **/

    @Override
    public boolean setUserProfile(UserProfile userProfile) {
        return userProfileMapper.setUserProfile(userProfile);
    }

    @Override
    public boolean updateUserProfile(UserProfile userProfile) {
        return userProfileMapper.updateUserProfile(userProfile);
    }

    @Override
    public UserProfile getUserProfile(String username) {
        return userProfileMapper.getUserProfile(username);
    }


    /**
     * 个人个性化内容
     **/
    @Override
    public List<TraverNote> getTraverNodeListbyName(String username) {
        return traverNoteMapper.getTraverNodeListbyName(username);
    }

    @Override
    public List<UserAsk> getUserAskListbyName(String username) {
        return userAskMapper.getUserAskListbyName(username);
    }

    @Override
    public List<UserAnswer> getUserAnswerListByName(String username) {
        return userAnswerMapper.getUserAnswerListByName(username);
    }

    @Override
    public List<UserAnswer> getUserAnswerByAsk(String userAskId) {
        return userAnswerMapper.getUserAnswerByAsk(userAskId);
    }

    @Override
    public String getVerifyCode(String username, String type) {
        return emailVerifyRecordMapper.getVerifyCode(username, type);
    }

    @Override
    public boolean setVerifyCode(String username, String code, String type) {
        return emailVerifyRecordMapper.setVerifyCode(username, code, type);
    }
}
