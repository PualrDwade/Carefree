package com.csu.carefree.Service;

import org.springframework.stereotype.Service;

//系统层的服务接口,用来提供各种服务
@Service
public interface SystemService {

    //调用java库的邮箱发送api
    boolean sendEmail(String content, String code, String type);

    //随机得到一个随机的邮箱验证码
    String getRandomCode();
}
