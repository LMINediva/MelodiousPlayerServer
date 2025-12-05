package com.melodiousplayer.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.melodiousplayer.entity.*;
import com.melodiousplayer.service.AndroidApplicationService;
import com.melodiousplayer.util.APKVersionUtils;
import com.melodiousplayer.util.DateUtils;
import com.melodiousplayer.util.StringUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
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

    @Autowired
    private Environment environment;

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
                        .like(StringUtils.isNotEmpty(query), "version", query));
        List<AndroidApplication> androidApplicationList = pageResult.getRecords();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("androidApplicationList", androidApplicationList);
        resultMap.put("total", pageResult.getTotal());
        return R.ok(resultMap);
    }

    /**
     * 根据id查询安卓应用
     *
     * @param id 安卓应用id
     * @return 页面响应entity
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('system:android:query')")
    public R findById(@PathVariable(value = "id") Integer id) {
        AndroidApplication androidApplication = androidApplicationService.getById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("androidApplication", androidApplication);
        return R.ok(map);
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
            File androidApplicationIconFile = new File(androidApplicationIconPath);
            File androidApplicationFile = new File(androidApplicationPath);
            if (androidApplicationIconFile.exists()) {
                boolean deleted = androidApplicationIconFile.delete();
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
            String newFileName = DateUtils.getCurrentDateStr() + suffixName;
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
            String newFileName = DateUtils.getCurrentDateStr() + suffixName;
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

    /**
     * 修改安卓应用图标图片
     *
     * @param androidApplication 安卓应用信息
     * @return 页面响应entity
     */
    @RequestMapping("/updateIconPicture")
    @PreAuthorize("hasAuthority('system:android:edit')")
    public R updateIconPicture(@RequestBody AndroidApplication androidApplication) {
        AndroidApplication currentAndroidApplication =
                androidApplicationService.getById(androidApplication.getId());
        String iconImagePath = androidApplicationImagesFilePath + currentAndroidApplication.getIcon();
        File iconImageFile = new File(iconImagePath);
        if (iconImageFile.exists()) {
            boolean deleted = iconImageFile.delete();
            if (!deleted) {
                return R.error("安卓应用图标图片删除失败");
            }
        } else {
            return R.error("安卓应用图标图片不存在：" + currentAndroidApplication.getIcon());
        }
        currentAndroidApplication.setIcon(androidApplication.getIcon());
        androidApplicationService.updateById(currentAndroidApplication);
        return R.ok();
    }

    /**
     * 修改安卓应用文件
     *
     * @param androidApplication 安卓应用信息
     * @return 页面响应entity
     */
    @RequestMapping("/updateAndroidApplication")
    @PreAuthorize("hasAuthority('system:android:edit')")
    public R updateAndroidApplication(@RequestBody AndroidApplication androidApplication) {
        AndroidApplication currentAndroidApplication =
                androidApplicationService.getById(androidApplication.getId());
        String androidApplicationPath = androidApplicationFilePath + currentAndroidApplication.getUrl();
        File androidApplicationFile = new File(androidApplicationPath);
        if (androidApplicationFile.exists()) {
            boolean deleted = androidApplicationFile.delete();
            if (!deleted) {
                return R.error("安卓应用文件删除失败");
            }
        } else {
            return R.error("安卓应用文件不存在：" + currentAndroidApplication.getUrl());
        }
        currentAndroidApplication.setUrl(androidApplication.getUrl());
        currentAndroidApplication.setSize(androidApplication.getSize());
        androidApplicationService.updateById(currentAndroidApplication);
        return R.ok();
    }

    /**
     * 添加或者修改安卓应用
     *
     * @param androidApplication 安卓应用信息
     * @return 页面响应entity
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('system:android:add')" + "||" + "hasAuthority('system:android:edit')")
    public R save(@RequestBody AndroidApplication androidApplication) {
        if (androidApplication.getId() == null || androidApplication.getId() == -1) {
            androidApplicationService.save(androidApplication);
        } else {
            AndroidApplication currentAndroidApplication =
                    androidApplicationService.getById(androidApplication.getId());
            if (!currentAndroidApplication.getIcon().equals(androidApplication.getIcon())) {
                String iconImagePath = androidApplicationImagesFilePath + currentAndroidApplication.getIcon();
                File iconImageFile = new File(iconImagePath);
                if (iconImageFile.exists()) {
                    boolean deleted = iconImageFile.delete();
                    if (!deleted) {
                        return R.error("安卓应用图标图片删除失败");
                    }
                } else {
                    return R.error("安卓应用图标图片不存在：" + currentAndroidApplication.getIcon());
                }
            }
            if (!currentAndroidApplication.getUrl().equals(androidApplication.getUrl())) {
                String androidApplicationPath = androidApplicationFilePath + currentAndroidApplication.getUrl();
                File androidApplicationFile = new File(androidApplicationPath);
                if (androidApplicationFile.exists()) {
                    boolean deleted = androidApplicationFile.delete();
                    if (!deleted) {
                        return R.error("安卓应用文件删除失败");
                    }
                } else {
                    return R.error("安卓应用文件不存在：" + currentAndroidApplication.getUrl());
                }
                androidApplication.setUrl(androidApplication.getUrl());
                androidApplication.setSize(androidApplication.getSize());
            }
            androidApplicationService.updateById(androidApplication);
        }
        return R.ok();
    }

    /**
     * 删除用户上传的未保存的安卓应用文件
     *
     * @param androidApplication 安卓应用信息
     * @return 页面响应entity
     */
    @PostMapping("/deleteUploadFileCache")
    @PreAuthorize("hasAuthority('system:android:delete')")
    public R deleteUploadFileCache(@RequestBody AndroidApplication androidApplication) {
        if (StrUtil.isNotBlank(androidApplication.getIcon())) {
            String iconImagePath = androidApplicationImagesFilePath + androidApplication.getIcon();
            File iconImageFile = new File(iconImagePath);
            if (iconImageFile.exists()) {
                boolean deleted = iconImageFile.delete();
                if (!deleted) {
                    return R.error("安卓应用图标图片删除失败");
                }
            } else {
                return R.error("安卓应用图标图片不存在：" + androidApplication.getIcon());
            }
        }
        if (StrUtil.isNotBlank(androidApplication.getUrl())) {
            String androidApplicationPath = androidApplicationFilePath + androidApplication.getUrl();
            File androidApplicationFile = new File(androidApplicationPath);
            if (androidApplicationFile.exists()) {
                boolean deleted = androidApplicationFile.delete();
                if (!deleted) {
                    return R.error("安卓应用文件删除失败");
                }
            } else {
                return R.error("安卓应用文件不存在：" + androidApplication.getUrl());
            }
        }
        return R.ok();
    }

    /**
     * 根据安卓应用文件名下载APK
     *
     * @param filename 安卓应用文件名
     * @return ResponseEntity对象
     * @throws IOException IO异常
     */
    @GetMapping("/downloadAPK/{filename}")
    public ResponseEntity<Resource> downloadAPK(@PathVariable(value = "filename") String filename)
            throws IOException {
        String androidApplicationPath = androidApplicationFilePath + filename;
        File androidApplicationFile = new File(androidApplicationPath);
        if (!androidApplicationFile.exists()) {
            return ResponseEntity.notFound().build();
        }
        // 构建文件资源
        Resource resource = new FileSystemResource(androidApplicationFile);
        // 对文件名进行URL编码，解决中文乱码问题
        String encodedFilename = URLEncoder.encode(filename, "UTF-8")
                .replaceAll("\\+", "%20");
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename*=UTF-8''" + encodedFilename)
                .body(resource);
    }

    /**
     * 检查安卓应用是否可更新
     *
     * @param version 安卓应用版本
     * @return VersionUpdate对象
     */
    @PostMapping("/checkUpdate")
    @ResponseBody
    public R checkUpdate(@RequestParam("version") String version) {
        VersionUpdate versionUpdate = new VersionUpdate();
        QueryWrapper<AndroidApplication> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("version");
        Page<AndroidApplication> page = new Page<>(1, 1);
        Page<AndroidApplication> resultPage = androidApplicationService.page(page, queryWrapper);
        AndroidApplication androidApplication = resultPage.getRecords().get(0);
        Map<String, Object> map = new HashMap<>();
        try {
            if (androidApplication != null) {
                String latestVersion = androidApplication.getVersion();
                // 版本号对比
                int result = APKVersionUtils.compareVersion(version, latestVersion);
                if (result == 0) {
                    // 版本号相等不需要更新版本
                    versionUpdate.setUpdate("No");
                } else if (result > 0) {
                    // 当前APK版本号大于数据库中APK的最新版本号，那么就不需要升级
                    versionUpdate.setUpdate("No");
                } else {
                    // 当前APK版本号小于数据库中APK的最新版本号，需要升级提示
                    versionUpdate.setApk_file_url(androidApplication.getUrl());
                    versionUpdate.setName(androidApplication.getName());
                    versionUpdate.setUpdate("Yes");
                    versionUpdate.setCode(androidApplication.getCode());
                    versionUpdate.setUpdate_log(androidApplication.getContent());
                    versionUpdate.setNew_version(androidApplication.getVersion());
                    versionUpdate.setTarget_size(androidApplication.getSize().toString());
                    versionUpdate.setConstraint(APKVersionUtils.getConstraintValue(androidApplication.getForce()));
                }
            } else {
                // 没有查到最新版本，返回不需要更新标识
                versionUpdate.setUpdate("No");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(String.valueOf(e));
        }
        map.put("versionUpdate", versionUpdate);
        return R.ok(map);
    }

}
