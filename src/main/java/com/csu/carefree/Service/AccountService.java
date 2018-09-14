package com.csu.carefree.Service;


import com.csu.carefree.Model.Account.EmailVerifyRecord;
import com.csu.carefree.Model.Account.Sigon;
import com.csu.carefree.Model.Account.UserProfile;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    //通过用户名得到登陆账号信息
    Sigon getSigon(String username);

    //用过注册账号,
    boolean setSigon(String username, String password);

    //通过邮箱找回密码
    boolean updateSigon(String username, String password);

    //通过userprofile创建用户详细信息
    boolean setUserProfile(UserProfile userProfile);

    //通过userprofile更新用户详细信息
    boolean updateUserProfile(UserProfile userProfile);

    //通过username得到用户详细信息
    UserProfile getUserProfile(String username);

    //通过邮箱得到验证码
    String getVerifyCode(String username, String type);


    //插入邮箱验证码信息
    boolean setVerifyCode(String username, String code, String type);
}
