package com.atguigu.lease.web.admin.mapper;

import com.atguigu.lease.model.entity.LeaseAgreement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author HP
* @description 针对表【lease_agreement(租约信息表)】的数据库操作Mapper
* @createDate 2025-08-15 16:57:22
* @Entity com.atguigu.model.domain.LeaseAgreement
*/
@Mapper
public interface LeaseAgreementMapper extends BaseMapper<LeaseAgreement> {

}




