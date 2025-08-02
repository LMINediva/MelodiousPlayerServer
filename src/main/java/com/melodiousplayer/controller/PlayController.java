package com.melodiousplayer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.melodiousplayer.entity.*;
import com.melodiousplayer.service.MvService;
import com.melodiousplayer.service.PlayMvService;
import com.melodiousplayer.service.SysUserService;
import com.melodiousplayer.service.PlayService;
import com.melodiousplayer.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
@RequestMapping("/data/list")
public class PlayController {

    @Autowired
    private PlayService playService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private MvService mvService;

    @Autowired
    private PlayMvService playMvService;

    /**
     * 查询所有音乐清单信息
     *
     * @return 页面响应entity
     */
    @GetMapping("/get_list")
    public Map<String, Object> selectAll(@RequestParam("offset") Integer offset,
                                         @RequestParam("size") Integer size) {
        Page<Play> pageResult = playService.page(new Page<>(offset, size));
        List<Play> playList = pageResult.getRecords();
        for (Play play : playList) {
            SysUser sysUser = sysUserService.getOne(new QueryWrapper<SysUser>().inSql(
                    "id", "select user_id from play_user where play_id = " + play.getId()));
            play.setSysUser(sysUser);
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("playLists", playList);
        return resultMap;
    }

    /**
     * 分页查询悦单页面的悦单信息
     *
     * @param pageBean 分页信息
     * @return 页面响应entity
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('system:user:query')")
    public R list(@RequestBody PageBean pageBean) {
        String query = pageBean.getQuery().trim();
        Page<Play> pageResult = playService.page(new Page<>(pageBean.getPageNum(), pageBean.getPageSize()),
                new QueryWrapper<Play>().like(StringUtil.isNotEmpty(query), "title", query));
        List<Play> playList = pageResult.getRecords();
        for (Play play : playList) {
            SysUser sysUser = sysUserService.getOne(new QueryWrapper<SysUser>().inSql(
                    "id", "select user_id from play_user where play_id = " + play.getId()));
            play.setSysUser(sysUser);
            List<Mv> mvList = mvService.list(new QueryWrapper<Mv>().inSql(
                    "id", "select mv_id from play_mv where play_id = " + play.getId()));
            play.setMvList(mvList);
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("playList", playList);
        resultMap.put("total", pageResult.getTotal());
        return R.ok(resultMap);
    }

    /**
     * 删除在线悦单
     *
     * @param ids 在线悦单的id
     * @return 页面响应entity
     */
    @Transactional
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('system:user:delete')")
    public R delete(@RequestBody Long[] ids) {
        playService.removeByIds(Arrays.asList(ids));
        playMvService.remove(new QueryWrapper<PlayMv>().in("play_id", Arrays.asList(ids)));
        return R.ok();
    }

    /**
     * 根据id查询在线悦单
     *
     * @param id 在线悦单的id
     * @return 页面响应entity
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('system:user:query')")
    public R findById(@PathVariable(value = "id") Integer id) {
        Play play = playService.getById(id);
        List<Mv> mvList = mvService.list(new QueryWrapper<Mv>().inSql(
                "id", "select mv_id from play_mv where play_id = " + play.getId()));
        play.setMvList(mvList);
        Map<String, Object> map = new HashMap<>();
        map.put("playItem", play);
        return R.ok(map);
    }

}
