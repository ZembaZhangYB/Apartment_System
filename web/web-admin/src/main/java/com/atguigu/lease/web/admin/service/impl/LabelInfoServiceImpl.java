package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.model.enums.ItemType;
import com.atguigu.lease.web.admin.mapper.LabelInfoMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.LabelInfo;
import com.atguigu.lease.web.admin.service.LabelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author HP
* @description 针对表【label_info(标签信息表)】的数据库操作Service实现
* @createDate 2025-08-15 16:57:22
*/
@Service
public class LabelInfoServiceImpl extends ServiceImpl<LabelInfoMapper, LabelInfo>
    implements LabelInfoService{

    @Autowired
    private LabelInfoMapper labelInfoMapper;

    @Override
    public List<LabelInfo> getLabelList(ItemType type) {
        LambdaQueryWrapper<LabelInfo> lqw = new LambdaQueryWrapper<>();
        lqw.eq(LabelInfo::getIsDeleted,0);
        return labelInfoMapper.selectList(lqw.eq(LabelInfo::getType,type.getCode()));
    }

    @Override
    public boolean deleteLabelInfo(Long id) {
        LambdaUpdateWrapper<LabelInfo> luw = new LambdaUpdateWrapper<>();
        luw.eq(LabelInfo::getId,id).set(LabelInfo::getIsDeleted,1);
        int changeRow = labelInfoMapper.update(null, luw);
        return changeRow > 0;
    }

    @Override
    public boolean saveOrUpdateLabelInfo(LabelInfo labelInfo) {
        LambdaQueryWrapper<LabelInfo> lqw = new LambdaQueryWrapper<>();
        if(labelInfoMapper.selectById(labelInfo.getId()) != null){
            //进入修改模式
            LambdaUpdateWrapper<LabelInfo> luw = new LambdaUpdateWrapper<>();
            luw.eq(LabelInfo::getId, labelInfo.getId())
                    .set(LabelInfo::getIsDeleted, labelInfo.getIsDeleted())
                    .set(LabelInfo::getName, labelInfo.getName())
                    .set(LabelInfo::getUpdateTime, labelInfo.getUpdateTime())
                    .set(LabelInfo::getType, labelInfo.getType());
            int changeRow = labelInfoMapper.update(null, luw);
            return changeRow > 0;
        }
        else {
            int changeRow = labelInfoMapper.insert(labelInfo);
            return changeRow > 0;
        }
    }
}
