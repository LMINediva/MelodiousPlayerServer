package com.melodiousplayer.mapper;

import com.melodiousplayer.entity.Play;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 24240
 * @description 针对表【play(音乐清单)】的数据库操作Mapper
 * @createDate 2025-08-01 10:55:46
 * @Entity com.melodiousplayer.entity.Play
 */
@Mapper
public interface PlayMapper extends BaseMapper<Play> {

}




