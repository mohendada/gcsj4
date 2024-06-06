package com.example.gcsj4supermarket.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.gcsj4supermarket.sys.entity.Form;
import com.example.gcsj4supermarket.sys.entity.User;
import com.example.gcsj4supermarket.sys.mapper.UserMapper;
import com.example.gcsj4supermarket.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserMapper userMapper;
    @Override
    public Map<String, Object> login(Form form) {
                //根据用户名和密码查询
        User user=new User();
        user.setUserName(form.getUsername());
        user.setPassword(form.getPassword());
       LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,user.getUserName());
        wrapper.eq(User::getPassword,user.getPassword());
        //结果不为空则生成token，
       User loginUser=this.baseMapper.selectOne(wrapper);
       if(loginUser !=null){
           Map<String, Object> data=new HashMap<>();
           String key="user:"+UUID.randomUUID();
           //存入redis
           loginUser.setPassword(null);
           redisTemplate.opsForValue().set(key,loginUser,30,TimeUnit.MINUTES);

           data.put("token",key);
           return data;
       }
       return null;
//        //根据用户名和密码查询
//        User loginUser=userMapper.getuserbyNa(form.getUsername(),form.getPassword());
//
//        //结果不为空则生成token，
//
//       if(loginUser !=null){
//           Map<String, Object> data=new HashMap<>();
//           data.put("userName",loginUser.getUserName());
//           return data;
//       }
//       return null;
    }

    @Override
    public Map<String, Object> getUserInfo(String token) {
        //根据token获取用户信息，redis,(序列化对象)
       Object obj= redisTemplate.opsForValue().get(token);
       if(obj!=null){
           //反序列化
         User loginUser=JSON.parseObject(JSON.toJSONString(obj),User.class);
            //取数据
           Map<String,Object>data=new HashMap<>();
           //用户
           data.put("name",loginUser.getUserName());
           //角色
           List<String> list=new ArrayList<>();
            list.add(loginUser.getRoles());
           data.put("roles",list);
           data.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
           return data;
       }
        return null;
    }

    @Override
    public User getloginUserInfo(Form form) {
        User user=new User();
        user.setUserName(form.getUsername());
        user.setPassword(form.getPassword());
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,user.getUserName());
        wrapper.eq(User::getPassword,user.getPassword());
        User loginUser=this.baseMapper.selectOne(wrapper);
        return loginUser;
    }

    @Override
    public User getforgotUserInfo(String userAccount, String phoneNumber) {
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,userAccount);
        wrapper.eq(User::getPhoneNumber,phoneNumber);
        User loginUser=this.baseMapper.selectOne(wrapper);
        return loginUser;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }

    @Override
    public User getuserbyNa(User user) {
        return userMapper.getuserbyNa(user);
    }

    @Override
    public void updatePasswordById(User user) {
        userMapper.updateById(user);
    }


}
