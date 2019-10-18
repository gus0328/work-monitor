package com.iccm.system.model;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iccm.common.annotation.Excel;
import com.iccm.system.model.BaseEntity;

/**
 * 作业气体监测对象 site_gas
 * 
 * @author gxj
 * @date 2019-09-23
 */
@Data
public class SiteGas extends BaseEntity
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

    /** 工单编号 */
    @Excel(name = "工单编号")
    private String workId;

    /** 气体监测设备id */
    @Excel(name = "气体监测设备id")
    private Long gasDeviceId;

    /** 排序编号 */
    @Excel(name = "排序编号")
    private Long orderNum;

    /**
     * 区域
     */
    private String purpose;

    /**
     * 设备类型
     */
    private String spareWord1;

    public SiteGas(String workId) {
        this.workId = workId;
    }

    public SiteGas() {
    }
}
