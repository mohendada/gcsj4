package com.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wms.entity.Supplier;
import com.wms.entity.User;
import com.wms.mapper.SupplierMapper;
import com.wms.mapper.UserMapper;
import com.wms.service.SupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wms
 * @since 2024-06-02
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements SupplierService {
    @Resource
    private SupplierMapper supplierMapper;
    @Override
    public IPage pageC(IPage<Supplier> page) {
        return supplierMapper.pageC(page);
    }

    @Override
    public IPage pageCC(IPage<Supplier> page, Wrapper wrapper) {
        return supplierMapper.pageCC(page,wrapper);
    }
}
