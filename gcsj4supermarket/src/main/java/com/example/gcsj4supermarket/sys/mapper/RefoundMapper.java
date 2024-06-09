package com.example.gcsj4supermarket.sys.mapper;

import com.example.gcsj4supermarket.sys.entity.Refound;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
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
public interface RefoundMapper extends BaseMapper<Refound> {

    @Select("select max(refound.return_goods_id) from refound")
    Integer selectMaxId();

    @Select("select * from refound where return_order_id=#{oederId}")
    Refound searchByOrderId(Integer orderId);

    @Delete("delete from refound where return_goods_id=#{returnGoodsId}")
    void deleteRefound(Integer returnGoodsId);

    @Update("update refound set return_statue=#{returnStatue}  where return_goods_id=#{returnGoodsId}")
    void updateStatus(Integer returnGoodsId, Integer returnStatue);
}
