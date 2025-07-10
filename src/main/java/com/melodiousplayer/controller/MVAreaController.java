package com.melodiousplayer.controller;

import com.melodiousplayer.entity.MvArea;
import com.melodiousplayer.service.MvAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * MV区域页面Controller控制器
 *
 * @author Mike
 * @date 2025/07/09
 */
@RestController
public class MVAreaController {

    @Autowired
    private MvAreaService mvAreaService;

    @GetMapping("/get_mv_areas")
    public List<MvArea> selectAll() {
        List<MvArea> mvAreaList = mvAreaService.list();
        return mvAreaList;
    }

}
