package com.melodiousplayer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.melodiousplayer.entity.Music;
import com.melodiousplayer.service.MusicService;
import com.melodiousplayer.mapper.MusicMapper;
import org.springframework.stereotype.Service;

/**
 * @author 24240
 * @description 针对表【Music(首页在线音乐数据项)】的数据库操作Service实现
 * @createDate 2025-07-22 13:27:54
 */
@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music>
        implements MusicService {

    @Override
    public Music getByTitle(String title) {
        return getOne(new QueryWrapper<Music>().eq("title", title));
    }

}
