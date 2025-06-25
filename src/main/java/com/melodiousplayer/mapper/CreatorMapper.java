package com.melodiousplayer.mapper;

import com.melodiousplayer.entity.Creator;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 24240
* @description 针对表【creator(创作者)】的数据库操作Mapper
* @createDate 2025-06-23 12:57:09
* @Entity com.melodiousplayer.entity.Creator
*/
@Mapper
public interface CreatorMapper extends BaseMapper<Creator> {

}




