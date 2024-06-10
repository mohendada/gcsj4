package com.example.gcsj4supermarket.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.example.gcsj4supermarket.sys.entity.Employee;
import com.example.gcsj4supermarket.sys.entity.Shoppingcart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.gcsj4supermarket.sys.entity.Shoppingcart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author li
 * @since 2024-06-10
 */
@Mapper
public interface ShoppingcartMapper extends BaseMapper<Shoppingcart> {

    IPage pageCC(IPage<Shoppingcart> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    IPage pageCC2(IPage<Shoppingcart> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select * from  shoppingcart where user_id = #{id}")
    List<Shoppingcart> GetShoppingcartListById(Integer id);
}
