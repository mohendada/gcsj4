package com.example.gcsj4supermarket.sys.service.impl;

import com.example.gcsj4supermarket.sys.entity.Employee;
import com.example.gcsj4supermarket.sys.mapper.EmployeeMapper;
import com.example.gcsj4supermarket.sys.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> GetDeliverymanList(){
        return employeeMapper.GetDeliverymanList();
    }

}
