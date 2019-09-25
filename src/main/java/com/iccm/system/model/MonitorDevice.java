package com.iccm.system.model;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iccm.common.annotation.Excel;
import com.iccm.system.model.BaseEntity;

/**
 * 监控设备对象 monitor_device
 * 
 * @author gxj
 * @date 2019-09-23
 */
@Data
public class MonitorDevice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 设备编号 */
    @Excel(name = "设备编号")
    private String itemCode;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String itemName;

    /** ip地址 */
    @Excel(name = "ip地址")
    private String ipAdress;

    /** 备用字段1 */
    @Excel(name = "备用字段1")
    private String spareWord1;

    /** 备用字段2 */
    @Excel(name = "备用字段2")
    private String spareWord2;

    /** 备用字段3 */
    @Excel(name = "备用字段3")
    private String spareWord3;

}
