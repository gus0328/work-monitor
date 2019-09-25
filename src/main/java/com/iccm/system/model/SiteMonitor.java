package com.iccm.system.model;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iccm.common.annotation.Excel;
import com.iccm.system.model.BaseEntity;

/**
 * 作业监控设备对象 site_monitor
 * 
 * @author gxj
 * @date 2019-09-23
 */
@Data
public class SiteMonitor extends BaseEntity
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

    /** 工单编号 */
    @Excel(name = "工单编号")
    private String workId;

    /** 气体监测设备id */
    @Excel(name = "气体监测设备id")
    private Long monitorDeviceId;

    /** 排序编号 */
    @Excel(name = "排序编号")
    private Long orderNum;

    public SiteMonitor(String workId) {
        this.workId = workId;
    }

    public SiteMonitor() {
    }
}
