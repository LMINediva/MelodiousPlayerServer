package com.melodiousplayer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.melodiousplayer.entity.Feedback;
import com.melodiousplayer.entity.PageBean;
import com.melodiousplayer.entity.R;
import com.melodiousplayer.service.FeedbackService;
import com.melodiousplayer.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                        .like(StringUtil.isNotEmpty(query), "content", query));
        List<Feedback> feedbackList = pageResult.getRecords();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("feedbackList", feedbackList);
        resultMap.put("total", pageResult.getTotal());
        return R.ok(resultMap);
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
            if (feedbackImageFile.exists()) {
                boolean deleted = feedbackImageFile.delete();
                if (!deleted) {
                    return R.error("用户反馈图片删除失败");
                }
            } else {
                return R.error("用户反馈图片不存在：" + feedback.getPicture());
            }
        }
        feedbackService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

}
