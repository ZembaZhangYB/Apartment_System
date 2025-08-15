package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.model.domain.ApartmentFeeValue;
import com.atguigu.lease.model.mapper.ApartmentFeeValueMapper;
import com.atguigu.lease.web.admin.mapper.ApartmentFeeValueMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.service.ApartmentFeeValueService;
import org.springframework.stereotype.Service;

/**
* @author HP
* @description 针对表【apartment_fee_value(公寓&杂费关联表)】的数据库操作Service实现
* @createDate 2025-08-15 16:56:32
*/
@Service
public class ApartmentFeeValueServiceImpl extends ServiceImpl<ApartmentFeeValueMapper, ApartmentFeeValue>
    implements ApartmentFeeValueService{

}




