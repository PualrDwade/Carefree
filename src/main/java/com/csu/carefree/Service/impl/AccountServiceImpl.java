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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public Sigon getSigonByUserName(String username) {
        return sigonMapper.getSigonByUserName(username);
    }

    @Override
    public void setSigon(String username, String password) {
        sigonMapper.setSigon(username, password);
    }

    @Override
    public void updateSigon(String username, String password) {
        sigonMapper.updateSigon(username, password);
    }


    /**
     * 个人信息
     **/

    @Override
    public void setUserProfile(UserProfile userProfile) {
         userProfileMapper.setUserProfile(userProfile);
    }

    @Override
    public void updateUserProfile(UserProfile userProfile) {
         userProfileMapper.updateUserProfile(userProfile);
    }

    @Override
    public UserProfile getUserProfileByUserName(String username) {
        return userProfileMapper.getUserProfileByUserName(username);
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
    public EmailVerifyRecord getEmailVerifyRecordByCodeAndType(String code, String send_type) {
        return emailVerifyRecordMapper.getEmailVerifyRecordByCodeAndType(code, send_type);
    }

    @Override
    public void setVerifyCodeRecord(EmailVerifyRecord emailVerifyRecord) {
        emailVerifyRecordMapper.setVerifyCodeRecord(emailVerifyRecord);
    }
}
