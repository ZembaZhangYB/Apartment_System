package com.atguigu.lease.web.admin.service;

import com.atguigu.lease.model.entity.RoomInfo;
import com.atguigu.lease.model.enums.ReleaseStatus;
import com.atguigu.lease.web.admin.vo.room.RoomDetailVo;
import com.atguigu.lease.web.admin.vo.room.RoomItemVo;
import com.atguigu.lease.web.admin.vo.room.RoomQueryVo;
import com.atguigu.lease.web.admin.vo.room.RoomSubmitVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author HP
* @description 针对表【room_info(房间信息表)】的数据库操作Service
* @createDate 2025-08-15 16:57:22
*/
public interface RoomInfoService extends IService<RoomInfo> {

    public boolean saveOrUpdate(RoomSubmitVo roomSubmitVo);

    public IPage<RoomItemVo> pageItem(IPage page, RoomQueryVo roomQueryVo);

    public RoomDetailVo getDetailById(Long id);

    public boolean removeById(Long id);

    public boolean updateReleaseStatusById(Long id, ReleaseStatus status);

    public List<RoomInfo> listBasicByApartmentId(Long id);

}
