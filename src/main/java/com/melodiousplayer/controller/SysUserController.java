package com.melodiousplayer.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.melodiousplayer.common.constant.Constant;
import com.melodiousplayer.entity.*;
import com.melodiousplayer.service.SysRoleService;
import com.melodiousplayer.service.SysUserRoleService;
import com.melodiousplayer.service.SysUserService;
import com.melodiousplayer.util.DateUtils;
import com.melodiousplayer.util.StringUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

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
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${avatarImagesFilePath}")
    private String avatarImagesFilePath;

    /**
     * 添加或者修改
     *
     * @param sysUser 用户信息
     * @return 页面响应entity
     */
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('system:user:add')" + "||" + "hasAuthority('system:user:edit')")
    public R save(@RequestBody SysUser sysUser) {
        if (sysUser.getId() == null || sysUser.getId() == -1) {
            sysUser.setCreateTime(new Date());
            sysUser.setPassword(bCryptPasswordEncoder.encode(sysUser.getPassword()));
            sysUserService.save(sysUser);
        } else {
            SysUser currentSysUser = sysUserService.getById(sysUser.getId());
            if (!currentSysUser.getAvatar().equals(sysUser.getAvatar())) {
                String avatarImagePath = avatarImagesFilePath + sysUser.getAvatar();
                File avatarImageFile = new File(avatarImagePath);
                if (avatarImageFile.exists()) {
                    boolean deleted = avatarImageFile.delete();
                    if (!deleted) {
                        return R.error("用户头像图片删除失败");
                    }
                } else {
                    return R.error("用户头像图片不存在：" + sysUser.getAvatar());
                }
            }
            sysUser.setUpdateTime(new Date());
            sysUserService.updateById(sysUser);
        }
        return R.ok();
    }

    /**
     * 修改密码
     *
     * @param sysUser 用户信息
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
     * @param file 用户头像图片
     * @return 页面响应entity
     * @throws Exception 用户头像图片上传异常
     */
    @RequestMapping("/uploadImage")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public Map<String, Object> uploadImage(MultipartFile file) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        if (!file.isEmpty()) {
            // 获取文件名
            String originalFilename = file.getOriginalFilename();
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = DateUtils.getCurrentDateStr() + suffixName;
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
     * @param sysUser 用户信息
     * @return 页面响应entity
     */
    @RequestMapping("/updateAvatar")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public R updateAvatar(@RequestBody SysUser sysUser) {
        SysUser currentUser = sysUserService.getById(sysUser.getId());
        String defaultAvatar = "default.jpg";
        if (!currentUser.getAvatar().equals(defaultAvatar)) {
            String avatarPath = avatarImagesFilePath + currentUser.getAvatar();
            File avatarFile = new File(avatarPath);
            if (avatarFile.exists()) {
                boolean deleted = avatarFile.delete();
                if (!deleted) {
                    return R.error("用户头像删除失败");
                }
            } else {
                return R.error("用户头像不存在：" + currentUser.getAvatar());
            }
        }
        currentUser.setUpdateTime(new Date());
        currentUser.setAvatar(sysUser.getAvatar());
        sysUserService.updateById(currentUser);
        return R.ok();
    }

    /**
     * 根据条件分页查询用户信息
     *
     * @param pageBean 分页信息
     * @return 页面响应entity
     */
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('system:user:query')")
    public R list(@RequestBody PageBean pageBean) {
        String query = pageBean.getQuery().trim();
        Page<SysUser> pageResult = sysUserService.page(new Page<>(pageBean.getPageNum(), pageBean.getPageSize()),
                new QueryWrapper<SysUser>().like(StringUtils.isNotEmpty(query), "username", query));
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

    /**
     * 根据id查询用户
     *
     * @param id 用户id
     * @return 页面响应entity
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('system:user:query')")
    public R findById(@PathVariable(value = "id") Integer id) {
        SysUser sysUser = sysUserService.getById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("sysUser", sysUser);
        return R.ok(map);
    }

    /**
     * 验证用户名是否存在
     *
     * @param sysUser 用户信息
     * @return 页面响应entity
     */
    @PostMapping("/checkUserName")
    @PreAuthorize("hasAuthority('system:user:query')")
    public R checkUserName(@RequestBody SysUser sysUser) {
        if (sysUserService.getByUsername(sysUser.getUsername()) == null) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    /**
     * 删除用户
     *
     * @param ids 用户id
     * @return 页面响应entity
     */
    @Transactional
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('system:user:delete')")
    public R delete(@RequestBody Long[] ids) {
        sysUserService.removeByIds(Arrays.asList(ids));
        sysUserRoleService.remove(new QueryWrapper<SysUserRole>().in("user_id", Arrays.asList(ids)));
        return R.ok();
    }

    /**
     * 重置用户密码
     *
     * @param id 用户id
     * @return 页面响应entity
     */
    @GetMapping("/resetPassword/{id}")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public R resetPassword(@PathVariable(value = "id") Integer id) {
        SysUser sysUser = sysUserService.getById(id);
        sysUser.setPassword(bCryptPasswordEncoder.encode(Constant.DEFAULT_PASSWORD));
        sysUser.setUpdateTime(new Date());
        sysUserService.updateById(sysUser);
        return R.ok();
    }

    /**
     * 更新用户状态
     *
     * @param id     用户id
     * @param status 用户状态
     * @return 页面响应entity
     */
    @GetMapping("/updateStatus/{id}/status/{status}")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public R updateStatus(@PathVariable(value = "id") Integer id,
                          @PathVariable(value = "status") String status) {
        SysUser sysUser = sysUserService.getById(id);
        sysUser.setStatus(status);
        sysUserService.saveOrUpdate(sysUser);
        return R.ok();
    }

    /**
     * 用户授予角色权限
     *
     * @param userId  用户id
     * @param roleIds 角色权限
     * @return 页面响应entity
     */
    @Transactional
    @PostMapping("/grantRole/{userId}")
    @PreAuthorize("hasAuthority('system:user:role')")
    public R grantRole(@PathVariable("userId") Long userId,
                       @RequestBody Long[] roleIds) {
        List<SysUserRole> userRoleList = new ArrayList<>();
        Arrays.stream(roleIds).forEach(r -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(r);
            sysUserRole.setUserId(userId);
            userRoleList.add(sysUserRole);
        });
        sysUserRoleService.remove(new QueryWrapper<SysUserRole>().eq("user_id", userId));
        sysUserRoleService.saveBatch(userRoleList);
        return R.ok();
    }

    /**
     * 查询用户总数
     *
     * @return 页面响应entity
     */
    @GetMapping("/total")
    @PreAuthorize("hasAuthority('system:user:query')")
    public R total() {
        Long total = sysUserService.count();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", total);
        return R.ok(resultMap);
    }

    /**
     * 查询过去近7年每年的用户注册数量
     *
     * @return 页面响应entity
     */
    @GetMapping("/pastUserQuantity")
    @PreAuthorize("hasAuthority('system:user:query')")
    public R pastData() {
        Date date = new Date();
        int year = Integer.parseInt(DateUtils.formatDate(date, "YYYY"));
        int[] pastYear = new int[7];
        Long[] pastUserQuantity = new Long[7];
        for (int i = 0; i < 7; i++) {
            pastYear[i] = year - 6 + i;
            pastUserQuantity[i] = sysUserService.count(
                    new QueryWrapper<SysUser>().eq("YEAR(create_time)", year - 6 + i));
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("pastYear", pastYear);
        resultMap.put("pastUserQuantity", pastUserQuantity);
        return R.ok(resultMap);
    }

    /**
     * 删除用户上传的未保存的文件缓存
     *
     * @param sysUser 用户信息
     * @return 页面响应entity
     */
    @PostMapping("/deleteUploadFileCache")
    @PreAuthorize("hasAuthority('system:user:edit')")
    public R deleteUploadFileCache(@RequestBody SysUser sysUser) {
        if (StrUtil.isNotBlank(sysUser.getAvatar())) {
            String avatarImagePath = avatarImagesFilePath + sysUser.getAvatar();
            File avatarImageFile = new File(avatarImagePath);
            if (avatarImageFile.exists()) {
                boolean deleted = avatarImageFile.delete();
                if (!deleted) {
                    return R.error("用户头像图片删除失败");
                }
            } else {
                return R.error("用户头像图片不存在：" + sysUser.getAvatar());
            }
        }
        return R.ok();
    }

}
