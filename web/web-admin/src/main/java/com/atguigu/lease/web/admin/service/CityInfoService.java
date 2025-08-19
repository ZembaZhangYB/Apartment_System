package com.atguigu.lease.web.admin.service;

import com.atguigu.lease.model.entity.CityInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author HP
* @description 针对表【city_info】的数据库操作Service
* @createDate 2025-08-15 16:57:22
*/
public interface CityInfoService extends IService<CityInfo> {
    public List<CityInfo> getCityInfoByProvinceID(Long provinceID);

}
