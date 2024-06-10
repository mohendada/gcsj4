package com.example.gcsj4supermarket.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
@Data
public class Employee implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private Integer id;
    @TableField(value = "employee_name")
    private String employeeName;

    private Integer employeeAge;

    private String employeePhoneNumber;

    private Integer employeeWorkStatus;

    private Integer employeeLfap;

    private Integer employeeSickLeave;

    private Float employeeDailyWage;

    private Integer employeeWorkDay;

    private Integer employeePermission;

    public String getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }

    private String employeePosition;

    public Integer getEmployeeSex() {
        return employeeSex;
    }

    public void setEmployeeSex(Integer employeeSex) {
        this.employeeSex = employeeSex;
    }

    private Integer employeeSex;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public Integer getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(Integer employeeAge) {
        this.employeeAge = employeeAge;
    }
    public String getEmployeePhoneNumber() {
        return employeePhoneNumber;
    }

    public void setEmployeePhoneNumber(String employeePhoneNumber) {
        this.employeePhoneNumber = employeePhoneNumber;
    }
    public Integer getEmployeeWorkStatus() {
        return employeeWorkStatus;
    }

    public void setEmployeeWorkStatus(Integer employeeWorkStatus) {
        this.employeeWorkStatus = employeeWorkStatus;
    }
    public Integer getEmployeeLfap() {
        return employeeLfap;
    }

    public void setEmployeeLfap(Integer employeeLfap) {
        this.employeeLfap = employeeLfap;
    }
    public Integer getEmployeeSickLeave() {
        return employeeSickLeave;
    }

    public void setEmployeeSickLeave(Integer employeeSickLeave) {
        this.employeeSickLeave = employeeSickLeave;
    }
    public Float getEmployeeDailyWage() {
        return employeeDailyWage;
    }

    public void setEmployeeDailyWage(Float employeeDailyWage) {
        this.employeeDailyWage = employeeDailyWage;
    }
    public Integer getEmployeeWorkDay() {
        return employeeWorkDay;
    }

    public void setEmployeeWorkDay(Integer employeeWorkDay) {
        this.employeeWorkDay = employeeWorkDay;
    }
    public Integer getEmployeePermission() {
        return employeePermission;
    }

    public void setEmployeePermission(Integer employeePermission) {
        this.employeePermission = employeePermission;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", employeeName='" + employeeName + '\'' +
                ", employeeAge=" + employeeAge +
                ", employeePhoneNumber='" + employeePhoneNumber + '\'' +
                ", employeeWorkStatus=" + employeeWorkStatus +
                ", employeeLfap=" + employeeLfap +
                ", employeeSickLeave=" + employeeSickLeave +
                ", employeeDailyWage=" + employeeDailyWage +
                ", employeeWorkDay=" + employeeWorkDay +
                ", employeePermission=" + employeePermission +
                ", employeePosition='" + employeePosition + '\'' +
                ", employeeSex=" + employeeSex +
                '}';
    }
}
