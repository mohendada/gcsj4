package com.example.gcsj4supermarket.sys.mapper;

import com.example.gcsj4supermarket.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user where user_name=#{username} and password=#{password}")
    User getuserbyNa(String username,String password);

}
