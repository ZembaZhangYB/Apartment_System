package com.atguigu.lease.web.app.service;

import com.atguigu.lease.model.entity.ApartmentInfo;
import com.atguigu.lease.web.app.vo.apartment.ApartmentDetailVo;
import com.atguigu.lease.web.app.vo.apartment.ApartmentItemVo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author liubo
 * @description 针对表【apartment_info(公寓信息表)】的数据库操作Service
 * @createDate 2023-07-26 11:12:39
 */
@Mapper
public interface ApartmentInfoService extends IService<ApartmentInfo> {
    ApartmentItemVo selectApartmentItemVoById(Long id);

    ApartmentDetailVo getDetailById(Long id);


}

