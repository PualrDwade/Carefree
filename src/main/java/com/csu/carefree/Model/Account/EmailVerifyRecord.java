package com.csu.carefree.Model.Account;

import java.io.Serializable;

public class EmailVerifyRecord implements Serializable {
    private String code;
    private String email;
    private String send_type;
    private String send_time;

    @Override
    public String toString() {
        return "EmailVerifyRecord{" +
                "code='" + code + '\'' +
                ", email='" + email + '\'' +
                ", send_type='" + send_type + '\'' +
                ", send_time='" + send_time + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSend_type() {
        return send_type;
    }

    public void setSend_type(String send_type) {
        this.send_type = send_type;
    }

    public String getSend_time() {
        return send_time;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }

    public EmailVerifyRecord() {
    }

    public String getRandomCode() {
        //得到随机验证码
        return null;
    }

    public EmailVerifyRecord(String code, String email, String send_type, String send_time) {
        this.code = code;
        this.email = email;
        this.send_type = send_type;
        this.send_time = send_time;
    }
}
