package com.melodiousplayer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.melodiousplayer.entity.Artist;
import com.melodiousplayer.entity.Mv;
import com.melodiousplayer.service.ArtistService;
import com.melodiousplayer.service.MvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MV页面Controller控制器
 *
 * @author Mike
 * @date 2025/07/09
 */
@RestController
@RequestMapping("/data/mv")
public class MVController {

    @Autowired
    private MvService mvService;

    @Autowired
    private ArtistService artistService;

    /**
     * 查询所有MV信息
     *
     * @return 页面响应entity
     */
    @GetMapping("/get_mv_list")
    public Map<String, Object> selectAll(@RequestParam("offset") Integer offset,
                                         @RequestParam("size") Integer size) {
        Page<Mv> pageResult = mvService.page(new Page<>(offset, size));
        List<Mv> mvList = pageResult.getRecords();
        for (Mv mv : mvList) {
            List<Artist> artistList = artistService.list(new QueryWrapper<Artist>().inSql(
                    "id", "select artist_id from mv_artist where mv_id = " + mv.getId()));
            mv.setArtists(artistList);
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("videos", mvList);
        return resultMap;
    }

}
