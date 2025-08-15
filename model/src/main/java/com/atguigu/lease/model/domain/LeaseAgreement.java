package com.atguigu.lease.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 租约信息表
 * @TableName lease_agreement
 */
@TableName(value ="lease_agreement")
@Data
public class LeaseAgreement implements Serializable {
    /**
     * 租约id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 承租人手机号码
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 承租人姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 承租人身份证号码
     */
    @TableField(value = "identification_number")
    private String identificationNumber;

    /**
     * 签约公寓id
     */
    @TableField(value = "apartment_id")
    private Long apartmentId;

    /**
     * 签约房间id
     */
    @TableField(value = "room_id")
    private Long roomId;

    /**
     * 租约开始日期
     */
    @TableField(value = "lease_start_date")
    private Date leaseStartDate;

    /**
     * 租约结束日期
     */
    @TableField(value = "lease_end_date")
    private Date leaseEndDate;

    /**
     * 租期id
     */
    @TableField(value = "lease_term_id")
    private Long leaseTermId;

    /**
     * 租金（元/月）
     */
    @TableField(value = "rent")
    private BigDecimal rent;

    /**
     * 押金（元）
     */
    @TableField(value = "deposit")
    private BigDecimal deposit;

    /**
     * 支付类型id

     */
    @TableField(value = "payment_type_id")
    private Long paymentTypeId;

    /**
     * 租约状态（1:签约待确认，2:已签约，3:已取消，4:已到期，5:退租待确认，6:已退租，7:续约待确认）
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 租约来源（1:新签，2:续约）
     */
    @TableField(value = "source_type")
    private Integer sourceType;

    /**
     * 备注信息
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