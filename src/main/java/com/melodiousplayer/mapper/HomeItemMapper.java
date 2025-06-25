package com.melodiousplayer.mapper;

import com.melodiousplayer.entity.HomeItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 24240
* @description 针对表【home_item(首页数据项)】的数据库操作Mapper
* @createDate 2025-06-23 12:57:09
* @Entity com.melodiousplayer.entity.HomeItem
*/
@Mapper
public interface HomeItemMapper extends BaseMapper<HomeItem> {

}




