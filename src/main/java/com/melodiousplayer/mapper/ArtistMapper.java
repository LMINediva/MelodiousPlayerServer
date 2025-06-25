package com.melodiousplayer.mapper;

import com.melodiousplayer.entity.Artist;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 24240
* @description 针对表【artist(艺术家)】的数据库操作Mapper
* @createDate 2025-06-23 12:57:09
* @Entity com.melodiousplayer.entity.Artist
*/
@Mapper
public interface ArtistMapper extends BaseMapper<Artist> {

}




