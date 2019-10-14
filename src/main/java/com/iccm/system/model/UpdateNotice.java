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

    /** 内容url */
    @Excel(name = "内容url")
    private String contentUrl;

}
