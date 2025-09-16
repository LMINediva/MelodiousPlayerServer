package com.melodiousplayer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.melodiousplayer.entity.*;
import com.melodiousplayer.service.SysNoticeService;
import com.melodiousplayer.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 系统公告Controller控制器
 *
 * @author Mike
 * @date 2025/08/14
 */
@RestController
@RequestMapping("/sys/notice")
public class SysNoticeController {

    @Autowired
    private SysNoticeService sysNoticeService;

    /**
     * 分页查询系统公告信息
     *
     * @param pageBean 分页信息
     * @return 页面响应entity
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('system:notice:query')")
    public R list(@RequestBody PageBean pageBean) {
        String query = pageBean.getQuery().trim();
        Page<SysNotice> pageResult = sysNoticeService.page(new Page<>(pageBean.getPageNum(), pageBean.getPageSize()),
                new QueryWrapper<SysNotice>().like(StringUtil.isNotEmpty(query), "title", query));
        List<SysNotice> noticeList = pageResult.getRecords();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("noticeList", noticeList);
        resultMap.put("total", pageResult.getTotal());
        return R.ok(resultMap);
    }

    /**
     * 删除系统公告
     *
     * @param ids 系统公告的id
     * @return 页面响应entity
     */
    @Transactional
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('system:notice:delete')")
    public R delete(@RequestBody Long[] ids) {
        sysNoticeService.removeByIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 验证系统公告标题是否存在
     *
     * @param sysNotice 系统公告信息
     * @return 页面响应entity
     */
    @PostMapping("/checkTitle")
    @PreAuthorize("hasAuthority('system:notice:query')")
    public R checkTitle(@RequestBody SysNotice sysNotice) {
        if (sysNoticeService.getByTitle(sysNotice.getTitle()) == null) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 根据id查询系统公告
     *
     * @param id 系统公告的id
     * @return 页面响应entity
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('system:notice:query')")
    public R findById(@PathVariable(value = "id") Integer id) {
        SysNotice sysNotice = sysNoticeService.getById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("sysNotice", sysNotice);
        return R.ok(map);
    }

    /**
     * 添加或者修改
     *
     * @param sysNotice 系统公告信息
     * @return 页面响应entity
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('system:notice:add')" + "||" + "hasAuthority('system:notice:edit')")
    public R save(@RequestBody SysNotice sysNotice) {
        if (sysNotice.getId() == null || sysNotice.getId() == -1) {
            sysNoticeService.save(sysNotice);
        } else {
            sysNoticeService.updateById(sysNotice);
        }
        return R.ok();
    }

}
