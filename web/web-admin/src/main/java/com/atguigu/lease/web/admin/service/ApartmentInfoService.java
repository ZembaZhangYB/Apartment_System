package com.atguigu.lease.web.admin.service;

import com.atguigu.lease.model.entity.ApartmentInfo;
import com.atguigu.lease.web.admin.vo.apartment.ApartmentDetailVo;
import com.atguigu.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.atguigu.lease.web.admin.vo.apartment.ApartmentQueryVo;
import com.atguigu.lease.web.admin.vo.apartment.ApartmentSubmitVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @author HP
* @description 针对表【apartment_info(公寓信息表)】的数据库操作Service
* @createDate 2025-08-15 16:56:48
*/
public interface ApartmentInfoService extends IService<ApartmentInfo> {
    public boolean saveOrUpdate(ApartmentSubmitVo apartmentSubmitVo);

    public IPage<ApartmentItemVo> getApartmentInfoList(IPage<ApartmentItemVo> page, ApartmentQueryVo queryVo);

    public ApartmentDetailVo getApartmentDetailVoById(Long id);
}
