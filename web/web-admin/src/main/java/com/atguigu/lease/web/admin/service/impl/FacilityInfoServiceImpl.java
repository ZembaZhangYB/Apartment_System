package com.atguigu.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.FacilityInfo;
import com.atguigu.lease.web.admin.service.FacilityInfoService;
import com.atguigu.lease.web.admin.mapper.FacilityInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author HP
* @description 针对表【facility_info(配套信息表)】的数据库操作Service实现
* @createDate 2025-08-15 16:57:22
*/
@Service
public class FacilityInfoServiceImpl extends ServiceImpl<FacilityInfoMapper, FacilityInfo>
    implements FacilityInfoService{

    @Autowired
    FacilityInfoMapper facilityInfoMapper;

    @Override
    public boolean deleteFacilityInfo(Long facilityId) {
        LambdaUpdateWrapper<FacilityInfo> luw = new LambdaUpdateWrapper<>();
        luw.eq(FacilityInfo::getId, facilityId)
                .set(FacilityInfo::getIsDeleted, 1);
        int changeRow = facilityInfoMapper.update(null, luw);
        return changeRow > 0;
    }

    @Override
    public List<FacilityInfo> getFacilityInfoByFacilityId(Integer facilityId) {
        LambdaQueryWrapper<FacilityInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(FacilityInfo::getType, facilityId).eq(FacilityInfo::getIsDeleted, 0);
        return facilityInfoMapper.selectList(lqw);
    }

    @Override
    public boolean saveOrUpdateFacilityInfo(FacilityInfo facilityInfo) {
        if(facilityInfoMapper.selectById(facilityInfo.getId()) != null){
            //以下是修改逻辑
            int changeRow = facilityInfoMapper.updateById(facilityInfo);
            return changeRow > 0;
        }else{
            int changeRow = facilityInfoMapper.insert(facilityInfo);
            return changeRow > 0;
        }
    }
}




