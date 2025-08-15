package com.atguigu.lease.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName district_info
 */
@TableName(value ="district_info")
@Data
public class DistrictInfo implements Serializable {
    /**
     * 区域id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 区域名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 所属城市id
     */
    @TableField(value = "city_id")
    private Integer cityId;

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