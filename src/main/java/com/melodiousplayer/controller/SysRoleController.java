package com.melodiousplayer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.melodiousplayer.entity.PageBean;
import com.melodiousplayer.entity.R;
import com.melodiousplayer.entity.SysRole;
import com.melodiousplayer.service.SysRoleService;
import com.melodiousplayer.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统角色Controller控制器
 *
 * @author Mike
 * @date 2025/07/05
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @GetMapping("/listAll")
    @PreAuthorize("hasAuthority('system:role:query')")
    public R listAll() {
        Map<String, Object> resultMap = new HashMap<>();
        List<SysRole> roleList = sysRoleService.list();
        resultMap.put("roleList", roleList);
        return R.ok(resultMap);
    }

    /**
     * 根据条件分页查询角色信息
     *
     * @param pageBean 分页信息
     * @return 页面响应entity
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('system:role:query')")
    public R list(@RequestBody PageBean pageBean) {
        String query = pageBean.getQuery().trim();
        Page<SysRole> pageResult = sysRoleService.page(new Page<>(pageBean.getPageNum(), pageBean.getPageSize()),
                new QueryWrapper<SysRole>().like(StringUtil.isNotEmpty(query), "name", query));
        List<SysRole> roleList = pageResult.getRecords();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("roleList", roleList);
        resultMap.put("total", pageResult.getTotal());
        return R.ok(resultMap);
    }

}
