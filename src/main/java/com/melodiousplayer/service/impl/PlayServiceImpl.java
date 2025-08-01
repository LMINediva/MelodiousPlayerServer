package com.melodiousplayer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.melodiousplayer.entity.Play;
import com.melodiousplayer.service.PlayService;
import com.melodiousplayer.mapper.PlayMapper;
import org.springframework.stereotype.Service;

/**
* @author 24240
* @description 针对表【play(音乐清单)】的数据库操作Service实现
* @createDate 2025-08-01 10:55:46
*/
@Service
public class PlayServiceImpl extends ServiceImpl<PlayMapper, Play>
    implements PlayService{

}




