package com.melodiousplayer.controller;

import com.melodiousplayer.model.HomeItem;
import com.melodiousplayer.service.HomeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Mike
 * @date 2025/03/18
 */
@RestController
public class HomeItemController {

    @Autowired
    private HomeItemService homeItemService;

    @RequestMapping("/front_page.json")
    public List<HomeItem> selectAll() {
        return homeItemService.selectAll();
    }

}