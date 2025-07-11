package com.melodiousplayer.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.melodiousplayer.entity.HomeItem;
import com.melodiousplayer.service.HomeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
     * 分页查询首页列表信息
     *
     * @param offset 第几页
     * @param size   每页记录数
     * @return 页面响应entity
     */
    @GetMapping("/front_page")
    public List<HomeItem> selectAll(@RequestParam("offset") Integer offset,
                                    @RequestParam("size") Integer size) {
        Page<HomeItem> pageResult = homeItemService.page(new Page<>(offset, size));
        return pageResult.getRecords();
    }

}