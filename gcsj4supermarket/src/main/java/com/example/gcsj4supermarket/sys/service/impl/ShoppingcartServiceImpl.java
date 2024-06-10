package com.example.gcsj4supermarket.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.gcsj4supermarket.sys.entity.Shoppingcart;
import com.example.gcsj4supermarket.sys.mapper.ShoppingcartMapper;
import com.example.gcsj4supermarket.sys.service.IShoppingcartService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author li
 * @since 2024-06-10
 */
@Service
public class ShoppingcartServiceImpl extends ServiceImpl<ShoppingcartMapper, Shoppingcart> implements IShoppingcartService {
    @Resource
    private ShoppingcartMapper shoppingcartMapper;

    @Override
    public List<Shoppingcart> GetShoppingcartListById(Integer id) {
        return shoppingcartMapper.GetShoppingcartListById(id);
    }

    @Override
    public IPage pageCC(IPage<Shoppingcart> page, Wrapper<Shoppingcart> wrapper) {
        return shoppingcartMapper.pageCC(page,wrapper);
    }

    @Override
    public IPage pageCC2(IPage<Shoppingcart> page, Wrapper wrapper) {

        return shoppingcartMapper.pageCC(page,wrapper);
    }
}
