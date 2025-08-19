package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.web.admin.mapper.CityInfoMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.CityInfo;
import com.atguigu.lease.web.admin.service.CityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author HP
* @description 针对表【city_info】的数据库操作Service实现
* @createDate 2025-08-15 16:57:22
*/
@Service
public class CityInfoServiceImpl extends ServiceImpl<CityInfoMapper, CityInfo>
    implements CityInfoService{
    @Autowired
    private CityInfoMapper cityInfoMapper;

    @Override
    public List<CityInfo> getCityInfoByProvinceID(Long provinceID) {
        LambdaQueryWrapper<CityInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(CityInfo::getProvinceId, provinceID);
        return cityInfoMapper.selectList(lqw);
    }
}




