package com.melodiousplayer.service;

import com.melodiousplayer.mapper.HomeItemMapper;
import com.melodiousplayer.model.HomeItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mike
 * @date 2025/03/18
 */
@Service
public class HomeItemService {

    @Autowired
    private HomeItemMapper homeItemMapper;

    public List<HomeItem> selectAll() {
        return homeItemMapper.selectAll();
    }

}