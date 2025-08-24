package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.model.entity.*;
import com.atguigu.lease.model.enums.ItemType;
import com.atguigu.lease.model.enums.ReleaseStatus;
import com.atguigu.lease.web.admin.mapper.*;
import com.atguigu.lease.web.admin.service.*;
import com.atguigu.lease.web.admin.vo.apartment.ApartmentDetailVo;
import com.atguigu.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.atguigu.lease.web.admin.vo.apartment.ApartmentQueryVo;
import com.atguigu.lease.web.admin.vo.apartment.ApartmentSubmitVo;
import com.atguigu.lease.web.admin.vo.fee.FeeValueVo;
import com.atguigu.lease.web.admin.vo.graph.GraphVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
* @author HP
* @description 针对表【apartment_info(公寓信息表)】的数据库操作Service实现
* @createDate 2025-08-15 16:56:48
*/
@Service
public class ApartmentInfoServiceImpl extends ServiceImpl<ApartmentInfoMapper, ApartmentInfo>
    implements ApartmentInfoService {

    @Autowired
    private FacilityInfoService facilityInfoService;

    @Autowired
    private LabelInfoService labelInfoService;

    @Autowired
    private FeeValueService feeValueService;

    @Autowired
    private GraphInfoService graphInfoService;

    @Autowired
    private ApartmentFacilityService apartmentFacilityService;

    @Autowired
    private ApartmentLabelService apartmentLabelService;

    @Autowired
    private ApartmentInfoMapper apartmentInfoMapper;

    @Autowired
    private ApartmentFeeValueService apartmentFeeValueService;
    @Autowired
    private LabelInfoMapper labelInfoMapper;
    @Autowired
    private FacilityInfoMapper facilityInfoMapper;
    @Autowired
    private FeeValueMapper feeValueMapper;
    @Autowired
    private FeeKeyMapper feeKeyMapper;

    /**
     *
     * @param apartmentSubmitVo
     * @return
     */
    @Override
    public boolean saveOrUpdate(ApartmentSubmitVo apartmentSubmitVo){

        LambdaQueryWrapper<ApartmentInfo> lqw = new LambdaQueryWrapper<>();
        if(apartmentInfoMapper.exists(lqw.eq(ApartmentInfo::getId, apartmentSubmitVo.getId()))){
            //以下是修改逻辑
            //先删除所有图片
            LambdaQueryWrapper<GraphInfo> gqw = new LambdaQueryWrapper<>();
            gqw.eq(GraphInfo::getItemType, ItemType.APARTMENT).eq(GraphInfo::getItemId, apartmentSubmitVo.getId());
            graphInfoService.remove(gqw);

            LambdaQueryWrapper<ApartmentFacility> fqw = new LambdaQueryWrapper<>();
            fqw.eq(ApartmentFacility::getApartmentId, apartmentSubmitVo.getId());
            apartmentFacilityService.remove(fqw);

            LambdaQueryWrapper<ApartmentLabel> lqw1 = new LambdaQueryWrapper<>();
            lqw1.eq(ApartmentLabel::getApartmentId, apartmentSubmitVo.getId());
            apartmentLabelService.remove(lqw1);

            LambdaQueryWrapper<ApartmentFeeValue> fqw1 = new LambdaQueryWrapper<>();
            fqw1.eq(ApartmentFeeValue::getApartmentId, apartmentSubmitVo.getId());
            apartmentFeeValueService.remove(fqw1);
            //至此修改结束
        }
        //下面是新增逻辑
        Iterator iter = apartmentSubmitVo.getFacilityInfoIds().iterator();
        while(iter.hasNext()){
            ApartmentFacility apartmentFacility = new ApartmentFacility();
            apartmentFacility.setApartmentId(apartmentSubmitVo.getId());
            apartmentFacility.setFacilityId((Long)iter.next());
            apartmentFacilityService.save(apartmentFacility);
        }

        Iterator iter2 = apartmentSubmitVo.getFeeValueIds().iterator();
        while(iter2.hasNext()){
            ApartmentFeeValue apartmentFeeValue = new ApartmentFeeValue();
            apartmentFeeValue.setApartmentId(apartmentSubmitVo.getId());
            apartmentFeeValue.setFeeValueId((Long)iter2.next());
            apartmentFeeValueService.save(apartmentFeeValue);
        }

        Iterator iter3 = apartmentSubmitVo.getLabelIds().iterator();
        while(iter3.hasNext()){
            ApartmentLabel apartmentLabel = new ApartmentLabel();
            apartmentLabel.setApartmentId(apartmentSubmitVo.getId());
            apartmentLabel.setLabelId((Long)iter3.next());
            apartmentLabelService.save(apartmentLabel);
        }

        Iterator iter4 = apartmentSubmitVo.getGraphVoList().iterator();
        while(iter4.hasNext()){
            GraphVo graphVo = (GraphVo)iter4.next();
            GraphInfo graphInfo = new GraphInfo();
            graphInfo.setId(apartmentSubmitVo.getId());
            graphInfo.setName(graphVo.getName());
            graphInfo.setUrl(graphVo.getUrl());
            graphInfoService.save(graphInfo);
        }
        return true;
    }

    /**
     * 分页查询
     * @param queryVo
     * @return
     */
    @Override
    public IPage<ApartmentItemVo> getApartmentInfoList(IPage<ApartmentItemVo> page, ApartmentQueryVo queryVo) {
        LambdaQueryWrapper<ApartmentInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ApartmentInfo::getProvinceId, queryVo.getProvinceId())
                .eq(ApartmentInfo::getCityId, queryVo.getCityId())
                .eq(ApartmentInfo::getDistrictId, queryVo.getDistrictId());

        List<ApartmentInfo> apartmentInfos = apartmentInfoMapper.selectList(lqw);
        Integer count = apartmentInfos.size();
        lqw.eq(ApartmentInfo::getIsRelease, 0).eq(ApartmentInfo::getIsDeleted, 0);
        Integer available = apartmentInfoMapper.selectList(lqw).size();

        List<ApartmentItemVo> aivs = new ArrayList<>();
        for(ApartmentInfo ai : apartmentInfos){
            ApartmentItemVo apartmentItemVo = new ApartmentItemVo();
            apartmentItemVo.setId(ai.getId());
            apartmentItemVo.setName(ai.getName());
            apartmentItemVo.setFreeRoomCount((long)available);
            apartmentItemVo.setTotalRoomCount((long)count);
            aivs.add(apartmentItemVo);
        }
        page.setRecords(aivs);
        return page;
    }

    @Autowired
    private GraphInfoMapper graphInfoMapper;
    @Autowired
    private ApartmentLabelMapper apartmentLabelMapper;
    @Autowired
    private ApartmentFacilityMapper apartmentFacilityMapper;
    @Autowired
    private ApartmentFeeValueMapper apartmentFeeValueMapper;

    @Override
    public ApartmentDetailVo getApartmentDetailVoById(Long id) {
        LambdaQueryWrapper<ApartmentInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ApartmentInfo::getId, id);
        ApartmentDetailVo apartmentDetailVo = new ApartmentDetailVo();
        List<GraphVo> graphVos = new ArrayList<>();
        LambdaQueryWrapper<GraphInfo> gqw = new LambdaQueryWrapper<>();
        gqw.eq(GraphInfo::getItemId, id);
        List<GraphInfo> graphInfos = graphInfoMapper.selectList(gqw);
        for(GraphInfo graphInfo : graphInfos){
            graphVos.add(new GraphVo(graphInfo.getName(), graphInfo.getUrl()));
        }
        apartmentDetailVo.setGraphVoList(graphVos);

        LambdaQueryWrapper<ApartmentLabel> lqw1 = new LambdaQueryWrapper<>();
        List<ApartmentLabel> apartmentLabels = apartmentLabelMapper.selectList(lqw1.eq(ApartmentLabel::getApartmentId, id)
                .eq(ApartmentLabel::getIsDeleted, 0));
        List<LabelInfo> labelInfos = new ArrayList<>();
        for(ApartmentLabel apartmentLabel : apartmentLabels){
            LambdaQueryWrapper<LabelInfo> lqw2 = new LambdaQueryWrapper<>();
            LabelInfo labelInfo = labelInfoMapper.selectOne(lqw2.eq(LabelInfo::getId, apartmentLabel.getLabelId()));
            labelInfos.add(labelInfo);
        }
        apartmentDetailVo.setLabelInfoList(labelInfos);

        LambdaQueryWrapper<ApartmentFacility> fqw = new LambdaQueryWrapper<>();
        List<ApartmentFacility> apartmentFacilities = apartmentFacilityMapper.selectList(
                fqw.eq(ApartmentFacility::getApartmentId, id)
                    .eq(ApartmentFacility::getIsDeleted, 0)
        );
        List<FacilityInfo> facilityInfos = new ArrayList<>();
        for(ApartmentFacility apartmentFacility : apartmentFacilities){
            LambdaQueryWrapper<FacilityInfo> fqw2 = new LambdaQueryWrapper<>();
            FacilityInfo facilityInfo = facilityInfoMapper.selectOne(fqw2.eq(FacilityInfo::getId, apartmentFacility.getFacilityId()));
            facilityInfos.add(facilityInfo);
        }
        apartmentDetailVo.setFacilityInfoList(facilityInfos);

        LambdaQueryWrapper<ApartmentFeeValue> fqw3 = new LambdaQueryWrapper<>();
        List<ApartmentFeeValue> apartmentFeeValues = apartmentFeeValueMapper.selectList(fqw3.eq(ApartmentFeeValue::getApartmentId, id)
                .eq(ApartmentFeeValue::getIsDeleted, 0));
        List<FeeValueVo> feeValueVos = new ArrayList<>();
        for(ApartmentFeeValue apartmentFeeValue : apartmentFeeValues){
            LambdaQueryWrapper<FeeValue> fqw4 = new LambdaQueryWrapper<>();
            FeeValue fv = feeValueMapper.selectOne(fqw4.eq(FeeValue::getIsDeleted, 0).eq(FeeValue::getId, apartmentFeeValue.getFeeValueId()));
            LambdaQueryWrapper<FeeKey> fqw5 = new LambdaQueryWrapper<>();
            feeValueVos.add(new FeeValueVo(feeKeyMapper.selectOne(fqw5.eq(FeeKey::getId, fv.getFeeKeyId())).getName()));
        }
        apartmentDetailVo.setFeeValueVoList(feeValueVos);

        return apartmentDetailVo;
    }

    @Override
    public boolean updateReleaseStatusById(Long id, ReleaseStatus status) {
        LambdaUpdateWrapper<ApartmentInfo> lqw = new LambdaUpdateWrapper<>();
        lqw.eq(ApartmentInfo::getId, id).eq(ApartmentInfo::getIsDeleted, 0)
                .set(ApartmentInfo::getIsRelease, status.getCode());
        apartmentInfoMapper.update(null, lqw);
        return true;
    }

    @Override
    public List<ApartmentInfo> listInfoByDistrictId(Long id) {
        LambdaQueryWrapper<ApartmentInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ApartmentInfo::getDistrictId, id).eq(ApartmentInfo::getIsDeleted, 0);
        return apartmentInfoMapper.selectList(lqw);
    }
}




