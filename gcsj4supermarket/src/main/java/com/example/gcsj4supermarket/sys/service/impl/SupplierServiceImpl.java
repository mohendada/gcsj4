package com.example.gcsj4supermarket.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.gcsj4supermarket.sys.entity.Supplier;
import com.example.gcsj4supermarket.sys.mapper.SupplierMapper;
import com.example.gcsj4supermarket.sys.service.ISupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements ISupplierService {
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
