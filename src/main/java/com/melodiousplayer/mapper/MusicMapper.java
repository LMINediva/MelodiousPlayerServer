package com.melodiousplayer.mapper;

import com.melodiousplayer.entity.Music;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 24240
 * @description 针对表【Music(首页在线音乐数据项)】的数据库操作Mapper
 * @createDate 2025-07-22 13:27:54
 * @Entity com.melodiousplayer.entity.Music
 */
@Mapper
public interface MusicMapper extends BaseMapper<Music> {

}




