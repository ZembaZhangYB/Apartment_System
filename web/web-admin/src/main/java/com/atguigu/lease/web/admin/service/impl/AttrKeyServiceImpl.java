package com.atguigu.lease.web.admin.service.impl;

import com.atguigu.lease.web.admin.mapper.AttrKeyMapper;
import com.atguigu.lease.web.admin.vo.attr.AttrKeyVo;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.lease.model.entity.AttrKey;
import com.atguigu.lease.web.admin.service.AttrKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author HP
* @description 针对表【attr_key(房间基本属性表)】的数据库操作Service实现
* @createDate 2025-08-15 16:57:22
*/
@Service
public class AttrKeyServiceImpl extends ServiceImpl<AttrKeyMapper, AttrKey>
    implements AttrKeyService{

    @Autowired
    private AttrKeyMapper attrKeyMapper;

    @Override
    public List<AttrKeyVo> listAttrInfo() {
        return attrKeyMapper.listAttrInfo();
    }

    @Override
    public boolean deleteByKey(Long id) {
        LambdaUpdateWrapper<AttrKey> luw = new LambdaUpdateWrapper<>();
        luw.eq(AttrKey::getId, id).set(AttrKey::getIsDeleted, 1);
        int changeRow = attrKeyMapper.update(null, luw);
        return changeRow > 0;
    }
}




