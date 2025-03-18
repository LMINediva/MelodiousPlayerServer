package com.melodiousplayer.mapper;

import com.melodiousplayer.model.MvPage;
import java.util.List;

public interface MvPageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MvPage record);

    MvPage selectByPrimaryKey(Integer id);

    List<MvPage> selectAll();

    int updateByPrimaryKey(MvPage record);
}