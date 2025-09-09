package com.melodiousplayer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.melodiousplayer.entity.R;
import com.melodiousplayer.entity.SysRole;
import com.melodiousplayer.entity.SysUser;
import com.melodiousplayer.entity.SysUserRole;
import com.melodiousplayer.service.SysRoleService;
import com.melodiousplayer.service.SysUserRoleService;
import com.melodiousplayer.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 后台登录页Controller控制器
 *
 * @author Mike
 * @date 2025/08/25
 */
@RestController
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 用户注册
     *
     * @param sysUser 用户信息
     * @return 页面响应entity
     */
    @PostMapping("/register")
    public R save(@RequestBody SysUser sysUser) {
        if (sysUserService.getByUsername(sysUser.getUsername()) == null) {
            sysUser.setCreateTime(new Date());
            sysUser.setPassword(bCryptPasswordEncoder.encode(sysUser.getPassword()));
            sysUserService.save(sysUser);
            List<SysUserRole> userRoleList = new ArrayList<>();
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(sysUser.getId());
            sysUserRole.setRoleId(2L);
            userRoleList.add(sysUserRole);
            sysUserRoleService.saveBatch(userRoleList);
            return R.ok();
        } else {
            return R.error(501, "用户名已存在");
        }
    }

    @GetMapping("/userInfo")
    public R userInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();
        SysUser currentUser = sysUserService.getByUsername(username);
        // 根据用户id获取所有的角色信息
        List<SysRole> roleList = sysRoleService.list(
                new QueryWrapper<SysRole>().inSql("id",
                        "SELECT role_id FROM sys_user_role WHERE user_id = " + currentUser.getId()));
        // 返回以逗号隔开的字符串
        currentUser.setRoles(roleList.stream().map(SysRole::getName).collect(Collectors.joining(",")));
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("currentUser", currentUser);
        return R.ok(resultMap);
    }

}
