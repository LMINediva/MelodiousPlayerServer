package com.melodiousplayer.controller;

import com.melodiousplayer.entity.R;
import com.melodiousplayer.entity.SysUser;
import com.melodiousplayer.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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

    /**
     * 添加用户
     *
     * @param sysUser 用户信息
     * @return 页面响应entity
     */
    @PostMapping("/addUser")
    public R save(@RequestBody SysUser sysUser) {
        if (sysUserService.getByUsername(sysUser.getUsername()) == null) {
            sysUser.setCreateTime(new Date());
            sysUser.setPassword(bCryptPasswordEncoder.encode(sysUser.getPassword()));
            sysUserService.save(sysUser);
            return R.ok();
        } else {
            return R.error(501, "用户名已存在");
        }
    }

}
