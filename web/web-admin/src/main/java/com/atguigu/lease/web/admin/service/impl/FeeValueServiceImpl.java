package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.web.admin.mapper.FeeValueMapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.FeeValue;
import com.atguigu.lease.web.admin.service.FeeValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author HP
* @description 针对表【fee_value(杂项费用值表)】的数据库操作Service实现
* @createDate 2025-08-15 16:57:22
*/
@Service
public class FeeValueServiceImpl extends ServiceImpl<FeeValueMapper, FeeValue>
    implements FeeValueService{
    @Autowired
    private FeeValueMapper feeValueMapper;

    @Override
    public boolean deleteFeeValue(Long id) {
        LambdaUpdateWrapper<FeeValue> luw = new LambdaUpdateWrapper<>();
        luw.eq(FeeValue::getId, id).set(FeeValue::getIsDeleted, 1);
        int changeRow = feeValueMapper.update(null, luw);
        return changeRow > 0;
    }
}




