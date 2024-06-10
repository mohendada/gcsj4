package com.example.gcsj4supermarket.sys.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.gcsj4supermarket.sys.entity.Shoppingcart;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wms
 * @since 2024-06-09
 */
public interface IShoppingcartService extends IService<Shoppingcart> {
    IPage pageCC(IPage<Shoppingcart> page, Wrapper<Shoppingcart> wrapper);
    IPage pageCC2(IPage<Shoppingcart> page, Wrapper wrapper);
}
