package com.melodiousplayer.controller;

import com.melodiousplayer.entity.R;
import com.melodiousplayer.entity.SysUser;
import com.melodiousplayer.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 用户Controller控制器
 *
 * @author Mike
 * @date 2025/06/29
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 添加或者修改
     *
     * @param sysUser
     * @return 页面响应entity
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('system:user:add')" + "||" + "hasAuthority('system:user:edit')")
    public R save(@RequestBody SysUser sysUser) {
        if (sysUser.getId() == null || sysUser.getId() == -1) {

        } else {
            sysUser.setUpdateTime(new Date());
            sysUserService.updateById(sysUser);
        }
        return R.ok();
    }

    /**
     * 修改密码
     *
     * @param sysUser
     * @return 页面响应entity
     */
    @PostMapping("/updateUserPwd")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public R updateUserPwd(@RequestBody SysUser sysUser) {
        SysUser currentUser = sysUserService.getById(sysUser.getId());
        if (bCryptPasswordEncoder.matches(sysUser.getOldPassword(), currentUser.getPassword())) {
            currentUser.setPassword(bCryptPasswordEncoder.encode(sysUser.getNewPassword()));
            currentUser.setUpdateTime(new Date());
            sysUserService.updateById(currentUser);
            return R.ok();
        } else {
            return R.error("输入旧密码错误！");
        }
    }

}
