package com.example.gcsj4supermarket.sys.entity;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public class Forgotuser {
    private String userAccount;
    private String phoneNumber;
    private String password;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
