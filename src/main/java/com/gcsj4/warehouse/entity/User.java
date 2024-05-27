package com.gcsj4.warehouse.entity;

import lombok.Data;

@Data
public class User {
    private Integer userId; // 用户ID
    private String userAccount; // 账号
    private String password; // 密码
    private Integer roleId; // 角色ID
    private String userName; // 用户名
    private String sex; // 性别
    private String phoneNumber; // 联系电话
    private String email; // 邮箱
    private String address; // 送货地址

    // Getters and Setters
    // ...
}
