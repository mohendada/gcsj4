package com.example.gcsj4supermarket.sys.service;

import com.example.gcsj4supermarket.sys.entity.Form;
import com.example.gcsj4supermarket.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
public interface IUserService extends IService<User> {

    Map<String, Object> login(Form form);

    Map<String, Object> getUserInfo(String token);
    User getloginUserInfo(Form form);
    User getforgotUserInfo(String userAccount,String phoneNumber);
    void logout(String token);

    User getuserbyNa(User user);

    void updatePasswordById(User user);
}
