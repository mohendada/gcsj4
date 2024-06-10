package com.example.gcsj4supermarket.sys.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.gcsj4supermarket.sys.entity.Order;
import com.example.gcsj4supermarket.sys.entity.Store;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.gcsj4supermarket.sys.entity.Store;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
public interface IStoreService extends IService<Store> {

    void finishOrder(Order order);
    IPage pageCC(IPage<Store> page, Wrapper<Store> wrapper);
    IPage pageCC2(IPage<Store> page, Wrapper wrapper);
}
