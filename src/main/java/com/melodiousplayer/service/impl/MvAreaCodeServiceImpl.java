package com.melodiousplayer.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.melodiousplayer.entity.MvAreaCode;
import com.melodiousplayer.service.MvAreaCodeService;
import com.melodiousplayer.mapper.MvAreaCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 24240
 * @description 针对表【mv_area_code】的数据库操作Service实现
 * @createDate 2025-07-29 11:10:00
 */
@Service
public class MvAreaCodeServiceImpl extends ServiceImpl<MvAreaCodeMapper, MvAreaCode>
        implements MvAreaCodeService {

    @Autowired
    MvAreaCodeMapper mvAreaCodeMapper;

    @Override
    public Boolean updateByMVIDAndMVAreaID(Integer mvId, Integer oldMvAreaId, Integer mvAreaId) {
        UpdateWrapper<MvAreaCode> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("mv_area_id", mvAreaId);
        updateWrapper.eq("mv_id", mvId);
        updateWrapper.eq("mv_area_id", oldMvAreaId);
        int result = mvAreaCodeMapper.update(null, updateWrapper);
        return result > 0;
    }

}




