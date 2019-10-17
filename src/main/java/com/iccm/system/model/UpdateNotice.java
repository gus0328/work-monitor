package com.iccm.system.model;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iccm.common.annotation.Excel;
import com.iccm.system.model.BaseEntity;

/**
 * app更新对象 update_notice
 * 
 * @author gxj
 * @date 2019-10-10
 */
@Data
public class UpdateNotice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 版本号 */
    @Excel(name = "版本号")
    private String versionCode;

    /** 下载地址 */
    @Excel(name = "下载地址")
    private String downloadAddr;

    /** 客户端名称（Android,iphone） */
    @Excel(name = "客户端名称", readConverterExp = "A=ndroid,iphone")
    private String clientName;

    /** 排序编号 */
    @Excel(name = "排序编号")
    private Long sortNum;

    /** 版本描述 */
    @Excel(name = "版本描述")
    private String desc;

    /** 强制更新（0否，1是） */
    @Excel(name = "强制更新", readConverterExp = "0=否，1是")
    private Long forceUpdate;

}
