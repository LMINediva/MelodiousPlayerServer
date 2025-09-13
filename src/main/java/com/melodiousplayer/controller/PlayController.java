package com.melodiousplayer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.melodiousplayer.entity.*;
import com.melodiousplayer.service.*;
import com.melodiousplayer.util.DateUtil;
import com.melodiousplayer.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

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

    @Autowired
    private PlayUserService playUserService;

    @Value("${listImagesFilePath}")
    private String listImagesFilePath;

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
    @PreAuthorize("hasAuthority('data:list:query')")
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
    @PreAuthorize("hasAuthority('data:list:delete')")
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
    @PreAuthorize("hasAuthority('data:list:query')")
    public R findById(@PathVariable(value = "id") Integer id) {
        Play play = playService.getById(id);
        List<Mv> mvList = mvService.list(new QueryWrapper<Mv>().inSql(
                "id", "select mv_id from play_mv where play_id = " + play.getId()));
        play.setMvList(mvList);
        Map<String, Object> map = new HashMap<>();
        map.put("playItem", play);
        return R.ok(map);
    }

    /**
     * 验证在线悦单名是否存在
     *
     * @param play 在线悦单信息
     * @return 页面响应entity
     */
    @PostMapping("/checkTitle")
    @PreAuthorize("hasAuthority('data:list:query')")
    public R checkTitle(@RequestBody Play play) {
        if (playService.getByTitle(play.getTitle()) == null) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 上传在线悦单缩略图
     *
     * @param file 在线悦单缩略图
     * @return 页面响应entity
     * @throws Exception 在线悦单缩略图上传异常
     */
    @RequestMapping("/uploadImage")
    @PreAuthorize("hasAuthority('data:list:edit')")
    public Map<String, Object> uploadImage(MultipartFile file) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        if (!file.isEmpty()) {
            // 获取文件名
            String originalFilename = file.getOriginalFilename();
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = DateUtil.getCurrentDateStr() + suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(listImagesFilePath + newFileName));
            resultMap.put("code", 0);
            resultMap.put("msg", "上传成功");
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("title", newFileName);
            dataMap.put("src", "image/mvPicture/" + newFileName);
            resultMap.put("data", dataMap);
        }
        return resultMap;
    }

    /**
     * 修改在线悦单缩略图
     *
     * @param play 在线音乐信息
     * @return 页面响应entity
     */
    @RequestMapping("/updateThumbnailPicture")
    @PreAuthorize("hasAuthority('data:list:edit')")
    public R updateThumbnailPicture(@RequestBody Play play) {
        Play currentPlay = playService.getById(play.getId());
        String thumbnailImagePath = listImagesFilePath + currentPlay.getThumbnailPic();
        File thumbnailImageFile = new File(thumbnailImagePath);
        if (thumbnailImageFile.exists()) {
            boolean deleted = thumbnailImageFile.delete();
            if (!deleted) {
                return R.error("悦单缩略图图片删除失败");
            }
        } else {
            return R.error("悦单缩略图图片不存在：" + currentPlay.getThumbnailPic());
        }
        currentPlay.setThumbnailPic(play.getThumbnailPic());
        playService.updateById(currentPlay);
        return R.ok();
    }

    /**
     * 添加或者修改在线悦单
     *
     * @param play 在线悦单信息
     * @return 页面响应entity
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('data:list:add')" + "||" + "hasAuthority('data:list:edit')")
    public R save(@RequestBody Play play) throws Exception {
        if (play.getId() == null || play.getId() == -1) {
            playService.save(play);
            Integer id = play.getId();
            PlayUser playUser = new PlayUser();
            playUser.setPlayId(id);
            playUser.setUserId(play.getSysUser().getId());
            playUserService.save(playUser);
            List<PlayMv> playMvList = new ArrayList<>();
            play.getMvList().forEach(mv -> {
                PlayMv playMv = new PlayMv();
                playMv.setPlayId(id);
                playMv.setMvId(mv.getId());
                playMvList.add(playMv);
            });
            playMvService.saveBatch(playMvList);
        } else {
            Play currentPlay = playService.getById(play.getId());
            if (!currentPlay.getThumbnailPic().equals(play.getThumbnailPic())) {
                String thumbnailImagePath = listImagesFilePath + currentPlay.getThumbnailPic();
                File thumbnailImageFile = new File(thumbnailImagePath);
                if (thumbnailImageFile.exists()) {
                    boolean deleted = thumbnailImageFile.delete();
                    if (!deleted) {
                        return R.error("悦单缩略图图片删除失败");
                    }
                } else {
                    return R.error("悦单缩略图图片不存在：" + currentPlay.getThumbnailPic());
                }
            }
            play.setUpdateTime(DateUtil.formatString(DateUtil.getCurrentDate(), "yyyy-MM-dd HH:mm:ss"));
            playService.updateById(play);
            playMvService.remove(new QueryWrapper<PlayMv>().eq("play_id", play.getId()));
            List<PlayMv> playMvList = new ArrayList<>();
            play.getMvList().forEach(mv -> {
                PlayMv playMv = new PlayMv();
                playMv.setPlayId(play.getId());
                playMv.setMvId(mv.getId());
                playMvList.add(playMv);
            });
            playMvService.saveBatch(playMvList);
        }
        return R.ok();
    }

    /**
     * 查询悦单总数
     *
     * @return 页面响应entity
     */
    @GetMapping("/total")
    @PreAuthorize("hasAuthority('data:list:query')")
    public R total() {
        Long total = playService.count();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", total);
        return R.ok(resultMap);
    }

}
