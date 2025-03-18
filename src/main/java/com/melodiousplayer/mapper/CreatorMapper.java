package com.melodiousplayer.mapper;

import com.melodiousplayer.model.Creator;
import java.util.List;

public interface CreatorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Creator record);

    Creator selectByPrimaryKey(Integer id);

    List<Creator> selectAll();

    int updateByPrimaryKey(Creator record);
}