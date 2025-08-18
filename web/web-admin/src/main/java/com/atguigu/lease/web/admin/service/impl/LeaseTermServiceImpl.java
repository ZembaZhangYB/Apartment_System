package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.web.admin.mapper.LeaseTermMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.LeaseTerm;
import com.atguigu.lease.web.admin.service.LeaseTermService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author HP
* @description 针对表【lease_term(租期)】的数据库操作Service实现
* @createDate 2025-08-15 16:57:22
*/
@Service
public class LeaseTermServiceImpl extends ServiceImpl<LeaseTermMapper, LeaseTerm>
    implements LeaseTermService{

    @Autowired
    private LeaseTermMapper leaseTermMapper;

    @Override
    public List<LeaseTerm> getLeaseTerm() {
        LambdaQueryWrapper<LeaseTerm> lqw = new LambdaQueryWrapper<>();
        lqw.select(LeaseTerm::getId, LeaseTerm::getMonthCount, LeaseTerm::getUnit).eq(LeaseTerm::getIsDeleted, 0);
        List<LeaseTerm> leaseTerms = leaseTermMapper.selectList(lqw);
        return leaseTerms;
    }

    @Override
    public boolean saveOrUpdateLeaseTerm(LeaseTerm leaseTerm) {
        if(leaseTermMapper.selectById(leaseTerm.getId()) != null){
            //进入修改状态
            LambdaUpdateWrapper<LeaseTerm> luw = new LambdaUpdateWrapper<>();
            luw.eq(LeaseTerm::getId, leaseTerm.getId())
                    .set(LeaseTerm::getMonthCount, leaseTerm.getMonthCount())
                    .set(LeaseTerm::getUnit, leaseTerm.getUnit())
                    .set(LeaseTerm::getIsDeleted, leaseTerm.getIsDeleted())
                    .set(LeaseTerm::getUpdateTime, leaseTerm.getUpdateTime());
            int changeRow = leaseTermMapper.update(leaseTerm, luw);
            return changeRow > 0;
        }
        else{
            int changeRow = leaseTermMapper.insert(leaseTerm);
            return changeRow > 0;
        }
    }

    @Override
    public boolean deleteLeaseTerm(Long id) {
        LambdaUpdateWrapper<LeaseTerm> luw = new LambdaUpdateWrapper<>();
        luw.eq(LeaseTerm::getId, id)
            .set(LeaseTerm::getIsDeleted, 1);
        int changeRow = leaseTermMapper.update(null, luw);
        return changeRow > 0;
    }
}




