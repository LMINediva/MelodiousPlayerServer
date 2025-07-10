package com.melodiousplayer.controller;

import com.melodiousplayer.entity.HomeItem;
import com.melodiousplayer.service.HomeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 首页Controller控制器
 *
 * @author Mike
 * @date 2025/03/18
 */
@RestController
public class HomeItemController {

    @Autowired
    private HomeItemService homeItemService;

    /**
     * 查询所有首页列表信息
     *
     * @return 页面响应entity
     */
    @GetMapping("/front_page")
    public List<HomeItem> selectAll() {
        return homeItemService.list();
    }

}