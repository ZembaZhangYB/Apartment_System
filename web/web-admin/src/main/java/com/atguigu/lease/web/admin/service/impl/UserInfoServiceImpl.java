package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.web.admin.mapper.UserInfoMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.domain.UserInfo;
import com.atguigu.lease.model.service.UserInfoService;
import com.atguigu.lease.model.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author HP
* @description 针对表【user_info(用户信息表)】的数据库操作Service实现
* @createDate 2025-08-15 16:57:22
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService{

}




