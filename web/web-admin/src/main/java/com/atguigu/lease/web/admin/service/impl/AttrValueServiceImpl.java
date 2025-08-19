package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.web.admin.mapper.AttrValueMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.AttrValue;
import com.atguigu.lease.web.admin.service.AttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author HP
* @description 针对表【attr_value(房间基本属性值表)】的数据库操作Service实现
* @createDate 2025-08-15 16:57:22
*/
@Service
public class AttrValueServiceImpl extends ServiceImpl<AttrValueMapper, AttrValue>
    implements AttrValueService{

    @Autowired
    private AttrValueMapper attrValueMapper;

    @Override
    public boolean deleteAttrValueByAttrId(Long attrId) {
        LambdaUpdateWrapper<AttrValue> luw = new LambdaUpdateWrapper<>();
        luw.eq(AttrValue::getAttrKeyId, attrId).set(AttrValue::getIsDeleted, 1);
        int changeRow = attrValueMapper.update(null, luw);
        return changeRow > 0;
    }
}




