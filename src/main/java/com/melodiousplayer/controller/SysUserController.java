package com.melodiousplayer.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.melodiousplayer.entity.PageBean;
import com.melodiousplayer.entity.R;
import com.melodiousplayer.entity.SysRole;
import com.melodiousplayer.entity.SysUser;
import com.melodiousplayer.service.SysRoleService;
import com.melodiousplayer.service.SysUserService;
import com.melodiousplayer.util.DateUtil;
import com.melodiousplayer.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private SysRoleService sysRoleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${avatarImagesFilePath}")
    private String avatarImagesFilePath;

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

    /**
     * 上传用户头像图片
     *
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("/uploadImage")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public Map<String, Object> uploadImage(MultipartFile file) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        if (!file.isEmpty()) {
            // 获取文件名
            String originalFilename = file.getOriginalFilename();
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = DateUtil.getCurrentDateStr() + suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(avatarImagesFilePath + newFileName));
            resultMap.put("code", 0);
            resultMap.put("msg", "上传成功");
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("title", newFileName);
            dataMap.put("src", "image/userAvatar/" + newFileName);
            resultMap.put("data", dataMap);
        }
        return resultMap;
    }

    /**
     * 修改用户头像
     *
     * @param sysUser
     * @return
     */
    @RequestMapping("/updateAvatar")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public R updateAvatar(@RequestBody SysUser sysUser) {
        SysUser currentUser = sysUserService.getById(sysUser.getId());
        currentUser.setUpdateTime(new Date());
        currentUser.setAvatar(sysUser.getAvatar());
        sysUserService.updateById(currentUser);
        return R.ok();
    }

    /**
     * 根据条件分页查询用户信息
     *
     * @param pageBean
     * @return
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('system:user:query')")
    public R list(@RequestBody PageBean pageBean) {
        String query = pageBean.getQuery().trim();
        Page<SysUser> pageResult = sysUserService.page(new Page<>(pageBean.getPageNum(), pageBean.getPageSize()),
                new QueryWrapper<SysUser>().like(StringUtil.isNotEmpty(query), "username", query));
        List<SysUser> userList = pageResult.getRecords();
        for (SysUser user : userList) {
            List<SysRole> roleList = sysRoleService.list(new QueryWrapper<SysRole>().inSql(
                    "id", "select role_id from sys_user_role where user_id = " + user.getId()));
            user.setSysRoleList(roleList);
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("userList", userList);
        resultMap.put("total", pageResult.getTotal());
        return R.ok(resultMap);
    }

}
