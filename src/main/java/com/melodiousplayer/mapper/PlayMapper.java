package com.melodiousplayer.mapper;

import com.melodiousplayer.model.Play;
import java.util.List;

public interface PlayMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Play record);

    Play selectByPrimaryKey(Integer id);

    List<Play> selectAll();

    int updateByPrimaryKey(Play record);
}