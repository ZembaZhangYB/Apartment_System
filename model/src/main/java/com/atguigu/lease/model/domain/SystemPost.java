package com.atguigu.lease.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 岗位信息表
 * @TableName system_post
 */
@TableName(value ="system_post")
@Data
public class SystemPost implements Serializable {
    /**
     * 岗位ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 岗位编码
     */
    @TableField(value = "code")
    private String code;

    /**
     * 岗位名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 状态（1正常 0停用）
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 删除标记（0:可用 1:已删除）
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}