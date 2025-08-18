package com.atguigu.lease.web.admin.service;

import com.atguigu.lease.model.entity.LeaseTerm;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author HP
* @description 针对表【lease_term(租期)】的数据库操作Service
* @createDate 2025-08-15 16:57:22
*/
public interface LeaseTermService extends IService<LeaseTerm> {
    public List<LeaseTerm> getLeaseTerm();

    public boolean saveOrUpdateLeaseTerm(LeaseTerm leaseTerm);

    public boolean deleteLeaseTerm(Long id);
}
