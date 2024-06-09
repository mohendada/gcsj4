package com.example.gcsj4supermarket.sys.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.gcsj4supermarket.sys.entity.Store;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wms
 * @since 2024-06-04
 */
public interface IStoreService extends IService<Store> {
    IPage pageCC(IPage<Store> page, Wrapper<Store> wrapper);
    IPage pageCC2(IPage<Store> page, Wrapper wrapper);
}
