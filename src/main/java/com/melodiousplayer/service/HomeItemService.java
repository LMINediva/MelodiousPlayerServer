package com.melodiousplayer.service;

import com.melodiousplayer.entity.HomeItem;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 24240
 * @description 针对表【home_item(首页在线音乐数据项)】的数据库操作Service
 * @createDate 2025-07-22 13:27:54
 */
public interface HomeItemService extends IService<HomeItem> {

    HomeItem getByTitle(String title);

}
