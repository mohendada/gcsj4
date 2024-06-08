package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.entity.Supplier;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wms.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wms
 * @since 2024-06-02
 */
public interface SupplierService extends IService<Supplier> {
    IPage pageC(IPage<Supplier> page);

    IPage pageCC(IPage<Supplier> page, Wrapper wrapper);
}
