package com.example.gcsj4supermarket.sys.entity;

import lombok.Data;

@Data
public class Supplier {
    private Integer supplierId; // 供货商编号ID
    private String supplierPhoneNumber; // 联系电话
    private String supplierAddress; // 供货地址
    private String supplierName; // 公司名称
}
