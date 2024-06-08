package com.wms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author wms
 * @since 2024-06-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Supplier对象", description="")
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "supplier_id", type = IdType.AUTO)
    @ApiModelProperty(value = "供应商编号")
    private Integer supplierId;
    @ApiModelProperty(value = "供应商电话")
    private String supplierPhoneNumber;
    @ApiModelProperty(value = "供应商地址")
    private String supplierAddress;
    @ApiModelProperty(value = "公司名称")
    private String supplierName;


}
