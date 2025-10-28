package com.melodiousplayer.common.security;

import com.melodiousplayer.common.exception.UserCountLockException;
import com.melodiousplayer.entity.SysUser;
import com.melodiousplayer.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 自定义UserDetails
 *
 * @author Mike
 * @date 2025/06/21
 */
@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名或者密码错误！");
        } else if ("1".equals(sysUser.getStatus())) {
            throw new UserCountLockException("该用户账号被封禁，具体请联系管理员！");
        }
        return new User(sysUser.getUsername(), sysUser.getPassword(), getUserAuthority(sysUser.getId()));
    }

    public List<GrantedAuthority> getUserAuthority(Long userId) {
        /**
         * 格式：
         * ROLE_admin,ROLE_common,system:user:resetPwd,system:role:delete,system:user:list,
         * system:menu:query,system:menu:list,system:menu:add,system:user:delete,system:role:list,
         * system:role:menu,system:user:edit,system:user:query,system:role:edit,system:user:add,
         * system:user:role,system:menu:delete,system:role:add,system:role:query,system:menu:edit,
         * system:notice:list,system:notice:query,system:notice:add,system:notice:edit,system:notice:delete,
         * system:android:list,system:android:query,system:android:add,system:android:edit,system:android:delete,
         * system:feedback:list,system:feedback:query,system:feedback:add,system:feedback:edit,system:feedback:delete,
         * data:music:list,data:music:query,data:music:add,data:music:edit,data:music:delete,
         * data:mv:list,data:mv:query,data:mv:add,data:mv:edit,data:mv:delete,
         * data:list:list,data:list:query,data:list:add,data:list:edit,data:list:delete
         **/
        String authority = sysUserService.getUserAuthorityInfo(userId);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
    }

}
