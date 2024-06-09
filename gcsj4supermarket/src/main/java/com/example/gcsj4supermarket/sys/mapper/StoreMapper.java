package com.example.gcsj4supermarket.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.gcsj4supermarket.sys.entity.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wms
 * @since 2024-06-04
 */
@Mapper
public interface StoreMapper extends BaseMapper<Store> {
    IPage pageCC(IPage<Store> page, @Param(Constants.WRAPPER) Wrapper wrapper);
    IPage pageCC2(IPage<Store> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
