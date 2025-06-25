package com.melodiousplayer.mapper;

import com.melodiousplayer.entity.Music;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 24240
* @description 针对表【music(悦单界面)】的数据库操作Mapper
* @createDate 2025-06-23 12:57:09
* @Entity com.melodiousplayer.entity.Music
*/
@Mapper
public interface MusicMapper extends BaseMapper<Music> {

}




