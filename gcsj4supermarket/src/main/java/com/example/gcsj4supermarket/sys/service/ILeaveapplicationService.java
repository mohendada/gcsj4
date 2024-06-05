package com.example.gcsj4supermarket.sys.service;

import com.example.gcsj4supermarket.sys.entity.Leaveapplication;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author li
 * @since 2024-05-30
 */
public interface ILeaveapplicationService extends IService<Leaveapplication> {
    void insertLapp(Leaveapplication leaveapplication);
}
