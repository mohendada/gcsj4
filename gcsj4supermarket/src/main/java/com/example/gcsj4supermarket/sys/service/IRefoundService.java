package com.example.gcsj4supermarket.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gcsj4supermarket.sys.entity.Order;
import com.example.gcsj4supermarket.sys.entity.Refound;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
public interface IRefoundService extends IService<Refound> {

    IPage<Refound> getAllRefounds(Page<Refound> page);

    void generate(int orderid);

    void request(Integer orderid);

    void confirm(Integer orderid, Integer status);
}
