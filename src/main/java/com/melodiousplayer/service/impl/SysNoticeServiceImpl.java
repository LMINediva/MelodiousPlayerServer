package com.melodiousplayer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.melodiousplayer.entity.SysNotice;
import com.melodiousplayer.service.SysNoticeService;
import com.melodiousplayer.mapper.SysNoticeMapper;
import org.springframework.stereotype.Service;

/**
 * @author 24240
 * @description 针对表【sys_notice】的数据库操作Service实现
 * @createDate 2025-08-14 12:48:44
 */
@Service
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice>
        implements SysNoticeService {

    @Override
    public SysNotice getByTitle(String title) {
        return getOne(new QueryWrapper<SysNotice>().eq("title", title));
    }

}




