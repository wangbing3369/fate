package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.SysUserMenu;
import com.ruoyi.system.mapper.SysUserMenuMapper;
import com.ruoyi.system.service.ISysUserMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户菜单Service业务层处理
 *
 * @author ruoyi
 * @date 2020-11-05
 */
@Service
public class SysUserMenuServiceImpl implements ISysUserMenuService {
    @Autowired
    private SysUserMenuMapper sysUserMenuMapper;

    /**
     * 查询用户菜单
     *
     * @param userId 用户菜单ID
     * @return 用户菜单
     */
    @Override
    public SysUserMenu selectSysUserMenuById(Long userId) {
        return sysUserMenuMapper.selectSysUserMenuById(userId);
    }

    /**
     * 查询用户菜单列表
     *
     * @param sysUserMenu 用户菜单
     * @return 用户菜单
     */
    @Override
    public List<SysUserMenu> selectSysUserMenuList(SysUserMenu sysUserMenu) {
        return sysUserMenuMapper.selectSysUserMenuList(sysUserMenu);
    }

    /**
     * 新增用户菜单
     *
     * @param sysUserMenu 用户菜单
     * @return 结果
     */
    @Override
    public int insertSysUserMenu(SysUserMenu sysUserMenu) {
        return sysUserMenuMapper.insertSysUserMenu(sysUserMenu);
    }

    /**
     * 修改用户菜单
     *
     * @param sysUserMenu 用户菜单
     * @return 结果
     */
    @Override
    public int updateSysUserMenu(SysUserMenu sysUserMenu) {
        return sysUserMenuMapper.updateSysUserMenu(sysUserMenu);
    }

    /**
     * 批量删除用户菜单
     *
     * @param userIds 需要删除的用户菜单ID
     * @return 结果
     */
    @Override
    public int deleteSysUserMenuByIds(Long[] userIds) {
        return sysUserMenuMapper.deleteSysUserMenuByIds(userIds);
    }

    /**
     * 删除用户菜单信息
     *
     * @param userId 用户菜单ID
     * @return 结果
     */
    @Override
    public int deleteSysUserMenuById(Long userId) {
        return sysUserMenuMapper.deleteSysUserMenuById(userId);
    }
}
