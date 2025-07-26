package com.melodiousplayer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.melodiousplayer.entity.*;
import com.melodiousplayer.service.ArtistService;
import com.melodiousplayer.service.MvService;
import com.melodiousplayer.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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

    @Value("${mvImagesFilePath}")
    private String mvImagesFilePath;

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

    /**
     * 分页查询MV页面的MV信息
     *
     * @param pageBean 分页信息
     * @return 页面响应entity
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('system:user:query')")
    public R list(@RequestBody PageBean pageBean) {
        String query = pageBean.getQuery().trim();
        Page<Mv> pageResult = mvService.page(new Page<>(pageBean.getPageNum(), pageBean.getPageSize()),
                new QueryWrapper<Mv>().like(StringUtil.isNotEmpty(query), "title", query));
        List<Mv> mvList = pageResult.getRecords();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("mvList", mvList);
        resultMap.put("total", pageResult.getTotal());
        return R.ok(resultMap);
    }

    /**
     * 删除在线MV
     *
     * @param ids 在线MV的id
     * @return 页面响应entity
     */
    @Transactional
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('system:user:delete')")
    public R delete(@RequestBody Long[] ids) {
        mvService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 根据id查询在线MV
     *
     * @param id 在线MV的id
     * @return 页面响应entity
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('system:user:query')")
    public R findById(@PathVariable(value = "id") Integer id) {
        Mv mvItem = mvService.getById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("mvItem", mvItem);
        return R.ok(map);
    }

}
