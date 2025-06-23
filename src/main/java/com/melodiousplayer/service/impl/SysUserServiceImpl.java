package com.melodiousplayer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.melodiousplayer.entity.SysUser;
import com.melodiousplayer.service.SysUserService;
import com.melodiousplayer.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
 * @author 24240
 * @description 针对表【sys_user】的数据库操作Service实现
 * @createDate 2025-06-23 12:57:09
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
        implements SysUserService {

    @Override
    public SysUser getByUsername(String username) {
        return getOne(new QueryWrapper<SysUser>().eq("username", username));
    }

}
