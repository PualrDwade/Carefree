package com.csu.carefree.Service;


import com.csu.carefree.Model.Account.EmailVerifyRecord;
import com.csu.carefree.Model.Account.Sigon;
import com.csu.carefree.Model.Account.UserProfile;
import com.csu.carefree.Model.TraverAsk.TraverNote;
import com.csu.carefree.Model.TraverAsk.UserAnswer;
import com.csu.carefree.Model.TraverAsk.UserAsk;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AccountService {

    /****************用户登陆注册模块Service****************************************/
    //通过用户名得到登陆账号信息
    Sigon getSigonByUserName(String username);

    //用过注册账号,
    void setSigon(String username, String password);

    //通过邮箱找回密码
    void updateSigon(String username, String password);

    //通过邮箱得到验证码
    EmailVerifyRecord getEmailVerifyRecordByCodeAndType(String code, String send_type);

    //插入邮箱验证码信息
    void setVerifyCodeRecord(EmailVerifyRecord emailVerifyRecord);
    /*****************************************************************************/


    /**********************用户中心模块Service*************************************/
    //通过userprofile创建用户详细信息
    void setUserProfile(UserProfile userProfile);

    //通过userprofile更新用户详细信息
    void updateUserProfile(UserProfile userProfile);

    //通过username得到用户详细信息
    UserProfile getUserProfileByUserName(String username);

    //通过username得到用户游记列表
    List<TraverNote> getTraverNodeListbyName(String username);

    //通过username得到用户问题列表
    List<UserAsk> getUserAskListbyName(String username);

    //通过username得到用户回答列表
    List<UserAnswer> getUserAnswerListByName(String username);

    //通过用户问题ID获取问题答案
    List<UserAnswer> getUserAnswerByAsk(String userAskId);

    /*****************************************************************************/

}
