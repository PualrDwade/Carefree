package com.csu.carefree.Service.impl;

import com.csu.carefree.Model.Account.EmailVerifyRecord;
import com.csu.carefree.Model.Account.Sigon;
import com.csu.carefree.Model.Account.UserProfile;
import com.csu.carefree.Persistence.EmailVerifyRecordMapper;
import com.csu.carefree.Persistence.SigonMapper;
import com.csu.carefree.Persistence.UserProfileMapper;
import com.csu.carefree.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountServiceIml implements AccountService {
    @Autowired
    SigonMapper sigonMapper;

    @Autowired
    UserProfileMapper userProfileMapper;

    @Autowired
    EmailVerifyRecordMapper emailVerifyRecordMapper;

    @Override
    public Sigon getSigon(String username) {
        return sigonMapper.getSigon(username);
    }

    @Override
    public boolean setSigon(String username, String password) {
        return false;
    }

    @Override
    public boolean updateSigon(String username, String password) {
        return false;
    }

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

    @Override
    public String getVerifyCode(String username, String type) {
        return emailVerifyRecordMapper.getVerifyCode(username, type);
    }

    @Override
    public boolean setVerifyCode(String username, String code, String type) {
        return emailVerifyRecordMapper.setVerifyCode(username, code, type);
    }
}
