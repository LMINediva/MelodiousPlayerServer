package com.melodiousplayer.mapper;

import com.melodiousplayer.entity.MusicUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 24240
 * @description 针对表【music_user】的数据库操作Mapper
 * @createDate 2025-09-14 13:24:17
 * @Entity com.melodiousplayer.entity.MusicUser
 */
@Mapper
public interface MusicUserMapper extends BaseMapper<MusicUser> {

}




