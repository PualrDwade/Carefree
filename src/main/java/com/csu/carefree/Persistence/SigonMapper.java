package com.csu.carefree.Persistence;

import com.csu.carefree.Model.Account.Sigon;

// 登陆注册模块的持久化类
public interface SigonMapper {
    //通过用户名得到登陆账号信息
    Sigon getSigon(String username);

    //用过注册账号,
    boolean setSigon(String username, String password);

    //通过邮箱找回密码
    boolean updateSigon(String username, String password);
}
