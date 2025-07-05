package com.melodiousplayer.controller;

import com.melodiousplayer.entity.R;
import com.melodiousplayer.entity.SysRole;
import com.melodiousplayer.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
