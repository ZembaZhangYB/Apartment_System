package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.model.entity.*;
import com.atguigu.lease.model.enums.ReleaseStatus;
import com.atguigu.lease.web.admin.mapper.*;
import com.atguigu.lease.web.admin.vo.attr.AttrValueVo;
import com.atguigu.lease.web.admin.vo.graph.GraphVo;
import com.atguigu.lease.web.admin.vo.room.RoomDetailVo;
import com.atguigu.lease.web.admin.vo.room.RoomItemVo;
import com.atguigu.lease.web.admin.vo.room.RoomQueryVo;
import com.atguigu.lease.web.admin.vo.room.RoomSubmitVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.web.admin.service.RoomInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author HP
* @description 针对表【room_info(房间信息表)】的数据库操作Service实现
* @createDate 2025-08-15 16:57:22
*/
@Service
public class RoomInfoServiceImpl extends ServiceImpl<RoomInfoMapper, RoomInfo>
    implements RoomInfoService{

    @Autowired
    private ApartmentInfoMapper apartmentInfoMapper;
    @Autowired
    private GraphInfoMapper graphInfoMapper;
    @Autowired
    private RoomInfoMapper roomInfoMapper;
    @Autowired
    private RoomAttrValueMapper roomAttrValueMapper;
    @Autowired
    private AttrValueMapper attrValueMapper;
    @Autowired
    private AttrKeyMapper attrKeyMapper;
    @Autowired
    private RoomFacilityMapper roomFacilityMapper;
    @Autowired
    private FacilityInfoMapper facilityInfoMapper;
    @Autowired
    private RoomLabelMapper roomLabelMapper;
    @Autowired
    private LabelInfoMapper labelInfoMapper;
    @Autowired
    private RoomPaymentTypeMapper roomPaymentTypeMapper;
    @Autowired
    private PaymentTypeMapper paymentTypeMapper;
    @Autowired
    private RoomLeaseTermMapper roomLeaseTermMapper;
    @Autowired
    private LeaseTermMapper leaseTermMapper;

    @Override
    public RoomDetailVo getDetailById(Long id) {
        RoomDetailVo roomDetailVo = new RoomDetailVo();
        LambdaQueryWrapper<RoomInfo> rqw = new LambdaQueryWrapper<>();
        rqw.eq(RoomInfo::getId, id).eq(RoomInfo::getIsDeleted, 0);
        RoomInfo ri = roomInfoMapper.selectOne(rqw);
        Long apartmentId = ri.getApartmentId();
        LambdaQueryWrapper<ApartmentInfo> apqw = new LambdaQueryWrapper<>();
        apqw.eq(ApartmentInfo::getId, apartmentId).eq(ApartmentInfo::getIsDeleted, 0);
        ApartmentInfo ap = apartmentInfoMapper.selectOne(apqw);
        roomDetailVo.setApartmentInfo(ap);

        List<GraphVo> graphVos = new ArrayList<>();
        LambdaQueryWrapper<GraphInfo>  gqw = new LambdaQueryWrapper<>();
        gqw.eq(GraphInfo::getItemId, id).eq(GraphInfo::getIsDeleted, 0).eq(GraphInfo::getItemType, 2);
        List<GraphInfo> graphInfos = graphInfoMapper.selectList(gqw);
        for(GraphInfo graphInfo : graphInfos){
            GraphVo graphVo = new GraphVo();
            graphVo.setName(graphInfo.getName());
            graphVo.setUrl(graphInfo.getUrl());
            graphVos.add(graphVo);
        }
        roomDetailVo.setGraphVoList(graphVos);

        List<AttrValueVo> attrValueVos = new ArrayList<>();
        LambdaQueryWrapper<RoomAttrValue> rqw2 = new LambdaQueryWrapper<>();
        rqw2.eq(RoomAttrValue::getRoomId, id).eq(RoomAttrValue::getIsDeleted, 0);
        List<RoomAttrValue> roomAttrValues = roomAttrValueMapper.selectList(rqw2);
        for(RoomAttrValue roomAttrValue : roomAttrValues){
            LambdaQueryWrapper<AttrValue> aqw = new LambdaQueryWrapper<>();
            aqw.eq(AttrValue::getId, roomAttrValue.getAttrValueId()).eq(AttrValue::getIsDeleted, 0);
            List<AttrValue> avs = attrValueMapper.selectList(aqw);
            for(AttrValue av : avs){
                LambdaQueryWrapper<AttrKey> akw = new LambdaQueryWrapper<>();
                akw.eq(AttrKey::getId, av.getAttrKeyId()).eq(AttrKey::getIsDeleted, 0);
                List<AttrKey> attrKeys = attrKeyMapper.selectList(akw);
                for(AttrKey attrKey : attrKeys){
                    AttrValueVo avv = new AttrValueVo();
                    avv.setAttrKeyName(attrKey.getName());
                    avv.setName(av.getName());
                    avv.setAttrKeyId(attrKey.getId());
                    attrValueVos.add(avv);
                }
            }
        }
        roomDetailVo.setAttrValueVoList(attrValueVos);

        List<FacilityInfo> facilityInfos = new ArrayList<>();
        LambdaQueryWrapper<RoomFacility> lfqw = new LambdaQueryWrapper<>();
        lfqw.eq(RoomFacility::getRoomId, id).eq(RoomFacility::getIsDeleted, 0);
        List<RoomFacility> roomFacilities = roomFacilityMapper.selectList(lfqw);
        for(RoomFacility rf : roomFacilities){
            LambdaQueryWrapper<FacilityInfo> lfqw2 = new LambdaQueryWrapper<>();
            lfqw2.eq(FacilityInfo::getId, rf.getFacilityId()).eq(FacilityInfo::getIsDeleted, 0);
            List<FacilityInfo> fi = facilityInfoMapper.selectList(lfqw2);
            facilityInfos.addAll(fi);
        }
        roomDetailVo.setFacilityInfoList(facilityInfos);

        LambdaQueryWrapper<RoomLabel> rqw1 = new LambdaQueryWrapper<>();
        rqw1.eq(RoomLabel::getRoomId, id).eq(RoomLabel::getIsDeleted, 0);
        List<RoomLabel> roomLabels = roomLabelMapper.selectList(rqw1);
        List<LabelInfo> labelInfos = new ArrayList<>();
        for(RoomLabel rl : roomLabels){
            LambdaQueryWrapper<LabelInfo> lqw = new LambdaQueryWrapper<>();
            lqw.eq(LabelInfo::getId, rl.getLabelId()).eq(LabelInfo::getIsDeleted, 0);
            labelInfos.add(labelInfoMapper.selectOne(lqw));
        }
        roomDetailVo.setLabelInfoList(labelInfos);

        LambdaQueryWrapper<RoomPaymentType> rqw3 = new LambdaQueryWrapper<>();
        rqw3.eq(RoomPaymentType::getRoomId, id).eq(RoomPaymentType::getIsDeleted, 0);
        List<RoomPaymentType> roomPaymentTypes = roomPaymentTypeMapper.selectList(rqw3);
        List<PaymentType> paymentTypes = new ArrayList<>();
        for(RoomPaymentType rpt : roomPaymentTypes){
            LambdaQueryWrapper<PaymentType> pqw = new LambdaQueryWrapper<>();
            pqw.eq(PaymentType::getId, rpt.getPaymentTypeId()).eq(PaymentType::getIsDeleted, 0);
            paymentTypes.add(paymentTypeMapper.selectOne(pqw));
        }
        roomDetailVo.setPaymentTypeList(paymentTypes);

        LambdaQueryWrapper<RoomLeaseTerm> rqw4 = new LambdaQueryWrapper<>();
        rqw4.eq(RoomLeaseTerm::getRoomId, id).eq(RoomLeaseTerm::getIsDeleted, 0);
        List<RoomLeaseTerm> roomLeaseTerms = roomLeaseTermMapper.selectList(rqw4);
        List<LeaseTerm> leaseTerms = new ArrayList<>();
        for(RoomLeaseTerm roomLeaseTerm : roomLeaseTerms){
            LambdaQueryWrapper<LeaseTerm> lqw = new LambdaQueryWrapper<>();
            lqw.eq(LeaseTerm::getId, roomLeaseTerm.getLeaseTermId()).eq(LeaseTerm::getIsDeleted, 0);
            leaseTerms.add(leaseTermMapper.selectOne(lqw));
        }
        roomDetailVo.setLeaseTermList(leaseTerms);

        return roomDetailVo;
    }

    @Autowired
    private LeaseAgreementMapper leaseAgreementMapper;

    @Override
    public IPage<RoomItemVo> pageItem(IPage page, RoomQueryVo roomQueryVo) {
        LambdaQueryWrapper<ApartmentInfo> aqw = new LambdaQueryWrapper<>();
        aqw.eq(ApartmentInfo::getProvinceId, roomQueryVo.getProvinceId())
                .eq(ApartmentInfo::getCityId, roomQueryVo.getCityId())
                .eq(ApartmentInfo::getDistrictId, roomQueryVo.getDistrictId())
                .eq(ApartmentInfo::getId, roomQueryVo.getApartmentId())
                .eq(ApartmentInfo::getIsDeleted, 0);
        ApartmentInfo apartmentInfo = apartmentInfoMapper.selectOne(aqw);
        List<RoomItemVo> roomItemVos = new ArrayList<>();
        LambdaQueryWrapper<RoomInfo> lfqw = new LambdaQueryWrapper<>();
        lfqw.eq(RoomInfo::getApartmentId, apartmentInfo.getId()).eq(RoomInfo::getIsDeleted, 0);
        List<RoomInfo> roomInfos = roomInfoMapper.selectList(lfqw);
        LambdaQueryWrapper<LeaseAgreement> laqw = new LambdaQueryWrapper<>();
        List<LeaseAgreement> leaseAgreements = new ArrayList<>();
        for(RoomInfo roomInfo : roomInfos){
            laqw.eq(LeaseAgreement::getApartmentId, roomInfo.getApartmentId())
                    .eq(LeaseAgreement::getIsDeleted, 0)
                    .eq(LeaseAgreement::getRoomId, roomInfo.getId());
            LeaseAgreement leaseAgreement = leaseAgreementMapper.selectOne(laqw);
            if (leaseAgreement != null) {
                RoomItemVo roomItemVo = new RoomItemVo();
                roomItemVo.setApartmentId(leaseAgreement.getApartmentId());
                roomItemVo.setApartmentInfo(apartmentInfo);
                roomItemVo.setLeaseEndDate(leaseAgreement.getLeaseEndDate());
                roomItemVo.setIsCheckIn(leaseAgreement.getRoomId() == null);
                roomItemVos.add(roomItemVo);
            }
        }
        return page.setRecords(roomItemVos);
    }

    @Override
    public List<RoomInfo> listBasicByApartmentId(Long id) {
        return List.of();
    }

    @Override
    public boolean saveOrUpdate(RoomSubmitVo roomSubmitVo) {
        return false;
    }

    @Override
    public boolean removeById(Long id) {
        return false;
    }

    @Override
    public boolean updateReleaseStatusById(Long id, ReleaseStatus status) {
        return false;
    }
}




