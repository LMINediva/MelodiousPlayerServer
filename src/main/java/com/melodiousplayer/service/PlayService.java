package com.melodiousplayer.service;

import com.melodiousplayer.entity.Play;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 24240
 * @description 针对表【play(音乐清单)】的数据库操作Service
 * @createDate 2025-08-01 10:55:46
 */
public interface PlayService extends IService<Play> {

    Play getByTitle(String title);

}
