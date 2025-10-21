package com.melodiousplayer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.melodiousplayer.entity.*;
import com.melodiousplayer.service.AndroidApplicationService;
import com.melodiousplayer.util.DateUtil;
import com.melodiousplayer.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mike
 * @date 2025/10/20
 */
@RestController
@RequestMapping("/sys/android")
public class AndroidApplicationController {

    @Autowired
    private AndroidApplicationService androidApplicationService;

    @Value("${androidApplicationImagesFilePath}")
    private String androidApplicationImagesFilePath;

    @Value("${androidApplicationFilePath}")
    private String androidApplicationFilePath;

    /**
     * 根据条件分页查询安卓应用信息
     *
     * @param pageBean 分页信息
     * @return 页面响应entity
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('system:android:query')")
    public R list(@RequestBody PageBean pageBean) {
        String query = pageBean.getQuery().trim();
        Page<AndroidApplication> pageResult =
                androidApplicationService.page(new Page<>(pageBean.getPageNum(),
                        pageBean.getPageSize()), new QueryWrapper<AndroidApplication>()
                        .like(StringUtil.isNotEmpty(query), "version", query));
        List<AndroidApplication> androidApplicationList = pageResult.getRecords();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("androidApplicationList", androidApplicationList);
        resultMap.put("total", pageResult.getTotal());
        return R.ok(resultMap);
    }

    /**
     * 删除安卓应用
     *
     * @param ids 安卓应用id
     * @return 页面响应entity
     */
    @Transactional
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('system:android:delete')")
    public R delete(@RequestBody Long[] ids) {
        for (Long id : ids) {
            AndroidApplication androidApplication = androidApplicationService.getById(id);
            String androidApplicationIconPath = androidApplicationImagesFilePath + androidApplication.getIcon();
            String androidApplicationPath = androidApplicationFilePath + androidApplication.getUrl();
            File ndroidApplicationIconFile = new File(androidApplicationIconPath);
            File androidApplicationFile = new File(androidApplicationPath);
            if (ndroidApplicationIconFile.exists()) {
                boolean deleted = ndroidApplicationIconFile.delete();
                if (!deleted) {
                    return R.error("安卓应用图标删除失败");
                }
            } else {
                return R.error("安卓应用图标不存在：" + androidApplication.getIcon());
            }
            if (androidApplicationFile.exists()) {
                boolean deleted = androidApplicationFile.delete();
                if (!deleted) {
                    return R.error("安卓应用文件删除失败");
                }
            } else {
                return R.error("安卓应用文件不存在：" + androidApplication.getUrl());
            }
        }
        androidApplicationService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 上传安卓应用图标
     *
     * @param file 安卓应用图标
     * @return 页面响应entity
     * @throws Exception 安卓应用图标上传异常
     */
    @RequestMapping("/uploadImage")
    @PreAuthorize("hasAuthority('system:android:edit')")
    public Map<String, Object> uploadImage(MultipartFile file) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        if (!file.isEmpty()) {
            // 获取文件名
            String originalFilename = file.getOriginalFilename();
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = DateUtil.getCurrentDateStr() + suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(),
                    new File(androidApplicationImagesFilePath + newFileName));
            resultMap.put("code", 0);
            resultMap.put("msg", "上传成功");
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("title", newFileName);
            dataMap.put("src", "image/androidApplicationPicture/" + newFileName);
            resultMap.put("data", dataMap);
        }
        return resultMap;
    }

    /**
     * 上传安卓应用文件
     *
     * @param file 安卓应用文件
     * @return 页面响应entity
     * @throws Exception 安卓应用文件上传异常
     */
    @RequestMapping("/uploadAPK")
    @PreAuthorize("hasAuthority('system:android:edit')")
    public Map<String, Object> uploadAPK(MultipartFile file) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        if (!file.isEmpty()) {
            // 获取文件名
            String originalFilename = file.getOriginalFilename();
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = DateUtil.getCurrentDateStr() + suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(),
                    new File(androidApplicationFilePath + newFileName));
            resultMap.put("code", 0);
            resultMap.put("msg", "上传成功");
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("title", newFileName);
            dataMap.put("src", "application/android/" + newFileName);
            resultMap.put("data", dataMap);
        }
        return resultMap;
    }

}
