package com.melodiousplayer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.melodiousplayer.entity.Music;
import com.melodiousplayer.service.MusicService;
import com.melodiousplayer.mapper.MusicMapper;
import org.springframework.stereotype.Service;

/**
* @author 24240
* @description 针对表【music(悦单界面)】的数据库操作Service实现
* @createDate 2025-06-23 12:57:09
*/
@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music>
    implements MusicService{

}




