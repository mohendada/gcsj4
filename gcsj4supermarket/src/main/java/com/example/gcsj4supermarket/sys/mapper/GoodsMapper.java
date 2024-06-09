package com.example.gcsj4supermarket.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.gcsj4supermarket.sys.entity.Goods;
import org.apache.ibatis.annotations.*;

import java.io.Serializable;


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

//    @Select("SELECT * FROM goods")
//    List<Goods> getGoodsList();

    @Insert("INSERT INTO goods (goods_id, goods_name, goods_price, goods_photo, supplier_id, goods_status, goods_type,goods_desc) " +
            "VALUES (#{goodsId}, #{goodsName}, #{goodsPrice}, #{goodsPhoto}, #{supplierId},#{goodsStatus},#{goodsType},#{goodsDesc})")
    int insert(Goods goods);

    @Select("select * FROM goods WHERE goods_id = #{id}")
    Goods getGoodsById(Integer id);

    void updateGoods(Goods goods);

    @Delete("delete from goods where goods_Id=#{id}")
    void delete(Integer id);

    @Select("select max(goods_Id) from goods")
    int selectId();

    @Select(("select * from goods where goods_name = #{orderName}"))
    Goods selectByName(String orderName);

    @Select("select  goods_Id from goods where goods_name=#{name}")
    int SelectByName(String orderName);
}
