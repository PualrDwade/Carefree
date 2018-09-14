package com.csu.carefree.Persistence;

import com.csu.carefree.Model.Account.EmailVerifyRecord;

import java.util.List;

//邮箱的持久化接口
public interface EmailVerifyRecordMapper {
    //通过邮箱号码和类型得到验证码
    String getVerifyCode(String username, String type);

    //插入邮箱验证码信息
    boolean setVerifyCode(String username, String code, String type);
}
