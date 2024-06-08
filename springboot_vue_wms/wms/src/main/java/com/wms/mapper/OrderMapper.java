package com.wms.mapper;

import com.wms.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wms
 * @since 2024-06-06
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
