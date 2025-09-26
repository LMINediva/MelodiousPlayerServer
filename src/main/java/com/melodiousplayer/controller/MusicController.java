package com.melodiousplayer.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.melodiousplayer.entity.*;
import com.melodiousplayer.service.MusicService;
import com.melodiousplayer.service.MusicUserService;
import com.melodiousplayer.service.SysUserService;
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
 * 首页在线音乐Controller控制器
 *
 * @author Mike
 * @date 2025/03/18
 */
@RestController
@RequestMapping("/data/music")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private MusicUserService musicUserService;

    @Value("${musicImagesFilePath}")
    private String musicImagesFilePath;

    @Value("${audioFilePath}")
    private String audioFilePath;

    @Value("${lyricFilePath}")
    private String lyricFilePath;

    /**
     * 分页查询首页列表信息
     *
     * @param offset 第几页
     * @param size   每页记录数
     * @return 页面响应entity
     */
    @GetMapping("/front_page")
    public List<Music> selectAll(@RequestParam("offset") Integer offset,
                                 @RequestParam("size") Integer size) {
        Page<Music> pageResult = musicService.page(new Page<>(offset, size));
        return pageResult.getRecords();
    }

    /**
     * 分页查询首页在线音乐列表信息
     *
     * @param pageBean 分页信息
     * @return 页面响应entity
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('data:music:query')")
    public R list(@RequestBody PageBean pageBean) {
        String query = pageBean.getQuery().trim();
        Page<Music> pageResult = musicService.page(new Page<>(pageBean.getPageNum(), pageBean.getPageSize()),
                new QueryWrapper<Music>().like(StringUtil.isNotEmpty(query), "title", query));
        List<Music> musicList = pageResult.getRecords();
        for (Music music : musicList) {
            SysUser sysUser = sysUserService.getOne(new QueryWrapper<SysUser>().inSql(
                    "id", "select user_id from music_user where music_id = " + music.getId()));
            music.setSysUser(sysUser);
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("musicList", musicList);
        resultMap.put("total", pageResult.getTotal());
        return R.ok(resultMap);
    }

    /**
     * 根据id查询在线音乐
     *
     * @param id 在线音乐id
     * @return 页面响应entity
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('data:music:query')")
    public R findById(@PathVariable(value = "id") Integer id) {
        Music music = musicService.getById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("music", music);
        return R.ok(map);
    }

    /**
     * 删除在线音乐
     *
     * @param ids 在线音乐id
     * @return 页面响应entity
     */
    @Transactional
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('data:music:delete')")
    public R delete(@RequestBody Long[] ids) {
        for (Long id : ids) {
            Music music = musicService.getById(id);
            String posterImagePath = musicImagesFilePath + music.getPosterPic();
            String thumbnailImagePath = musicImagesFilePath + music.getThumbnailPic();
            String audioPath = audioFilePath + music.getUrl();
            String lyricPath = lyricFilePath + music.getLyric();
            File posterImageFile = new File(posterImagePath);
            File thumbnailImageFile = new File(thumbnailImagePath);
            File audioFile = new File(audioPath);
            File lyricFile = new File(lyricPath);
            if (posterImageFile.exists()) {
                boolean deleted = posterImageFile.delete();
                if (!deleted) {
                    return R.error("海报图片删除失败");
                }
            } else {
                return R.error("海报图片不存在：" + music.getPosterPic());
            }
            if (thumbnailImageFile.exists()) {
                boolean deleted = thumbnailImageFile.delete();
                if (!deleted) {
                    return R.error("缩略图图片删除失败");
                }
            } else {
                return R.error("缩略图图片不存在：" + music.getThumbnailPic());
            }
            if (audioFile.exists()) {
                boolean deleted = audioFile.delete();
                if (!deleted) {
                    return R.error("音乐文件删除失败");
                }
            } else {
                return R.error("音乐文件不存在：" + music.getUrl());
            }
            if (lyricFile.exists()) {
                boolean deleted = lyricFile.delete();
                if (!deleted) {
                    return R.error("歌词文件删除失败");
                }
            } else {
                return R.error("歌词文件不存在：" + music.getLyric());
            }
        }
        musicService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 更新在线音乐状态
     *
     * @param id     在线音乐id
     * @param status 在线音乐状态
     * @return 页面响应entity
     */
    @GetMapping("/updateStatus/{id}/status/{status}")
    @PreAuthorize("hasAuthority('data:music:edit')")
    public R updateStatus(@PathVariable(value = "id") Integer id,
                          @PathVariable(value = "status") Integer status) {
        Music music = musicService.getById(id);
        music.setStatus(status);
        musicService.saveOrUpdate(music);
        return R.ok();
    }

    /**
     * 上传在线音乐海报图或缩略图
     *
     * @param file 在线音乐海报图或缩略图
     * @return 页面响应entity
     * @throws Exception 在线音乐海报图或缩略图上传异常
     */
    @RequestMapping("/uploadImage")
    @PreAuthorize("hasAuthority('data:music:edit')")
    public Map<String, Object> uploadImage(MultipartFile file) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        if (!file.isEmpty()) {
            // 获取文件名
            String originalFilename = file.getOriginalFilename();
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = DateUtil.getCurrentDateStr() + suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(musicImagesFilePath + newFileName));
            resultMap.put("code", 0);
            resultMap.put("msg", "上传成功");
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("title", newFileName);
            dataMap.put("src", "image/musicPicture/" + newFileName);
            resultMap.put("data", dataMap);
        }
        return resultMap;
    }

    /**
     * 上传歌词文件
     *
     * @param file 歌词文件
     * @return 页面响应entity
     * @throws Exception 歌词文件上传异常
     */
    @RequestMapping("/uploadLyric")
    @PreAuthorize("hasAuthority('data:music:edit')")
    public Map<String, Object> uploadLyric(MultipartFile file) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        if (!file.isEmpty()) {
            // 获取文件名
            String originalFilename = file.getOriginalFilename();
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = DateUtil.getCurrentDateStr() + suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(lyricFilePath + newFileName));
            resultMap.put("code", 0);
            resultMap.put("msg", "上传成功");
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("title", newFileName);
            dataMap.put("src", "audio/lyric/" + newFileName);
            resultMap.put("data", dataMap);
        }
        return resultMap;
    }

    /**
     * 上传音乐文件
     *
     * @param file 音乐文件
     * @return 页面响应entity
     * @throws Exception 音乐文件上传异常
     */
    @RequestMapping("/uploadAudio")
    @PreAuthorize("hasAuthority('data:music:edit')")
    public Map<String, Object> uploadAudio(MultipartFile file) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        if (!file.isEmpty()) {
            // 获取文件名
            String originalFilename = file.getOriginalFilename();
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = DateUtil.getCurrentDateStr() + suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(audioFilePath + newFileName));
            resultMap.put("code", 0);
            resultMap.put("msg", "上传成功");
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("title", newFileName);
            dataMap.put("src", "audio/music/" + newFileName);
            resultMap.put("data", dataMap);
        }
        return resultMap;
    }

    /**
     * 修改在线音乐海报图
     *
     * @param music 在线音乐信息
     * @return 页面响应entity
     */
    @RequestMapping("/updatePosterPicture")
    @PreAuthorize("hasAuthority('data:music:edit')")
    public R updatePosterPicture(@RequestBody Music music) {
        Music currentMusic = musicService.getById(music.getId());
        String posterImagePath = musicImagesFilePath + currentMusic.getPosterPic();
        File posterImageFile = new File(posterImagePath);
        if (posterImageFile.exists()) {
            boolean deleted = posterImageFile.delete();
            if (!deleted) {
                return R.error("音乐海报图片删除失败");
            }
        } else {
            return R.error("音乐海报图片不存在：" + currentMusic.getPosterPic());
        }
        currentMusic.setPosterPic(music.getPosterPic());
        musicService.updateById(currentMusic);
        return R.ok();
    }

    /**
     * 修改在线音乐缩略图
     *
     * @param music 在线音乐信息
     * @return 页面响应entity
     */
    @RequestMapping("/updateThumbnailPicture")
    @PreAuthorize("hasAuthority('data:music:edit')")
    public R updateThumbnailPicture(@RequestBody Music music) {
        Music currentMusic = musicService.getById(music.getId());
        String thumbnailImagePath = musicImagesFilePath + currentMusic.getThumbnailPic();
        File thumbnailImageFile = new File(thumbnailImagePath);
        if (thumbnailImageFile.exists()) {
            boolean deleted = thumbnailImageFile.delete();
            if (!deleted) {
                return R.error("音乐缩略图图片删除失败");
            }
        } else {
            return R.error("音乐缩略图图片不存在：" + currentMusic.getThumbnailPic());
        }
        currentMusic.setThumbnailPic(music.getThumbnailPic());
        musicService.updateById(currentMusic);
        return R.ok();
    }

    /**
     * 修改在线音乐歌词文件
     *
     * @param music 在线音乐信息
     * @return 页面响应entity
     */
    @RequestMapping("/updateLyric")
    @PreAuthorize("hasAuthority('data:music:edit')")
    public R updateLyric(@RequestBody Music music) {
        Music currentMusic = musicService.getById(music.getId());
        String lyricPath = lyricFilePath + currentMusic.getLyric();
        File lyricFile = new File(lyricPath);
        if (lyricFile.exists()) {
            boolean deleted = lyricFile.delete();
            if (!deleted) {
                return R.error("歌词文件删除失败");
            }
        } else {
            return R.error("歌词文件不存在：" + currentMusic.getLyric());
        }
        currentMusic.setLyric(music.getLyric());
        musicService.updateById(currentMusic);
        return R.ok();
    }

    /**
     * 修改在线音乐音频（一般品质、高品质和超高品质）
     *
     * @param music 在线音乐信息
     * @return 页面响应entity
     */
    @RequestMapping("/updateAudio")
    @PreAuthorize("hasAuthority('data:music:edit')")
    public R updateAudio(@RequestBody Music music) {
        Music currentMusic = musicService.getById(music.getId());
        String audioPath = audioFilePath + currentMusic.getUrl();
        File audioFile = new File(audioPath);
        if (audioFile.exists()) {
            boolean deleted = audioFile.delete();
            if (!deleted) {
                return R.error("音乐文件删除失败");
            }
        } else {
            return R.error("音乐文件不存在：" + currentMusic.getUrl());
        }
        currentMusic.setUrl(music.getUrl());
        currentMusic.setHdUrl(music.getUrl());
        currentMusic.setUhdUrl(music.getUrl());
        currentMusic.setMusicSize(music.getMusicSize());
        currentMusic.setHdMusicSize(music.getMusicSize());
        currentMusic.setUhdMusicSize(music.getMusicSize());
        musicService.updateById(currentMusic);
        return R.ok();
    }

    /**
     * 验证在线音乐名是否存在
     *
     * @param music 在线音乐信息
     * @return 页面响应entity
     */
    @PostMapping("/checkTitle")
    @PreAuthorize("hasAuthority('data:music:query')")
    public R checkTitle(@RequestBody Music music) {
        if (musicService.getByTitle(music.getTitle()) == null) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 添加或者修改在线音乐
     *
     * @param music 在线音乐信息
     * @return 页面响应entity
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('data:music:add')" + "||" + "hasAuthority('data:music:edit')")
    public R save(@RequestBody Music music) {
        if (music.getId() == null || music.getId() == -1) {
            musicService.save(music);
            Integer id = music.getId();
            MusicUser musicUser = new MusicUser();
            musicUser.setMusicId(id);
            musicUser.setUserId(music.getSysUser().getId());
            musicUserService.save(musicUser);
        } else {
            Music currentMusic = musicService.getById(music.getId());
            if (!currentMusic.getPosterPic().equals(music.getPosterPic())) {
                String posterImagePath = musicImagesFilePath + currentMusic.getPosterPic();
                File posterImageFile = new File(posterImagePath);
                if (posterImageFile.exists()) {
                    boolean deleted = posterImageFile.delete();
                    if (!deleted) {
                        return R.error("音乐海报图片删除失败");
                    }
                } else {
                    return R.error("音乐海报图片不存在：" + currentMusic.getPosterPic());
                }
            }
            if (!currentMusic.getThumbnailPic().equals(music.getThumbnailPic())) {
                String thumbnailImagePath = musicImagesFilePath + currentMusic.getThumbnailPic();
                File thumbnailImageFile = new File(thumbnailImagePath);
                if (thumbnailImageFile.exists()) {
                    boolean deleted = thumbnailImageFile.delete();
                    if (!deleted) {
                        return R.error("音乐缩略图图片删除失败");
                    }
                } else {
                    return R.error("音乐缩略图图片不存在：" + currentMusic.getThumbnailPic());
                }
            }
            if (!currentMusic.getUrl().equals(music.getUrl())) {
                String audioPath = audioFilePath + currentMusic.getUrl();
                File audioFile = new File(audioPath);
                if (audioFile.exists()) {
                    boolean deleted = audioFile.delete();
                    if (!deleted) {
                        return R.error("音乐文件删除失败");
                    }
                } else {
                    return R.error("音乐文件不存在：" + currentMusic.getUrl());
                }
                music.setUrl(music.getUrl());
                music.setHdUrl(music.getUrl());
                music.setUhdUrl(music.getUrl());
                music.setMusicSize(music.getMusicSize());
                music.setHdMusicSize(music.getMusicSize());
                music.setUhdMusicSize(music.getMusicSize());
            }
            if (!currentMusic.getLyric().equals(music.getLyric())) {
                String lyricPath = lyricFilePath + currentMusic.getLyric();
                File lyricFile = new File(lyricPath);
                if (lyricFile.exists()) {
                    boolean deleted = lyricFile.delete();
                    if (!deleted) {
                        return R.error("歌词文件删除失败");
                    }
                } else {
                    return R.error("歌词文件不存在：" + currentMusic.getLyric());
                }
            }
            musicService.updateById(music);
        }
        return R.ok();
    }

    /**
     * 删除用户上传的未保存的文件缓存
     *
     * @param music 在线音乐信息
     * @return 页面响应entity
     */
    @PostMapping("/deleteUploadFileCache")
    @PreAuthorize("hasAuthority('data:music:delete')")
    public R deleteUploadFileCache(@RequestBody Music music) {
        if (StrUtil.isNotBlank(music.getPosterPic())) {
            String posterImagePath = musicImagesFilePath + music.getPosterPic();
            File posterImageFile = new File(posterImagePath);
            if (posterImageFile.exists()) {
                boolean deleted = posterImageFile.delete();
                if (!deleted) {
                    return R.error("音乐海报图片删除失败");
                }
            } else {
                return R.error("音乐海报图片不存在：" + music.getPosterPic());
            }
        }
        if (StrUtil.isNotBlank(music.getThumbnailPic())) {
            String thumbnailImagePath = musicImagesFilePath + music.getThumbnailPic();
            File thumbnailImageFile = new File(thumbnailImagePath);
            if (thumbnailImageFile.exists()) {
                boolean deleted = thumbnailImageFile.delete();
                if (!deleted) {
                    return R.error("音乐缩略图图片删除失败");
                }
            } else {
                return R.error("音乐缩略图图片不存在：" + music.getThumbnailPic());
            }
        }
        if (StrUtil.isNotBlank(music.getUrl())) {
            String audioPath = audioFilePath + music.getUrl();
            File audioFile = new File(audioPath);
            if (audioFile.exists()) {
                boolean deleted = audioFile.delete();
                if (!deleted) {
                    return R.error("音乐文件删除失败");
                }
            } else {
                return R.error("音乐文件不存在：" + music.getUrl());
            }
        }
        if (StrUtil.isNotBlank(music.getLyric())) {
            String lyricPath = lyricFilePath + music.getLyric();
            File lyricFile = new File(lyricPath);
            if (lyricFile.exists()) {
                boolean deleted = lyricFile.delete();
                if (!deleted) {
                    return R.error("歌词文件删除失败");
                }
            } else {
                return R.error("歌词文件不存在：" + music.getLyric());
            }
        }
        return R.ok();
    }

    /**
     * 查询在线音乐总数
     *
     * @return 页面响应entity
     */
    @GetMapping("/total")
    @PreAuthorize("hasAuthority('data:music:query')")
    public R total() {
        Long total = musicService.count();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", total);
        return R.ok(resultMap);
    }

}