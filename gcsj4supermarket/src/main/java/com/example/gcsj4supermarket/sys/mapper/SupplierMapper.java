package com.example.gcsj4supermarket.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.gcsj4supermarket.sys.entity.Supplier;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
@Mapper
public interface SupplierMapper extends BaseMapper<Supplier> {

    IPage pageC(IPage<Supplier> supplierIPage);
    IPage pageCC(IPage<Supplier> supplierIPage ,@Param(Constants.WRAPPER) Wrapper wrapper);

}