package com.example.gcsj4supermarket.sys.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author li
 * @since 2024-05-27
 */
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer employeeId;

    private String employeeName;

    private Integer employeeAge;

    private String employeePhoneNumber;

    private Integer employeeWorkStatus;

    private Integer employeeLfap;

    private Integer employeeSickLeave;

    private Float employeeDailyWage;

    private Integer employeeWorkDay;

    private Integer employeePermission;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
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
            "employeeId=" + employeeId +
            ", employeeName=" + employeeName +
            ", employeeAge=" + employeeAge +
            ", employeePhoneNumber=" + employeePhoneNumber +
            ", employeeWorkStatus=" + employeeWorkStatus +
            ", employeeLfap=" + employeeLfap +
            ", employeeSickLeave=" + employeeSickLeave +
            ", employeeDailyWage=" + employeeDailyWage +
            ", employeeWorkDay=" + employeeWorkDay +
            ", employeePermission=" + employeePermission +
        "}";
    }
}
