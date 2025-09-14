package com.melodiousplayer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.melodiousplayer.entity.MusicUser;
import com.melodiousplayer.service.MusicUserService;
import com.melodiousplayer.mapper.MusicUserMapper;
import org.springframework.stereotype.Service;

/**
 * @author 24240
 * @description 针对表【music_user】的数据库操作Service实现
 * @createDate 2025-09-14 13:24:17
 */
@Service
public class MusicUserServiceImpl extends ServiceImpl<MusicUserMapper, MusicUser>
        implements MusicUserService {

}




