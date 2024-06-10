package com.example.gcsj4supermarket.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.example.gcsj4supermarket.sys.entity.Order;
import com.example.gcsj4supermarket.sys.entity.Store;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.gcsj4supermarket.sys.entity.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
public interface StoreMapper extends BaseMapper<Store> {
    IPage pageCC(IPage<Store> page, @Param(Constants.WRAPPER) Wrapper wrapper);
    IPage pageCC2(IPage<Store> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    void update(Order order);

    @Update("update store set goods_number = goods_number-#{num} where goods_id= #{id}")
    void storeDeduce(int id, int num);


    @Select("select * from store where goods_id=${goodsId}")
    Store selectByGoodsId(int goodsId);
}
