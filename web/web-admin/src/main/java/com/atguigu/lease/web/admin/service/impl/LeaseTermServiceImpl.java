package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.web.admin.mapper.LeaseTermMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.LeaseTerm;
import com.atguigu.lease.web.admin.service.LeaseTermService;
import org.springframework.stereotype.Service;

/**
* @author HP
* @description 针对表【lease_term(租期)】的数据库操作Service实现
* @createDate 2025-08-15 16:57:22
*/
@Service
public class LeaseTermServiceImpl extends ServiceImpl<LeaseTermMapper, LeaseTerm>
    implements LeaseTermService{

}




