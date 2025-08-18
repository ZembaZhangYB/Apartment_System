package com.atguigu.lease.web.admin.service;

import com.atguigu.lease.model.entity.PaymentType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author HP
* @description 针对表【payment_type(支付方式表)】的数据库操作Service
* @createDate 2025-08-15 16:57:22
*/
public interface PaymentTypeService extends IService<PaymentType> {
    public List<PaymentType> getPaymentTypeList();

    public boolean saveOrUpdatePaymentType(PaymentType paymentType);

    public boolean deletePaymentType(Long id);
}
