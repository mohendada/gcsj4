package com.example.gcsj4supermarket.sys.mapper;

import com.example.gcsj4supermarket.sys.entity.Order;
import com.example.gcsj4supermarket.sys.entity.Store;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
public interface StoreMapper extends BaseMapper<Store> {

    void update(Order order);

    @Update("update store set goods_number = goods_number-#{num} where goods_id= #{id}")
    void storeDeduce(int id, int num);

}
