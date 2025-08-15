package com.atguigu.lease.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 支付方式表
 * @TableName payment_type
 */
@TableName(value ="payment_type")
@Data
public class PaymentType implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 付款方式名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 每次支付租期数
     */
    @TableField(value = "pay_month_count")
    private Integer payMonthCount;

    /**
     * 付费说明
     */
    @TableField(value = "additional_info")
    private String additionalInfo;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}