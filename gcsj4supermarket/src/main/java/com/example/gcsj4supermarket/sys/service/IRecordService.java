package com.example.gcsj4supermarket.sys.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.gcsj4supermarket.sys.entity.Record;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wms
 * @since 2022-10-16
 */
public interface IRecordService extends IService<Record> {

    IPage<Record> pageCC(IPage<Record> page, Wrapper wrapper);
}
