package com.atguigu.lease.web.admin.service;

import com.atguigu.lease.model.entity.ViewAppointment;
import com.atguigu.lease.model.enums.AppointmentStatus;
import com.atguigu.lease.web.admin.vo.appointment.AppointmentQueryVo;
import com.atguigu.lease.web.admin.vo.appointment.AppointmentVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @author HP
* @description 针对表【view_appointment(预约看房信息表)】的数据库操作Service
* @createDate 2025-08-15 16:57:22
*/
public interface ViewAppointmentService extends IService<ViewAppointment> {
    public IPage<AppointmentVo> page(IPage pg, AppointmentQueryVo queryVo);

    public boolean updateStatusById(Long id, AppointmentStatus status);
}
