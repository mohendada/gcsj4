package com.example.gcsj4supermarket.sys.entity;

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

    private Integer employeeId;

    private String employeeName;

    private Integer employeeAge;

    private String employeePhoneNumber;

    private Integer employeeWorkStatus;

    private Integer employeeLfap;

    private Integer employeeSickLeave;

    private Float employeeDailyWage;
}