package com.atguigu.lease.web.admin.service;

import com.atguigu.lease.model.entity.LabelInfo;
import com.atguigu.lease.model.enums.ItemType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author HP
* @description 针对表【label_info(标签信息表)】的数据库操作Service
* @createDate 2025-08-15 16:57:22
*/
public interface LabelInfoService extends IService<LabelInfo> {
    public List<LabelInfo> getLabelList(ItemType type);

    public boolean saveOrUpdateLabelInfo(LabelInfo labelInfo);

    public boolean deleteLabelInfo(Long id);
}
