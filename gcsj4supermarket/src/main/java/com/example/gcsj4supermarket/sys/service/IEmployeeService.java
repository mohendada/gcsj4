package com.example.gcsj4supermarket.sys.service;

import com.example.gcsj4supermarket.sys.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
public interface IEmployeeService extends IService<Employee> {
    List<Employee> GetDeliverymanList();
}
