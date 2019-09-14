package com.iccm.system.model;

import com.iccm.common.annotation.Excel;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 菜单对象 menu
 *
 * @author gxj
 * @date 2019-09-07
 */
@Data
public class Menu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long menuId;

    /** 菜单名称 */
    @Excel(name = "菜单名称")
    private String menuTitle;

    /** 菜单name */
    @Excel(name = "菜单name")
    private String menuName;

    /** 菜单路径 */
    @Excel(name = "菜单路径")
    private String menuUrl;

    /** 类型 */
    @Excel(name = "类型")
    private String menuType;

    /** 排序编号 */
    @Excel(name = "排序编号")
    private Integer orderNum;

    /** 父菜单id */
    @Excel(name = "父菜单id")
    private Long parentId;
}
