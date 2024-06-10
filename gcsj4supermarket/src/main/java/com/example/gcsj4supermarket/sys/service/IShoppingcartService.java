package com.example.gcsj4supermarket.sys.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.gcsj4supermarket.sys.entity.Shoppingcart;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author li
 * @since 2024-06-10
 */
public interface IShoppingcartService extends IService<Shoppingcart> {
    List<Shoppingcart> GetShoppingcartListById(Integer id);

    IPage pageCC(IPage<Shoppingcart> page, Wrapper<Shoppingcart> wrapper);

    IPage pageCC2(IPage<Shoppingcart> page, Wrapper wrapper);
}
