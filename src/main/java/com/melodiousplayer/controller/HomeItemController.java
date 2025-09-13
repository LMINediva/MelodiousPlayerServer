package com.melodiousplayer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.melodiousplayer.entity.*;
import com.melodiousplayer.service.HomeItemService;
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
public class HomeItemController {

    @Autowired
    private HomeItemService homeItemService;

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
    public List<HomeItem> selectAll(@RequestParam("offset") Integer offset,
                                    @RequestParam("size") Integer size) {
        Page<HomeItem> pageResult = homeItemService.page(new Page<>(offset, size));
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
        Page<HomeItem> pageResult = homeItemService.page(new Page<>(pageBean.getPageNum(), pageBean.getPageSize()),
                new QueryWrapper<HomeItem>().like(StringUtil.isNotEmpty(query), "title", query));
        List<HomeItem> homeItemList = pageResult.getRecords();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("musicList", homeItemList);
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
        HomeItem homeItem = homeItemService.getById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("homeItem", homeItem);
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
            HomeItem homeItem = homeItemService.getById(id);
            String posterImagePath = musicImagesFilePath + homeItem.getPosterPic();
            String thumbnailImagePath = musicImagesFilePath + homeItem.getThumbnailPic();
            String audioPath = audioFilePath + homeItem.getUrl();
            String lyricPath = lyricFilePath + homeItem.getLyric();
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
                return R.error("海报图片不存在：" + homeItem.getPosterPic());
            }
            if (thumbnailImageFile.exists()) {
                boolean deleted = thumbnailImageFile.delete();
                if (!deleted) {
                    return R.error("缩略图图片删除失败");
                }
            } else {
                return R.error("缩略图图片不存在：" + homeItem.getThumbnailPic());
            }
            if (audioFile.exists()) {
                boolean deleted = audioFile.delete();
                if (!deleted) {
                    return R.error("音乐文件删除失败");
                }
            } else {
                return R.error("音乐文件不存在：" + homeItem.getUrl());
            }
            if (lyricFile.exists()) {
                boolean deleted = lyricFile.delete();
                if (!deleted) {
                    return R.error("歌词文件删除失败");
                }
            } else {
                return R.error("歌词文件不存在：" + homeItem.getLyric());
            }
        }
        homeItemService.removeByIds(Arrays.asList(ids));
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
        HomeItem homeItem = homeItemService.getById(id);
        homeItem.setStatus(status);
        homeItemService.saveOrUpdate(homeItem);
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
     * @param homeItem 在线音乐信息
     * @return 页面响应entity
     */
    @RequestMapping("/updatePosterPicture")
    @PreAuthorize("hasAuthority('data:music:edit')")
    public R updatePosterPicture(@RequestBody HomeItem homeItem) {
        HomeItem currentHomeItem = homeItemService.getById(homeItem.getId());
        String posterImagePath = musicImagesFilePath + currentHomeItem.getPosterPic();
        File posterImageFile = new File(posterImagePath);
        if (posterImageFile.exists()) {
            boolean deleted = posterImageFile.delete();
            if (!deleted) {
                return R.error("音乐海报图片删除失败");
            }
        } else {
            return R.error("音乐海报图片不存在：" + currentHomeItem.getPosterPic());
        }
        currentHomeItem.setPosterPic(homeItem.getPosterPic());
        homeItemService.updateById(currentHomeItem);
        return R.ok();
    }

    /**
     * 修改在线音乐缩略图
     *
     * @param homeItem 在线音乐信息
     * @return 页面响应entity
     */
    @RequestMapping("/updateThumbnailPicture")
    @PreAuthorize("hasAuthority('data:music:edit')")
    public R updateThumbnailPicture(@RequestBody HomeItem homeItem) {
        HomeItem currentHomeItem = homeItemService.getById(homeItem.getId());
        String thumbnailImagePath = musicImagesFilePath + currentHomeItem.getThumbnailPic();
        File thumbnailImageFile = new File(thumbnailImagePath);
        if (thumbnailImageFile.exists()) {
            boolean deleted = thumbnailImageFile.delete();
            if (!deleted) {
                return R.error("音乐缩略图图片删除失败");
            }
        } else {
            return R.error("音乐缩略图图片不存在：" + currentHomeItem.getThumbnailPic());
        }
        currentHomeItem.setThumbnailPic(homeItem.getThumbnailPic());
        homeItemService.updateById(currentHomeItem);
        return R.ok();
    }

    /**
     * 修改在线音乐歌词文件
     *
     * @param homeItem 在线音乐信息
     * @return 页面响应entity
     */
    @RequestMapping("/updateLyric")
    @PreAuthorize("hasAuthority('data:music:edit')")
    public R updateLyric(@RequestBody HomeItem homeItem) {
        HomeItem currentHomeItem = homeItemService.getById(homeItem.getId());
        String lyricPath = lyricFilePath + currentHomeItem.getLyric();
        File lyricFile = new File(lyricPath);
        if (lyricFile.exists()) {
            boolean deleted = lyricFile.delete();
            if (!deleted) {
                return R.error("歌词文件删除失败");
            }
        } else {
            return R.error("歌词文件不存在：" + currentHomeItem.getLyric());
        }
        currentHomeItem.setLyric(homeItem.getLyric());
        homeItemService.updateById(currentHomeItem);
        return R.ok();
    }

    /**
     * 修改在线音乐音频（一般品质、高品质和超高品质）
     *
     * @param homeItem 在线音乐信息
     * @return 页面响应entity
     */
    @RequestMapping("/updateAudio")
    @PreAuthorize("hasAuthority('data:music:edit')")
    public R updateAudio(@RequestBody HomeItem homeItem) {
        HomeItem currentHomeItem = homeItemService.getById(homeItem.getId());
        String audioPath = audioFilePath + currentHomeItem.getUrl();
        File audioFile = new File(audioPath);
        if (audioFile.exists()) {
            boolean deleted = audioFile.delete();
            if (!deleted) {
                return R.error("音乐文件删除失败");
            }
        } else {
            return R.error("音乐文件不存在：" + currentHomeItem.getUrl());
        }
        currentHomeItem.setUrl(homeItem.getUrl());
        currentHomeItem.setHdUrl(homeItem.getUrl());
        currentHomeItem.setUhdUrl(homeItem.getUrl());
        currentHomeItem.setMusicSize(homeItem.getMusicSize());
        currentHomeItem.setHdMusicSize(homeItem.getMusicSize());
        currentHomeItem.setUhdMusicSize(homeItem.getMusicSize());
        homeItemService.updateById(currentHomeItem);
        return R.ok();
    }

    /**
     * 验证在线音乐名是否存在
     *
     * @param homeItem 在线音乐信息
     * @return 页面响应entity
     */
    @PostMapping("/checkTitle")
    @PreAuthorize("hasAuthority('data:music:query')")
    public R checkTitle(@RequestBody HomeItem homeItem) {
        if (homeItemService.getByTitle(homeItem.getTitle()) == null) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 添加或者修改在线音乐
     *
     * @param homeItem 在线音乐信息
     * @return 页面响应entity
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('data:music:add')" + "||" + "hasAuthority('data:music:edit')")
    public R save(@RequestBody HomeItem homeItem) {
        if (homeItem.getId() == null || homeItem.getId() == -1) {
            homeItemService.save(homeItem);
        } else {
            HomeItem currentHomeItem = homeItemService.getById(homeItem.getId());
            if (!currentHomeItem.getPosterPic().equals(homeItem.getPosterPic())) {
                String posterImagePath = musicImagesFilePath + currentHomeItem.getPosterPic();
                File posterImageFile = new File(posterImagePath);
                if (posterImageFile.exists()) {
                    boolean deleted = posterImageFile.delete();
                    if (!deleted) {
                        return R.error("音乐海报图片删除失败");
                    }
                } else {
                    return R.error("音乐海报图片不存在：" + currentHomeItem.getPosterPic());
                }
            }
            if (!currentHomeItem.getThumbnailPic().equals(homeItem.getThumbnailPic())) {
                String thumbnailImagePath = musicImagesFilePath + currentHomeItem.getThumbnailPic();
                File thumbnailImageFile = new File(thumbnailImagePath);
                if (thumbnailImageFile.exists()) {
                    boolean deleted = thumbnailImageFile.delete();
                    if (!deleted) {
                        return R.error("音乐缩略图图片删除失败");
                    }
                } else {
                    return R.error("音乐缩略图图片不存在：" + currentHomeItem.getThumbnailPic());
                }
            }
            if (!currentHomeItem.getUrl().equals(homeItem.getUrl())) {
                String audioPath = audioFilePath + currentHomeItem.getUrl();
                File audioFile = new File(audioPath);
                if (audioFile.exists()) {
                    boolean deleted = audioFile.delete();
                    if (!deleted) {
                        return R.error("音乐文件删除失败");
                    }
                } else {
                    return R.error("音乐文件不存在：" + currentHomeItem.getUrl());
                }
                homeItem.setUrl(homeItem.getUrl());
                homeItem.setHdUrl(homeItem.getUrl());
                homeItem.setUhdUrl(homeItem.getUrl());
                homeItem.setMusicSize(homeItem.getMusicSize());
                homeItem.setHdMusicSize(homeItem.getMusicSize());
                homeItem.setUhdMusicSize(homeItem.getMusicSize());
            }
            if (!currentHomeItem.getLyric().equals(homeItem.getLyric())) {
                String lyricPath = lyricFilePath + currentHomeItem.getLyric();
                File lyricFile = new File(lyricPath);
                if (lyricFile.exists()) {
                    boolean deleted = lyricFile.delete();
                    if (!deleted) {
                        return R.error("歌词文件删除失败");
                    }
                } else {
                    return R.error("歌词文件不存在：" + currentHomeItem.getLyric());
                }
            }
            homeItemService.updateById(homeItem);
        }
        return R.ok();
    }

    @GetMapping("/front_page2")
    public R selectById(@RequestParam("offset") Integer offset,
                        @RequestParam("size") Integer size) {
        System.out.println("offset = " + offset);
        System.out.println("size = " + size);
        HomeItem homeItem = homeItemService.getById(1);
        Map<String, Object> map = new HashMap<>();
        map.put("result", homeItem);
        return R.ok(map);
    }

    /**
     * 查询在线音乐总数
     *
     * @return 页面响应entity
     */
    @GetMapping("/total")
    @PreAuthorize("hasAuthority('data:music:query')")
    public R total() {
        Long total = homeItemService.count();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", total);
        return R.ok(resultMap);
    }

}