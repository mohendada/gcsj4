package com.example.gcsj4supermarket.sys.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author wms
 * @since 2022-10-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Record对象", description="")
public class Record implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "货品id")
    private Integer goodsId;

//    @ApiModelProperty(value = "取货人/补货人")
//    @TableField("userId")
//    private Integer userid;
//
//    @ApiModelProperty(value = "操作人id")
//    private Integer adminId;

    @ApiModelProperty(value = "数量")
    private Integer goodsNumber;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "存储仓号")
    private String goodsStoreId;

    @ApiModelProperty(value = "商品类型")
    private String goodsKind;

    @ApiModelProperty(value = "操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createtime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "inOrout")
    private String action;
}
