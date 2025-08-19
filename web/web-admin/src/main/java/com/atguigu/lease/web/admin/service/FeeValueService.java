package com.atguigu.lease.web.admin.service;

import com.atguigu.lease.model.entity.FeeValue;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author HP
* @description 针对表【fee_value(杂项费用值表)】的数据库操作Service
* @createDate 2025-08-15 16:57:22
*/
public interface FeeValueService extends IService<FeeValue> {
    public boolean deleteFeeValue(Long id);
}
