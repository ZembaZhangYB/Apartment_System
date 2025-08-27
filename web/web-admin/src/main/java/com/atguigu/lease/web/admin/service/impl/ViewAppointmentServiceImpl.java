package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.model.entity.ApartmentInfo;
import com.atguigu.lease.model.enums.AppointmentStatus;
import com.atguigu.lease.web.admin.mapper.ApartmentInfoMapper;
import com.atguigu.lease.web.admin.vo.appointment.AppointmentQueryVo;
import com.atguigu.lease.web.admin.vo.appointment.AppointmentVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.ViewAppointment;
import com.atguigu.lease.web.admin.service.ViewAppointmentService;
import com.atguigu.lease.web.admin.mapper.ViewAppointmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author HP
* @description 针对表【view_appointment(预约看房信息表)】的数据库操作Service实现
* @createDate 2025-08-15 16:57:22
*/
@Service
public class ViewAppointmentServiceImpl extends ServiceImpl<ViewAppointmentMapper, ViewAppointment>
    implements ViewAppointmentService{

    @Override
    public boolean updateStatusById(Long id, AppointmentStatus status) {
        LambdaUpdateWrapper<ViewAppointment> luw = new LambdaUpdateWrapper<>();
        luw.eq(ViewAppointment::getId, id).eq(ViewAppointment::getIsDeleted, 0)
                .set(ViewAppointment::getAppointmentStatus, status.getCode());
        viewAppointmentMapper.update(null, luw);

        return true;
    }

    @Autowired
    private ApartmentInfoMapper apartmentInfoMapper;
    @Autowired
    private ViewAppointmentMapper viewAppointmentMapper;

    @Override
    public IPage<AppointmentVo> page(IPage pg, AppointmentQueryVo queryVo) {
        List<AppointmentVo> appointmentVoList = null;
        LambdaQueryWrapper<ApartmentInfo> aqw = new LambdaQueryWrapper<>();
        aqw.eq(ApartmentInfo::getProvinceId, queryVo.getProvinceId()).eq(ApartmentInfo::getCityId, queryVo.getCityId())
                .eq(ApartmentInfo::getDistrictId, queryVo.getDistrictId()).eq(ApartmentInfo::getId, queryVo.getApartmentId())
                .eq(ApartmentInfo::getIsDeleted, 0);
        ApartmentInfo apartmentInfo = apartmentInfoMapper.selectOne(aqw);
        LambdaQueryWrapper<ViewAppointment> vqw = new LambdaQueryWrapper<>();
        vqw.eq(ViewAppointment::getName, queryVo.getName()).eq(ViewAppointment::getApartmentId, queryVo.getApartmentId())
                .eq(ViewAppointment::getPhone, queryVo.getPhone()).eq(ViewAppointment::getIsDeleted, 0);
        List<ViewAppointment> viewAppointments = viewAppointmentMapper.selectList(vqw);
        for(ViewAppointment viewAppointment : viewAppointments){
            AppointmentVo appointmentVo = new AppointmentVo();
            appointmentVo.setId(viewAppointment.getId());
            appointmentVo.setName(viewAppointment.getName());
            appointmentVo.setApartmentId(viewAppointment.getApartmentId());
            appointmentVo.setPhone(viewAppointment.getPhone());
            appointmentVo.setIsDeleted(viewAppointment.getIsDeleted());
            appointmentVo.setUserId(viewAppointment.getUserId());
            appointmentVo.setApartmentInfo(apartmentInfo);
        }
        pg.setRecords(appointmentVoList);

        return pg;
    }
}




