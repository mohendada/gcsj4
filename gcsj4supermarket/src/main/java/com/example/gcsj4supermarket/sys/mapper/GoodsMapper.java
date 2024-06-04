package com.example.gcsj4supermarket.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.gcsj4supermarket.sys.entity.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zui
 * @since 2024-05-27
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    @Select("SELECT * FROM goods")
    List<Goods> getGoodsList();

    @Insert("INSERT INTO goods (goods_id, goods_name, goods_price, goods_photo, supplier_id) " +
            "VALUES (#{goodsId}, #{goodsName}, #{goodsPrice}, #{goodsPhoto}, #{supplierId})")
    int insert(Goods goods);

    @Select("select * FROM goods WHERE goods_id = #{id}")
    Goods getGoodsById(Integer id);

    @Update("update goods " +
            "set goods_name=#{goodsName},goods_price=#{goodsPrice},goods_photo=#{goodsPhoto},supplier_id=#{supplierId} where goods_Id=#{goodsId}")
    void update(Goods goods);

    @Delete("delete from goods where goods_Id=#{id}")
    void delete(Integer id);

    @Select("select max(goods_Id) from goods")
    int selectId();
}
