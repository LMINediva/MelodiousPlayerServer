package com.melodiousplayer.controller;

import com.melodiousplayer.entity.R;
import com.melodiousplayer.entity.SysUser;
import com.melodiousplayer.service.SysUserService;
import com.melodiousplayer.util.JwtUtils;
import com.melodiousplayer.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试
 *
 * @author Mike
 * @date 2025/06/18
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 测试通过mybatis-plus查询所用用户信息
     *
     * @return 所有用户信息
     */
    @RequestMapping("/user/list")
    @PreAuthorize("hasAuthority('system:user2:list')")
    public R userList(@RequestHeader(required = false) String token) {
        if (StringUtil.isNotEmpty(token)) {
            Map<String, Object> resultMap = new HashMap<>();
            List<SysUser> userList = sysUserService.list();
            resultMap.put("userList", userList);
            return R.ok(resultMap);
        } else {
            return R.error(401, "没有权限访问");
        }
    }

    @RequestMapping("/login")
    public R login() {
        String token = JwtUtils.genJwtToken("java1234");
        return R.ok().put("token", token);
    }

}