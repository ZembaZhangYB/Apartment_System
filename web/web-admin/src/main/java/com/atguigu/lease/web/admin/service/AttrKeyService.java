package com.atguigu.lease.web.admin.service;

import com.atguigu.lease.model.entity.AttrKey;
import com.atguigu.lease.web.admin.vo.attr.AttrKeyVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author HP
* @description 针对表【attr_key(房间基本属性表)】的数据库操作Service
* @createDate 2025-08-15 16:57:22
*/
public interface AttrKeyService extends IService<AttrKey> {
    public boolean deleteByKey(Long id);

    public List<AttrKeyVo> listAttrInfo();
}
