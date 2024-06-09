package com.example.gcsj4supermarket.sys.mapper;

import com.example.gcsj4supermarket.sys.entity.Refound;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

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
}
