package com.csu.carefree.Model.Account;


import java.io.Serializable;

// 用户登陆注册的javabean
public class Sigon implements Serializable {
    private String username;
    private String password;

    @Override
    public String toString() {
        return "Sigon{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Sigon() {
    }

    public Sigon(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
