package com.melodiousplayer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.melodiousplayer.entity.*;
import com.melodiousplayer.service.MvAreaCodeService;
import com.melodiousplayer.service.MvAreaService;
import com.melodiousplayer.service.MvService;
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
    private MvAreaService mvAreaService;

    @Autowired
    private MvAreaCodeService mvAreaCodeService;

    @Value("${mvImagesFilePath}")
    private String mvImagesFilePath;

    @Value("${mvFilePath}")
    private String mvFilePath;

    /**
     * 查询所有MV信息
     *
     * @return 页面响应entity
     */
    @GetMapping("/get_mv_list")
    public Map<String, Object> selectAll(@RequestParam("offset") Integer offset,
                                         @RequestParam("size") Integer size,
                                         @RequestParam("area") String area) {
        Page<Mv> pageResult = mvService.page(new Page<>(offset, size),
                new QueryWrapper<Mv>().inSql(
                        "id", "select mv_id from mv_area_code where " +
                                "mv_area_id in (select id from mv_area where code = \"" + area + "\")"
                ));
        List<Mv> mvList = pageResult.getRecords();
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
        for (Mv mv : mvList) {
            MvArea mvArea = mvAreaService.getOne(new QueryWrapper<MvArea>().inSql(
                    "id", "select mv_area_id from mv_area_code where mv_id = " + mv.getId()
            ));
            mv.setMvArea(mvArea);
        }
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
        for (Long id : ids) {
            Mv mv = mvService.getById(id);
            String posterImagePath = mvImagesFilePath + mv.getPosterPic();
            String thumbnailImagePath = mvImagesFilePath + mv.getThumbnailPic();
            String mvPath = mvFilePath + mv.getUrl();
            File posterImageFile = new File(posterImagePath);
            File thumbnailImageFile = new File(thumbnailImagePath);
            File mvFile = new File(mvPath);
            if (posterImageFile.exists()) {
                boolean deleted = posterImageFile.delete();
                if (!deleted) {
                    return R.error("海报图片删除失败");
                }
            } else {
                return R.error("海报图片不存在：" + mv.getPosterPic());
            }
            if (thumbnailImageFile.exists()) {
                boolean deleted = thumbnailImageFile.delete();
                if (!deleted) {
                    return R.error("缩略图图片删除失败");
                }
            } else {
                return R.error("缩略图图片不存在：" + mv.getThumbnailPic());
            }
            if (mvFile.exists()) {
                boolean deleted = mvFile.delete();
                if (!deleted) {
                    return R.error("MV文件删除失败");
                }
            } else {
                return R.error("MV文件不存在：" + mv.getUrl());
            }
        }
        mvService.removeByIds(Arrays.asList(ids));
        mvAreaCodeService.remove(new QueryWrapper<MvAreaCode>().in("mv_id", Arrays.asList(ids)));
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
        MvArea mvArea = mvAreaService.getOne(new QueryWrapper<MvArea>().inSql(
                "id", "select mv_area_id from mv_area_code where mv_id = " + mvItem.getId()
        ));
        mvItem.setMvArea(mvArea);
        Map<String, Object> map = new HashMap<>();
        map.put("mvItem", mvItem);
        return R.ok(map);
    }

    /**
     * 上传在线MV海报图或缩略图
     *
     * @param file 在线MV海报图或缩略图
     * @return 页面响应entity
     * @throws Exception 在线MV海报图或缩略图上传异常
     */
    @RequestMapping("/uploadImage")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public Map<String, Object> uploadImage(MultipartFile file) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        if (!file.isEmpty()) {
            // 获取文件名
            String originalFilename = file.getOriginalFilename();
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = DateUtil.getCurrentDateStr() + suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(mvImagesFilePath + newFileName));
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
     * 上传MV视频文件
     *
     * @param file MV视频文件
     * @return 页面响应entity
     * @throws Exception 音乐文件上传异常
     */
    @RequestMapping("/uploadVideo")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public Map<String, Object> uploadVideo(MultipartFile file) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        if (!file.isEmpty()) {
            // 获取文件名
            String originalFilename = file.getOriginalFilename();
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = DateUtil.getCurrentDateStr() + suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(mvFilePath + newFileName));
            resultMap.put("code", 0);
            resultMap.put("msg", "上传成功");
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("title", newFileName);
            dataMap.put("src", "video/mv/" + newFileName);
            resultMap.put("data", dataMap);
        }
        return resultMap;
    }

    /**
     * 验证在线MV名是否存在
     *
     * @param mv 在线MV信息
     * @return 页面响应entity
     */
    @PostMapping("/checkTitle")
    @PreAuthorize("hasAuthority('system:user:query')")
    public R checkTitle(@RequestBody Mv mv) {
        if (mvService.getByTitle(mv.getTitle()) == null) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 修改在线MV海报图
     *
     * @param mv 在线MV信息
     * @return 页面响应entity
     */
    @RequestMapping("/updatePosterPicture")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public R updatePosterPicture(@RequestBody Mv mv) {
        Mv currentMV = mvService.getById(mv.getId());
        String posterImagePath = mvImagesFilePath + currentMV.getPosterPic();
        File posterImageFile = new File(posterImagePath);
        if (posterImageFile.exists()) {
            boolean deleted = posterImageFile.delete();
            if (!deleted) {
                return R.error("MV海报图片删除失败");
            }
        } else {
            return R.error("MV海报图片不存在：" + currentMV.getPosterPic());
        }
        currentMV.setPosterPic(mv.getPosterPic());
        mvService.updateById(currentMV);
        return R.ok();
    }

    /**
     * 修改在线MV缩略图
     *
     * @param mv 在线MV信息
     * @return 页面响应entity
     */
    @RequestMapping("/updateThumbnailPicture")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public R updateThumbnailPicture(@RequestBody Mv mv) {
        Mv currentMV = mvService.getById(mv.getId());
        String thumbnailImagePath = mvImagesFilePath + currentMV.getThumbnailPic();
        File thumbnailImageFile = new File(thumbnailImagePath);
        if (thumbnailImageFile.exists()) {
            boolean deleted = thumbnailImageFile.delete();
            if (!deleted) {
                return R.error("MV缩略图图片删除失败");
            }
        } else {
            return R.error("MV缩略图图片不存在：" + currentMV.getThumbnailPic());
        }
        currentMV.setThumbnailPic(mv.getThumbnailPic());
        mvService.updateById(currentMV);
        return R.ok();
    }

    /**
     * 修改在线MV视频（一般品质、高品质和超高品质）
     *
     * @param mv 在线MV信息
     * @return 页面响应entity
     */
    @RequestMapping("/updateVideo")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public R updateVideo(@RequestBody Mv mv) {
        Mv currentMV = mvService.getById(mv.getId());
        String videoPath = mvFilePath + currentMV.getUrl();
        File lyricFile = new File(videoPath);
        if (lyricFile.exists()) {
            boolean deleted = lyricFile.delete();
            if (!deleted) {
                return R.error("MV文件删除失败");
            }
        } else {
            return R.error("MV文件不存在：" + currentMV.getUrl());
        }
        currentMV.setUrl(mv.getUrl());
        currentMV.setHdUrl(mv.getUrl());
        currentMV.setUhdUrl(mv.getUrl());
        currentMV.setVideoSize(mv.getVideoSize());
        currentMV.setHdVideoSize(mv.getVideoSize());
        currentMV.setUhdVideoSize(mv.getVideoSize());
        currentMV.setDuration(mv.getDuration());
        mvService.updateById(currentMV);
        return R.ok();
    }

    /**
     * 添加或者修改在线MV
     *
     * @param mv 在线MV信息
     * @return 页面响应entity
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('system:user:add')" + "||" + "hasAuthority('system:user:edit')")
    public R save(@RequestBody Mv mv) {
        if (mv.getId() == null || mv.getId() == -1) {
            mvService.save(mv);
            Integer id = mv.getId();
            MvAreaCode mvAreaCode = new MvAreaCode();
            mvAreaCode.setMvId(id);
            mvAreaCode.setMvAreaId(mv.getMvArea().getId());
            mvAreaCodeService.save(mvAreaCode);
        } else {
            Mv currentMV = mvService.getById(mv.getId());
            if (!currentMV.getPosterPic().equals(mv.getPosterPic())) {
                String posterImagePath = mvImagesFilePath + currentMV.getPosterPic();
                File posterImageFile = new File(posterImagePath);
                if (posterImageFile.exists()) {
                    boolean deleted = posterImageFile.delete();
                    if (!deleted) {
                        return R.error("MV海报图片删除失败");
                    }
                } else {
                    return R.error("MV海报图片不存在：" + currentMV.getPosterPic());
                }
            }
            if (!currentMV.getThumbnailPic().equals(mv.getThumbnailPic())) {
                String thumbnailImagePath = mvImagesFilePath + currentMV.getThumbnailPic();
                File thumbnailImageFile = new File(thumbnailImagePath);
                if (thumbnailImageFile.exists()) {
                    boolean deleted = thumbnailImageFile.delete();
                    if (!deleted) {
                        return R.error("MV缩略图图片删除失败");
                    }
                } else {
                    return R.error("MV缩略图图片不存在：" + currentMV.getThumbnailPic());
                }
            }
            if (!currentMV.getUrl().equals(mv.getUrl())) {
                String videoPath = mvFilePath + currentMV.getUrl();
                File lyricFile = new File(videoPath);
                if (lyricFile.exists()) {
                    boolean deleted = lyricFile.delete();
                    if (!deleted) {
                        return R.error("MV文件删除失败");
                    }
                } else {
                    return R.error("MV文件不存在：" + currentMV.getUrl());
                }
                mv.setHdUrl(mv.getUrl());
                mv.setUhdUrl(mv.getUrl());
                mv.setHdVideoSize(mv.getVideoSize());
                mv.setUhdVideoSize(mv.getVideoSize());
            }
            mvService.updateById(mv);
            mvAreaCodeService.updateByMVIDAndMVAreaID(mv.getId(), mv.getOldMvAreaId(), mv.getMvArea().getId());
        }
        return R.ok();
    }

    /**
     * 查询在线MV总数
     *
     * @return 页面响应entity
     */
    @GetMapping("/total")
    @PreAuthorize("hasAuthority('system:user:query')")
    public R total() {
        Long total = mvService.count();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", total);
        return R.ok(resultMap);
    }

}
