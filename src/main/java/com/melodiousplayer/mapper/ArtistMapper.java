package com.melodiousplayer.mapper;

import com.melodiousplayer.model.Artist;
import java.util.List;

public interface ArtistMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Artist record);

    Artist selectByPrimaryKey(Integer id);

    List<Artist> selectAll();

    int updateByPrimaryKey(Artist record);
}