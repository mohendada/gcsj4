package com.example.gcsj4supermarket.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gcsj4supermarket.sys.entity.Refound;
import com.example.gcsj4supermarket.sys.mapper.RefoundMapper;
import com.example.gcsj4supermarket.sys.service.IRefoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
@Service
public class RefoundServiceImpl extends ServiceImpl<RefoundMapper, Refound> implements IRefoundService {

    @Autowired
    RefoundMapper refoundMapper;


    @Override
    public IPage<Refound> getAllRefounds(Page<Refound> page) {
        return refoundMapper.selectPage(page, null);
    }
}
