package com.csu.carefree.Persistence;

import com.csu.carefree.Model.Account.EmailVerifyRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//邮箱的持久化接口
public interface EmailVerifyRecordMapper {

    //通过邮箱Code和type得到验证码
    EmailVerifyRecord getEmailVerifyRecordByCodeAndType(@Param("code") String code, @Param("send_type") String send_type);

    //插入邮箱验证码信息
    void setVerifyCodeRecord(EmailVerifyRecord emailVerifyRecord);
}
