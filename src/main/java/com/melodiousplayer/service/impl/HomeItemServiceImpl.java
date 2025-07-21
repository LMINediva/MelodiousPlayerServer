package com.melodiousplayer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.melodiousplayer.entity.HomeItem;
import com.melodiousplayer.service.HomeItemService;
import com.melodiousplayer.mapper.HomeItemMapper;
import org.springframework.stereotype.Service;

/**
 * @author 24240
 * @description 针对表【home_item(首页在线音乐数据项)】的数据库操作Service实现
 * @createDate 2025-07-19 13:35:40
 */
@Service
public class HomeItemServiceImpl extends ServiceImpl<HomeItemMapper, HomeItem>
        implements HomeItemService {

    @Override
    public HomeItem getByTitle(String title) {
        return getOne(new QueryWrapper<HomeItem>().eq("title", title));
    }
}




