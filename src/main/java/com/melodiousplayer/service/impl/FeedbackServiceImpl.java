package com.melodiousplayer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.melodiousplayer.entity.Feedback;
import com.melodiousplayer.service.FeedbackService;
import com.melodiousplayer.mapper.FeedbackMapper;
import org.springframework.stereotype.Service;

/**
* @author 24240
* @description 针对表【feedback(反馈)】的数据库操作Service实现
* @createDate 2025-10-28 11:17:52
*/
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback>
    implements FeedbackService{

}




