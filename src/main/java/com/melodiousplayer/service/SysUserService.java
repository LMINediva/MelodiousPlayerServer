package com.melodiousplayer.service;

import com.melodiousplayer.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 24240
 * @description 针对表【sys_user】的数据库操作Service
 * @createDate 2025-06-23 12:57:09
 */
public interface SysUserService extends IService<SysUser> {

    SysUser getByUsername(String username);

    String getUserAuthorityInfo(Long userId);

}
