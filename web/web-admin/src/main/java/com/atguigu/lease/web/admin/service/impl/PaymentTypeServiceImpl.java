package com.atguigu.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.PaymentType;
import com.atguigu.lease.web.admin.service.PaymentTypeService;
import com.atguigu.lease.web.admin.mapper.PaymentTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author HP
* @description 针对表【payment_type(支付方式表)】的数据库操作Service实现
* @createDate 2025-08-15 16:57:22
*/
@Service
public class PaymentTypeServiceImpl extends ServiceImpl<PaymentTypeMapper, PaymentType>
    implements PaymentTypeService{

    @Autowired
    private PaymentTypeMapper paymentTypeMapper;

    @Override
    public List<PaymentType> getPaymentTypeList() {
        LambdaQueryWrapper<PaymentType> lqw = new LambdaQueryWrapper<>();
        lqw.select(PaymentType::getName, PaymentType::getAdditionalInfo).eq(PaymentType::getIsDeleted, 0);
        List<PaymentType> list = this.list(lqw);
        return list;
    }

    @Override
    public boolean saveOrUpdatePaymentType(PaymentType paymentType){
        LambdaQueryWrapper<PaymentType> lqw = new LambdaQueryWrapper<>();
        int changeRow = 0;
        if(lqw.eq(PaymentType::getId, paymentType.getId()) != null){
            //此时是修改逻辑
            LambdaUpdateWrapper<PaymentType> luw = new LambdaUpdateWrapper<>();
            luw.eq(PaymentType::getId, paymentType.getId())
                    .set(PaymentType::getName, paymentType.getName())
                    .set(PaymentType::getAdditionalInfo, paymentType.getAdditionalInfo())
                    .set(PaymentType::getUpdateTime, paymentType.getUpdateTime())
                    .set(PaymentType::getIsDeleted, paymentType.getIsDeleted());
            changeRow = paymentTypeMapper.update(paymentType, luw);
        }else{
            //此时是保存、添加逻辑
            changeRow = paymentTypeMapper.insert(paymentType);
        }
        return changeRow == 1;
    }

    @Override
    public boolean deletePaymentType(Long id){
        LambdaUpdateWrapper<PaymentType> luw = new LambdaUpdateWrapper<>();
        luw.eq(PaymentType::getId, id)
                .set(PaymentType::getIsDeleted, 1);
        int changeRow = paymentTypeMapper.update(null, luw);
        return changeRow == 1;
    }

}




