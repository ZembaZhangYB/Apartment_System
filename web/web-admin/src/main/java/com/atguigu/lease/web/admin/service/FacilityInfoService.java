package com.atguigu.lease.web.admin.service;

import com.atguigu.lease.model.entity.FacilityInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author HP
* @description 针对表【facility_info(配套信息表)】的数据库操作Service
* @createDate 2025-08-15 16:57:22
*/
public interface FacilityInfoService extends IService<FacilityInfo> {

    public List<FacilityInfo> getFacilityInfoByFacilityId(Integer facilityId);

    public boolean saveOrUpdateFacilityInfo(FacilityInfo facilityInfo);

    public boolean deleteFacilityInfo(Long facilityId);

}
