package com.melodiousplayer.common.security;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.melodiousplayer.entity.R;
import com.melodiousplayer.entity.SysMenu;
import com.melodiousplayer.entity.SysRole;
import com.melodiousplayer.entity.SysUser;
import com.melodiousplayer.service.SysMenuService;
import com.melodiousplayer.service.SysRoleService;
import com.melodiousplayer.service.SysUserService;
import com.melodiousplayer.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 登录成功处理器
 *
 * @author Mike
 * @date 2025/06/21
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysMenuService sysMenuService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        String username = authentication.getName();
        String token = JwtUtils.genJwtToken(username);
        SysUser currentUser = sysUserService.getByUsername(username);
        // 根据用户id获取所有的角色信息
        List<SysRole> roleList = sysRoleService.list(
                new QueryWrapper<SysRole>().inSql("id",
                        "SELECT role_id FROM sys_user_role WHERE user_id = " + currentUser.getId()));
        // 遍历所有角色，获取所有资源权限，而且不重复
        Set<SysMenu> menuSet = new HashSet<>();
        for (SysRole sysRole : roleList) {
            List<SysMenu> sysMenuList = sysMenuService.list(
                    new QueryWrapper<SysMenu>().inSql("id",
                            "SELECT menu_id FROM sys_role_menu WHERE role_id = " + sysRole.getId()));
            for (SysMenu sysMenu : sysMenuList) {
                menuSet.add(sysMenu);
            }
        }
        List<SysMenu> sysMenuList = new ArrayList<>(menuSet);
        // 排序
        sysMenuList.sort(Comparator.comparing(SysMenu::getOrderNum));
        // 转菜单树
        List<SysMenu> menuList = sysMenuService.buildTreeMenu(sysMenuList);
        outputStream.write(JSONUtil.toJsonStr(R.ok("登录成功").put("authorization", token)
                .put("currentUser", currentUser).put("menuList", menuList)).getBytes());
        outputStream.flush();
        outputStream.close();
    }

}
