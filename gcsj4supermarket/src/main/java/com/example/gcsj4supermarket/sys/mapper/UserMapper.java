package com.example.gcsj4supermarket.sys.mapper;

import com.example.gcsj4supermarket.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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


    @Update("UPDATE user SET password = #{password} WHERE user_Id = #{userId}")
    int updateById(User user);
    @Select("select * from user where user_Id = #{userId}")
    User getuserbyNa(User user);

    @Select("select * from user where user_name=#{username}")
    User getUserByName(String username);

    @Select("select * from user where user_id=#{userId}")
    User getUserByUId(Integer userId);
}
