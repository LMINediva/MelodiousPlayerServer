package com.melodiousplayer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.melodiousplayer.entity.Creator;
import com.melodiousplayer.entity.Play;
import com.melodiousplayer.service.CreatorService;
import com.melodiousplayer.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 音乐清单页面Controller控制器
 *
 * @author Mike
 * @date 2025/07/10
 */
@RestController
public class PlayController {

    @Autowired
    private PlayService playService;

    @Autowired
    private CreatorService creatorService;

    /**
     * 查询所有音乐清单信息
     *
     * @return 页面响应entity
     */
    @GetMapping("/list")
    public Map<String, Object> selectAll(@RequestParam("offset") Integer offset,
                                         @RequestParam("size") Integer size) {
        Page<Play> pageResult = playService.page(new Page<>(offset, size));
        List<Play> playList = pageResult.getRecords();
        for (Play play : playList) {
            Creator creator = creatorService.getOne(new QueryWrapper<Creator>().inSql(
                    "id", "select creator_id from play_creator where play_id = " + play.getId()));
            play.setCreator(creator);
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("playLists", playList);
        return resultMap;
    }

}
