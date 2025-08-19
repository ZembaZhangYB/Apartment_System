package com.atguigu.lease.web.admin.mapper;

import com.atguigu.lease.model.entity.FeeKey;
import com.atguigu.lease.web.admin.vo.fee.FeeKeyVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author HP
* @description 针对表【fee_key(杂项费用名称表)】的数据库操作Mapper
* @createDate 2025-08-15 16:57:22
* @Entity com.atguigu.model.domain.FeeKey
*/
@Mapper
public interface FeeKeyMapper extends BaseMapper<FeeKey> {
    public List<FeeKeyVo> listFeeInfo();
}




