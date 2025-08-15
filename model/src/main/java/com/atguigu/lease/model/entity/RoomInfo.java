package com.atguigu.lease.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 房间信息表
 * @TableName room_info
 */
@TableName(value ="room_info")
@Data
public class RoomInfo implements Serializable {
    /**
     * 房间id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 房间号
     */
    @TableField(value = "room_number")
    private String roomNumber;

    /**
     * 租金（元/月）
     */
    @TableField(value = "rent")
    private BigDecimal rent;

    /**
     * 所属公寓id
     */
    @TableField(value = "apartment_id")
    private Long apartmentId;

    /**
     * 是否发布
     */
    @TableField(value = "is_release")
    private Integer isRelease;

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