package com.melodiousplayer.mapper;

import com.melodiousplayer.model.Mv;
import java.util.List;

public interface MvMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Mv record);

    Mv selectByPrimaryKey(Integer id);

    List<Mv> selectAll();

    int updateByPrimaryKey(Mv record);
}