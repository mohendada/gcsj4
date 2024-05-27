package com.gcsj4.warehouse.entity;

import lombok.Data;

@Data
public class Employee {
    private Integer employeeId; // 员工编号ID
    private String employeeName; // 员工姓名
    private Integer employeeAge; // 员工年龄
    private String employeePhoneNumber; // 员工联系电话
    private Integer employeeWorkStatus; // 员工在职状态
    private Integer employeeLfap; // 员工事假次数
    private Integer employeeSickLeave; // 员工病假次数
    private Float employeeDailyWage; // 员工每日工资
    private Integer employeeWorkDay; // 员工工作时长
    private Integer employeePermission; // 员工权限
}
