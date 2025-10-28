package com.melodiousplayer.mapper;

import com.melodiousplayer.entity.Feedback;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 24240
 * @description 针对表【feedback(反馈)】的数据库操作Mapper
 * @createDate 2025-10-28 11:17:52
 * @Entity com.melodiousplayer.entity.Feedback
 */
@Mapper
public interface FeedbackMapper extends BaseMapper<Feedback> {

}




