package com.atguigu.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.SystemPost;
import com.atguigu.lease.web.admin.service.SystemPostService;
import com.atguigu.lease.web.admin.mapper.SystemPostMapper;
import org.springframework.stereotype.Service;

/**
* @author HP
* @description 针对表【system_post(岗位信息表)】的数据库操作Service实现
* @createDate 2025-08-15 16:57:22
*/
@Service
public class SystemPostServiceImpl extends ServiceImpl<SystemPostMapper, SystemPost>
    implements SystemPostService{

}




