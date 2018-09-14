package com.csu.carefree.Persistence;

import com.csu.carefree.Model.Account.Sigon;
import com.csu.carefree.Model.Account.UserProfile;

//用户操作的接口
public interface UserProfileMapper {

    //通过userprofile创建用户详细信息
    boolean setUserProfile(UserProfile userProfile);

    //通过username得到用户详细信息
    UserProfile getUserProfile(String username);

    //用过userprofile更新用户详细信息
    boolean updateUserProfile(UserProfile userProfile);
}
