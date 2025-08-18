package com.atguigu.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.SystemUser;
import com.atguigu.lease.web.admin.service.SystemUserService;
import com.atguigu.lease.web.admin.mapper.SystemUserMapper;
import org.springframework.stereotype.Service;

/**
* @author HP
* @description 针对表【system_user(员工信息表)】的数据库操作Service实现
* @createDate 2025-08-15 16:57:22
*/
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser>
    implements SystemUserService{

}




