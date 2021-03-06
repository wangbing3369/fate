package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysUserMenu;

import java.util.List;

/**
 * 用户菜单Service接口
 *
 * @author ruoyi
 * @date 2020-11-05
 */
public interface ISysUserMenuService {
    /**
     * 查询用户菜单
     *
     * @param userId 用户菜单ID
     * @return 用户菜单
     */
    SysUserMenu selectSysUserMenuById(Long userId);

    /**
     * 查询用户菜单列表
     *
     * @param sysUserMenu 用户菜单
     * @return 用户菜单集合
     */
    List<SysUserMenu> selectSysUserMenuList(SysUserMenu sysUserMenu);

    /**
     * 新增用户菜单
     *
     * @param sysUserMenu 用户菜单
     * @return 结果
     */
    int insertSysUserMenu(SysUserMenu sysUserMenu);

    /**
     * 修改用户菜单
     *
     * @param sysUserMenu 用户菜单
     * @return 结果
     */
    int updateSysUserMenu(SysUserMenu sysUserMenu);

    /**
     * 批量删除用户菜单
     *
     * @param userIds 需要删除的用户菜单ID
     * @return 结果
     */
    int deleteSysUserMenuByIds(Long[] userIds);

    /**
     * 删除用户菜单信息
     *
     * @param userId 用户菜单ID
     * @return 结果
     */
    int deleteSysUserMenuById(Long userId);
}
