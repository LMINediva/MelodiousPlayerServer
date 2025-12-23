package com.melodiousplayer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.melodiousplayer.entity.*;
import com.melodiousplayer.service.FeedbackService;
import com.melodiousplayer.service.FeedbackUserService;
import com.melodiousplayer.service.SysUserService;
import com.melodiousplayer.util.DateUtils;
import com.melodiousplayer.util.StringUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mike
 * @date 2025/10/28
 */
@RestController
@RequestMapping("/sys/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private FeedbackUserService feedbackUserService;

    @Value("${feedbackImagesFilePath}")
    private String feedbackImagesFilePath;

    /**
     * 根据条件分页查询用户反馈信息
     *
     * @param pageBean 分页信息
     * @return 页面响应entity
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('system:feedback:query')")
    public R list(@RequestBody PageBean pageBean) {
        String query = pageBean.getQuery().trim();
        Page<Feedback> pageResult =
                feedbackService.page(new Page<>(pageBean.getPageNum(),
                        pageBean.getPageSize()), new QueryWrapper<Feedback>()
                        .like(StringUtils.isNotEmpty(query), "content", query));
        List<Feedback> feedbackList = pageResult.getRecords();
        for (Feedback feedback : feedbackList) {
            SysUser sysUser = sysUserService.getOne(new QueryWrapper<SysUser>().inSql(
                    "id", "select user_id from feedback_user where feedback_id = " + feedback.getId()));
            feedback.setSysUser(sysUser);
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("feedbackList", feedbackList);
        resultMap.put("total", pageResult.getTotal());
        return R.ok(resultMap);
    }

    /**
     * 根据id查询用户反馈信息
     *
     * @param id 用户反馈信息id
     * @return 页面响应entity
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('system:feedback:query')")
    public R findById(@PathVariable(value = "id") Integer id) {
        Feedback feedback = feedbackService.getById(id);
        SysUser sysUser = sysUserService.getOne(new QueryWrapper<SysUser>().inSql(
                "id", "select user_id from feedback_user where feedback_id = " + feedback.getId()));
        feedback.setSysUser(sysUser);
        Map<String, Object> map = new HashMap<>();
        map.put("feedback", feedback);
        return R.ok(map);
    }

    /**
     * 删除用户反馈信息
     *
     * @param ids 用户反馈信息id
     * @return 页面响应entity
     */
    @Transactional
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('system:feedback:delete')")
    public R delete(@RequestBody Long[] ids) {
        for (Long id : ids) {
            Feedback feedback = feedbackService.getById(id);
            String feedbackImagePath = feedbackImagesFilePath + feedback.getPicture();
            File feedbackImageFile = new File(feedbackImagePath);
            if (feedback.getPicture() != null) {
                if (!feedback.getPicture().isEmpty()) {
                    if (feedbackImageFile.exists()) {
                        boolean deleted = feedbackImageFile.delete();
                        if (!deleted) {
                            return R.error("用户反馈图片删除失败");
                        }
                    } else {
                        return R.error("用户反馈图片不存在：" + feedback.getPicture());
                    }
                }
            }
        }
        feedbackService.removeByIds(Arrays.asList(ids));
        feedbackUserService.remove(new QueryWrapper<FeedbackUser>().in("feedback_id", Arrays.asList(ids)));
        return R.ok();
    }

    /**
     * 上传用户反馈图片
     *
     * @param file 用户反馈图片
     * @return 页面响应entity
     * @throws Exception 用户反馈图片上传异常
     */
    @RequestMapping("/uploadImage")
    @PreAuthorize("hasAuthority('system:feedback:edit')")
    public Map<String, Object> uploadImage(MultipartFile file) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        if (!file.isEmpty()) {
            // 获取文件名
            String originalFilename = file.getOriginalFilename();
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = DateUtils.getCurrentDateStr() + suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(),
                    new File(feedbackImagesFilePath + newFileName));
            resultMap.put("code", 0);
            resultMap.put("msg", "上传成功");
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("title", newFileName);
            dataMap.put("src", "image/feedbackPicture/" + newFileName);
            resultMap.put("data", dataMap);
        }
        return resultMap;
    }

    /**
     * 修改用户反馈图片
     *
     * @param feedback 用户反馈信息
     * @return 页面响应entity
     */
    @RequestMapping("/updatePicture")
    @PreAuthorize("hasAuthority('system:feedback:edit')")
    public R updateIconPicture(@RequestBody Feedback feedback) {
        Feedback currentFeedback = feedbackService.getById(feedback.getId());
        String imagePath = feedbackImagesFilePath + currentFeedback.getPicture();
        File imageFile = new File(imagePath);
        if (currentFeedback.getPicture() != null && !currentFeedback.getPicture().isEmpty()) {
            if (!currentFeedback.getPicture().equals(feedback.getPicture())) {
                if (imageFile.exists()) {
                    boolean deleted = imageFile.delete();
                    if (!deleted) {
                        return R.error("用户反馈图片删除失败");
                    }
                } else {
                    return R.error("用户反馈图片不存在：" + currentFeedback.getPicture());
                }
            }
        }
        currentFeedback.setPicture(feedback.getPicture());
        feedbackService.updateById(currentFeedback);
        return R.ok();
    }

    /**
     * 添加或者修改用户反馈信息
     *
     * @param feedback 用户反馈信息
     * @return 页面响应entity
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('system:feedback:add')" + "||" + "hasAuthority('system:feedback:edit')")
    public R save(@RequestBody Feedback feedback) {
        if (feedback.getId() == null || feedback.getId() == -1) {
            feedbackService.save(feedback);
            Integer id = feedback.getId();
            FeedbackUser feedbackUser = new FeedbackUser();
            feedbackUser.setFeedbackId(id);
            feedbackUser.setUserId(feedback.getSysUser().getId());
            feedbackUserService.save(feedbackUser);
        } else {
            Feedback currentFeedback = feedbackService.getById(feedback.getId());
            if (currentFeedback.getPicture() != null) {
                if (!currentFeedback.getPicture().isEmpty()) {
                    if (!currentFeedback.getPicture().equals(feedback.getPicture())) {
                        String imagePath = feedbackImagesFilePath + currentFeedback.getPicture();
                        File imageFile = new File(imagePath);
                        if (imageFile.exists()) {
                            boolean deleted = imageFile.delete();
                            if (!deleted) {
                                return R.error("用户反馈图片删除失败");
                            }
                        } else {
                            return R.error("用户反馈图片不存在：" + currentFeedback.getPicture());
                        }
                    }
                }
            }
            feedbackService.updateById(feedback);
        }
        return R.ok();
    }

    /**
     * 删除用户上传的未保存的反馈图片
     *
     * @param feedback 用户反馈信息
     * @return 页面响应entity
     */
    @PostMapping("/deleteUploadFileCache")
    @PreAuthorize("hasAuthority('system:feedback:edit')")
    public R deleteUploadFileCache(@RequestBody Feedback feedback) {
        if (feedback.getId() == null || feedback.getId() == -1) {
            if (feedback.getPicture() != null) {
                if (!feedback.getPicture().isEmpty()) {
                    String imagePath = feedbackImagesFilePath + feedback.getPicture();
                    File imageFile = new File(imagePath);
                    if (imageFile.exists()) {
                        boolean deleted = imageFile.delete();
                        if (!deleted) {
                            return R.error("用户反馈图片删除失败");
                        }
                    } else {
                        return R.error("用户反馈图片不存在：" + feedback.getPicture());
                    }
                }
            }
        } else {
            Feedback currentFeedback = feedbackService.getById(feedback.getId());
            if (currentFeedback.getPicture() != null) {
                if (!currentFeedback.getPicture().isEmpty()) {
                    if (!currentFeedback.getPicture().equals(feedback.getPicture())) {
                        String imagePath = feedbackImagesFilePath + feedback.getPicture();
                        File imageFile = new File(imagePath);
                        if (imageFile.exists()) {
                            boolean deleted = imageFile.delete();
                            if (!deleted) {
                                return R.error("用户反馈图片删除失败");
                            }
                        } else {
                            return R.error("用户反馈图片不存在：" + feedback.getPicture());
                        }
                    }
                }
            }
        }
        return R.ok();
    }

}
