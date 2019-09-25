package com.iccm.system.model;

import com.iccm.common.annotation.Excel;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2019/9/24.
 */
@Data
public class SiteWorkerVo {

    /** 作业人员 */
    @Excel(name = "作业人员")
    private String personName;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String mobileNum;

    /** 作业工单编号 */
    @Excel(name = "作业工单编号")
    private String workId;

    /**
     * 设备
     */
    List<WearDevice> devices;

    @Data
    public static class WearDevice{

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
    }
}
