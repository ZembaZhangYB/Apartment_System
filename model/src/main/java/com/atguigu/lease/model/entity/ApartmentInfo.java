package com.atguigu.lease.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 公寓信息表
 * @TableName apartment_info
 */
@TableName(value ="apartment_info")
@Data
public class ApartmentInfo implements Serializable {
    /**
     * 公寓id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 公寓名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 公寓介绍
     */
    @TableField(value = "introduction")
    private String introduction;

    /**
     * 所处区域id
     */
    @TableField(value = "district_id")
    private Long districtId;

    /**
     * 区域名称
     */
    @TableField(value = "district_name")
    private String districtName;

    /**
     * 所处城市id
     */
    @TableField(value = "city_id")
    private Long cityId;

    /**
     * 城市名称
     */
    @TableField(value = "city_name")
    private String cityName;

    /**
     * 所处省份id
     */
    @TableField(value = "province_id")
    private Long provinceId;

    /**
     * 省份名称
     */
    @TableField(value = "province_name")
    private String provinceName;

    /**
     * 详细地址
     */
    @TableField(value = "address_detail")
    private String addressDetail;

    /**
     * 经度
     */
    @TableField(value = "latitude")
    private String latitude;

    /**
     * 纬度
     */
    @TableField(value = "longitude")
    private String longitude;

    /**
     * 公寓前台电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 是否发布（1:发布，0:未发布）
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