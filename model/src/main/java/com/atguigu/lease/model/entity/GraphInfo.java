package com.atguigu.lease.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 图片信息表
 * @TableName graph_info
 */
@TableName(value ="graph_info")
@Data
public class GraphInfo implements Serializable {
    /**
     * 图片id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 图片名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 图片所属对象类型（1:apartment,2:room）
     */
    @TableField(value = "item_type")
    private Integer itemType;

    /**
     * 图片所有对象id
     */
    @TableField(value = "item_id")
    private Long itemId;

    /**
     * 图片地址
     */
    @TableField(value = "url")
    private String url;

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