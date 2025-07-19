package com.melodiousplayer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.melodiousplayer.entity.Artist;
import com.melodiousplayer.service.ArtistService;
import com.melodiousplayer.mapper.ArtistMapper;
import org.springframework.stereotype.Service;

/**
 * @author 24240
 * @description 针对表【artist(艺术家)】的数据库操作Service实现
 * @createDate 2025-06-23 12:57:09
 */
@Service
public class ArtistServiceImpl extends ServiceImpl<ArtistMapper, Artist>
        implements ArtistService {

}




