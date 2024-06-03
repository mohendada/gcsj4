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
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer supplierId;

    private String supplierPhoneNumber;

    private String supplierAddress;

    private String supplierName;

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }
    public String getSupplierPhoneNumber() {
        return supplierPhoneNumber;
    }

    public void setSupplierPhoneNumber(String supplierPhoneNumber) {
        this.supplierPhoneNumber = supplierPhoneNumber;
    }
    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Override
    public String toString() {
        return "Supplier{" +
            "supplierId=" + supplierId +
            ", supplierPhoneNumber=" + supplierPhoneNumber +
            ", supplierAddress=" + supplierAddress +
            ", supplierName=" + supplierName +
        "}";
    }
}
