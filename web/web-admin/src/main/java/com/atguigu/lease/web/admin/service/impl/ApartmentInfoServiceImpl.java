package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.model.entity.*;
import com.atguigu.lease.model.enums.ItemType;
import com.atguigu.lease.web.admin.mapper.ApartmentFacilityMapper;
import com.atguigu.lease.web.admin.mapper.ApartmentInfoMapper;
import com.atguigu.lease.web.admin.mapper.FacilityInfoMapper;
import com.atguigu.lease.web.admin.service.*;
import com.atguigu.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.atguigu.lease.web.admin.vo.apartment.ApartmentQueryVo;
import com.atguigu.lease.web.admin.vo.apartment.ApartmentSubmitVo;
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
}




