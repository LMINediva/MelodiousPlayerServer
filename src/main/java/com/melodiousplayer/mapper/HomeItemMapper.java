package com.melodiousplayer.mapper;

import com.melodiousplayer.model.HomeItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HomeItemMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(HomeItem record);

    HomeItem selectByPrimaryKey(Integer id);

    List<HomeItem> selectAll();

    int updateByPrimaryKey(HomeItem record);

}