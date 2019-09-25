package com.iccm.system.model;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.iccm.common.annotation.Excel;
import com.iccm.system.model.BaseEntity;

/**
 * 作业人员对象 site_worker
 * 
 * @author gxj
 * @date 2019-09-23
 */
@Data
public class SiteWorker extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 作业人员 */
    @Excel(name = "作业人员")
    private String personName;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String mobileNum;

    /** 作业工单编号 */
    @Excel(name = "作业工单编号")
    private String workId;

    /** 穿戴设备id */
    @Excel(name = "穿戴设备id")
    private Long wearDeviceId;

    /** 设备编号 */
    @Excel(name = "设备编号")
    private String itemCode;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String itemName;

    /** 排序编号 */
    @Excel(name = "排序编号")
    private Long orderNum;

    public SiteWorker(String workId) {
        this.workId = workId;
    }

    public SiteWorker() {
    }

    public SiteWorker(String personName, String mobileNum, String workId, Long wearDeviceId, String itemCode, String itemName, Long orderNum) {
        this.personName = personName;
        this.mobileNum = mobileNum;
        this.workId = workId;
        this.wearDeviceId = wearDeviceId;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.orderNum = orderNum;
    }
}
