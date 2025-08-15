package com.atguigu.lease.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 预约看房信息表
 * @TableName view_appointment
 */
@TableName(value ="view_appointment")
@Data
public class ViewAppointment implements Serializable {
    /**
     * 预约id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 用户姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 用户手机号码
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 公寓id
     */
    @TableField(value = "apartment_id")
    private Integer apartmentId;

    /**
     * 预约时间
     */
    @TableField(value = "appointment_time")
    private Date appointmentTime;

    /**
     * 备注信息
     */
    @TableField(value = "additional_info")
    private String additionalInfo;

    /**
     * 预约状态（1:待看房，2:已取消，3已看房）
     */
    @TableField(value = "appointment_status")
    private Integer appointmentStatus;

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