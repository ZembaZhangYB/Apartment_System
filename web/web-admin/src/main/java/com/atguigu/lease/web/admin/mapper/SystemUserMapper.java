package com.atguigu.lease.web.admin.mapper;

import com.atguigu.lease.model.entity.SystemUser;
import com.atguigu.lease.web.admin.vo.system.user.SystemUserItemVo;
import com.atguigu.lease.web.admin.vo.system.user.SystemUserQueryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;

/**
* @author HP
* @description 针对表【system_user(员工信息表)】的数据库操作Mapper
* @createDate 2025-08-15 16:57:22
* @Entity com.atguigu.model.domain.SystemUser
*/
@Mapper
public interface SystemUserMapper extends BaseMapper<SystemUser> {
    IPage<SystemUserItemVo> pageSystemUserByQuery(IPage<SystemUser> page, SystemUserQueryVo queryVo);

    SystemUser selectOneByUsername(String username);
}




