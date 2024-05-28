package com.gcsj4.warehouse.mapper;

import com.gcsj4.warehouse.entity.Goods;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsMapper {

    @Select("SELECT goods_id AS goodsId, goods_name AS goodsName, goods_price AS goodsPrice, goods_photo AS goodsPhoto, supplier_id AS supplierId FROM goods")
    List<Goods> getGoodsList();

    @Insert("INSERT INTO goods (goods_id, goods_name, goods_price, goods_photo, supplier_id) " +
            "VALUES (#{goodsId}, #{goodsName}, #{goodsPrice}, #{goodsPhoto}, #{supplierId})")
    void insertGoods(Goods goods);

    @Select("SELECT goods_id AS goodsId, goods_name AS goodsName, goods_price AS goodsPrice, goods_photo AS goodsPhoto, supplier_id AS supplierId FROM goods WHERE goods_id = #{id}")
    Goods getGoodsById(Integer id);
}
