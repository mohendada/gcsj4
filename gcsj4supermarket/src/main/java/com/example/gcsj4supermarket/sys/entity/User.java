package com.example.gcsj4supermarket.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
@Data
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String roles;

    @TableField(value = "user_Id")
    private Integer userId;
    @TableField(value = "user_account")
    private String userAccount;

    private String password;
    @TableField(value = "role_ID")
    private Integer roleId;
    @TableField(value = "user_name")
    private String userName;


    private String sex;


    @TableField(value = "phone_number")
    private String phoneNumber;

    private String email;

    private String address;


}
