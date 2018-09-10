package com.csu.carefree.Model.Account;

public class UserProfile {
    private String nick_name;
    private String birthday;
    private String gender;
    private String address;
    private String mobile;
    private String image;
    private String email;

    @Override
    public String toString() {
        return "UserProfile{" +
                "nick_name='" + nick_name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", image='" + image + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public UserProfile() {
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserProfile(String nick_name, String birthday,
                       String gender, String address, String mobile, String image, String email) {
        this.nick_name = nick_name;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.mobile = mobile;
        this.image = image;
        this.email = email;
    }
}
