package com.atguigu.lease.web.admin.mapper;

import com.atguigu.lease.model.entity.RoomLeaseTerm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author HP
* @description 针对表【room_lease_term(房间租期管理表)】的数据库操作Mapper
* @createDate 2025-08-15 16:57:22
* @Entity com.atguigu.model.domain.RoomLeaseTerm
*/
@Mapper
public interface RoomLeaseTermMapper extends BaseMapper<RoomLeaseTerm> {

}




