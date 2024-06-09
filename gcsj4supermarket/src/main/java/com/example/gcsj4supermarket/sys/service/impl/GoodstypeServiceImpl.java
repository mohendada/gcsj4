package com.example.gcsj4supermarket.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gcsj4supermarket.sys.entity.Goodstype;
import com.example.gcsj4supermarket.sys.mapper.GoodstypeMapper;
import com.example.gcsj4supermarket.sys.service.IGoodstypeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;



/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wms
 * @since 2022-10-15
 */
@Service
public class GoodstypeServiceImpl extends ServiceImpl<GoodstypeMapper, Goodstype> implements IGoodstypeService {

    @Resource
    private GoodstypeMapper goodstypeMapper;
    @Override
    public IPage pageCC(IPage<Goodstype> page, Wrapper wrapper) {
        return goodstypeMapper.pageCC(page,wrapper);
    }
}
