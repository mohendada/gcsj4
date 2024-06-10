package com.example.gcsj4supermarket.sys.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.gcsj4supermarket.sys.entity.Supplier;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
public interface ISupplierService extends IService<Supplier> {
    IPage pageC(IPage<Supplier> page);

    IPage pageCC(IPage<Supplier> page, Wrapper wrapper);
}
