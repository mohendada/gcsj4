package com.wms.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.entity.Goods;
import com.wms.entity.Store;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wms
 * @since 2024-06-04
 */
public interface StoreService extends IService<Store> {
    IPage pageCC(IPage<Store> page, Wrapper wrapper);
    IPage pageCC2(IPage<Store> page, Wrapper wrapper);
}
