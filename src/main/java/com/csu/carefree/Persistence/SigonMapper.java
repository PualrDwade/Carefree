package com.csu.carefree.Persistence;

import com.csu.carefree.Model.Account.Sigon;
import org.apache.ibatis.annotations.Param;

// 登陆注册模块的持久化类
public interface SigonMapper {
    //通过用户名得到登陆账号信息
    Sigon getSigonByUserName(String username);

    //用来注册账号,
    void setSigon(@Param("username") String username, @Param("password") String password);

    //通过找回密码
    void updateSigon(@Param("username") String username,@Param("password") String password);
    void updateSigon(@Param("username") String username, @Param("password") String password);
}
