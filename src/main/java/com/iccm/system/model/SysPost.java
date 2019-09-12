package com.iccm.system.model;

import com.iccm.common.annotation.Excel;
import lombok.Data;

/**
 * 岗位信息对象 sys_post
 * 
 * @author gxj
 * @date 2019-09-11
 */
@Data
public class SysPost extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 岗位ID */
    private Long postId;

    /** 岗位编码 */
    @Excel(name = "岗位编码")
    private String postCode;

    /** 岗位名称 */
    @Excel(name = "岗位名称")
    private String postName;

    /** 显示顺序 */
    @Excel(name = "显示顺序")
    private Integer postSort;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;
}
