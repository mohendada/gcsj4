package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.entity.Goods;
import com.wms.entity.Store;
import com.wms.mapper.GoodsMapper;
import com.wms.mapper.StoreMapper;
import com.wms.service.StoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wms
 * @since 2024-06-04
 */
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {
    @Resource
    private StoreMapper storeMapper;
    @Override
    public IPage pageCC(IPage<Store> page, Wrapper wrapper) {
        return storeMapper.pageCC(page,wrapper);
    }

    @Override
    public IPage pageCC2(IPage<Store> page, Wrapper wrapper) {

        return storeMapper.pageCC(page,wrapper);
    }
}
