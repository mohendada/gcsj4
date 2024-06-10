package com.example.gcsj4supermarket.sys.mapper;

import com.example.gcsj4supermarket.sys.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.gcsj4supermarket.sys.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
    @Select("select * from  employee where employee_position = 'deliveryman'")
    List<Employee> GetDeliverymanList();
}
