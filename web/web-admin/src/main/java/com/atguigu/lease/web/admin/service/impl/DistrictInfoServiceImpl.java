package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.web.admin.mapper.DistrictInfoMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.DistrictInfo;
import com.atguigu.lease.web.admin.service.DistrictInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author HP
* @description 针对表【district_info】的数据库操作Service实现
* @createDate 2025-08-15 16:57:22
*/
@Service
public class DistrictInfoServiceImpl extends ServiceImpl<DistrictInfoMapper, DistrictInfo>
    implements DistrictInfoService{
    @Autowired
    private DistrictInfoMapper districtInfoMapper;

    @Override
    public List<DistrictInfo> getDistrictInfoByCityID(Long id) {
        LambdaQueryWrapper<DistrictInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(DistrictInfo::getCityId, id);
        return districtInfoMapper.selectList(lqw);
    }
}




