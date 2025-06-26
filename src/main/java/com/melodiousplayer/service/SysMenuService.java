package com.melodiousplayer.service;

import com.melodiousplayer.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author 24240
 * @description 针对表【sys_menu】的数据库操作Service
 * @createDate 2025-06-25 10:31:09
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> buildTreeMenu(List<SysMenu> sysMenuList);

}
