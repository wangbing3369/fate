package com.ruoyi.system.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户菜单对象 sys_user_menu
 *
 * @author ruoyi
 * @date 2020-11-05
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SysUserMenu extends BaseEntity {

    /**
     * 用户编码
     */
    @Excel(name = "用户编码")
    private Long userId;

    /**
     * 菜单编码
     */
    @Excel(name = "菜单编码")
    private Long menuId;


}
