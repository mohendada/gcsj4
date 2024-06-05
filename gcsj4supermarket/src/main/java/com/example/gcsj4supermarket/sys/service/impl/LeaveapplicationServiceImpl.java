package com.example.gcsj4supermarket.sys.service.impl;

import com.example.gcsj4supermarket.sys.entity.Leaveapplication;
import com.example.gcsj4supermarket.sys.mapper.LeaveapplicationMapper;
import com.example.gcsj4supermarket.sys.service.ILeaveapplicationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author li
 * @since 2024-05-30
 */
@Service
public class LeaveapplicationServiceImpl extends ServiceImpl<LeaveapplicationMapper, Leaveapplication> implements ILeaveapplicationService {
    @Autowired
    LeaveapplicationMapper leaveapplicationMapper;
    @Override
    public void insertLapp(Leaveapplication leaveapplication) {
        leaveapplicationMapper.insertnewLapp(leaveapplication);
    }
}
