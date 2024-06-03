package com.example.gcsj4supermarket.sys.entity;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public class Form {
    private String username;
    private String password;

    public Form() {
    }

    public Form(String username, String password) {
        this.username = username;
        this.password = password;
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
}
