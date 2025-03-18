package com.melodiousplayer.mapper;

import com.melodiousplayer.model.MvArea;
import java.util.List;

public interface MvAreaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MvArea record);

    MvArea selectByPrimaryKey(Integer id);

    List<MvArea> selectAll();

    int updateByPrimaryKey(MvArea record);
}